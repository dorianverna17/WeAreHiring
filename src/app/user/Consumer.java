package app.user;

import app.company.Company;
import app.info.Date;
import app.info.Education;
import app.info.Experience;
import app.info.Information;

import java.util.*;

// clasa consumer - contine un obiect de tip
// resume si o lista de contacte
public abstract class Consumer {
    private Resume resume;
    private ArrayList<Consumer> contacts;

    public Consumer() {
        contacts = new ArrayList<>();
    }
    // getter si setter pentru obiectele pe
    // care le contine clasa
    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ArrayList<Consumer> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Consumer> contacts) {
        this.contacts = contacts;
    }

    // adaugarea unor studii
    public void add(Education education) {
        resume.addEducation(education);
    }

    // adaugarea unei experiente profesionale
    public void add(Experience experience) {
        resume.addExperience(experience);
    }

    // adaugarea unui nou cunoscut
    public void add(Consumer consumer) {
        if (!contacts.contains(consumer)) {
            contacts.add(consumer);
        }
    }

    // determinarea gradului de prietenie cu un alt utilizator
    public int getDegreeFriendship(Consumer consumer) {
        int degree = Integer.MAX_VALUE;
        List<Consumer> visited = new LinkedList<>();
        List<Pair<Consumer, Integer>> list = new LinkedList<>();
        visited.add(this);
        if (this == consumer)
            return 0;
        else {
            for (int i = 0; i < contacts.size(); i++)
                if (!visited.contains(contacts.get(i)))
                    list.add(0, new Pair(contacts.get(i),  1));
            while (!list.isEmpty()) {
                Pair<Consumer, Integer> pair = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                if (visited.contains(pair.getValue1()))
                    continue;
                visited.add(pair.getValue1());
                if (pair.getValue1() == consumer) {
                    if (pair.getValue2() < degree)
                        degree = pair.getValue2();
                } else {
                    for (int i = 0; i < pair.getValue1().contacts.size(); i++)
                        list.add(0, new Pair(pair.getValue1().contacts.get(i), pair.getValue2() + 1));
                }
            }
        }
        return degree;
    }

    // Eliminarea unei persoane din reteaua sociala
    public void remove(Consumer consumer) {
        if (contacts.contains(consumer)) {
            contacts.remove(consumer);
        }
    }

    // Determinarea anului absolvirii
    public Integer getGraduationYear() {
        for (int i = 0; i < resume.education.size(); i++) {
            if (resume.education.get(i).getEducation_level().equals("college")) {
                if (resume.education.get(i).getEnd_date() == null)
                    return null;
                return resume.education.get(i).getEnd_date().getYear();
            }
        }
        return null;
    }

    // Determinarea mediei studiilor absolvite
    public Double meanGPA() {
        double academic_mean = 0;
        int count = 0;
        for (int i = 0; i < this.getResume().getEducation().size(); i++) {
            academic_mean += this.getResume().getEducation().get(i).getGrade();
            count++;
        }
        if (count != 0)
            academic_mean /= count;
        return academic_mean;
    }

    // metoda pentru obtinerea anilor de experienta
    public int getExperienceYears() {
        int total_years = 0, aux;
        int diff_years, diff_months;
        Experience exp;
        for (int i = 0; i < getResume().getExperience().size(); i++) {
            exp = getResume().getExperience().get(i);
            if (exp.getEnd_date() != null) {
                diff_years = exp.getEnd_date().getYear() - exp.getBegin_date().getYear();
                diff_months = exp.getEnd_date().getMonth() - exp.getBegin_date().getMonth();
                total_years += diff_years;
                if (diff_months >= 3)
                    total_years += 1;
            } else {
                aux = Calendar.getInstance().get(Calendar.YEAR);
                diff_years = aux - exp.getBegin_date().getYear();
                aux = Calendar.getInstance().get(Calendar.MONTH);
                diff_months = aux - exp.getBegin_date().getMonth();
                total_years += diff_years;
                if (diff_months >= 3)
                    total_years += 1;
            }
        }
        return total_years;
    }

    // clasa interna resume care pune la dispozitie metodele dorite
    public static class Resume {
        private Information information;
        private ArrayList<Education> education;
        private ArrayList<Experience> experience;

        public Information getInformation() {
            return information;
        }

        public void setInformation(Information information) {
            this.information = information;
        }

        public ArrayList<Education> getEducation() {
            return education;
        }

        public void setEducation(ArrayList<Education> education) {
            this.education = education;
        }

        public ArrayList<Experience> getExperience() {
            return experience;
        }

        public void setExperience(ArrayList<Experience> experience) {
            this.experience = experience;
        }

        public Resume(ResumeBuilder resume) {
            this.information = resume.information;
            this.education = resume.education;
            this.experience = resume.experience;
        }

        public void addEducation(Education obj) {
            education.add(obj);
            Collections.sort(education);
        }

        public void addExperience(Experience obj) {
            experience.add(obj);
            Collections.sort(experience);
        }

        public String toString() {
            String aux;
            aux = information.toString() + education.toString() + experience.toString();
            return aux;
        }

        public static class ResumeBuilder {
            private Information information;
            private ArrayList<Education> education;
            private ArrayList<Experience> experience;

            public ResumeBuilder() {
                education = new ArrayList<>();
                experience = new ArrayList<>();
                information = new Information();
                information.setFirstname(null);
                information.setLastname(null);
                information.setBirth_date(null);
            }

            public ResumeBuilder lastname(String lastname) {
                this.information.setLastname(lastname);
                return this;
            }

            public ResumeBuilder firstname(String firstname) {
                this.information.setFirstname(firstname);
                return this;
            }

            public ResumeBuilder email(String email) {
                this.information.setEmail(email);
                return this;
            }

            public ResumeBuilder birthdate(Date birthdate) {
                this.information.setBirth_date(birthdate);
                return this;
            }

            public ResumeBuilder gender(String gender) {
                this.information.setSex(gender);
                return this;
            }

            public ResumeBuilder phonenumber(String phonenumber) {
                this.information.setPhone_number(phonenumber);
                return this;
            }

            public ResumeBuilder education(ArrayList<Education> education) {
                this.education = education;
                return this;
            }

            public ResumeBuilder experience(ArrayList<Experience> experience) {
                this.experience = experience;
                return this;
            }

            public ResumeBuilder language(ArrayList<Information.Language> language) {
                this.information.setLanguages(language);
                return this;
            }

            public Resume build() {
                try {
                    if (this.education.size() == 0)
                        throw new ResumeIncompleteException("Resume-ul nu este complet");
                    else if (this.information.getLastname() == null)
                        throw new ResumeIncompleteException("Resume-ul nu este complet");
                    else if (this.information.getFirstname() == null)
                        throw new ResumeIncompleteException("Resume-ul nu este complet");
                    else if (this.information.getBirth_date() == null)
                        throw new ResumeIncompleteException("Resume-ul nu este complet");
                    return new Resume(this);
                } catch (ResumeIncompleteException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }

    public String toString() {
        return getResume().getInformation().getLastname() + " " +
                getResume().getInformation().getFirstname();
    }
}

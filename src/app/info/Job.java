package app.info;

import app.architecture.Application;
import app.company.*;
import app.user.Consumer;
import app.user.Pair;
import app.user.Recruiter;
import app.user.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Job {
    private String name;
    private String company;
    private boolean open_job;
    private ArrayList<User> candidates;
    private int nr_candidates;
    private double salary;
    private Constraint<Integer> graduation;
    private Constraint<Integer> experience;
    private Constraint<Double> grade;

    // constructor pentru clasa Job
    public Job(String name, String company, boolean open_job,
               ArrayList<User> candidates, int nr_candidates, double salary,
               Constraint<Integer> graduation, Constraint<Integer> experience,
               Constraint<Double> grade) {
        this.name = name;
        this.company = company;
        this.open_job = open_job;
        this.candidates = candidates;
        this.nr_candidates = nr_candidates;
        this.salary = salary;
        this.graduation = graduation;
        this.experience = experience;
        this.grade = grade;
    }

    public Job() {
        candidates = new ArrayList<>();
        nr_candidates = 0;
    }

    // gettere si settere
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isOpen_job() {
        return open_job;
    }

    public void setOpen_job(boolean open_job) {
        this.open_job = open_job;
    }

    public List<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<User> candidates) {
        this.candidates = candidates;
    }

    public int getNr_candidates() {
        return nr_candidates;
    }

    public void setNr_candidates(int nr_candidates) {
        this.nr_candidates = nr_candidates;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Constraint<Integer> getGraduation() {
        return graduation;
    }

    public void setGraduation(Constraint<Integer> graduation) {
        this.graduation = graduation;
    }

    public Constraint<Integer> getExperience() {
        return experience;
    }

    public void setExperience(Constraint<Integer> experience) {
        this.experience = experience;
    }

    public Constraint<Double> getGrade() {
        return grade;
    }

    public void setGrade(Constraint<Double> grade) {
        this.grade = grade;
    }

    // metoda apply
    public void apply(User user) {
        Company company = Application.getInstance().getCompany(this.getCompany());
        Recruiter recruiter = company.findRecruiter(user);
//        System.out.println("Recruiter-ul gasit este " + recruiter.getResume().getInformation().getLastname() + " " +
//                recruiter.getResume().getInformation().getFirstname());
        candidates.add(user);
        recruiter.evaluate(this, user);
    }

    // returneaza departamentul job-ului nostru
    public String getDepartment() {
        Company company = Application.getInstance().getCompany(getCompany());
        Department dep;
        String aux = "";
        for (int i = 0; i < company.getDepartments().size(); i++) {
            dep = company.getDepartments().get(i);
            for (int j = 0; j < dep.getJobs().size(); j++) {
                if (dep.getJobs().get(i) == this) {
                    if (dep instanceof IT)
                        return "IT";
                    if (dep instanceof Finance)
                        return "Finance";
                    if (dep instanceof Marketing)
                        return "Marketing";
                    if (dep instanceof Management)
                        return "Management";
                }
            }
        }
        return aux;
    }

    // metoda care verifica daca un user indeplineste conditiile pentru job
    public boolean meetsRequirments(User user) {
        int aux1, aux2, aux3;
//        System.out.println(user.meanGPA() + " " + user.getExperienceYears() + " " +
//                user.getGraduationYear());
        if (this.isOpen_job()) {
            if (getGrade().getInf() <= user.meanGPA()) {
                if (getExperience().getInf() == null)
                    aux1 = 0;
                else
                    aux1 = getExperience().getInf();
                if (getExperience().getSup() == null)
                    aux2 = Integer.MAX_VALUE;
                else
                    aux2 = getExperience().getSup();
                if (aux1 <= user.getExperienceYears() &&
                        aux2 >= user.getExperienceYears()) {
                    if (getGraduation().getInf() == null)
                        aux1 = 0;
                    else
                        aux1 = getGraduation().getInf();
                    if (getGraduation().getSup() == null)
                        aux2 = Integer.MAX_VALUE;
                    else
                        aux2 = getGraduation().getSup();
                    if (user.getGraduationYear() == null)
                        aux3 = Integer.MAX_VALUE - 1;
                    else
                        aux3 = user.getGraduationYear();
                        if (aux1 <= aux3 && aux2 >= aux3) {
                            return true;
                        }
                }
            }
        }
        return false;
    }
}

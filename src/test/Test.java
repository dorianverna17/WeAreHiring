package test;

import app.architecture.Application;
import app.company.*;
import app.gui.*;
import app.info.*;
import app.info.Date;
import app.user.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {
    ArrayList<Employee> list_employees;
    ArrayList<Recruiter> list_recruiters;

    public Test() {
        list_employees = new ArrayList<>();
        list_recruiters = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        JSONObject json = (JSONObject) new JSONParser().parse(new FileReader("consumers.json"));
        Test test = new Test();
        // Prima oara ma ocup de citirea userilor
        ArrayList<Map> users = (ArrayList<Map>) json.get("users");
        User user;
        for (int i = 0; i < users.size(); i++) {
            user = new User();
            user.setResume(test.createConsumerResume(users.get(i)));
            Application.getInstance().add(user);
            user.setInterests((ArrayList<String>) users.get(i).get("interested_companies"));
        }
        // acum ma voi ocupa de manageri
        ArrayList<Map> managers = (ArrayList<Map>) json.get("managers");
        String company_name;
        Manager manager;
        for (int i = 0; i < managers.size(); i++) {
            company_name = test.searchCompanyName(managers.get(i), "CEO");
            Company company = new Company();
            company.setName(company_name);
            manager = new Manager();
            company.setManager(manager);
            company.setRecruiters(new ArrayList<>());
            manager.setResume(test.createConsumerResume(managers.get(i)));
            company.setDepartments(new ArrayList<>());
            company.add(DepartmentFactory.factory("IT"));
            company.add(DepartmentFactory.factory("Marketing"));
            company.add(DepartmentFactory.factory("Finance"));
            company.add(DepartmentFactory.factory("Management"));
            Application.getInstance().add(company);
            manager.setCompany(company_name);
            manager.setSalary(Double.parseDouble(managers.get(i).get("salary").toString()));
        }
        // acum pun observerii in lista companiilor
        User aux;
        for (int i = 0; i < Application.getInstance().getUsers().size(); i++) {
            aux = Application.getInstance().getUsers().get(i);
            for (int j = 0; j < aux.getInterests().size(); j++) {
                Application.getInstance().getCompany(aux.getInterests().get(j)).addObject(aux);
            }
        }
        // acum ma voi ocupa de recruiteri
        ArrayList<Map> recruiters = (ArrayList<Map>) json.get("recruiters");
        Recruiter recruiter;
        for (int i = 0; i < recruiters.size(); i++) {
            recruiter = new Recruiter();
            recruiter.setResume(test.createConsumerResume(recruiters.get(i)));
            company_name = test.searchCompanyName(recruiters.get(i), "Recruiter");
            Company company = Application.getInstance().getCompany(company_name);
            company.add(recruiter);
            recruiter.setSalary(Double.parseDouble(recruiters.get(i).get("salary").toString()));
            company.getDepartment("IT").add(recruiter);
            test.list_recruiters.add(recruiter);
        }
        // acum ma voi ocupa de employees
        ArrayList<Map> employees = (ArrayList<Map>) json.get("employees");
        Employee employee;
        for (int i = 0; i < employees.size(); i++) {
            employee = new Employee();
            employee.setSalary(Double.parseDouble(employees.get(i).get("salary").toString()));
            employee.setResume(test.createConsumerResume(employees.get(i)));
            company_name = test.searchCompanyName(employees.get(i));
            Company company = Application.getInstance().getCompany(company_name);
            test.searchDepartment(employees.get(i), company).add(employee);
            employee.setCompany(company_name);
            test.list_employees.add(employee);
        }
        test.makeSocialNetwork();
        test.addJobs();
        // verificam daca aplicam unde trebuie
        test.applyJobs();
        Main main = new Main();
    }

    public String searchCompanyName(Map consumer) {
        ArrayList<Map> companies = (ArrayList<Map>) consumer.get("experience");
        String final_date;
        for (int i = 0; i < companies.size(); i++) {
            final_date = (String) companies.get(i).get("end_date");
            if (final_date == null) {
                return (String) companies.get(i).get("company");
            }
        }
        return null;
    }

    public Department searchDepartment(Map consumer, Company comp) throws ClassNotFoundException {
        ArrayList<Map> companies = (ArrayList<Map>) consumer.get("experience");
        String dep;
        String final_date;
        for (int i = 0; i < companies.size(); i++) {
            final_date = (String) companies.get(i).get("end_date");
            dep = (String) companies.get(i).get("department");
            if (final_date == null && comp.getName().equals((String) companies.get(i).get("company"))) {
                return comp.getDepartment(dep);
            }
        }
        return null;
    }

    public String searchCompanyName(Map manager, String position_name) {
        ArrayList<Map> companies = (ArrayList<Map>) manager.get("experience");
        String final_date, position;
        for (int i = 0; i < companies.size(); i++) {
            final_date = (String) companies.get(i).get("end_date");
            position = (String) companies.get(i).get("position");
            if (final_date == null && position.equals(position_name)) {
                return (String) companies.get(i).get("company");
            }
        }
        return null;
    }

    public Date parseDate(String date) {
        if (date == null)
            return null;
        int index1;
        int year, month, day;
        index1 = date.indexOf('.');
        day = Integer.parseInt(date.substring(0, index1));
        date = date.substring(index1 + 1);
        index1 = date.indexOf('.');
        month = Integer.parseInt(date.substring(0, index1));
        date = date.substring(index1 + 1);
        year = Integer.parseInt(date);
        Date date_obj = new Date(year, month, day);
        return date_obj;
    }

    public ArrayList<Education> parseEducation(ArrayList<Map> education) {
        ArrayList<Education> list = new ArrayList<>();
        Education new_education;
        Date begin_date, end_date;
        String level, institution;
        double grade;
        for (int i = 0; i < education.size(); i++) {
            new_education = new Education();
            begin_date = parseDate((String) education.get(i).get("start_date"));
            end_date = parseDate((String) education.get(i).get("end_date"));
            level = (String) education.get(i).get("level");
            institution = (String) education.get(i).get("name");
            grade = Double.parseDouble(education.get(i).get("grade").toString());
            new_education.setBegin_date(begin_date);
            new_education.setEnd_date(end_date);
            new_education.setEducation_level(level);
            new_education.setGrade(grade);
            new_education.setInstitution(institution);
            list.add(new_education);
        }
        return list;
    }

    public ArrayList<Experience> parseExperience(ArrayList<Map> experience) {
        ArrayList<Experience> list = new ArrayList<>();
        Experience new_experience;
        Date begin_date, end_date;
        String position, company;
        double grade;
        for (int i = 0; i < experience.size(); i++) {
            new_experience = new Experience();
            begin_date = parseDate((String) experience.get(i).get("start_date"));
            end_date = parseDate((String) experience.get(i).get("end_date"));
            position = (String) experience.get(i).get("position");
            company = (String) experience.get(i).get("company");
            new_experience.setBegin_date(begin_date);
            new_experience.setEnd_date(end_date);
            new_experience.setPosition(position);
            new_experience.setCompany(company);
            list.add(new_experience);
        }
        return list;
    }

    public Consumer.Resume createConsumerResume(Map entry) {
        String name = (String) entry.get("name");
        int index = name.indexOf(' ');
        String firstname = name.substring(0, index);
        String lastname = name.substring(index + 1);
        String phone = (String) entry.get("phone");
        String email = (String) entry.get("email");
        String date = (String) entry.get("date_of_birth");
        String genre = (String) entry.get("genre");
        Date date_obj = parseDate(date);
        //preiau limba
        ArrayList<String> languages = (ArrayList<String>) entry.get("languages");
        ArrayList<String> languages_level = (ArrayList<String>) entry.get("languages_level");
        ArrayList<Information.Language> languages_list = new ArrayList<>();
        Information.Language new_language;
        for (int i = 0; i < languages.size(); i++) {
            new_language = new Information.Language(languages.get(i), languages_level.get(i));
            languages_list.add(new_language);
        }
        // acum ma voi ocupa de educatie
        ArrayList<Map> education = (ArrayList<Map>) entry.get("education");
        ArrayList<Education> education_list = parseEducation(education);
        // acum ma voi ocupa de experienta
        ArrayList<Map> experience = (ArrayList<Map>) entry.get("experience");
        ArrayList<Experience> experience_list = parseExperience(experience);
        Consumer.Resume resume = new Consumer.Resume.ResumeBuilder()
                .lastname(lastname)
                .firstname(firstname)
                .birthdate(date_obj)
                .email(email)
                .phonenumber(phone)
                .gender(genre)
                .language(languages_list)
                .education(education_list)
                .experience(experience_list)
                .build();
        return resume;
    }

    // aici fac reteaua sociala
    public void makeSocialNetwork() {
        Application app = Application.getInstance();
        app.getUsers().get(0).add(app.getUsers().get(1));
        app.getUsers().get(0).add(list_employees.get(2));
        app.getUsers().get(1).add(list_recruiters.get(0));
        app.getUsers().get(1).add(list_employees.get(6));
        app.getUsers().get(1).add(app.getUsers().get(0));
        app.getUsers().get(2).add(list_employees.get(2));
        app.getUsers().get(2).add(app.getUsers().get(3));
        app.getUsers().get(3).add(app.getUsers().get(2));
        app.getUsers().get(3).add(list_employees.get(9));
        list_employees.get(1).add(list_employees.get(9));
        list_employees.get(1).add(list_recruiters.get(2));
        list_employees.get(2).add(list_employees.get(5));
        list_employees.get(2).add(list_recruiters.get(1));
        list_employees.get(2).add(app.getUsers().get(0));
        list_employees.get(2).add(app.getUsers().get(2));
        list_employees.get(5).add(list_recruiters.get(3));
        list_employees.get(5).add(list_employees.get(2));
        list_employees.get(9).add(list_employees.get(1));
        list_employees.get(9).add(app.getUsers().get(3));
        list_employees.get(6).add(app.getUsers().get(1));
        list_recruiters.get(0).add(app.getUsers().get(1));
        list_recruiters.get(1).add(list_employees.get(2));
        list_recruiters.get(2).add(list_employees.get(1));
        list_recruiters.get(3).add(list_employees.get(5));
    }

    public void addJobs() throws ClassNotFoundException {
        Job job1, job2, job3, job4;
        // graduation, experience, grade
        Constraint<Integer> constraint1, constraint2;
        Constraint<Double> constraint3;
        // adaugarea primului job
        job1 = new Job();
        job1.setName("Software Developer Engineer");
        job1.setOpen_job(true);
        job1.setCompany("Google");
        job1.setNr_candidates(1);
        job1.setSalary(10000);
        constraint1 = new Constraint<>(2002, 2020);
        constraint2 = new Constraint<>(2, 6);
        constraint3 = new Constraint<Double>(8.0, null);
        job1.setGraduation(constraint1);
        job1.setExperience(constraint2);
        job1.setGrade(constraint3);
        // adaugarea celui de-al doilea job
        job2 = new Job();
        job2.setName("Software Developer Engineer Intern");
        job2.setOpen_job(true);
        job2.setCompany("Google");
        job2.setNr_candidates(1);
        job2.setSalary(5000);
        constraint1 = new Constraint<>(null, null);
        constraint2 = new Constraint<>(0, 2);
        constraint3 = new Constraint<Double>(9.0, null);
        job2.setGraduation(constraint1);
        job2.setExperience(constraint2);
        job2.setGrade(constraint3);
        // adaugarea celui de-al treilea job
        job3 = new Job();
        job3.setName("Software Developer Engineer");
        job3.setOpen_job(true);
        job3.setCompany("Amazon");
        job3.setNr_candidates(1);
        job3.setSalary(12000);
        constraint1 = new Constraint<>(2014, 2020);
        constraint2 = new Constraint<>(1, null);
        constraint3 = new Constraint<Double>(9.0 , null);
        job3.setGraduation(constraint1);
        job3.setExperience(constraint2);
        job3.setGrade(constraint3);
        // adaugarea celui de-al patrulea job
        job4 = new Job();
        job4.setName("Software Developer Engineer Intern");
        job4.setOpen_job(true);
        job4.setCompany("Amazon");
        job4.setNr_candidates(1);
        job4.setSalary(6000);
        constraint1 = new Constraint<>(null, null);
        constraint2 = new Constraint<>(0, 2);
        constraint3 = new Constraint<Double>(9.35, null);
        job4.setGraduation(constraint1);
        job4.setExperience(constraint2);
        job4.setGrade(constraint3);
        Company company = Application.getInstance().getCompany("Google");
        company.getDepartment("IT").add(job1);
        company.getDepartment("IT").add(job2);
        company = Application.getInstance().getCompany("Amazon");
        company.getDepartment("IT").add(job3);
        company.getDepartment("IT").add(job4);
    }

    public void applyJobs() {
        Application app = Application.getInstance();
        Company company;
        // Aici am partea in care userii aplica la joburile lor
        for (int i = 0; i < app.getUsers().size(); i++) {
            for (int j = 0; j < app.getUsers().get(i).getInterests().size(); j++) {
                company = app.getCompany(app.getUsers().get(i).getInterests().get(j));
                for (int k = 0; k < company.getJobs().size(); k++) {
//                    System.out.println("User-ul " + app.getUsers().get(i).getResume().getInformation().getLastname() +
//                            " " + app.getUsers().get(i).getResume().getInformation().getLastname() +
//                            " a aplicat la compania " + company.getName() + " la positia de " +
//                            company.getJobs().get(k).getName());
                    company.getJobs().get(k).apply(app.getUsers().get(i));
                }
            }
        }
//         printez lista de candidati de la fiecare job
//        for (int i = 0; i < app.getCompanies().size(); i++) {
//            for (int j = 0; j < app.getCompanies().get(i).getJobs().size(); j++) {
//                System.out.println(app.getCompanies().get(i).getName() + " " +
//                        app.getCompanies().get(i).getJobs().get(j).getName() + " " +
//                        app.getCompanies().get(i).getJobs().get(j).getCandidates());
//            }
//        }
        Manager manager;
        // acum voi printa lista de requesturi de la fiecare manager
//        for (int i = 0; i < app.getCompanies().size(); i++) {
//            manager = app.getCompanies().get(i).getManager();
//            System.out.println(app.getCompanies().get(i).getName());
//            for (int j = 0; j < manager.getRequests().size(); j++) {
//                System.out.println(((Job)manager.getRequests().get(j).getKey()).getName() + " " +
//                        manager.getRequests().get(j).getValue1() + " " + manager.getRequests().get(j).getScore() + " " +
//                        ((Recruiter)manager.getRequests().get(j).getValue2()).getRating());
//            }
//        }
        // acum voi procesa fiecare request
        for (int i = 0; i < app.getCompanies().size(); i++) {
            manager = app.getCompanies().get(i).getManager();
            ArrayList<Job> list = app.getCompanies().get(i).getJobs();
            for (int j = 0; j < list.size(); j++) {
                manager.process(list.get(j));
            }
        }
        // acum voi printa notificarile pe care le are user-ul Edmund
        System.out.println("Notificarile pentru Edmund");
        app.getUsers().get(0).seeNotifications();
    }
}

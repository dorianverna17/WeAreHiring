package app.company;

import app.architecture.Application;
import app.info.Job;
import app.user.Employee;
import app.user.Notification;

import java.util.ArrayList;

public abstract class Department {
    private ArrayList<Employee> employees;
    private ArrayList<Job> jobs;

    // constructorii pentru departament
    public Department() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public Department(ArrayList employees, ArrayList jobs) {
        this.employees = employees;
        this.jobs = jobs;
    }

    // getteri si setteri
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    // functie care returneaza job-urile open dintr-un departament
    public ArrayList<Job> getJobs() {
        ArrayList open_jobs = new ArrayList();
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).isOpen_job())
                open_jobs.add(jobs.get(i));
        }
        return open_jobs;
    }

    // metoda abstracta ceruta
    public abstract double getTotalSalaryBudget();

    // metoda de adaugare a unui employee
    public void add(Employee employee) {
        if (!employees.contains(employee))
            employees.add(employee);
    }

    // metoda de stergere a unui employee
    public void remove(Employee employee) {
        if (employees.contains(employee))
            employees.remove(employee);
    }

    // metoda de adaugare a unui job
    public void add(Job job) {
        Company company = Application.getInstance().getCompany(job.getCompany());
        if (!jobs.contains(job))
            jobs.add(job);
        Notification notification = new Notification(job.getCompany(),
                "A fost adaugat un job nou: " + job.getName());
        company.notifyAllObserver(notification);
    }

    // metoda care returneaza un job din departament (cautat dupa nume)
    public Job getJob(String name) {
        for (int i = 0; i < getJobs().size(); i++) {
            if (getJobs().get(i).getName().equals(name))
                return getJobs().get(i);
        }
        return null;
    }
}

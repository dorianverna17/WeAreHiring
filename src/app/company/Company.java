package app.company;

import app.architecture.Application;
import app.info.Job;
import app.user.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Company implements Subject {
    private String name;
    private Manager manager;
    private ArrayList<Recruiter> recruiters;
    private ArrayList<Department> departments;
    private ArrayList<User> observers;

    // constructor pentru clasa company
    public Company(String name, Manager manager, ArrayList<Recruiter> recruiters,
                   ArrayList<Department> departments) {
        this.name = name;
        this.manager = manager;
        this.recruiters = recruiters;
        this.departments = departments;
        this.observers = new ArrayList<>();
    }

    // company pentru clasa company (fara parametrii)
    public Company() {
        this.recruiters = new ArrayList<>();
        this.departments = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // getteri si setteri
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ArrayList<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(ArrayList<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    // adaugare departament
    public void add(Department department) {
        if (!departments.contains(department))
            departments.add(department);
    }

    // adaugarea unui recruiter
    public void add(Recruiter recruiter) {
        if (!recruiters.contains(recruiter))
            recruiters.add(recruiter);
        add(recruiter, getDepartment("IT"));
    }

    // returneaza un recruiter dupa nume
    public Recruiter getRecruiter(String name) {
        String aux;
        for (int i = 0; i < getRecruiters().size(); i++) {
            aux = getRecruiters().get(i).getResume().getInformation().getLastname() + " " +
                    getRecruiters().get(i).getResume().getInformation().getFirstname();
            if (aux.equals(name))
                return getRecruiters().get(i);
        }
        return null;
    }

    // returneaza departamentul dupa nume
    public Department getDepartment(String dep) {
        Department result = null;
        String depart;
        for (int i = 0; i < getDepartments().size(); i++) {
            depart = getDepartments().get(i).getClass().toString();
            depart = depart.substring(depart.lastIndexOf('.') + 1);
            if (depart.equals(dep)) {
                result = getDepartments().get(i);
            } else if (depart.equals(dep)) {
                result = getDepartments().get(i);
            } else if (depart.equals(dep)) {
                result = getDepartments().get(i);
            } else if (depart.equals(dep))
                result = getDepartments().get(i);
        }
        return result;
    }

    // adaugarea unui employee intr-un departament
    public void add(Employee employee, Department department) {
        if (!department.getEmployees().contains(employee))
            department.add(employee);
    }

    // stergerea unui employee dintr-un departament
    public void remove(Employee employee) {
        for (int i = 0; i < getDepartments().size(); i++) {
            if (getDepartments().get(i).getEmployees().contains(employee))
                getDepartments().get(i).remove(employee);
        }
    }

    // stergerea unui departament
    public void remove(Department department) {
        if (departments.contains(department))
            departments.remove(department);
    }

    // stergerea unui recruiter
    public void remove(Recruiter recruiter) {
        if (recruiters.contains(recruiter))
            recruiters.remove(recruiter);
    }

    // mutarea angajatilor dintr-un departament in alt departament
    public void move(Department source, Department destination) {
        for (int i = 0; i < source.getEmployees().size(); i++) {
            destination.add(source.getEmployees().get(i));
        }
        for (int i = 0; i < source.getJobs().size(); i++) {
            destination.add(source.getJobs().get(i));
        }
        remove(source);
    }

    // mutarea unui employee intr-un departament
    public void move(Employee employee, Department newDepartment) {
        for (int i = 0; i < getDepartments().size(); i++) {
            if (getDepartments().get(i).getEmployees().contains(employee)) {
                getDepartments().get(i).remove(employee);
                newDepartment.add(employee);
            }
        }
    }

    // verifica daca exista un departament
    public boolean contains(Department department) {
        if (departments.contains(department))
            return true;
        return false;
    }

    // verifica daca exista acel angajat in companie
    public boolean contains(Employee employee) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getEmployees().contains(employee))
                return true;
        }
        return false;
    }

    // verifica daca exista acel angajat in companie
    // verifica dupa nume
    public Employee getEmployee(String name) {
        String aux1, aux2;
        for (int i = 0; i < departments.size(); i++) {
            for (int j = 0; j < departments.get(i).getEmployees().size(); j++) {
                aux1 = departments.get(i).getEmployees().get(j).getResume().getInformation().getLastname();
                aux2 = departments.get(i).getEmployees().get(j).getResume().getInformation().getFirstname();
                System.out.println(aux1 + " " + aux2);
                if ((aux1 + " " + aux2).equals(name))
                    return departments.get(i).getEmployees().get(j);
            }
        }
        return null;
    }

    // verifica daca exista un angajat in companie
    // verifica dupa email
    public Employee getEmployeeByEmail(String email) {
        String aux;
        for (int i = 0; i < departments.size(); i++) {
            for (int j = 0; j < departments.get(i).getEmployees().size(); j++) {
                aux = departments.get(i).getEmployees().get(j).getResume().getInformation().getEmail();
                if ((aux).equals(email))
                    return departments.get(i).getEmployees().get(j);
            }
        }
        return null;
    }

    // verifica daca exista acel recruiter in companie
    public boolean contains(Recruiter recruiter) {
        if (recruiters.contains(recruiter))
            return true;
        return false;
    }

    public int getDegreeRecruiter(Consumer recruiter, Consumer user) {
        int degree = Integer.MIN_VALUE;
        List<Consumer> visited = new LinkedList<>();
        List<Pair<Consumer, Integer>> list = new LinkedList<>();
        visited.add(user);
        if (user == recruiter)
            return 0;
        else {
            for (int i = 0; i < user.getContacts().size(); i++)
                if (!visited.contains(user.getContacts().get(i)))
                    list.add(0, new Pair(user.getContacts().get(i),  1));
            while (!list.isEmpty()) {
                Pair<Consumer, Integer> pair = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                if (visited.contains(pair.getValue1()))
                    continue;
                visited.add(pair.getValue1());
                if (pair.getValue1() == recruiter) {
                    if (pair.getValue2() > degree)
                        degree = pair.getValue2();
                }
                else {
                    for (int i = 0; i < pair.getValue1().getContacts().size(); i++)
                        if (!visited.contains(pair.getValue1().getContacts().get(i)))
                            list.add(0, new Pair(pair.getValue1().getContacts().get(i), pair.getValue2() + 1));
                }
            }
        }
        return degree;
    }

    // functia asta e buna
    public Recruiter findRecruiter(User user) {
        Company company = this;
        ArrayList<Recruiter> list = company.getRecruiters();
        ArrayList<Pair<Recruiter, Integer>> degrees = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            degrees.add(new Pair(list.get(i), user.getDegreeFriendship(list.get(i))));
        }
        int max = Integer.MIN_VALUE;
        Recruiter recruit = null;
        Double aux;
        for (int i = 0; i < degrees.size(); i++) {
            if (degrees.get(i).getValue2() > max) {
                recruit = degrees.get(i).getValue1();
                max = degrees.get(i).getValue2();
            } else {
                if (degrees.get(i).getValue2() == max) {
                    if (recruit == null)
                        aux = 0d;
                    else
                        aux = recruit.getRating();
                    if (degrees.get(i).getValue1().getRating() > aux) {
                        recruit = degrees.get(i).getValue1();
                        max = degrees.get(i).getValue2();
                    }
                }
            }
        }
        return recruit;
    }

    // functie care returneza recruiter-ul pentru un user
    public Recruiter getRecruiter(User user) {
        Recruiter result = findRecruiter(user);
        return result;
    }

    // functie care returneaza lista job-urilor dintr-o companie
    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            for (int j = 0; j < departments.get(i).getJobs().size(); j++) {
                jobs.add(departments.get(i).getJobs().get(j));
            }
        }
        return jobs;
    }

    @Override
    public void addObject(User user) {
        if (!observers.contains(user))
            observers.add(user);
    }

    @Override
    public void removeObserver(User user) {
        if (observers.contains(user))
            observers.remove(user);
    }

    @Override
    public void notifyAllObserver(Notification notification) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(notification);
        }
    }

    public void notifyObserver(Notification notification, User user) {
        user.update(notification);
    }

    public ArrayList<User> getObservers() {
        return observers;
    }
}

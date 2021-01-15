package app.user;

import app.architecture.Application;
import app.company.Company;
import app.company.Department;
import app.info.*;
import app.info.Date;

import java.util.*;

public class Manager extends Employee {
    private ArrayList<Request> requests;

    public Manager() {
        requests = new ArrayList<>();
    }

    public Manager(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request) {
        if (!requests.contains(request)) {
            requests.add(request);
        }
    }

    public void process(Job job) {
        if (job.isOpen_job()) {
            ArrayList<Pair<User, Double>> candidates = new ArrayList<>();
//            System.out.println(requests.size());
            for (int i = 0; i < requests.size(); i++) {
                if (job == requests.get(i).getKey()) {
//                    System.out.println(((User)requests.get(i).getValue1()).getResume().getInformation().getLastname());
                    if (Application.getInstance().getUsers().contains((User)requests.get(i).getValue1())) {
                        candidates.add(new Pair(requests.get(i).getValue1(), requests.get(i).getScore()));
                    }
                }
            }
            Collections.sort(candidates, new Comparator<Pair<User, Double>>() {
                @Override
                public int compare(Pair<User, Double> o1, Pair<User, Double> o2) {
                    if (o1.degree > o2.degree)
                        return -1;
                    if (o1.degree < o2.degree)
                        return 1;
                    return 0;
                }
            });
            Employee employee;
            Company company;
            ArrayList<Company> list = Application.getInstance().getCompanies();
            for (int i = 0; i < candidates.size(); i++) {
                if (job.isOpen_job()) {
                    System.out.println("Notificarile pentru " + candidates.get(i).user.getResume().getInformation().getLastname());
                    candidates.get(i).user.seeNotifications();
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(i).getObservers().contains(candidates.get(i).user)) {
                            list.get(i).removeObserver(candidates.get(i).user);
                        }
                    }
                    employee = candidates.get(i).user.convert();
                    employee.setCompany(job.getCompany());
                    employee.setSalary(job.getSalary());
                    company = Application.getInstance().getCompany(job.getCompany());
                    company.getDepartment("IT").add(employee);
                    Date date1 = new Date(Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH) + 1,
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    try {
                        employee.getResume().addExperience(new Experience(date1, null, job.getName(), company.getName()));
                    } catch (InvalidDatesException e) {
                        e.printStackTrace();
                    }
                    job.setNr_candidates(job.getNr_candidates() - 1);
                } else {
                    Notification notification = new Notification(job.getCompany(), "Ati fost respins de la job-ul "
                            + job.getName());
                    Application.getInstance().getCompany(job.getCompany()).notifyObserver(notification, candidates.get(i).user);
                }
                if (job.getNr_candidates() == 0) {
                    job.setOpen_job(false);
                }
            }
            if (!job.isOpen_job()) {
                Notification notification = new Notification(job.getCompany(), "Au fost inchise aplicarile pentru postul " +
                        "de " + job.getName());
                Application.getInstance().getCompany(job.getCompany()).notifyAllObserver(notification);
            }
        }
    }
}

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
            for (int i = 0; i < requests.size(); i++) {
                if (job == requests.get(i).getKey()) {
                    if (Application.getInstance().getUsers().contains((User)requests.get(i).getValue1())) {
                        candidates.add(new Pair(requests.get(i).getValue1(), requests.get(i).getScore()));
                    }
                }
            }
            Collections.sort(candidates, new Comparator<Pair<User, Double>>() {
                @Override
                public int compare(Pair<User, Double> o1, Pair<User, Double> o2) {
                    if (o1.getValue2() > o2.getValue2())
                        return -1;
                    if (o1.getValue2() < o2.getValue2())
                        return 1;
                    return 0;
                }
            });
            Employee employee;
            Company company;
            ArrayList<Company> list = Application.getInstance().getCompanies();
            for (int i = 0; i < candidates.size(); i++) {
                if (job.isOpen_job()) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getObservers().contains(candidates.get(i).getValue1())) {
                            list.get(j).removeObserver(candidates.get(i).getValue1());
                        }
                    }
                    ArrayList<Request> requests = new ArrayList<>();
                    for (int k = 0; k < getRequests().size(); k++) {
                        if (getRequests().get(k).getValue1() != candidates.get(i).getValue1()) {
                            requests.add(getRequests().get(k));
                        }
                    }
                    setRequests(requests);
                    employee = candidates.get(i).getValue1().convert();
                    employee.setCompany(job.getCompany());
                    employee.setSalary(job.getSalary());
                    company = Application.getInstance().getCompany(job.getCompany());
                    company.getDepartment(job.getDepartment()).add(employee);
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
                    Application.getInstance().getCompany(job.getCompany()).notifyObserver(notification, candidates.get(i).getValue1());
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

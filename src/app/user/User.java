package app.user;

import app.architecture.Application;

import java.util.ArrayList;

public class User extends Consumer implements Observer {
    private ArrayList<String> interests;
    private ArrayList<Pair<Notification, Boolean>> notifications;

    // constructorii
    public User() {
        interests = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public User(ArrayList<String> interests) {
        this.interests = interests;
    }

    // getter si setteri
    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    // metoda convert
    public Employee convert() {
        Employee employee = new Employee();
        employee.setResume(this.getResume());
        employee.setContacts(this.getContacts());
        for (int i = 0; i < this.getContacts().size(); i++) {
            this.getContacts().get(i).getContacts().remove(this);
            this.getContacts().get(i).getContacts().add(employee);
        }
        Application.getInstance().remove(this);
        return employee;
    }

    // metoda de adaugare a unei companii de care e interesat user-ul
    public void addInterest(String company) {
        interests.add(company);
    }

    // metoda de aflare a scorului
    public Double getTotalScore() {
        double total = this.getExperienceYears();
        double academic_mean = this.meanGPA();
        total = 1.5 * total + academic_mean;
        return total;
    }

    // returneaza o lista cu notificarile care nu sunt vazute
    public ArrayList<Pair<Notification, Boolean>> getUnseenNotifications() {
        ArrayList<Pair<Notification, Boolean>> list = new ArrayList<>();
        for (int i = 0; i < notifications.size(); i++) {
            if (!notifications.get(i).getValue2()) {
                list.add(notifications.get(i));
                notifications.get(i).setValue2(true);
            }
        }
        return list;
    }

    public ArrayList<String> getNotifications() {
        ArrayList<String> not = new ArrayList<>();
        for (int i = notifications.size() - 1; i >= 0; i--) {
            not.add(notifications.get(i).getValue1().getText());
            notifications.get(i).setValue2(true);
        }
        return not;
    }

    @Override
    public void update(Notification notification) {
        notifications.add(new Pair<>(notification, false));
    }

    // afiseaza in consola ntificariles
    public void seeNotifications() {
        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).getValue2() == false) {
                System.out.println(notifications.get(i).getValue1().getCompany() + ": " +
                        notifications.get(i).getValue1().getText());
                notifications.get(i).setValue2(true);
            }
        }
    }
}

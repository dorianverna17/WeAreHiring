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

    @Override
    public void update(Notification notification) {
        notifications.add(new Pair<>(notification, false));
    }

    public void seeNotifications() {
        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).degree == false) {
                System.out.println(notifications.get(i).user.getCompany() + ": " +
                        notifications.get(i).user.getText());
                notifications.get(i).degree = true;
            }
        }
    }
}

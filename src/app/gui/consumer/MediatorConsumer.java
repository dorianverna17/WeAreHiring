package app.gui.consumer;

import app.user.Consumer;

public interface MediatorConsumer {
    public void createWin(Consumer consumer);
    public void getToMainScreen();
    public void showDetailsWin();
    public void applyConsumer();
    public void listJobs();
    public void listNotifications();
    public void listDepartments();
    public void listEmployees();
    public void listApplications();
    public void evaluateUser();
    public void listRequests();
    public void hire();
    public void listRecruiters();
    public void moveTo(String dep);
}

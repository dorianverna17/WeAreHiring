package app.gui.consumer;

import app.architecture.Application;
import app.company.Company;
import app.gui.Main;
import app.gui.ShowDetails;
import app.gui.consumer.buttons.*;
import app.gui.consumer.lists.*;
import app.info.Job;
import app.info.Request;
import app.user.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MediatorDialog implements MediatorConsumer {
    private JFrame frame;
    private JLabel logginas;
    private Consumer loggedconsumer;

    private LogoutButton logout;
    private ScrollablePanel mainpanel;
    private PanelList panellist;
    private DetailsButton details;
    private GetJobsButton job_button;
    private ApplyButton apply_button;
    private NotificationsButton notifications_button;
    private MyCompanyButton companybutton;
    private SeeEmployees employeesbutton;
    private ViewApplications applications;
    private EvaluateButton evaluate;
    private HireButton hirebutton;
    private RequestsButton requests;

    @Override
    public void createWin(Consumer consumer) {
        frame = new JFrame("We are hiring");
        frame.setMinimumSize(new Dimension(700, 700));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(200, 200, 150));

        JPanel panel_east = new JPanel(new FlowLayout());
        JPanel panel_west = new JPanel(new FlowLayout());
        JPanel panel_north = new JPanel(new BorderLayout());
        JPanel panel_south = new JPanel(new BorderLayout());
        JPanel panel_center = new JPanel(new BorderLayout());
        panel_east.setBackground(new Color(200, 200, 150));
        panel_west.setBackground(new Color(200, 200, 150));
        panel_north.setBackground(new Color(200, 200, 150));
        panel_south.setBackground(new Color(200, 200, 150));
        panel_center.setBackground(new Color(200, 200, 150));
        panel_east.setPreferredSize(new Dimension(100, 500));
        panel_west.setPreferredSize(new Dimension(100, 500));
        panel_north.setPreferredSize(new Dimension(700, 100));
        panel_south.setPreferredSize(new Dimension(700, 100));
        frame.add(panel_east, BorderLayout.EAST);
        frame.add(panel_west, BorderLayout.WEST);
        frame.add(panel_north, BorderLayout.NORTH);
        frame.add(panel_south, BorderLayout.SOUTH);
        frame.add(panel_center, BorderLayout.CENTER);
        JPanel panel_north_south = new JPanel(new BorderLayout());
        JPanel panel_west_aux = new JPanel(new BorderLayout());
        panel_north_south.setPreferredSize(new Dimension(700, 60));
        panel_west_aux.setPreferredSize(new Dimension(100, 60));
        panel_north_south.setBackground(new Color(200, 200, 150));
        panel_west_aux.setBackground(new Color(200, 200, 150));

        panel_north_south.add(panel_west_aux, BorderLayout.WEST);
        panel_north.add(panel_north_south, BorderLayout.SOUTH);

        logout = new LogoutButton(this);
        logginas = new JLabel();
        details = new DetailsButton(this);
        panellist = new PanelList(consumer, this);
        mainpanel = new ScrollablePanel(panellist, consumer, this);
        panel_center.add(mainpanel);

        logginas.setText("Logged in as " + consumer.getResume().getInformation().getFirstname() + " " +
                consumer.getResume().getInformation().getLastname());
        panel_west.add(logout);
        panel_north_south.add(logginas, BorderLayout.CENTER);
        panel_east.add(details, BorderLayout.WEST);
        loggedconsumer = consumer;
        if (consumer instanceof User) {
            apply_button = new ApplyButton(this);
            job_button = new GetJobsButton(this);
            panel_west.add(apply_button);
            panel_west.add(job_button);
            ArrayList<Pair<Notification, Boolean>> list = ((User)consumer).getUnseenNotifications();
            notifications_button = new NotificationsButton(list.size() + " notificari noi", this);
            panel_west.add(notifications_button);
        } else {
            companybutton = new MyCompanyButton(this);
            employeesbutton = new SeeEmployees(this);
            panel_west.add(companybutton);
            panel_west.add(employeesbutton);
            if (consumer instanceof Manager) {
                job_button = new GetJobsButton(this);
                requests = new RequestsButton(this);
                hirebutton = new HireButton(this);
                panel_west.add(job_button);
                panel_west.add(requests);
                panel_west.add(hirebutton);
            } else if (consumer instanceof Recruiter) {
                applications = new ViewApplications(this);
                evaluate = new EvaluateButton(this);
                panel_west.add(applications);
                panel_west.add(evaluate);
            }
        }
        frame.setVisible(true);
    }

    public void getToMainScreen() {
        frame.dispose();
        Main main = new Main();
    }

    @Override
    public void showDetailsWin() {
        String aux = panellist.getSelectedValue().toString();
        Consumer consumer = Application.getInstance().getConsumer(aux);
        ShowDetails details = new ShowDetails(consumer);
    }

    @Override
    public void applyConsumer() {
        Job job = Application.getInstance().findJob((String)panellist.getSelectedValue());
        System.out.println(job);
        if (job != null)
            job.applyJob((User) loggedconsumer);
    }

    @Override
    public void listJobs() {
        JobList list;
        if (loggedconsumer instanceof User)
            list = new JobList(loggedconsumer, this);
        else
            list = new JobList(Application.getInstance().getCompany(((Manager)loggedconsumer).getCompany()),
                    this);
        panellist.replaceList(list);
    }

    @Override
    public void listNotifications() {
        NotificationList list = new NotificationList((User)loggedconsumer, this);
        panellist.replaceList(list);
    }

    @Override
    public void listDepartments() {
        String aux = ((Employee)loggedconsumer).getCompany();
        System.out.println(aux);
        DepartmentList list = new DepartmentList(this, Application.getInstance().getCompany(aux), loggedconsumer);
        panellist.replaceList(list);
    }

    @Override
    public void listEmployees() {
        String aux = (String) panellist.getSelectedValue();
        int index = aux.indexOf(',');
        aux = aux.substring(0, index);
        Company comp = Application.getInstance().getCompany(((Employee) loggedconsumer).getCompany());
        EmployeeList employee = new EmployeeList(this, comp.getDepartment(aux));
        panellist.replaceList(employee);
    }

    @Override
    public void listApplications() {
        Recruiter recruiter = (Recruiter) loggedconsumer;
        ArrayList<Pair<User, Job>> list = recruiter.getEvaluatedUsers();
        EvaluationList aux = new EvaluationList(this, list);
        panellist.replaceList(aux);
    }

    @Override
    public void evaluateUser() {
        String aux = (String) panellist.getSelectedValue();
        int index = aux.indexOf('-');
        User user = Application.getInstance().getUser(aux.substring(0, index - 1));
        Company company = Application.getInstance().getCompany(((Recruiter) loggedconsumer).getCompany());
        Job job = null;
        for (int i = 0; i < company.getDepartments().size(); i++) {
            job = company.getDepartments().get(i).getJob(aux.substring(index + 2));
            if (job != null)
                break;
        }
        if (job != null) {
            ((Recruiter)loggedconsumer).evaluate(job, user);
            panellist.removeElement();
        }
    }

    @Override
    public void listRequests() {
        String aux = (String) panellist.getSelectedValue();
        Manager manager = (Manager) loggedconsumer;
        int index = aux.indexOf(' ');
        Company company = Application.getInstance().getCompany(manager.getCompany());
        Job job = null;
        for (int i = 0; i < company.getDepartments().size(); i++) {
            job = company.getDepartments().get(i).getJob(aux.substring(index + 1));
            if (job != null)
                break;
        }
        ArrayList<Request> requests = new ArrayList<>();
        if (job != null) {
            for (int i = 0; i < manager.getRequests().size(); i++) {
                if (manager.getRequests().get(i).getKey() == job) {
                    requests.add(manager.getRequests().get(i));
                }
            }
        }
        RequestList list = new RequestList(this, requests);
        panellist.replaceList(list);
    }

    @Override
    public void hire() {

    }
}

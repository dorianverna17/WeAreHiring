package app.gui.consumer;

import app.architecture.Application;
import app.company.Company;
import app.company.Department;
import app.gui.Main;
import app.gui.ShowDetails;
import app.gui.consumer.buttons.*;
import app.gui.consumer.lists.*;
import app.info.*;
import app.user.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

// clasa care implementeaza interfata de mediator
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
    private RecruitersButton recruitersbutton;
    private MoveToManagement moveManagement;
    private MoveToIT moveIT;
    private MoveToFinance moveFinance;
    private MoveToMarketing moveMarketing;
    private SeeContacts contacts;

    // metoda care imi creeaza fereastra
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
        contacts = new SeeContacts(this);
        panel_west.add(contacts);
        apply_button = null;
        if (consumer instanceof User) {
            apply_button = new ApplyButton(this);
            job_button = new GetJobsButton(this);
            panel_west.add(apply_button);
            panel_west.add(job_button);
            ArrayList<Pair<Notification, Boolean>> list = ((User)consumer).getUnseenNotifications();
            notifications_button = new NotificationsButton(list.size() + " notificari noi", this);
            panel_west.add(notifications_button);
            apply_button.setEnabled(false);
        } else {
            companybutton = new MyCompanyButton(this);
            employeesbutton = new SeeEmployees(this);
            panel_west.add(companybutton);
            panel_west.add(employeesbutton);
            employeesbutton.setEnabled(false);
            if (consumer instanceof Manager) {
                job_button = new GetJobsButton(this);
                requests = new RequestsButton(this);
                hirebutton = new HireButton(this);
                hirebutton.setEnabled(false);
                requests.setEnabled(false);
                recruitersbutton = new RecruitersButton(this);
                moveFinance = new MoveToFinance(this);
                moveIT = new MoveToIT(this);
                moveManagement = new MoveToManagement(this);
                moveMarketing = new MoveToMarketing(this);
                moveMarketing.setEnabled(false);
                moveFinance.setEnabled(false);
                moveManagement.setEnabled(false);
                moveIT.setEnabled(false);
                panel_west.add(job_button);
                panel_west.add(requests);
                panel_west.add(hirebutton);
                panel_west.add(recruitersbutton);
                panel_east.add(moveFinance);
                panel_east.add(moveIT);
                panel_east.add(moveManagement);
                panel_east.add(moveMarketing);
            } else if (consumer instanceof Recruiter) {
                applications = new ViewApplications(this);
                evaluate = new EvaluateButton(this);
                panel_west.add(applications);
                panel_west.add(evaluate);
                evaluate.setEnabled(false);
            }
        }
        frame.setVisible(true);
    }

    // metoda apelata in momentul in care fac Log Out
    public void getToMainScreen() {
        frame.dispose();
        Main main = new Main();
    }

    // metoda apelata atunci cand vreau sa vad detaliile legate de un utilizator
    @Override
    public void showDetailsWin() {
        if (panellist.getSelectedValue() != null) {
            String aux = panellist.getSelectedValue().toString();
            Consumer consumer = Application.getInstance().getConsumer(aux);
            ShowDetails details = new ShowDetails(consumer);
        }
    }

    // Metoda folosita atunci cand aplic la un Job
    @Override
    public void applyConsumer() {
        if (panellist.getSelectedValue() != null) {
            Job job = Application.getInstance().findJob((String) panellist.getSelectedValue());
            System.out.println(job);
            if (job != null)
                job.applyJob((User) loggedconsumer);
        }
    }

    // metoda folosita pentru a lista job-urile
    @Override
    public void listJobs() {
        JobList list;
        if (loggedconsumer instanceof User)
            list = new JobList(loggedconsumer, this);
        else
            list = new JobList(Application.getInstance().getCompany(
                    ((Manager)loggedconsumer).getCompany()), this);
        if (employeesbutton != null)
            employeesbutton.setEnabled(false);
        panellist.replaceList(list);
        if (apply_button != null)
            apply_button.setEnabled(true);
        if (requests != null)
            requests.setEnabled(true);
        if (hirebutton != null)
            hirebutton.setEnabled(false);
        details.setEnabled(false);
        if (loggedconsumer instanceof Manager) {
            moveMarketing.setEnabled(false);
            moveFinance.setEnabled(false);
            moveManagement.setEnabled(false);
            moveIT.setEnabled(false);
        }
    }

    // metoda folosita pentru a lista notificarile
    @Override
    public void listNotifications() {
        details.setEnabled(false);
        apply_button.setEnabled(false);
        NotificationList list = new NotificationList((User)loggedconsumer, this);
        panellist.replaceList(list);
    }

    // metoda folosita pentru a lista departamentele
    @Override
    public void listDepartments() {
        String aux = ((Employee)loggedconsumer).getCompany();
        System.out.println(aux);
        DepartmentList list = new DepartmentList(this, Application.getInstance().getCompany(aux), loggedconsumer);
        panellist.replaceList(list);
        details.setEnabled(false);
        employeesbutton.setEnabled(true);
        if (hirebutton != null)
            hirebutton.setEnabled(false);
        if (requests != null)
            requests.setEnabled(false);
        if (loggedconsumer instanceof Manager) {
            moveMarketing.setEnabled(false);
            moveFinance.setEnabled(false);
            moveManagement.setEnabled(false);
            moveIT.setEnabled(false);
        }
        if (evaluate != null)
            evaluate.setEnabled(false);
    }

    // metoda folosita pentru a lista angajatii dintr-un departament
    @Override
    public void listEmployees() {
        if (panellist.getSelectedValue() != null) {
            String aux = (String) panellist.getSelectedValue();
            int index = aux.indexOf(',');
            if (index != -1)
                aux = aux.substring(0, index);
            Company comp = Application.getInstance().getCompany(((Employee) loggedconsumer).getCompany());
            EmployeeList employee = new EmployeeList(this, comp.getDepartment(aux));
            panellist.replaceList(employee);
            employeesbutton.setEnabled(false);
            details.setEnabled(true);
            if (hirebutton != null)
                hirebutton.setEnabled(false);
            if (requests != null)
                requests.setEnabled(false);
            if (loggedconsumer instanceof Manager) {
                moveMarketing.setEnabled(true);
                moveFinance.setEnabled(true);
                moveManagement.setEnabled(true);
                moveIT.setEnabled(true);
            }
            if (evaluate != null)
                evaluate.setEnabled(false);
        }
    }

    // metoda folosita pentru a lista userii care au aplica pentru
    // un job anume
    @Override
    public void listApplications() {
        employeesbutton.setEnabled(false);
        Recruiter recruiter = (Recruiter) loggedconsumer;
        ArrayList<Pair<User, Job>> list = recruiter.getEvaluatedUsers();
        EvaluationList aux = new EvaluationList(this, list);
        panellist.replaceList(aux);
        details.setEnabled(true);
        evaluate.setEnabled(true);
    }

    // metoda apelata atunci cand recruiter-ul doreste sa evalueze un user
    @Override
    public void evaluateUser() {
        if (panellist.getSelectedValue() != null) {
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
                ((Recruiter) loggedconsumer).evaluate(job, user);
                panellist.removeElement();
            }
        }
    }

    // metoda folosita atunci cand managerul doreste sa listeze
    // requesturile pe care le are de analizat
    @Override
    public void listRequests() {
        if (panellist.getSelectedValue() != null) {
            hirebutton.setEnabled(true);
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
            Collections.sort(requests, new Comparator<Request>() {
                @Override
                public int compare(Request o1, Request o2) {
                    if (o1.getScore() > o2.getScore())
                        return -1;
                    else if (o1.getScore() < o2.getScore())
                        return 1;
                    return 0;
                }
            });
            RequestList list = new RequestList(this, requests);
            panellist.replaceList(list);
            if (loggedconsumer instanceof Manager) {
                moveMarketing.setEnabled(false);
                moveFinance.setEnabled(false);
                moveManagement.setEnabled(false);
                moveIT.setEnabled(false);
            }
        }
    }

    // metoda folosita de manager atunci cand doreste sa angajeze un user
    @Override
    public void hire() {
        if (panellist.getSelectedValue() != null) {
            Company company = Application.getInstance().getCompany(((Manager)
                    loggedconsumer).getCompany());
            String aux = (String) panellist.getSelectedValue();
            String job_aux = aux.substring(aux.lastIndexOf('-') + 2);
            aux = aux.substring(0, aux.indexOf('-') - 1);
            Job job = null;
            for (int i = 0; i < company.getDepartments().size(); i++) {
                job = company.getDepartments().get(i).getJob(job_aux);
                if (job != null)
                    break;
            }
            Manager manager = (Manager) loggedconsumer;
            if (job != null) {
                User user = Application.getInstance().getUser(aux);
                ArrayList<Request> requests = new ArrayList<>();
                for (int i = 0; i < manager.getRequests().size(); i++) {
                    if (manager.getRequests().get(i).getValue1() != user) {
                        requests.add(manager.getRequests().get(i));
                    }
                }
                manager.setRequests(requests);
                Employee employee;
                company.getObservers().contains(user);
                company.removeObserver(user);
                employee = user.convert();
                employee.setCompany(job.getCompany());
                employee.setSalary(job.getSalary());
                company = Application.getInstance().getCompany(job.getCompany());
                company.getDepartment(job.getDepartment()).add(employee);
                Date date1 = new Date(Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH) + 1,
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                try {
                    employee.getResume().addExperience(new Experience(date1,
                            null, job.getName(), company.getName()));
                } catch (InvalidDatesException e) {
                    e.printStackTrace();
                }
            }
            DefaultListModel model = (DefaultListModel) panellist.getModel();
            model.remove(panellist.getSelectedIndex());
        }
    }

    // metoda folosita pentru a lista recruiterii dintr-o companie
    @Override
    public void listRecruiters() {
        RecruiterList list = new RecruiterList(this, (Manager)loggedconsumer);
        panellist.replaceList(list);
        details.setEnabled(false);
        if (hirebutton != null)
            hirebutton.setEnabled(false);
        if (requests != null)
            requests.setEnabled(false);
        if (loggedconsumer instanceof Manager) {
            moveMarketing.setEnabled(false);
            moveFinance.setEnabled(false);
            moveManagement.setEnabled(false);
            moveIT.setEnabled(false);
        }
    }

    // metoda folosita pentru a muta un anumit angajat dintr-un departament
    // in altul
    @Override
    public void moveTo(String dep) {
        if (panellist.getSelectedValue() != null) {
            Manager manager = (Manager) loggedconsumer;
            Company company = Application.getInstance().getCompany(manager.getCompany());
            Department department = company.getDepartment(dep);
            String aux = (String) panellist.getSelectedValue();
            Employee employee = company.getEmployee(aux);
            if (!department.getEmployees().contains(employee)) {
                company.move(employee, department);
                DefaultListModel model = (DefaultListModel) panellist.getModel();
                model.remove(panellist.getSelectedIndex());
            }
        }
    }

    // metoda folosita atunci cand se apasa butonul pentru a vedea contactele
    @Override
    public void seeContacts() {
        details.setEnabled(true);
        if (apply_button != null)
            apply_button.setEnabled(false);
        PanelList list = new PanelList(loggedconsumer, this);
        panellist.replaceList(list);
        if (employeesbutton != null)
            employeesbutton.setEnabled(false);
        if (hirebutton != null)
            hirebutton.setEnabled(false);
        if (requests != null)
            requests.setEnabled(false);
        if (loggedconsumer instanceof Manager) {
            moveMarketing.setEnabled(false);
            moveFinance.setEnabled(false);
            moveManagement.setEnabled(false);
            moveIT.setEnabled(false);
        }
        if (evaluate != null)
            evaluate.setEnabled(false);
    }
}

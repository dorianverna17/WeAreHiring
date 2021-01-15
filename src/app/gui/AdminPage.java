package app.gui;

import app.architecture.Application;
import app.company.Company;
import app.company.Department;
import app.info.Job;
import app.user.Consumer;
import app.user.Employee;
import app.user.Recruiter;
import app.user.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class AdminPage extends JFrame implements ActionListener {
    private JButton users;
    private JButton companies;
    private JButton show_details;
    private JButton show_employees;
    private JButton show_departments;
    private JButton show_jobs;
    private JButton show_recruiters;
    private JButton logout;
    private JList list;
    // 0 pt user, 1 pt companie, 2 pt employee,
    // 3 pt dep, 4 pt job, 5 pt recruiter
    private int displayed;
    private Company company;

    public AdminPage() {
        super("We are hiring");
        setMinimumSize(new Dimension(700, 700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(200, 200, 150));

        JPanel panel_east = new JPanel(new FlowLayout());
        JPanel panel_west = new JPanel(new FlowLayout());
        JPanel panel_north = new JPanel(new BorderLayout());
        JPanel panel_south = new JPanel(new BorderLayout());
        JPanel panel_center = new JPanel(new FlowLayout());
        panel_east.setBackground(new Color(200, 200, 150));
        panel_west.setBackground(new Color(200, 200, 150));
        panel_north.setBackground(new Color(200, 200, 150));
        panel_south.setBackground(new Color(200, 200, 150));
        panel_center.setBackground(new Color(200, 200, 150));
        panel_east.setPreferredSize(new Dimension(100, 500));
        panel_west.setPreferredSize(new Dimension(100, 500));
        panel_north.setPreferredSize(new Dimension(700, 100));
        panel_south.setPreferredSize(new Dimension(700, 100));
        add(panel_east, BorderLayout.EAST);
        add(panel_west, BorderLayout.WEST);
        add(panel_north, BorderLayout.NORTH);
        add(panel_south, BorderLayout.SOUTH);
        add(panel_center, BorderLayout.CENTER);
        JScrollPane pane;
        list = new JList();
        DefaultListModel model = new DefaultListModel();
        list = new JList(model);
        pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(460, 450));
        panel_center.add(pane);
        users = new JButton("Users");
        companies = new JButton("Companies");
        show_details = new JButton("Show Details");
        show_departments = new JButton("Departments");
        show_employees = new JButton("Employees");
        show_jobs = new JButton("Available Jobs");
        show_recruiters = new JButton("Recruiters");
        users.addActionListener(this);
        companies.addActionListener(this);
        show_details.addActionListener(this);
        show_departments.addActionListener(this);
        show_employees.addActionListener(this);
        show_jobs.addActionListener(this);
        show_recruiters.addActionListener(this);
        JPanel aux_panel1 = new JPanel(new FlowLayout());
        panel_north.add(aux_panel1, BorderLayout.SOUTH);
        aux_panel1.setBackground(new Color(200, 200, 150));
        aux_panel1.add(users);
        aux_panel1.add(companies);
        aux_panel1.add(show_details);

        JPanel aux_panel2 = new JPanel(new FlowLayout());
        panel_south.add(aux_panel2, BorderLayout.NORTH);
        aux_panel2.setBackground(new Color(200, 200, 150));
        aux_panel2.add(show_departments);
        aux_panel2.add(show_employees);
        aux_panel2.add(show_recruiters);
        aux_panel2.add(show_jobs);

        Application app = Application.getInstance();
        String aux1, aux2;
        for (int i = 0; i < app.getUsers().size(); i++) {
            aux1 = app.getUsers().get(i).getResume().getInformation().getLastname();
            aux2 = app.getUsers().get(i).getResume().getInformation().getFirstname();
            model.addElement(aux1 + " " + aux2);
        }
        displayed = 0;
        logout = new JButton("Log Out");
        panel_west.add(logout);
        logout.addActionListener(this);

        show_recruiters.setEnabled(false);
        show_jobs.setEnabled(false);
        show_departments.setEnabled(false);
        show_employees.setEnabled(false);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application app = Application.getInstance();
        JButton button = (JButton)e.getSource();
        if (button.getText().equals("Users")) {
            show_recruiters.setEnabled(false);
            show_jobs.setEnabled(false);
            show_departments.setEnabled(false);
            show_employees.setEnabled(false);
            show_details.setText("Show Details");
            DefaultListModel model = new DefaultListModel();
            String aux1, aux2;
            for (int i = 0; i < app.getUsers().size(); i++) {
                aux1 = app.getUsers().get(i).getResume().getInformation().getLastname();
                aux2 = app.getUsers().get(i).getResume().getInformation().getFirstname();
                model.addElement(aux1 + " " + aux2);
            }
            displayed = 0;
            list.setModel((ListModel) model);
        } else if (button.getText().equals("Companies")) {
            show_recruiters.setEnabled(true);
            show_jobs.setEnabled(false);
            show_departments.setEnabled(true);
            show_employees.setEnabled(false);
            DefaultListModel model = new DefaultListModel();
            String aux1;
            show_details.setText("Show Manager Details");
            for (int i = 0; i < app.getCompanies().size(); i++) {
                aux1 = app.getCompanies().get(i).getName();
                model.addElement(aux1);
            }
            displayed = 1;
            list.setModel((ListModel) model);
        } else if (button.getText().contains("Details")) {
            String text = list.getSelectedValue().toString();
            if (displayed == 0) {
                User user = app.getUser(text);
                ShowDetails win = new ShowDetails(user);
            } else if (displayed == 1) {
                Company comp = app.getCompany(text);
                ShowDetails win = new ShowDetails(comp);
            } else if (displayed == 3) {
                Recruiter rec = company.getRecruiter(text);
                ShowDetails win = new ShowDetails(rec);
            } else {
                Employee empl = company.getEmployee(text);
                System.out.println("yes");
                ShowDetails win = new ShowDetails(empl);
            }
        } else if (button.getText().equals("Log Out")) {
            this.dispose();
            Main main = new Main();
        } else if (button.getText().equals("Departments")) {
            show_details.setEnabled(true);
            Company comp;
            if (displayed != 3 && displayed != 4 && displayed != 5) {
                String text = list.getSelectedValue().toString();
                comp = app.getCompany(text);
                this.company = comp;
            } else {
                comp = company;
            }
            displayed = 2;
            DefaultListModel model = new DefaultListModel();
            String aux1;
            ArrayList<Department> deps = comp.getDepartments();
            for (int i = 0; i < deps.size(); i++) {
                String dep_name = deps.get(i).getClass().toGenericString();
                dep_name = dep_name.substring(dep_name.lastIndexOf('.') + 1);
                model.addElement(dep_name);
            }
            list.setModel((ListModel) model);
            show_recruiters.setEnabled(false);
            show_jobs.setEnabled(true);
            show_departments.setEnabled(false);
            show_employees.setEnabled(true);
        } else if (button.getText().equals("Recruiters")) {
            show_details.setEnabled(true);
            show_details.setText("Show Details");
            String text = list.getSelectedValue().toString();
            displayed = 3;
            Company comp = app.getCompany(text);
            this.company = comp;
            DefaultListModel model = new DefaultListModel();
            String aux1, aux2;
            for (int i = 0; i < company.getRecruiters().size(); i++) {
                aux1 = company.getRecruiters().get(i).getResume().getInformation().getLastname();
                aux2 = company.getRecruiters().get(i).getResume().getInformation().getFirstname();
                model.addElement(aux1 + " " + aux2);
            }
            show_recruiters.setEnabled(false);
            list.setModel((ListModel) model);
        } else if (button.getText().equals("Employees")) {
            displayed = 4;
            show_details.setEnabled(true);
            show_details.setText("Show Details");
            show_recruiters.setEnabled(true);
            show_jobs.setEnabled(false);
            show_departments.setEnabled(true);
            show_employees.setEnabled(false);
            String text = list.getSelectedValue().toString();
            Department dep = null;
            DefaultListModel model = new DefaultListModel();
            ArrayList<Department> deps = company.getDepartments();
            for (int i = 0; i < deps.size(); i++) {
                String dep_name = deps.get(i).getClass().toString();
                dep_name = dep_name.substring(dep_name.lastIndexOf('.') + 1);
                if (dep_name.equals(text)) {
                    dep = deps.get(i);
                }
            }
            String aux1;
            for (int i = 0; i < dep.getEmployees().size(); i++) {
                aux1 = dep.getEmployees().get(i).getResume().getInformation().getLastname() + " " +
                        dep.getEmployees().get(i).getResume().getInformation().getFirstname();
                model.addElement(aux1);
            }
            list.setModel((ListModel) model);
        } else if (button.getText().equals("Available Jobs")) {
            displayed = 5;
            show_details.setEnabled(false);
            String text = list.getSelectedValue().toString();
            Department dep = company.getDepartment(text);
            DefaultListModel model = new DefaultListModel();
            String aux;
            String grade, grading, exp;
            for (int i = 0; i < dep.getJobs().size(); i++) {
                // graduation year
                if (dep.getJobs().get(i).getGraduation().getInf() == null &&
                        dep.getJobs().get(i).getGraduation().getSup() == null) {
                    grading = "no graduation year constraints";
                } else if (dep.getJobs().get(i).getGraduation().getInf() == null &&
                        dep.getJobs().get(i).getGraduation().getSup() != null) {
                    grading = "max: " + dep.getJobs().get(i).getGraduation().getSup();
                } else if (dep.getJobs().get(i).getGraduation().getInf() != null &&
                        dep.getJobs().get(i).getGraduation().getSup() != null) {
                    grading = dep.getJobs().get(i).getGraduation().getInf() + " - " +
                        dep.getJobs().get(i).getGraduation().getSup();
                } else {
                    grading = "min: " + dep.getJobs().get(i).getGraduation().getInf();
                }
                // experienta
                if (dep.getJobs().get(i).getExperience().getInf() == null &&
                        dep.getJobs().get(i).getExperience().getSup() == null) {
                    exp = "no experience constraints";
                } else if (dep.getJobs().get(i).getExperience().getInf() == null &&
                        dep.getJobs().get(i).getExperience().getSup() != null) {
                    exp = "max: " + dep.getJobs().get(i).getExperience().getSup();
                } else if (dep.getJobs().get(i).getExperience().getInf() != null &&
                        dep.getJobs().get(i).getExperience().getSup() != null) {
                    exp = dep.getJobs().get(i).getExperience().getInf() + " - " +
                            dep.getJobs().get(i).getExperience().getSup();
                } else {
                    exp = "min: " + dep.getJobs().get(i).getExperience().getInf();
                }
                // grade
                if (dep.getJobs().get(i).getGrade().getInf() == null)
                    grade = "no grade constraints";
                else
                    grade = "minimum academic mean: " + dep.getJobs().get(i).getGrade().getInf();
                aux = dep.getJobs().get(i).getName() + ", " + dep.getJobs().get(i).getSalary() + ", " +
                grading + ", " + exp + ", " + grade;
                model.addElement(aux);
            }
            list.setModel((ListModel) model);
            show_employees.setEnabled(false);
            show_jobs.setEnabled(false);
            show_departments.setEnabled(true);
        }
    }
}

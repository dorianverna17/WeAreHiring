package app.gui.registration;

import app.architecture.Application;
import app.gui.registration.RegisterCompany;
import app.gui.registration.RegisterEmployee;
import app.gui.registration.RegisterInterests;
import app.info.*;
import app.user.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class ExperienceWin extends JFrame implements ActionListener {
    private JLabel position;
    private JLabel company;
    private JLabel begindate;
    private JLabel enddate;
    private JLabel registeras;
    private JTextField position_field;
    private JTextField company_field;
    private JTextField begindate_field;
    private JTextField enddate_field;
    private JButton addexp;
    private JButton register;
    private ArrayList<Experience> list;
    private Information information;
    private ArrayList<Education> education;
    private ArrayList<Experience> experience;
    private JComboBox registeras_box;
    private Consumer.Resume resume;

    public ExperienceWin(Consumer.Resume resume) {
        super("We are hiring");
        setMinimumSize(new Dimension(700, 700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(200, 200, 150));

        JPanel panel_east = new JPanel(new FlowLayout());
        JPanel panel_west = new JPanel(new FlowLayout());
        JPanel panel_north = new JPanel(new FlowLayout());
        JPanel panel_south = new JPanel(new FlowLayout());
        JPanel panel_center = new JPanel(new GridLayout(5, 2, 3, 50));
        panel_east.setBackground(new Color(200, 200, 150));
        panel_west.setBackground(new Color(200, 200, 150));
        panel_north.setBackground(new Color(200, 200, 150));
        panel_south.setBackground(new Color(200, 200, 150));
        panel_center.setBackground(new Color(200, 200, 150));
        panel_east.setPreferredSize(new Dimension(100, 500));
        panel_west.setPreferredSize(new Dimension(100, 500));
        panel_north.setPreferredSize(new Dimension(700, 100));
        panel_south.setPreferredSize(new Dimension(700, 200));
        add(panel_east, BorderLayout.EAST);
        add(panel_west, BorderLayout.WEST);
        add(panel_north, BorderLayout.NORTH);
        add(panel_south, BorderLayout.SOUTH);
        add(panel_center, BorderLayout.CENTER);
        company = new JLabel("Company:");
        position = new JLabel("Position:");
        begindate = new JLabel("Begin date:");
        enddate = new JLabel("End date:");
        company_field = new JTextField();
        position_field = new JTextField();
        begindate_field = new JTextField();
        enddate_field = new JTextField();
        begindate_field.setText("DD/MM/YYYY");
        enddate_field.setText("DD/MM/YYYY");
        addexp = new JButton("Add Experience");
        panel_center.add(company);
        panel_center.add(company_field);
        panel_center.add(position);
        panel_center.add(position_field);
        panel_center.add(begindate);
        panel_center.add(begindate_field);
        panel_center.add(enddate);
        panel_center.add(enddate_field);
        panel_center.add(addexp);
        addexp.addActionListener(this);
        register = new JButton("Register");
        register.addActionListener(this);
        registeras = new JLabel("Register as:");
        Vector<String> vector = new Vector<>();
        vector.add("User");
        vector.add("Employee");
        vector.add("Recruiter");
        vector.add("Manager");
        registeras_box = new JComboBox(vector);
        panel_south.add(registeras);
        panel_south.add(registeras_box);
        panel_south.add(register);

        list = new ArrayList<>();
        experience = new ArrayList<>();
//        information = info;
//        education = edu;
        this.resume = resume;
        resume.setExperience(experience);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).getText().equals("Register")) {
            RegisterEmployee reemp;
            if (registeras_box.getSelectedItem().toString().equals("Manager")) {
                Manager manager = new Manager();
                manager.setResume(resume);
                manager.setContacts(new ArrayList<>());
                RegisterCompany recomp = new RegisterCompany(manager);
            }
            if (registeras_box.getSelectedItem().toString().equals("Employee")) {
                Employee employee = new Employee();
                employee.setResume(resume);
                employee.setContacts(new ArrayList<>());
                reemp = new RegisterEmployee(employee);
            }
            if (registeras_box.getSelectedItem().toString().equals("Recruiter")) {
                Recruiter recruiter = new Recruiter();
                recruiter.setResume(resume);
                recruiter.setContacts(new ArrayList<>());
                reemp = new RegisterEmployee(recruiter);
                recruiter.setRating(5);
            }
            if (registeras_box.getSelectedItem().toString().equals("User")) {
                User user2 = new User();
                user2.setResume(resume);
                user2.setContacts(new ArrayList<>());
                user2.setInterests(new ArrayList<>());
                Application.getInstance().add(user2);
                RegisterInterests win = new RegisterInterests(user2);
//                Application.getInstance().add(user2);
//                Application.getInstance().print_user();
            }
            setVisible(false);
        }
        if (((JButton)e.getSource()).getText().equals("Add Experience")) {
            Experience exp = null;
            exp = new Experience();
            exp.setCompany(company_field.getText());
            exp.setPosition(position_field.getText());

            String day, month, year;
            String aux_date = begindate_field.getText();
            int index = aux_date.indexOf('/');
            day = aux_date.substring(0, index);
            aux_date = aux_date.substring(index + 1);
            index = aux_date.indexOf('/');
            month = aux_date.substring(0, index);
            year = aux_date.substring(index + 1);
            int Day, Month, Year;
            if (day.charAt(0) == '0')
                Day = Integer.parseInt(String.valueOf(day.charAt(1)));
            else
                Day = Integer.parseInt(String.valueOf(day));
            if (month.charAt(0) == '0')
                Month = Integer.parseInt(String.valueOf(month.charAt(1)));
            else
                Month = Integer.parseInt(String.valueOf(month));
            Year = Integer.parseInt(year);
            exp.setBegin_date(new Date(Year, Month, Day));

            aux_date = enddate_field.getText();
            if (!aux_date.equals("")) {
                index = aux_date.indexOf('/');
                day = aux_date.substring(0, index);
                aux_date = aux_date.substring(index + 1);
                index = aux_date.indexOf('/');
                month = aux_date.substring(0, index);
                year = aux_date.substring(index + 1);
                if (day.charAt(0) == '0')
                    Day = Integer.parseInt(String.valueOf(day.charAt(1)));
                else
                    Day = Integer.parseInt(String.valueOf(day));
                if (month.charAt(0) == '0')
                    Month = Integer.parseInt(String.valueOf(month.charAt(1)));
                else
                    Month = Integer.parseInt(String.valueOf(month));
                Year = Integer.parseInt(year);
                exp.setEnd_date(new Date(Year, Month, Day));
            } else {
                exp.setEnd_date(null);
            }
            resume.addExperience(exp);
        }
    }
}

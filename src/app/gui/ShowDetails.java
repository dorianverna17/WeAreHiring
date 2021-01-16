package app.gui;

import app.architecture.Application;
import app.company.Company;
import app.company.Department;
import app.user.Consumer;
import app.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowDetails extends JFrame implements ActionListener {
    private JButton resume;
    private JButton education;
    private JButton experience;
    private JPanel panel_aux;
    private JPanel panel;
    private Consumer user;
    private Company company;
    private JScrollPane pane;

    public ShowDetails() {
        super("We are hiring");
        setMinimumSize(new Dimension(700, 700));
        setLayout(new BorderLayout());
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
        resume = new JButton("Resume");
        education = new JButton("Education");
        experience = new JButton("Experience");
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(200, 200, 150));
        panel_north.add(panel, BorderLayout.SOUTH);
        panel.add(resume, BorderLayout.SOUTH);
        panel.add(education, BorderLayout.SOUTH);
        panel.add(experience, BorderLayout.SOUTH);
        resume.addActionListener(this);
        education.addActionListener(this);
        experience.addActionListener(this);
        this.panel = panel_center;
        this.pane = null;
        setVisible(true);
    }

    public ShowDetails(Consumer user) {
        this();
        this.user = user;
        showResume(user);
    }

    public ShowDetails(Company company) {
        this();
        this.company = company;
        showCompanyDetailsManager(company);
    }

    public void showCompanyDetailsManager(Company company) {
        if (this.pane != null)
            remove(this.pane);
        user = company.getManager();
        showResume(user);
        setVisible(true);
    }

    public void showResume(Consumer user) {
        if (this.pane != null)
            remove(this.pane);
        panel_aux = new JPanel(new GridLayout(7, 1, 5, 10));
        panel_aux.setBackground(new Color(200, 200, 150));
        JLabel lastname = new JLabel("LastName:");
        JLabel firstname = new JLabel("FirstName:");
        JLabel email = new JLabel("Email:");
        JLabel phone = new JLabel("Phone:");
        JLabel sex = new JLabel("Sex:");
        JLabel date = new JLabel("Birth date:");
        JLabel language = new JLabel("Languages:");
        System.out.println(user);
        JLabel lastname_result = new JLabel(user.getResume().getInformation().getLastname());
        JLabel firstname_result = new JLabel(user.getResume().getInformation().getFirstname());
        JLabel email_result = new JLabel(user.getResume().getInformation().getEmail());
        JLabel phone_result = new JLabel(user.getResume().getInformation().getPhone_number());
        JLabel sex_result = new JLabel(user.getResume().getInformation().getSex());
        JLabel date_result = new JLabel(user.getResume().getInformation().getBirth_date().toString());
        String aux = "";
        for (int i = 0; i < user.getResume().getInformation().getLanguages().size(); i++) {
            aux += user.getResume().getInformation().getLanguages().get(i).getName() + "(" +
                    user.getResume().getInformation().getLanguages().get(i).getLevel() + ") ";
        }
        JLabel language_result = new JLabel(aux);
        panel_aux.add(lastname);
        panel_aux.add(lastname_result);
        panel_aux.add(firstname);
        panel_aux.add(firstname_result);
        panel_aux.add(email);
        panel_aux.add(email_result);
        panel_aux.add(sex);
        panel_aux.add(sex_result);
        panel_aux.add(phone);
        panel_aux.add(phone_result);
        panel_aux.add(date);
        panel_aux.add(date_result);
        panel_aux.add(language);
        panel_aux.add(language_result);
        remove(panel);
        add(panel_aux, BorderLayout.CENTER);
        setVisible(true);
    }

    public void showEducation(Consumer user) {
        if (this.pane != null)
            remove(this.pane);
        JScrollPane pane;
        JList list = new JList();
        DefaultListModel model = new DefaultListModel();
        list = new JList(model);
        pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(460, 450));
        remove(panel);
        add(pane, BorderLayout.CENTER);
        for (int i = 0; i < user.getResume().getEducation().size(); i++) {
            model.addElement(user.getResume().getEducation().get(i));
        }
        this.pane = pane;
        setVisible(true);
    }

    public void showExperience(Consumer user) {
        if (this.pane != null)
            remove(this.pane);
        JScrollPane pane;
        JList list = new JList();
        DefaultListModel model = new DefaultListModel();
        list = new JList(model);
        pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(460, 450));
        remove(panel);
        add(pane, BorderLayout.CENTER);
        for (int i = 0; i < user.getResume().getExperience().size(); i++) {
            model.addElement(user.getResume().getExperience().get(i));
        }
        this.pane = pane;
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if (button.getText().equals("Resume")) {
            showResume(user);
        }
        if (button.getText().equals("Education")) {
            showEducation(user);
        }
        if (button.getText().equals("Experience")) {
            showExperience(user);
        }
    }
}

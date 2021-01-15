package app.gui;

import app.architecture.Application;
import app.info.Date;
import app.info.Education;
import app.info.Experience;
import app.info.Information;
import app.user.Consumer;
import app.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class EducationWin extends JFrame implements ActionListener {
    private JLabel institution;
    private JLabel level;
    private JLabel begindate;
    private JLabel enddate;
    private JLabel grade;
    private JTextField institution_field;
    private JTextField level_field;
    private JTextField begindate_field;
    private JTextField enddate_field;
    private JTextField grade_field;
    private JButton addedu;
    private JButton next;
    private ArrayList<Education> list;
    private ArrayList<Education> education;
    private Information information;
//    private User user;
    private Consumer.Resume resume;

    public EducationWin(Consumer.Resume resume) {
        super("We are hiring");
//        Application.getInstance().add(user);
//        Application.getInstance().print_user();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(700, 700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(200, 200, 150));

        JPanel panel_east = new JPanel(new FlowLayout());
        JPanel panel_west = new JPanel(new FlowLayout());
        JPanel panel_north = new JPanel(new FlowLayout());
        JPanel panel_south = new JPanel(new FlowLayout());
        JPanel panel_center = new JPanel(new GridLayout(8, 2, 3, 40));
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
        institution = new JLabel("Institution:");
        level = new JLabel("Education level:");
        begindate = new JLabel("Begin date:");
        enddate = new JLabel("End date:");
        grade = new JLabel("Grade:");
        institution_field = new JTextField();
        level_field = new JTextField();
        begindate_field = new JTextField();
        enddate_field = new JTextField();
        grade_field = new JTextField();
        begindate_field.setText("DD/MM/YYYY");
        enddate_field.setText("DD/MM/YYYY");
        addedu = new JButton("Add Education");
        next = new JButton("Next Step");
        panel_center.add(institution);
        panel_center.add(institution_field);
        panel_center.add(level);
        panel_center.add(level_field);
        panel_center.add(begindate);
        panel_center.add(begindate_field);
        panel_center.add(enddate);
        panel_center.add(enddate_field);
        panel_center.add(grade);
        panel_center.add(grade_field);
        panel_center.add(addedu);
        panel_center.add(next);
        addedu.addActionListener(this);
        next.addActionListener(this);

        list = new ArrayList<>();
        education = new ArrayList<>();
//        information = info;
//        this.user = user;
        resume.setEducation(education);
        this.resume = resume;

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).getText().equals("Next Step")) {
            ExperienceWin experience = new ExperienceWin(resume);
            setVisible(false);
        }
        if (((JButton)e.getSource()).getText().equals("Add Education")) {
            Education edu = new Education();
            edu.setInstitution(institution_field.getText());
            edu.setGrade(Double.parseDouble(grade_field.getText()));
            edu.setEducation_level(level_field.getText());

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
            edu.setBegin_date(new Date(Year, Month, Day));

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
                edu.setEnd_date(new Date(Year, Month, Day));
            } else {
                edu.setEnd_date(null);
            }
            resume.addEducation(edu);
        }
    }
}

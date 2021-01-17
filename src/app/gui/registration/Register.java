package app.gui.registration;

import app.info.Date;
import app.info.Information;
import app.user.Consumer;
import app.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class Register extends JFrame implements ActionListener {
    private JButton step;
    private JTextField lastname_text;
    private JTextField firstname_text;
    private JTextField email_text;
    private JTextField phone_text;
    private JTextField birthdate;
    private JButton addLanguage;
    private JComboBox<String> sex_box;
    private JTextField languages_text;
    private ArrayList<Information.Language> languages_list;
    private Information info;

    public Register() {
        super("We are Hiring");
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

        JLabel lastname = new JLabel("Lastname:");
        JLabel firstname = new JLabel("Firstname:");
        JLabel email = new JLabel("email:");
        JLabel phone = new JLabel("Phone:");
        JLabel birth_date = new JLabel("Birth Date:");
        JLabel sex = new JLabel("Sex:");
        JLabel languages = new JLabel("Languages:");
        lastname_text = new JTextField();
        firstname_text = new JTextField();
        email_text = new JTextField();
        phone_text = new JTextField();
        birthdate = new JTextField();
        birthdate.setText("DD/MM/YYYY");
        Vector<String> list = new Vector<>();
        list.add("male");
        list.add("female");
        sex_box = new JComboBox<String>(list);
        languages_text = new JTextField();
        languages_text.setText("Language/Level (Beginner/Intermediate/Advanced)");
        addLanguage = new JButton("Add Language");
        panel_center.add(lastname);
        panel_center.add(lastname_text);
        panel_center.add(firstname);
        panel_center.add(firstname_text);
        panel_center.add(email);
        panel_center.add(email_text);
        panel_center.add(phone);
        panel_center.add(phone_text);
        panel_center.add(birth_date);
        panel_center.add(birthdate);
        panel_center.add(sex);
        panel_center.add(sex_box);
        panel_center.add(languages);
        panel_center.add(languages_text);
        panel_center.add(addLanguage);

        step = new JButton("Next Step");
        panel_south.add(step);

        lastname_text.addActionListener(this);
        languages_text.addActionListener(this);
        firstname_text.addActionListener(this);
        email_text.addActionListener(this);
        phone_text.addActionListener(this);
        birthdate.addActionListener(this);
        addLanguage.addActionListener(this);
        step.addActionListener(this);

        languages_list = new ArrayList<>();
        info = new Information();

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).getText().equals("Add Language")) {
            String aux = languages_text.getText();
            Information.Language lang = new Information.Language(aux.substring(0, aux.indexOf('/')),
                    aux.substring(aux.indexOf('/') + 1));
            if (!languages_list.contains(lang))
                languages_list.add(lang);
        }
        if (((JButton)e.getSource()).getText().equals("Next Step")) {
            Consumer user = new User();
            Consumer.Resume resume = new Consumer.Resume(new Consumer.Resume.ResumeBuilder());
            info.setPhone_number(phone_text.getText());
            info.setSex(sex_box.getSelectedItem().toString());
            info.setEmail(email_text.getText());
            info.setFirstname(firstname_text.getText());
            info.setLastname(lastname_text.getText());
            info.setLanguages(languages_list);

            String aux_date = birthdate.getText();
            String day, month, year;
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
            info.setBirth_date(new Date(Year, Month, Day));
            resume.setInformation(info);
            EducationWin education = new EducationWin(resume);
            setVisible(false);
        }
    }
}

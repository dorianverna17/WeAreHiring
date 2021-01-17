package app.gui.registration;

import app.architecture.Application;
import app.company.Company;
import app.gui.Main;
import app.user.Employee;
import app.user.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterCompany extends JFrame implements ActionListener {
    private JLabel company;
    private JLabel salary;
    private JTextField company_field;
    private JTextField salary_field;
    private JButton finish;
    private Manager manager;

    public RegisterCompany(Manager manager) {
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
        company = new JLabel("Company Name:");
        salary = new JLabel("Your Salary:");
        company_field = new JTextField();
        salary_field = new JTextField();
        finish = new JButton("Finish Registration");
        finish.addActionListener(this);
        panel_center.add(company);
        panel_center.add(company_field);
        panel_center.add(salary);
        panel_center.add(salary_field);
        panel_south.add(finish);
        this.manager = manager;
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Company comp = new Company();
        comp.setManager(manager);
        comp.setName(company_field.getText());
        Application app = Application.getInstance();
        app.add(comp);
        setVisible(false);
        Main main = new Main();
    }
}

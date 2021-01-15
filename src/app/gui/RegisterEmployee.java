package app.gui;

import app.architecture.Application;
import app.company.*;
import app.user.Employee;
import app.user.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterEmployee extends JFrame implements ActionListener {
    private JLabel company;
    private JLabel salary;
    private JLabel department;
    private JTextField company_field;
    private JTextField salary_field;
    private JTextField department_field;
    private JButton finish;
    private Employee emp;

    public RegisterEmployee(Employee employee) {
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
        department = new JLabel("Your Department:");
        salary = new JLabel("Your Salary:");
        company_field = new JTextField();
        salary_field = new JTextField();
        department_field = new JTextField();
        finish = new JButton("Finish Registration");
        finish.addActionListener(this);
        panel_center.add(company);
        panel_center.add(company_field);
        panel_center.add(department);
        panel_center.add(department_field);
        panel_center.add(salary);
        panel_center.add(salary_field);
        panel_south.add(finish);
        emp = employee;
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getText().equals("Finish Registration")) {
            Application app = Application.getInstance();
            Company comp = app.getCompany(company_field.getText());
            Department dep = null;
            dep = comp.getDepartment(department_field.getText());
            emp.setSalary(Double.parseDouble(salary_field.getText()));
            emp.setCompany(comp.getName());
            comp.add(emp, dep);
        }
        Main main = new Main();
        setVisible(false);
    }
}

package app.gui.registration;

import app.gui.Main;
import app.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterInterests extends JFrame implements ActionListener {
    private User user;
    private JButton button;
    private JButton register;
    private JLabel label;
    private JTextField field;
    private ArrayList<String> interests;

    public RegisterInterests(User user) {
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
        this.user = user;
        label = new JLabel("Company:");
        field = new JTextField();
        panel_center.add(label);
        panel_center.add(field);
        button = new JButton("Add interest");
        panel_south.add(button);
        button.addActionListener(this);
        interests = new ArrayList<>();
        register = new JButton("Complete Registration");
        panel_south.add(register);
        register.addActionListener(this);
        interests = new ArrayList<>();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but = (JButton) e.getSource();
        if (but.getText().equals("Add interest")) {
            interests.add(field.getText());
        } else {
            user.setInterests(interests);
            setVisible(false);
            Main main = new Main();
        }
    }
}

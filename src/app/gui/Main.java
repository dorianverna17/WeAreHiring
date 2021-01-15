package app.gui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JLabel label;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JTextPane email;
    private JPasswordField password;
    private JButton login;
    private JButton signup;
    private JButton admin;

    public Main() {
        super("We are Hiring");
        setMinimumSize(new Dimension(700, 700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(200, 200, 150));
        panel1 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new FlowLayout());
        panel3 = new JPanel(new BorderLayout());
        panel4 = new JPanel(new FlowLayout());
        panel5 = new JPanel(new FlowLayout());
        panel1.setBackground(new Color(200, 200, 150));
        panel2.setBackground(new Color(200, 200, 150));
        panel3.setBackground(new Color(200, 200, 150));
        panel4.setBackground(new Color(200, 200, 150));
        panel5.setBackground(new Color(200, 200, 150));
        panel1.setPreferredSize(new Dimension(100, 500));
        panel2.setPreferredSize(new Dimension(100, 500));
        panel4.setPreferredSize(new Dimension(700, 100));
        panel5.setPreferredSize(new Dimension(700, 100));
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);
        add(panel3, BorderLayout.CENTER);
        add(panel4, BorderLayout.NORTH);
        add(panel5, BorderLayout.SOUTH);
        Font font = new Font(Font.DIALOG_INPUT,  Font.BOLD, 45);
        label = new JLabel("WE ARE HIRING");
        label.setFont(font);

        JPanel panel_aux1 = new JPanel();
        JPanel panel_aux2 = new JPanel(new BorderLayout());
        JPanel panel3_aux = new JPanel();
        JPanel panel4_aux = new JPanel();
        JPanel panel5_aux = new JPanel(new GridLayout(7, 1, 5,20));
        JPanel panel6_aux = new JPanel();
        JLabel label1 = new JLabel("email");
        JLabel label2 = new JLabel("password");
        panel3.add(panel_aux1, BorderLayout.NORTH);
        panel3.add(panel_aux2, BorderLayout.CENTER);
        panel3.add(panel6_aux, BorderLayout.SOUTH);
        panel_aux2.add(panel3_aux, BorderLayout.EAST);
        panel_aux2.add(panel4_aux, BorderLayout.WEST);
        panel_aux2.add(panel5_aux, BorderLayout.CENTER);
        panel_aux1.setPreferredSize(new Dimension(100, 90));
        panel6_aux.setPreferredSize(new Dimension(100, 100));
        panel3_aux.setPreferredSize(new Dimension(150, 200));
        panel4_aux.setPreferredSize(new Dimension(150, 200));
        panel5_aux.setPreferredSize(new Dimension(200, 250));
        panel_aux1.add(label);
        label.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        email = new JTextPane();
        password = new JPasswordField();
        login = new JButton("Log In");
        signup = new JButton("Sign Up");
        admin = new JButton("Log In as Admin");
        panel5_aux.add(label1);
        panel5_aux.add(email);
        panel5_aux.add(label2);
        panel5_aux.add(password);
        panel5_aux.add(login);
        panel5_aux.add(signup);
        panel5_aux.add(admin);
        panel_aux1.setBackground(new Color(200, 200, 150));
        panel_aux2.setBackground(new Color(200, 200, 150));
        panel3_aux.setBackground(new Color(200, 200, 150));
        panel4_aux.setBackground(new Color(200, 200, 150));
        panel5_aux.setBackground(new Color(200, 200, 150));
        panel6_aux.setBackground(new Color(200, 200, 150));

        StyledDocument doc = email.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, true);

        signup.addActionListener(this);
        login.addActionListener(this);
        admin.addActionListener(this);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if (button.getText().equals("Sign Up")) {
            setVisible(false);
            email.setText("");
            password.setText("");
            Register register_frame = new Register();
        }
        if (button.getText().equals("Log In as Admin")) {
            setVisible(false);
            AdminPage page = new AdminPage();
        }
    }
}

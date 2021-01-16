package app.gui.user;

import app.architecture.Application;
import app.gui.Main;
import app.gui.ShowDetails;
import app.info.Job;
import app.user.Consumer;
import app.user.User;

import javax.swing.*;
import java.awt.*;

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
        if (consumer instanceof User) {
            apply_button = new ApplyButton(this);
            job_button = new GetJobsButton(this);
            panel_west.add(apply_button);
            panel_west.add(job_button);
        }

        loggedconsumer = consumer;
        frame.setVisible(true);
    }

    public void getToMainScreen() {
        frame.dispose();
        Main main = new Main();
    }

    @Override
    public void showDetailsWin() {
        String aux = panellist.getSelectedValue().toString();
        Consumer consumer = Application.getInstance().getConsumer(aux);
        ShowDetails details = new ShowDetails(consumer);
    }

    @Override
    public void applyConsumer() {
        Job job = Application.getInstance().findJob((String)panellist.getSelectedValue());
        System.out.println(job);
        if (job != null)
            job.apply((User) loggedconsumer);
    }

    @Override
    public void listJobs() {
        JobList list = new JobList(loggedconsumer, this);
        panellist.replaceList(list);
    }
}

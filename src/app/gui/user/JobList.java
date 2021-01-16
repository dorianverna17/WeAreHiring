package app.gui.user;

import app.architecture.Application;
import app.info.Job;
import app.user.Consumer;
import app.user.User;

import javax.swing.*;
import java.util.ArrayList;

public class JobList extends JList {
    MediatorConsumer mediator;

    public JobList(Consumer consumer, MediatorConsumer mediator) {
        super();
        this.mediator = mediator;
        User user = (User) consumer;
        ArrayList<Job> list = Application.getInstance().getJobs(user.getInterests());
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < list.size(); i++) {
            model.addElement(list.get(i).getCompany() + " " + list.get(i).getName());
        }
        this.setModel(model);
    }
}

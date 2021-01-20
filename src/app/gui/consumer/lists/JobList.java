package app.gui.consumer.lists;

import app.architecture.Application;
import app.company.Company;
import app.gui.consumer.MediatorConsumer;
import app.info.Job;
import app.user.Consumer;
import app.user.User;

import javax.swing.*;
import java.util.ArrayList;

// lista joburilor disponibile
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

    public JobList(Company company, MediatorConsumer mediator) {
        super();
        this.mediator = mediator;
        ArrayList<Job> list = company.getJobs();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < list.size(); i++) {
            model.addElement(list.get(i).getCompany() + " " + list.get(i).getName());
        }
        this.setModel(model);
    }
}

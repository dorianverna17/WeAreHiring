package app.gui.consumer.lists;

import app.gui.consumer.MediatorConsumer;
import app.info.Job;
import app.info.Request;
import app.user.Recruiter;
import app.user.User;

import javax.swing.*;
import java.util.ArrayList;

public class RequestList extends JList {
    private MediatorConsumer mediator;

    public RequestList(MediatorConsumer mediator, ArrayList<Request> requests) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < requests.size(); i++) {
            model.addElement(((User) requests.get(i).getValue1()).getResume().getInformation().getLastname() + " " +
                    ((User) requests.get(i).getValue1()).getResume().getInformation().getFirstname() + " - " +
                    ((Double) requests.get(i).getScore()).toString() + " - " +
                    ((Job) requests.get(i).getKey()).getName());
        }
        this.setModel(model);
    }
}

package app.gui.consumer.lists;

import app.gui.consumer.MediatorConsumer;
import app.info.Job;
import app.info.Request;
import app.user.Recruiter;
import app.user.User;

import javax.swing.*;
import java.util.ArrayList;

// clasa pentru a lista requesturile
public class RequestList extends JList {
    private MediatorConsumer mediator;

    public RequestList(MediatorConsumer mediator, ArrayList<Request> requests) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        User user;
        for (int i = 0; i < requests.size(); i++) {
            user = (User) requests.get(i).getValue1();
            model.addElement(user.getResume().getInformation().getLastname() + " " +
                    user.getResume().getInformation().getFirstname() + " - " +
                    ((Double) requests.get(i).getScore()).toString() + " - " +
                    ((Job) requests.get(i).getKey()).getName());
        }
        this.setModel(model);
    }
}

package app.gui.consumer.lists;

import app.gui.consumer.MediatorConsumer;
import app.info.Job;
import app.user.Pair;
import app.user.User;

import javax.swing.*;
import java.util.ArrayList;

public class EvaluationList extends JList {
    private MediatorConsumer mediator;

    public EvaluationList(MediatorConsumer mediator, ArrayList<Pair<User, Job>> list) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < list.size(); i++) {
            model.addElement(list.get(i).value1.getResume().getInformation().getLastname() + " " +
                    list.get(i).value1.getResume().getInformation().getFirstname() + " - " +
                    list.get(i).value2.getName());
        }
        setModel(model);
    }
}

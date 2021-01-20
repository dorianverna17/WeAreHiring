package app.gui.consumer.lists;

import app.gui.consumer.MediatorConsumer;
import app.info.Job;
import app.user.Pair;
import app.user.User;

import javax.swing.*;
import java.util.ArrayList;

// lista celor care trebuie evaluati de catre recruiter
public class EvaluationList extends JList {
    private MediatorConsumer mediator;

    public EvaluationList(MediatorConsumer mediator, ArrayList<Pair<User, Job>> list) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        User user;
        for (int i = 0; i < list.size(); i++) {
            user = list.get(i).getValue1();
            model.addElement(user.getResume().getInformation().getLastname() + " " +
                    user.getResume().getInformation().getFirstname() + " - " +
                    list.get(i).getValue2().getName());
        }
        setModel(model);
    }
}

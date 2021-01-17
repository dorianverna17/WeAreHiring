package app.gui.consumer.lists;

import app.gui.consumer.MediatorConsumer;
import app.user.User;

import javax.swing.*;
import java.util.ArrayList;

public class NotificationList extends JList {
    private MediatorConsumer mediator;

    public NotificationList(User user, MediatorConsumer mediator) {
        super();
        this.mediator = mediator;
        ArrayList<String> notes = user.getNotifications();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < notes.size(); i++) {
            model.addElement(notes.get(i));
        }
        this.setModel(model);
    }
}

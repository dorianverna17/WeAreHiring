package app.gui.user;

import app.user.Consumer;

import javax.swing.*;
import java.util.ArrayList;

public class PanelList extends JList {
    MediatorConsumer mediator;

    public PanelList(Consumer consumer, MediatorConsumer mediator) {
        super();
        this.mediator = mediator;
        ArrayList<Consumer> list_aux = consumer.getContacts();
        ArrayList<String> list = new ArrayList<>();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < list_aux.size(); i++) {
            model.addElement(list_aux.get(i).getResume().getInformation().getLastname() + " " +
                    list_aux.get(i).getResume().getInformation().getFirstname());
        }
        this.setModel(model);
    }

    public void replaceList(JobList list) {
        DefaultListModel model = (DefaultListModel) list.getModel();
        this.setModel(model);
    }
}

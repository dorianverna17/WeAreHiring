package app.gui.consumer.lists;

import app.company.Department;
import app.gui.consumer.MediatorConsumer;
import app.user.Consumer;

import javax.swing.*;
import java.util.ArrayList;

// lista cu contactele (e numita panellist deoarece peste ea suprapun
// listele pe care le voi folosi mai departe in interfata grafica - pentru
//  asta am metoda replacelist). Remove element este folosita pentru a
// sterge un element din lista
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

    public void replaceList(JList list) {
        DefaultListModel model = (DefaultListModel) list.getModel();
        this.setModel(model);
    }

    public void removeElement() {
        int i = getSelectedIndex();
        DefaultListModel model = (DefaultListModel) getModel();
        model.remove(i);
    }
}

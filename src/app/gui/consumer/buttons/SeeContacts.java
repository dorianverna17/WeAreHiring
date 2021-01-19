package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SeeContacts extends JButton {
    private MediatorConsumer mediator;

    public SeeContacts(MediatorConsumer mediator) {
        super("See Contacts");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.seeContacts();
    }
}

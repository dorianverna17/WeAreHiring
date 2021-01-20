package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Butonul prin care se muta un angajat in departamentul Management
public class MoveToManagement extends JButton {
    private MediatorConsumer mediator;

    public MoveToManagement(MediatorConsumer mediator) {
        super("To Management");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.moveTo("Management");
    }
}

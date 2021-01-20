package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Butonul prin care se muta un angajat in departamentul Finance
public class MoveToFinance extends JButton {
    private MediatorConsumer mediator;

    public MoveToFinance(MediatorConsumer mediator) {
        super("To Finance");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.moveTo("Finance");
    }
}

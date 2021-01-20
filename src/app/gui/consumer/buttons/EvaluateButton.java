package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// butonul de evaluate folosit de recruiteri
public class EvaluateButton extends JButton {
    private MediatorConsumer mediator;

    public EvaluateButton(MediatorConsumer mediator) {
        super("Evaluate");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.evaluateUser();
    }
}

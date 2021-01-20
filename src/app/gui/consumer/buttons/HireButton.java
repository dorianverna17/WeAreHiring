package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// butonul folosit pentru a angaja
public class HireButton extends JButton {
    private MediatorConsumer mediator;

    public HireButton(MediatorConsumer mediator) {
        super("Hire");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.hire();
    }
}

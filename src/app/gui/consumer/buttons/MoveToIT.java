package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveToIT extends JButton {
    private MediatorConsumer mediator;

    public MoveToIT(MediatorConsumer mediator) {
        super("To IT");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.moveTo("IT");
    }
}

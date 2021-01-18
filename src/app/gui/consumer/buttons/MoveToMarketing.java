package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveToMarketing extends JButton {
    private MediatorConsumer mediator;

    public MoveToMarketing(MediatorConsumer mediator) {
        super("To Marketing");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.moveTo("Marketing");
    }
}

package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RequestsButton extends JButton {
    private MediatorConsumer mediator;

    public RequestsButton(MediatorConsumer mediator) {
        super("Requests");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.listRequests();
    }
}

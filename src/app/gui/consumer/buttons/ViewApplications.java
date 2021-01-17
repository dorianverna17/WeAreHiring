package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewApplications extends JButton {
    private MediatorConsumer mediator;

    public ViewApplications(MediatorConsumer mediator) {
        super("Users to evaluate");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.listApplications();
    }
}

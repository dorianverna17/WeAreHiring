package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// butonul folosit pentru a vedea userii ce au aplicat pentru un
// anume job
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

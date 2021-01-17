package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GetJobsButton extends JButton {
    private MediatorConsumer mediator;

    public GetJobsButton(MediatorConsumer mediator) {
        super("Search Jobs");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.listJobs();
    }
}

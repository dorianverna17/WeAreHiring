package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;
import app.user.Recruiter;

import javax.swing.*;
import java.awt.event.ActionEvent;

// butonul folosit pentru a arata recruiterii
public class RecruitersButton extends JButton {
    private MediatorConsumer mediator;

    public RecruitersButton(MediatorConsumer mediator) {
        super("Recruiters");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.listRecruiters();
    }
}

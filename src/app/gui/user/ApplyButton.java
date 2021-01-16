package app.gui.user;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ApplyButton extends JButton {
    private MediatorConsumer mediator;

    public ApplyButton(MediatorConsumer mediator) {
        super("Apply");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.applyConsumer();
    }
}

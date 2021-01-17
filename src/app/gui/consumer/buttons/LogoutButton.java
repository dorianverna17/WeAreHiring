package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LogoutButton extends JButton {
    private MediatorConsumer mediator;

    public LogoutButton(MediatorConsumer mediator) {
        super("Log Out");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.getToMainScreen();
    }
}

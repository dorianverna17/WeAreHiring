package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// butonul prin care se afiseaza notificarile unui anume user
public class NotificationsButton extends JButton {
    private MediatorConsumer mediator;

    public NotificationsButton(String text, MediatorConsumer mediator) {
        super(text);
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.listNotifications();
    }
}

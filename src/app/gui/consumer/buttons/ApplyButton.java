package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Butonul folosit de un user pentru a aplica la o companie
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

package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DetailsButton extends JButton {
    private MediatorConsumer mediator;

    public DetailsButton(MediatorConsumer mediator) {
        super("Details");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.showDetailsWin();
    }
}

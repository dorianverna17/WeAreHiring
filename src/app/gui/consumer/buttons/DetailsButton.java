package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

// butonul folosit de un user pentru a obtine detalii referitoare
// la un user, employee, recruiter sau manager
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

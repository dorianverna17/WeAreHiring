package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SeeEmployees extends JButton {
    private MediatorConsumer mediator;

    public SeeEmployees(MediatorConsumer mediator) {
        super("Employees");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.listEmployees();
    }
}

package app.gui.consumer.buttons;

import app.gui.consumer.MediatorConsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyCompanyButton extends JButton {
    private MediatorConsumer mediator;

    public MyCompanyButton(MediatorConsumer mediator) {
        super("My Company");
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.listDepartments();
    }
}

package app.gui.consumer;

import app.user.Consumer;

import javax.swing.*;

// panelul in care pun listele
public class ScrollablePanel extends JScrollPane {
    private MediatorConsumer mediator;

    public ScrollablePanel(JList list1, Consumer consumer, MediatorConsumer mediator) {
        super(list1);
        this.mediator = mediator;
    }
}

package app.gui.user;

import app.user.Consumer;

import javax.swing.*;
import java.util.ArrayList;

public class ScrollablePanel extends JScrollPane {
    private MediatorConsumer mediator;

    public ScrollablePanel(JList list1, Consumer consumer, MediatorConsumer mediator) {
        super(list1);
        this.mediator = mediator;
    }
}

package app.gui.consumer.lists;

import app.company.Department;
import app.gui.consumer.MediatorConsumer;
import app.user.Employee;

import javax.swing.*;

public class EmployeeList extends JList {
    private MediatorConsumer mediator;

    public EmployeeList(MediatorConsumer mediator, Department department) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < department.getEmployees().size(); i++) {
            model.addElement(department.getEmployees().get(i).getResume().getInformation().getLastname() + " " +
                    department.getEmployees().get(i).getResume().getInformation().getFirstname());
        }
        setModel(model);
    }
}

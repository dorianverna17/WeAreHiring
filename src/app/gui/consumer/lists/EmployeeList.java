package app.gui.consumer.lists;

import app.company.Department;
import app.gui.consumer.MediatorConsumer;
import app.user.Employee;

import javax.swing.*;

// lista angajatilor
public class EmployeeList extends JList {
    private MediatorConsumer mediator;

    public EmployeeList(MediatorConsumer mediator, Department department) {
        super();
        this.mediator = mediator;
        Employee employee;
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < department.getEmployees().size(); i++) {
            employee = department.getEmployees().get(i);
            model.addElement(employee.getResume().getInformation().getLastname() +
                    " " + employee.getResume().getInformation().getFirstname());
        }
        setModel(model);
    }
}

package app.gui.consumer.lists;

import app.company.Company;
import app.company.Department;
import app.gui.consumer.MediatorConsumer;
import app.user.Consumer;
import app.user.Manager;

import javax.swing.*;

// lista departamentelor
public class DepartmentList extends JList {
    private MediatorConsumer mediator;

    public DepartmentList(MediatorConsumer mediator, Company company, Consumer consumer) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        Department department;
        for (int i = 0; i < company.getDepartments().size(); i++) {
            department = company.getDepartments().get(i);
            int index = department.getClass().toString().lastIndexOf('.');
            String dep = department.getClass().toString().substring(index + 1);
            if (consumer instanceof Manager)
                model.addElement(dep + ", salary budget: " +
                        company.getDepartment(dep).getTotalSalaryBudget());
            else
                model.addElement(dep);
        }
        setModel(model);
    }
}

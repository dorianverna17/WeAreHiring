package app.gui.consumer.lists;

import app.company.Company;
import app.gui.consumer.MediatorConsumer;
import app.user.Consumer;
import app.user.Manager;

import javax.swing.*;

public class DepartmentList extends JList {
    private MediatorConsumer mediator;

    public DepartmentList(MediatorConsumer mediator, Company company, Consumer consumer) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < company.getDepartments().size(); i++) {
            int index = company.getDepartments().get(i).getClass().toString().lastIndexOf('.');
            String dep = company.getDepartments().get(i).getClass().toString().substring(index + 1);
            if (consumer instanceof Manager)
                model.addElement(dep + ", salary budget: " + company.getDepartment(dep).getTotalSalaryBudget());
            else
                model.addElement(dep);
        }
        setModel(model);
    }
}

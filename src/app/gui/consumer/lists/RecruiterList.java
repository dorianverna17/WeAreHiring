package app.gui.consumer.lists;

import app.architecture.Application;
import app.company.Company;
import app.gui.consumer.MediatorConsumer;
import app.user.Manager;

import javax.swing.*;

public class RecruiterList extends JList {
    private MediatorConsumer mediator;

    public RecruiterList(MediatorConsumer mediator, Manager manager) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        Company company = Application.getInstance().getCompany(manager.getCompany());
        for (int i = 0; i < company.getRecruiters().size(); i++) {
            model.addElement(company.getRecruiters().get(i).getResume().getInformation().getLastname() + " " +
                    company.getRecruiters().get(i).getResume().getInformation().getFirstname() + " " +
                    company.getRecruiters().get(i).getRating());
        }
        setModel(model);
    }
}

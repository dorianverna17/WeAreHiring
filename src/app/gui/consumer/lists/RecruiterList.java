package app.gui.consumer.lists;

import app.architecture.Application;
import app.company.Company;
import app.gui.consumer.MediatorConsumer;
import app.user.Manager;
import app.user.Recruiter;

import javax.swing.*;

// clasa pentru a lista recruiterii
public class RecruiterList extends JList {
    private MediatorConsumer mediator;

    public RecruiterList(MediatorConsumer mediator, Manager manager) {
        super();
        this.mediator = mediator;
        DefaultListModel model = new DefaultListModel();
        Recruiter recruiter;
        Company company = Application.getInstance().getCompany(manager.getCompany());
        for (int i = 0; i < company.getRecruiters().size(); i++) {
            recruiter = company.getRecruiters().get(i);
            model.addElement(recruiter.getResume().getInformation().getLastname() +
                    " " + recruiter.getResume().getInformation().getFirstname() +
                    " " + company.getRecruiters().get(i).getRating());
        }
        setModel(model);
    }
}

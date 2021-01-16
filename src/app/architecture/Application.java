package app.architecture;

import app.company.Company;
import app.info.Job;
import app.user.Consumer;
import app.user.User;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static Application app;
    private ArrayList<Company> companies;
    private ArrayList<User> users;

    // constructorul pentru singleton
    private Application() {
        companies = new ArrayList<Company>();
        users = new ArrayList<User>();
    }

    // metoda getInstance pentru Singleton
    public static synchronized Application getInstance() {
        if (app == null)
            app = new Application();
        return app;
    }

    // determinarea companiilor care au fost inscrise in aplicatie
    public ArrayList<Company> getCompanies() {
        return companies;
    }

    // setter pentru lista de companii
    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    // getter pentru lista de useri
    public ArrayList<User> getUsers() {
        return users;
    }

    // setter pentru lista de useri
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    // determinarea unei anumite companii in functie de numele furnizat
    public Company getCompany(String name) {
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getName().equals(name))
                return companies.get(i);
        }
        return null;
    }

    // determinarea unui anumit user in functie de numele furnizat
    // (nume + prenume)
    public User getUser(String name) {
        String aux1, aux2;
        for (int i = 0; i < users.size(); i++) {
            aux1 = users.get(i).getResume().getInformation().getLastname();
            aux2 = users.get(i).getResume().getInformation().getFirstname();
            if ((aux1 + " " + aux2).equals(name))
                return users.get(i);
        }
        return null;
    }

    // adaugarea unei companii
    public void add(Company company) {
        if (!companies.contains(company)) {
            companies.add(company);
        }
    }

    //adaugarea unui utilizator
    public void add(User user) {
        if (!users.contains(user)) {
            users.add(user);
            for (int i = 0; i < user.getInterests().size(); i++) {
                getInstance().getCompany(user.getInterests().get(i)).addObject(user);
            }
        }
    }

    // Stergerea unei companii
    public boolean remove(Company company) {
        if (companies.contains(company)) {
            companies.remove(company);
            return true;
        }
        return false;
    }

    // Stergerea unui utilizator
    public boolean remove(User user) {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        }
        return false;
    }

    // Determinarea joburilor disponibile de la companiile
    // pe care le prefera utilizatorul
    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> jobs = new ArrayList<>();
        Company aux;
        for (int i = 0; i < companies.size(); i++) {
            aux = null;
            for (int j = 0; j < this.companies.size(); j++) {
                if (companies.get(i).equals(this.companies.get(j).getName())) {
                    aux = this.companies.get(j);
                }
            }
            if (aux != null) {
                for (int j = 0; j < aux.getJobs().size(); j++) {
                    jobs.add(aux.getJobs().get(j));
                }
            }
        }
        return jobs;
    }

    // functie auxiliara pe care am folosit-o pentru printarea user-ilor
    public void print_user() {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getResume().toString());
        }
    }

    // obtinerea unui consumer pe baza numelui
    public Consumer getConsumer(String name) {
        String aux1, aux2;
        for (int i = 0; i < getInstance().getUsers().size(); i++) {
            aux1 = getInstance().getUsers().get(i).getResume().getInformation().getLastname();
            aux2 = getInstance().getUsers().get(i).getResume().getInformation().getFirstname();
            if ((aux1 + " " + aux2).equals(name))
                return getInstance().getUsers().get(i);
        }
        for (int i = 0; i < getInstance().getCompanies().size(); i++) {
            if (getInstance().getCompanies().get(i).getEmployee(name) != null)
                return getInstance().getCompanies().get(i).getEmployee(name);
        }
        return null;
    }

    public Job findJob(String selectedValue) {
        int index = selectedValue.indexOf(' ');
        String company_name = selectedValue.substring(0, index);
        String job_name = selectedValue.substring(index + 1);
        System.out.println(job_name);
        Company comp = Application.getInstance().getCompany(company_name);
        Job job = null;
        for (int i = 0; i < comp.getDepartments().size(); i++) {
            job = comp.getDepartments().get(i).getJob(job_name);
            if (job != null)
                return job;
        }
        return null;
    }
}

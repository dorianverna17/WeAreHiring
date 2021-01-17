package app.user;

import app.architecture.Application;
import app.info.Job;
import app.info.Request;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Recruiter extends Employee {
    private double rating;
    private int number_evaluations;
    private ArrayList<Pair<User, Job>> evaluated_users;

    public Recruiter() {
        evaluated_users = new ArrayList<>();
        number_evaluations = 0;
        rating = 5;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int evaluate(Job job, User user) {
        double result = 0;
        if (job.meetsRequirments(user)) {
            result = (getRating() * user.getTotalScore());
            for (int i = 0; i < Application.getInstance().getCompanies().size(); i++) {
                if (Application.getInstance().getCompanies().get(i).getName().equals(job.getCompany())) {
                    Application.getInstance().getCompanies().get(i).getManager().addRequest(
                            new Request<Job, Consumer>(job, user, this, result));
                }
            }
        } else {
            Notification notification = new Notification(job.getCompany(), "Ati fost respins de la job-ul pentru " +
                    "pozitia de " + job.getName() + " (nu indepliniti constrangerile)");
            Application.getInstance().getCompany(job.getCompany()).notifyObserver(notification, user);
        }
        number_evaluations++;
        setRating(5 + 0.1 * number_evaluations);
        return (int) result;
    }

    // adaugarea unui user din lista de Useri de evaluat
    public void addUserToEvaluate(Pair<User, Job> pair) {
        evaluated_users.add(pair);
    }

    public ArrayList<Pair<User, Job>> getEvaluatedUsers() {
        return evaluated_users;
    }
}

package app.company;

import app.user.Notification;
import app.user.User;

// interfata pe care o folosesc pentru a implementa Observer
public interface Subject {
    public void addObject(User user);
    public void removeObserver(User c);
    public void notifyAllObserver(Notification notification);
    public void notifyObserver(Notification notification, User user);
}

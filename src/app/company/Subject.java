package app.company;

import app.user.Notification;
import app.user.User;

public interface Subject {
    public void addObject(User user);
    public void removeObserver(User c);
    public void notifyAllObserver(Notification notification);
    public void notifyObserver(Notification notification, User user);
}

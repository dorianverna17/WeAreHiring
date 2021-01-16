package app.gui.user;

import app.user.Consumer;

public interface MediatorConsumer {
    public void createWin(Consumer consumer);
    public void getToMainScreen();
    public void showDetailsWin();
    public void applyConsumer();
    public void listJobs();
}

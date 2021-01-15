package app.user;

public class Notification {
    private String company;
    private String text;

    public Notification(String company, String text) {
        this.company = company;
        this.text = text;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

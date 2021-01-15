package app.user;

public class Employee extends Consumer {
    private String company;
    private double salary;

    public Employee() {
        this.company = null;
        this.salary = 0;
    }

    public Employee(String company, double salary) {
        this.company = company;
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

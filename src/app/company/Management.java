package app.company;

public class Management extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (int i = 0; i < getEmployees().size(); i++) {
            total += getEmployees().get(i).getSalary() + (double) 16 / 100 * getEmployees().get(i).getSalary();
        }
        return total;
    }
}

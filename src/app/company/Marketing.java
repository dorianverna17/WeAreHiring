package app.company;

public class Marketing extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (int i = 0; i < getEmployees().size(); i++) {
            if (getEmployees().get(i).getSalary() > 5000) {
                total += (double) 10 / 100 * getEmployees().get(i).getSalary();
            } else {
                if (getEmployees().get(i).getSalary() < 3000)
                    total += getEmployees().get(i).getSalary();
                else
                    total += (double) 16 / 100 * getEmployees().get(i).getSalary();
            }
        }
        return total;
    }
}

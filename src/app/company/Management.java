package app.company;

// clasa pentru departamentul de Management
public class Management extends Department {
    // metoda care returneaza bugetul total
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (int i = 0; i < getEmployees().size(); i++) {
            total += getEmployees().get(i).getSalary() +
                    (double) 16 / 100 * getEmployees().get(i).getSalary();
        }
        return total;
    }
}

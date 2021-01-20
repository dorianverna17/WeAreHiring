package app.company;

// clasa pentru departamentul IT
public class IT extends Department {
    // metoda care returneaza bugetul
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (int i = 0; i < getEmployees().size(); i++) {
            total += getEmployees().get(i).getSalary();
        }
        return total;
    }
}

package app.company;

// clasa pentru departamentul de Marketing
public class Marketing extends Department {
    // metoda pentru a returna bugetul
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (int i = 0; i < getEmployees().size(); i++) {
            if (getEmployees().get(i).getSalary() > 5000) {
                total += getEmployees().get(i).getSalary() +
                        (double) 10 / 100 * getEmployees().get(i).getSalary();
            } else {
                if (getEmployees().get(i).getSalary() < 3000)
                    total += getEmployees().get(i).getSalary();
                else
                    total += getEmployees().get(i).getSalary() +
                            (double) 16 / 100 * getEmployees().get(i).getSalary();
            }
        }
        return total;
    }
}

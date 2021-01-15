package app.company;

import app.info.Date;
import app.info.Experience;
import app.user.Employee;

import java.util.Calendar;

public class Finance extends Department {

    // metoda care returneaza anii de experienta a unui employee in cadrul companiei
    public int getExperienceinCompany(Employee employee) {
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        int current_month = Calendar.getInstance().get(Calendar.MONTH);
        int dif_year, dif_month, total = 0;
        Date begin_date = null;
        Experience exp = null;
        for (int i = 0; i < employee.getResume().getExperience().size(); i++) {
            exp = employee.getResume().getExperience().get(i);
            if (exp.getCompany().equals(employee.getCompany()))
                if (exp.getEnd_date() == null) {
                    dif_year = current_year - exp.getBegin_date().getYear();
                    dif_month = current_month - exp.getBegin_date().getMonth();
                    total += dif_year;
                    if (dif_month >= 3)
                        total += 1;
                }
        }
        return total;
    }

    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (int i = 0; i < getEmployees().size(); i++) {
            if (getExperienceinCompany(getEmployees().get(i)) == 0) {
                total += (double) 10 / 100 * getEmployees().get(i).getSalary();
            } else {
                total += (double) 16 / 100 * getEmployees().get(i).getSalary();
            }
        }
        return total;
    }
}

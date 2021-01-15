package app.info;

public class Experience implements Comparable {
    private Date begin_date;
    private Date end_date;
    private String position;
    private String company;

    // constructorul cu parametrii
    public Experience(Date begin_date, Date end_date,
                      String position, String company) throws InvalidDatesException {
        if (end_date != null)
            if (begin_date.compareTo(end_date) >= 0)
                throw new InvalidDatesException("The begin and end date of the studies are invalid");
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.position = position;
        this.company = company;
    }

    public Experience() {
        begin_date = null;
        end_date = null;
    }

    // getter-ii si setter-ii
    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        try {
            this.begin_date = begin_date;
            if (end_date != null) {
                if (begin_date.compareTo(end_date) < 0)
                    throw new InvalidDatesException("The begin and end date of the studies are invalid");
            }
        } catch (InvalidDatesException e) {
            e.printStackTrace();
        }
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        try {
            this.end_date = end_date;
            if (end_date != null) {
                if (begin_date.compareTo(end_date) < 0)
                    throw new InvalidDatesException("The begin and end date of the studies are invalid");
            }
        } catch (InvalidDatesException e) {
            e.printStackTrace();
        }
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    // descrescator dupa data finalizarii
    // crescator dupa numele companiei
    @Override
    public int compareTo(Object o) {
        int result = 0;
        if (end_date != null && ((Experience) o).getEnd_date() != null)
            result = end_date.compareTo(((Experience) o).getEnd_date());
        else {
            return company.compareTo(((Experience) o).getCompany());
        }
        return result;
    }

    // metoda toString
    @Override
    public String toString() {
        if (end_date == null)
            return company + " - " + position + " " + begin_date + " - present";
        return company + " - " + position + " " + begin_date + " - " + end_date;
    }
}

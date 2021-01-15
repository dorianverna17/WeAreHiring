package app.info;

public class Education implements Comparable {
    private Date begin_date;
    private Date end_date;
    private String institution;
    private String education_level;
    private Double grade;

    // constructorul cu parametrii al clasei
    public Education(Date begin_date, Date end_date,
                     String institution, String education_level, Double grade) throws InvalidDatesException {
        if (begin_date.compareTo(end_date) >= 0)
            throw new InvalidDatesException("The begin and end date of the studies are invalid");
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.institution = institution;
        this.education_level = education_level;
        this.grade = grade;
    }

    // constructorul fara parametrii
    public Education() {
        begin_date = null;
        end_date = null;
        institution = null;
        education_level = null;
        grade = null;
    }

    // getter-ii si setter-ii clasei
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

    public void setEnd_date(Date end_date){
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

    // gettere si settere
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getInstitution() {
        return institution;
    }

    public String getEducation_level() {
        return education_level;
    }

    public Double getGrade() {
        return grade;
    }

    // descrescator dupa data de finalizare
    // crescator dupa data de incepere
    // descrescator dupa medie
    @Override
    public int compareTo(Object o) {
        int result = 0;
        if (end_date != null && ((Education) o).getEnd_date() != null)
            result = end_date.compareTo(((Education) o).getEnd_date());
        else {
            result = begin_date.compareTo(((Education) o).getBegin_date());
            result = -result;
        }
        if (result == 0) {
            if (this.grade > ((Education) o).grade) {
                result = -1;
            } else {
                if (this.grade < ((Education) o).grade)
                    result = 1;
                else
                    result = 0;
            }
        }
        return result;
    }

    // metoda toString
    @Override
    public String toString() {
        if (end_date == null)
            return institution + " - " + education_level + " " + begin_date + " - present GPA: " + grade;
        return institution + " - " + education_level + " " + begin_date + " - " + end_date + " GPA: " + grade;
    }
}

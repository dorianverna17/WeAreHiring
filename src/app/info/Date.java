package app.info;

// clasa care modeleaza o data
public class Date implements Comparable {
    private int year;
    private int day;
    private int month;

    // constructorul
    public Date(int year, int month, int day) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    // gettere si settere
    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    // metoda compareTo pentru a realiza sortarea
    @Override
    public int compareTo(Object o) {
        Date obj = (Date) o;
        if (this.getYear() > obj.getYear()) {
            return -1;
        } else {
           if (this.getYear() < obj.getYear()) {
               return 1;
           } else {
               if (this.getMonth() > obj.getMonth()) {
                   return -1;
               } else {
                   if (this.getMonth() < obj.getMonth())
                       return 1;
                   else {
                       if (this.getDay() > obj.getDay())
                           return -1;
                       else {
                           if (this.getDay() < obj.getDay())
                               return 1;
                       }
                   }
               }
           }
        }
        return 0;
    }

    // metoda toString uzitata pentru a efectua printarea datei
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}

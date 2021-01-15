package app.info;

import java.util.ArrayList;

// clasa information
public class Information {
    private String lastname;
    private String firstname;
    private String email;
    private String phone_number;
    private Date birth_date;
    private String sex;
    private ArrayList<Language> languages;

    // constructorul acestei clase
    public Information(String lastname, String firstname, String email,
                       String phone_number, Date birth_date, String sex,
                       ArrayList<Language> languages) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.sex = sex;
        this.languages = languages;
    }

    // constructorul fara parametrii
    public Information() {
        languages = new ArrayList<>();
    }

    // getter-ii si setter-ii clasei
    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getSex() {
        return sex;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setBirth_date(Date date) {
        this.birth_date = date;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    // clasa interna Language care caracterizeaza o limba
    public static class Language {
        private String name;
        private String level;

        public Language(String name, String level) {
            this.name = name;
            this.level = level;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public String getLevel() {
            return level;
        }
    }

    // metoda pentru a afisa numele utilizatorului
    @Override
    public String toString() {
        return lastname + " " + firstname + ", ";
    }
}

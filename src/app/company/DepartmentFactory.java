package app.company;

public class DepartmentFactory {
    public static Department factory(String type) {
        switch (type) {
            case "IT":
                return new IT();
            case "Finance":
                return new Finance();
            case "Management":
                return new Management();
            case "Marketing":
                return new Marketing();
            default:
                return null;
        }
    }
}

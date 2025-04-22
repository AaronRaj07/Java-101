package SMS;
public class Student {
    private int id;
    private String name;
    private double gpa;
    private String city;
    private String university;

    public Student(int id, String name, double gpa, String city, String university) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.city = city;
        this.university = university;
    }
    //Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getGpa() {
        return gpa;
    }
    public String getCity() {
        return city;
    }
    public String getUniversity() {
        return university;
    }
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    //toString method to display student information
    @Override
    public String toString() {
        return id + "\t" + name + "\t" + gpa + "\t" + city + "\t" + university;
    }
}
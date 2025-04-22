package SMS;

import java.io.*;
import java.util.*;

public class StudentManagementSystem {

    private static List<Student> students = new ArrayList<>();


    public static void main(String[] args) {
        

        String fileName = "StudentData.txt";
        readStudentData(fileName);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Student Management System!");
        do {
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Display Students by Name (Ascending)");
            System.out.println("5. Display Students by GPA (Descending)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    removeStudent(sc);
                    break;
                case 3:
                    updateStudent(sc);
                    break;
                case 4:
                    displayStudentsByName();
                    break;
                case 5:
                    displayStudentsByGPA();
                    break;
                case 6:
                    // Save students to file before exiting
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                        for (Student student : students) {
                            bw.write(student.toString() + "\n");
                        }
                    } catch (IOException e) {
                        System.out.println("Error writing student data: " + e.getMessage());
                    }
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!exit);
        

    }

    private static void displayStudentsByGPA() {
        
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getGpa(), s1.getGpa()); // Descending order
            }
        });
        System.out.println("Students sorted by GPA (Descending):");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void displayStudentsByName() {
        
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName()); // Ascending order
            }
        });
        System.out.println("Students sorted by name:");
        System.out.println("ID\tName\tGPA\tCity\tUniverity");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent(Scanner sc) {
        
        
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter Name: ");
                String name = sc.next();
                if (!name.isEmpty()) {
                    student.setName(name);
                }
                System.out.print("Enter GPA: ");
                String gpaInput = sc.next();
                if (!gpaInput.isEmpty()) {
                    double gpa = Double.parseDouble(gpaInput);
                    student.setGpa(gpa);
                }
                System.out.print("Enter City: ");
                String city = sc.next();
                if (!city.isEmpty()) {
                    student.setCity(city);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void removeStudent(Scanner sc) {
        
        System.out.print("Enter student ID to remove: ");
        int id = sc.nextInt();
        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                System.out.println("Student removed successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void addStudent(Scanner sc) {
        
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline character
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter GPA: ");
        double gpa = sc.nextDouble();
        sc.nextLine(); // Consume newline character
        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter University: ");
        String university = sc.nextLine();

        Student student = new Student(id, name, gpa, city, university);
        students.add(student);

        /*try (BufferedWriter bw = new BufferedWriter(new FileWriter("StudentData.txt", true))) {
            bw.write(student.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing student data: " + e.getMessage());
        }*/
        
        System.out.println("Student added successfully!");

    }

    public static void readStudentData(String fileName) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double gpa = Double.parseDouble(data[2]);
                String city = data[3];
                String university = data[4];
                Student student = new Student(id, name, gpa, city, university);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("Error reading student data: " + e.getMessage());
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    private class Student {
        private String rollNumber;
        private String name;
        private int age;
        private String course;

        public Student(String rollNumber, String name, int age, String course) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.course = course;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public void displayStudentDetails() {
            System.out.println("Roll Number: " + rollNumber);
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Course: " + course);
        }

        public void setAge(int age) {
        }

        public void setCourse(String course) {
        }

        public void setName(String name) {
        }
    }

    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Remove Student");
            System.out.println("4. View Student Details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    viewStudentDetails();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();


        if (findStudent(rollNumber) != null) {
            System.out.println("A student with this roll number already exists. Please use a unique roll number.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        Student newStudent = new Student(rollNumber, name, age, course);
        students.add(newStudent);
        System.out.println("Student added successfully!");
    }

    private void updateStudent() {
        System.out.print("Enter roll number of the student to update: ");
        String rollNumber = scanner.nextLine();
        Student student = findStudent(rollNumber);
        if (student != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new course: ");
            String course = scanner.nextLine();

            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
            System.out.println("Student details updated successfully!");
        }
    }

    private void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        String rollNumber = scanner.nextLine();
        Student student = findStudent(rollNumber);
        if (student != null) {
            students.remove(student);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void viewStudentDetails() {
        System.out.print("Enter roll number of the student to view: ");
        String rollNumber = scanner.nextLine();
        Student student = findStudent(rollNumber);
        if (student != null) {
            student.displayStudentDetails();
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }
}

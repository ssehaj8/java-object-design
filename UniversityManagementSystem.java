import java.util.ArrayList;
import java.util.Scanner;

// Student class
class StudentInfo {
    private String name;
    private ArrayList<CourseInfo> courses;

    public StudentInfo(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void enrollCourse(CourseInfo course) {
        courses.add(course);
        course.addStudent(this);
    }

    public void displayCourses() {
        System.out.println("Student: " + name);
        System.out.println("Enrolled Courses:");
        for (CourseInfo course : courses) {
            System.out.println("  - " + course.getCourseName());
        }
    }
}

// Professor class
class Professor {
    private String name;
    private ArrayList<CourseInfo> courses;

    public Professor(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void assignCourse(CourseInfo course) {
        courses.add(course);
        course.setProfessor(this);
    }

    public void displayCourses() {
        System.out.println("Professor: " + name);
        System.out.println("Teaching Courses:");
        for (CourseInfo course : courses) {
            System.out.println("  - " + course.getCourseName());
        }
    }
}

// Course class
class CourseInfo {
    private String courseName;
    private Professor professor;
    private ArrayList<StudentInfo> students;

    public CourseInfo(String courseName) {
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void addStudent(StudentInfo student) {
        students.add(student);
    }

    public void displayDetails() {
        System.out.println("Course: " + courseName);
        if (professor != null) {
            System.out.println("Professor: " + professor.getName());
        }
        System.out.println("Enrolled Students:");
        for (StudentInfo student : students) {
            System.out.println("  - " + student.getName());
        }
    }
}

// Main class
public class UniversityManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creating courses
        System.out.print("How many courses to add? ");
        int courseCount = sc.nextInt();
        sc.nextLine();
        ArrayList<CourseInfo> courses = new ArrayList<>();
        for (int i = 0; i < courseCount; i++) {
            System.out.print("Enter course name: ");
            courses.add(new CourseInfo(sc.nextLine()));
        }

        // Creating professors
        System.out.print("How many professors to add? ");
        int professorCount = sc.nextInt();
        sc.nextLine();
        ArrayList<Professor> professors = new ArrayList<>();
        for (int i = 0; i < professorCount; i++) {
            System.out.print("Enter professor name: ");
            professors.add(new Professor(sc.nextLine()));
        }

        // Assigning courses to professors
        for (Professor professor : professors) {
            System.out.print("How many courses to assign to " + professor.getName() + "? ");
            int assignCount = sc.nextInt();
            sc.nextLine();
            for (int j = 0; j < assignCount; j++) {
                System.out.print("Enter course name to assign: ");
                String courseName = sc.nextLine();
                CourseInfo course = courses.stream().filter(c -> c.getCourseName().equals(courseName)).findFirst().orElse(null);
                if (course != null) {
                    professor.assignCourse(course);
                }
                else {
                    System.out.println("Course not found.");
                }
            }
        }

        // Creating students
        System.out.print("How many students to add? ");
        int studentCount = sc.nextInt();
        sc.nextLine();
        ArrayList<StudentInfo> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            System.out.print("Enter student name: ");
            students.add(new StudentInfo(sc.nextLine()));
        }

        // Enrolling students in courses
        for (StudentInfo student : students) {
            System.out.print("How many courses to enroll " + student.getName() + " in? ");
            int enrollCount = sc.nextInt();
            sc.nextLine();
            for (int j = 0; j < enrollCount; j++) {
                System.out.print("Enter course name to enroll in: ");
                String courseName = sc.nextLine();
                CourseInfo course = courses.stream().filter(c -> c.getCourseName().equals(courseName)).findFirst().orElse(null);
                if (course != null) {
                    student.enrollCourse(course);
                }
                else {
                    System.out.println("Course not found.");
                }
            }
        }

        // Displaying details
        for (CourseInfo course : courses) {
            System.out.println();
            course.displayDetails();
        }
        for (StudentInfo student : students) {
            System.out.println();
            student.displayCourses();
        }
        for (Professor professor : professors) {
            System.out.println();
            professor.displayCourses();
        }
    }
}


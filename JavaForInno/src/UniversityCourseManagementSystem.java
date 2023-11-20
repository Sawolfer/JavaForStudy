import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class UniversityCourseManagementSystem {
    private static List<Student> students = new ArrayList<Student>();
    private static List<Professor> professors = new ArrayList<Professor>();
    private static List<Course> courses = new ArrayList<Course>();
    private static int memberId = 0;
    private static int courseId = 0;
    private static boolean found = false;
    private static String memberName;
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        fillInitialData();
        while (sc.hasNextLine()) {
            String currentString = sc.nextLine();
            switch (currentString) {
                case (" "): finish("");
                case ("course"):
                    memberName = sc.nextLine();
                    if (checkOtherNames(memberName) && !(memberName.compareTo("") == 0)) {
                        memberName = memberName.toLowerCase();
                        for (Course other: courses) {
                            if (other.getCourseName().compareTo(memberName) == 0) {
                                finish("Course exists");
                            }
                        }
                        String courseLevel = sc.nextLine();
                        courseLevel = courseLevel.toLowerCase();
                        if (courseLevel.compareTo("master") == 0) {
                            Course newCourse = new Course(memberName, CourseLevel.MASTER);
                            courses.add(newCourse);
                            System.out.println("Added successfully");
                            break;
                        } else if (courseLevel.compareTo("bachelor") == 0) {
                            Course newCourse = new Course(memberName, CourseLevel.BACHELOR);
                            courses.add(newCourse);
                            System.out.println("Added successfully");
                            break;
                        } else {
                            finish("Wrong inputs");
                        }
                    } else {
                        finish("Wrong inputs");
                    }
                    break;
                case ("student"):
                    memberName = sc.nextLine();
                    if (checkOtherNames(memberName) && !(memberName.compareTo("") == 0)) {
                        Student newStudent = new Student(memberName);
                        students.add(newStudent);
                        System.out.println("Added successfully");
                    } else {
                        finish("Wrong inputs");
                    }
                    break;
                case ("professor"):
                    memberName = sc.nextLine();
                    if (checkOtherNames(memberName) &&  !(memberName.compareTo("") == 0)) {
                        Professor newProfessor = new Professor(memberName);
                        professors.add(newProfessor);
                        System.out.println("Added successfully");
                    } else {
                        finish("Wrong inputs");
                    }
                    break;
                case ("enroll"):
                    found = false;
                    currentString = sc.nextLine();
                    if (checkMemberId(currentString)) {
                        memberId = Integer.parseInt(currentString);
                        for (Student man: students) {
                            if (man.memberId == memberId) {
                                currentString = sc.nextLine();
                                if (checkCourseId(currentString)) {
                                    courseId = Integer.parseInt(currentString);
                                    if (!man.enroll(courses.get(courseId - 1))) {
                                        finish("");
                                    } else {
                                        found = true;
                                        System.out.println("Enrolled successfully");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!found) {
                         finish("Wrong inputs");
                    }
                    break;
                case ("drop"):
                    found = false;
                    currentString = sc.nextLine();
                    if (checkMemberId(currentString)) {
                        memberId = Integer.parseInt(currentString);
                        for (Student man: students) {
                            if (man.memberId == memberId) {
                                currentString = sc.nextLine();
                                if (checkCourseId(currentString)) {
                                    courseId = Integer.parseInt(currentString);
                                    if (man.drop(courses.get(courseId - 1))) {
                                        found = true;
                                        System.out.println("Dropped successfully");
                                    } else {
                                        finish("");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!found) {
                        finish("Wrong inputs");
                    }
                    break;
                case ("exempt"):
                    found = false;
                    currentString = sc.nextLine();
                    if (checkMemberId(currentString)) {
                        memberId = Integer.parseInt(currentString);
                        for (Professor man: professors) {
                            if (man.memberId == memberId) {
                                currentString = sc.nextLine();
                                if (checkCourseId(currentString)) {
                                    courseId = Integer.parseInt(currentString);
                                    if (man.exempt(courses.get(courseId - 1))) {
                                        found = true;
                                        System.out.println("Professor is exempted");
                                    } else {
                                        finish("");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!found) {
                        finish("Wrong inputs");
                    }
                    break;
                case ("teach"):
                    found = false;
                    currentString = sc.nextLine();
                    if (checkMemberId(currentString)) {
                        memberId = Integer.parseInt(currentString);
                        for (Professor man: professors) {
                            if (man.memberId == memberId) {
                                currentString = sc.nextLine();
                                if (checkCourseId(currentString)) {
                                    courseId = Integer.parseInt(currentString);
                                    if (man.teach(courses.get(courseId - 1))) {
                                        found = true;
                                        System.out.println("Professor is successfully assigned to teach this course");
                                    } else {
                                        finish("");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!found) {
                        finish("Wrong inputs");
                    }
                    break;
                default:
                    finish("Wrong inputs");
                    break;
            }
        }
        finish("");
    }

    public static boolean checkCourseId(String id) {
        int courseId = 0;
        try {
            courseId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            finish("Wrong inputs");
        }
        if (courseId > courses.size() || courseId < 1) {
            finish("Wrong inputs");
        }
        return true;
    }
    public static boolean checkMemberId(String id) {
        int memberId = 0;
        try {
            memberId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Wrong inputs");
            finish("");
        }
        if (memberId < 1 || memberId > (students.size() + professors.size())) {
            System.out.println("Wrong inputs");
            finish("");
        }
        return true;
    }
    public static boolean checkOtherNames(String name) {
        name = name.toLowerCase();
        List<String> banned = Arrays.asList("course", "student", "professor", "enroll", "drop", "exempt", "teach");
        if (name.contains("___") || name.charAt(0) == '_' || name.charAt(name.length() - 1) == '_') {
            System.out.println("Wrong inputs");
            finish("");
        }
        for (String item : banned) {
            if (name.compareTo(item) == 0) {
                System.out.println("Wrong inputs");
                finish("");
            }
        }
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '_') {
                continue;
            }
            if ('a' > name.charAt(i) || name.charAt(i) > 'z') {
                System.out.println("Wrong inputs");
                finish("");
            }
        }
        return true;
    }

    public static void finish(String typeOfError) {
        System.out.println(typeOfError);
        System.exit(0);
    }

    public static void fillInitialData() {
        Course javaBeginner = new Course("java_beginner", CourseLevel.BACHELOR);
        courses.add(javaBeginner);
        Course javaIntermediate = new Course("java_intermediate", CourseLevel.BACHELOR);
        courses.add(javaIntermediate);
        Course pythonBasics = new Course("python_basics", CourseLevel.BACHELOR);
        courses.add(pythonBasics);
        Course algorithms = new Course("algorithms", CourseLevel.MASTER);
        courses.add(algorithms);
        Course advancedProgramming = new Course("advanced_programming", CourseLevel.MASTER);
        courses.add(advancedProgramming);
        Course mathematicalAnalysis = new Course("mathematical_analysis", CourseLevel.MASTER);
        courses.add(mathematicalAnalysis);
        Course computerVision = new Course("computer_vision", CourseLevel.MASTER);
        courses.add(computerVision);

        Student alice = new Student("Alice");
        students.add(alice);
        Student bob = new Student("Bob");
        students.add(bob);
        Student alex = new Student("Alex");
        students.add(alex);
        alice.enroll(javaBeginner);
        alice.enroll(javaIntermediate);
        alice.enroll(pythonBasics);
        bob.enroll(javaBeginner);
        bob.enroll(algorithms);
        alex.enroll(advancedProgramming);

        Professor ali = new Professor("Ali");
        professors.add(ali);
        Professor ahmed = new Professor("Ahmed");
        professors.add(ahmed);
        Professor andrey = new Professor("Andrey");
        professors.add(andrey);
        ali.teach(javaBeginner);
        ali.teach(javaIntermediate);
        ahmed.teach(pythonBasics);
        ahmed.teach(advancedProgramming);
        andrey.teach(mathematicalAnalysis);
    }
}

abstract class UniversityMember {
    public static int numberOfMembers;
    protected int memberId;
    protected String memberName;

    public UniversityMember(int memberId, String memberName) {
        numberOfMembers++;
        this.memberId = memberId;
        this.memberName = memberName;
    }


}

interface Enrollable {

    public boolean drop(Course course);
    public boolean enroll(Course course);
}

class Student extends UniversityMember implements Enrollable {
    private final int MAX_ENROLLMENT = 3;
    private List<Course> enrolledCourse = new ArrayList<Course>();
    public Student(String memberName) {
        super(numberOfMembers + 1, memberName);
    }

    public boolean drop(Course course) {
        for (Course enrolled: enrolledCourse) {
            if (enrolled.getCourseld() == course.getCourseld()) {
                enrolledCourse.remove(course);
                course.drop(this);
                return true;
            }
        }
        System.out.println("Student is not enrolled in this course");
        return false;
    }
    public boolean enroll(Course course) {
        for (Course enrolled: enrolledCourse) {
            if (enrolled.getCourseld() == course.getCourseld()) {
                System.out.println("Student is already enrolled in this course");
                return false;
            }
        }
        if (enrolledCourse.size() >= MAX_ENROLLMENT) {
            System.out.println("Maximum enrollment is reached for the student");
            return false;
        }
        if (course.isFull()) {
            System.out.println("Course is full");
            return false;
        }


        enrolledCourse.add(course);
        course.enroll(this);
        return true;
    }


}

class Professor extends UniversityMember {
    private static int MAX_LOAD = 2;
    private List<Course> assigmentCourses = new ArrayList<Course>();


    public Professor(String memberName) {
        super(numberOfMembers + 1, memberName);
    }
    public boolean teach(Course course) {
        if (assigmentCourses.size() >= MAX_LOAD) {
            System.out.println("Professor's load is complete");
            return false;
        }
        for (Course assigned: assigmentCourses) {
            if (assigned.getCourseld() == course.getCourseld()) {
                System.out.println("Professor is already teaching this course");
                return false;
            }

        }
        assigmentCourses.add(course);
        return true;
    }
    public boolean exempt(Course course) {
        for (Course assigned : assigmentCourses) {
            if (course.getCourseld() == assigned.getCourseld()) {
                assigmentCourses.remove(course);
                return true;
            }
        }
        System.out.println("Professor is not teaching this course");
        return false;
    }
}

enum CourseLevel {
    BACHELOR,
    MASTER
}

class Course {
    private final int CAPACITY = 3;
    private static int numberOfCourses;

    private int courseld;
    private String courseName;
    private List<Student> enrolledStudents = new ArrayList<Student>();
    private CourseLevel courseLevel;


    public Course(String courseName, CourseLevel couseLevel) {
        numberOfCourses++;
        this.courseName = courseName;
        this.courseLevel = couseLevel;
        courseld = numberOfCourses;

    }

    public String getCourseName() {
        return courseName;
    }
    public int getCourseld() {
        return courseld;
    }
    public void drop(Student student) {
        enrolledStudents.remove(student);
    }
    public void enroll(Student student) {
        enrolledStudents.add(student);
    }

    public boolean isFull() {
        if (enrolledStudents.size() >= CAPACITY) {
            return true;
        }
        return false;
    }
}

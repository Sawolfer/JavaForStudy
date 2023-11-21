import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * the main class that implements reading input string, working with them, checking its validity and running commands
 */
public class UniversityCourseManagementSystem {
    /**
     * list of all students
     * list of all professors
     * list of all courses
     */
    private static List<Student> students = new ArrayList<Student>();
    private static List<Professor> professors = new ArrayList<Professor>();
    private static List<Course> courses = new ArrayList<Course>();
    /**
     * int variable member id that will be used in different parts of main like tmp id of course/student/professor
     * int variable course id that will be changed in different parts of main like tmp id of course/student/professor
     * boolean found if we found a particular student/professor in the list of students/professors
     * string member name that will be used in different parts of main like tmp name of course/student/professor
     */
    private static int memberId = 0;
    private static int courseId = 0;
    private static boolean found = false;
    private static String memberName;


    /**
     * function main work until it has error
     * it's start function to initialise all data
     * it gets string and if it's correct command, implement method which we send
     * it checks all incoming values for correctness and throw exception in case of error
     *
     * @param args input arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                    if (chechNames(memberName) && !(memberName.compareTo("") == 0)) {
                        Student newStudent = new Student(memberName);
                        students.add(newStudent);
                        System.out.println("Added successfully");
                    }
                    break;
                case ("professor"):
                    memberName = sc.nextLine();
                    if (chechNames(memberName) &&  !(memberName.compareTo("") == 0)) {
                        Professor newProfessor = new Professor(memberName);
                        professors.add(newProfessor);
                        System.out.println("Added successfully");
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

    /**
     * this method get id as String and try convert to int
     * it also checks if the course id exist and suitable for conditions
     *
     * @param id string that we send to check
     * @return   true if the course id is valid, false otherwise
     */
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

    /**
     * this method get id as String and try convert to int
     * it also checks if the member id exist and suitable for conditions
     *
     * @param id string that we send to check
     * @return   true if the member id is correct, false otherwise
     */
    public static boolean checkMemberId(String id) {
        int memberId = 0;
        try {
            memberId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            finish("Wrong inputs");
        }
        if (memberId < 1 || memberId > (students.size() + professors.size())) {
            finish("Wrong inputs");
        }
        return true;
    }

    /**
     * check that name is exist, has only english letters and doesn't equal to names of other commands or null string
     * @param name string that we send to validate
     * @return turn if
     */
    public static boolean chechNames(String name) {
        name = name.toLowerCase();
        List<String> banned = Arrays.asList("course", "student", "professor", "enroll", "drop", "exempt", "teach", "");

        for (String item : banned) {
            if (name.compareTo(item) == 0) {
                finish("Wrong inputs");
            }
        }
        for (int i = 0; i < name.length(); i++) {
            if ('a' > name.charAt(i) || name.charAt(i) > 'z') {
                finish("Wrong inputs");
            }
        }
        return true;
    }

    /**
     * check that name is exist, consist only english letters, doesn't have ___,
     * doesn't equal to names of other commands and also doesn't have _ as the first or last symbol
     *
     * @param name string that we send to validate
     * @return true if name is correct
     */
    public static boolean checkOtherNames(String name) {
        name = name.toLowerCase();
        List<String> banned = Arrays.asList("course", "student", "professor", "enroll", "drop", "exempt", "teach", "");

        for (String item : banned) {
            if (name.compareTo(item) == 0) {
                finish("Wrong inputs");
            }
        }
        if (name.contains("___") || name.charAt(0) == '_' || name.charAt(name.length() - 1) == '_') {
            finish("Wrong inputs");
        }

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '_') {
                continue;
            }
            if ('a' > name.charAt(i) || name.charAt(i) > 'z') {
                finish("Wrong inputs");
            }
        }
        return true;
    }

    /**
     * exit program with throwing exception
     * @param typeOfError message with type of error
     */
    public static void finish(String typeOfError) {
        System.out.println(typeOfError);
        System.exit(0);
    }

    /**
     * initialise the data
     */
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

/**
 * The UniversityMember class is an abstract class that represents a member of a university.
 */
abstract class UniversityMember {
    protected static int numberOfMembers;
    protected int memberId;
    protected String memberName;

    /**
     * constructor for new member which gives him a unique id and name
     *
     * @param memberId id of new member
     * @param memberName name of new member
     */
    public UniversityMember(int memberId, String memberName) {
        numberOfMembers++;
        this.memberId = memberId;
        this.memberName = memberName;
    }
}

/**
 * interface for implementation drop and enroll functions in class Student
 */
interface Enrollable {

    public boolean drop(Course course);
    public boolean enroll(Course course);
}

/**
 * class which contain all function of student like enroll drop and adding new student
 * it also contains list of courses in which the student is enrolled
 */
class Student extends UniversityMember implements Enrollable {
    private static final int MAX_ENROLLMENT = 3;
    private final List<Course> enrolledCourse = new ArrayList<Course>();

    /**
     * constructor for new student
     * @param memberName name of new student
     */
    public Student(String memberName) {
        super(numberOfMembers + 1, memberName);
    }

    /**
     * check if student on this course and drop him from it and remove course om his courses
     * @param course the course from which the student is to be dropped
     * @return true if student dropped successfully and false if student is not enrolled in the course
     */
    public boolean drop(Course course) {
        for (Course enrolled: enrolledCourse) {
            if (enrolled.getCourseld() == course.getCourseld()) {
                enrolledCourse.remove(course);
                course.drop(this);
                return true;
            }
        }
        UniversityCourseManagementSystem.finish("Student is not enrolled in this course");
        return false;
    }

    /**
     * try to enroll student on course and add this course to the list of student's courses
     * @param course the course at which the student try to enroll
     * @return true if all is good and false in case of error
     */
    public boolean enroll(Course course) {
        for (Course enrolled: enrolledCourse) {
            if (enrolled.getCourseld() == course.getCourseld()) {
                UniversityCourseManagementSystem.finish("Student is already enrolled in this course");
                return false;
            }
        }
        if (enrolledCourse.size() >= MAX_ENROLLMENT) {
            UniversityCourseManagementSystem.finish("Maximum enrollment is reached for the student");
            return false;
        }
        if (course.isFull()) {
            UniversityCourseManagementSystem.finish("Course is full");
            return false;
        }


        enrolledCourse.add(course);
        course.enroll(this);
        return true;
    }


}

/**
 * class that store all data about professor and have functions about professor
 */
class Professor extends UniversityMember {
    private static final int MAX_LOAD = 2;
    private final List<Course> assigmentCourses = new ArrayList<Course>();

    /**
     * constructor of professor
     * @param memberName name of professor
     */
    public Professor(String memberName) {
        super(numberOfMembers + 1, memberName);
    }

    /**
     * assigns the professor to teach the specified course and add it to list of courses
     * @param course a course to be taught by professor
     * @return true if all is good and false in case of error
     */
    public boolean teach(Course course) {
        if (assigmentCourses.size() >= MAX_LOAD) {
            UniversityCourseManagementSystem.finish("Professor's load is complete");
            return false;
        }
        for (Course assigned: assigmentCourses) {
            if (assigned.getCourseld() == course.getCourseld()) {
                UniversityCourseManagementSystem.finish("Professor is already teaching this course");
                return false;
            }

        }
        assigmentCourses.add(course);
        return true;
    }

    /**
     * exempts the professor from teaching the specified course and remove it from list of assigment courses
     * @param course course from which professor should be exempted
     * @return true if the professor is successfully exempted from teaching
     */
    public boolean exempt(Course course) {
        for (Course assigned : assigmentCourses) {
            if (course.getCourseld() == assigned.getCourseld()) {
                assigmentCourses.remove(course);
                return true;
            }
        }
        UniversityCourseManagementSystem.finish("Professor is not teaching this course");
        return false;
    }
}

/**
 * enum of two levels of course
 */
enum CourseLevel {
    BACHELOR,
    MASTER
}

/**
 * class in which all necessary data about course and all functions
 */
class Course {
    private static final int CAPACITY = 3;
    private static int numberOfCourses;

    private int courseld;
    private String courseName;
    private List<Student> enrolledStudents = new ArrayList<Student>();
    private CourseLevel courseLevel;

    /**
     * constructor of course
     * @param courseName name of new course
     * @param couseLevel level of new course (bachelor/master)
     */
    public Course(String courseName, CourseLevel couseLevel) {
        numberOfCourses++;
        this.courseName = courseName;
        this.courseLevel = couseLevel;
        courseld = numberOfCourses;

    }

    /**
     * get name of course to other functions
     * @return name of this course
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * get id of this course
     * @return id of this course
     */
    public int getCourseld() {
        return courseld;
    }

    /**
     * function that remove student from list of enrolled students
     * @param student that should be dropped
     */
    public void drop(Student student) {
        enrolledStudents.remove(student);
    }

    /**
     * function that add student to the list of enrolled students
     * @param student that should be enrolled
     */
    public void enroll(Student student) {
        enrolledStudents.add(student);
    }
    /**
     * function that gives us to know if we have any free places at course
     * @return false if the course isn't full and true if full
     */
    public boolean isFull() {
        if (enrolledStudents.size() >= CAPACITY) {
            return true;
        }
        return false;
    }
}

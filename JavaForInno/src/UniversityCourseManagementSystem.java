import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UniversityCourseManagementSystem {

    static List<Student> students = new ArrayList<Student>();
    static List<Professor> professors = new ArrayList<Professor>();
    static List<Course> courses = new ArrayList<Course>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        fillInitialData();
        int memberId;
        int courseId;
        String memberName;
        boolean work = true;
        while (work){
            String _currentString = sc.next();
            switch (_currentString){
                case ("course"):
                    memberName = sc.next();
                    String courseLevel = sc.next();
                    CourseLevel courseLevelEnum = CourseLevel.BACHELOR;
                    if (CheckName(courseLevel)){
                        courseLevel = courseLevel.toLowerCase();
                        courseLevelEnum = courseLevel == "master" ? CourseLevel.MASTER : CourseLevel.BACHELOR;
                    }
                    if (CheckName(memberName)){
                        Course _newCourse = new Course(memberName, courseLevelEnum);
                        System.out.printf("Added successfully");
                    }
                    break;
                case ("student"):
                    memberName = sc.next();
                    if (CheckName(memberName)){
                        Student _newStudent = new Student(memberName);
                        students.add(_newStudent);
                        System.out.println("Added successfully");
                    }
                    break;
                case ("professor"):
                    memberName = sc.next();
                    if (CheckName(memberName)){
                        Professor _newProfessor = new Professor(memberName);
                        professors.add(_newProfessor);
                        System.out.println("Added successfully");
                    }
                    break;
                case ("enroll"):
                    memberId = sc.nextInt();
                    courseId = sc.nextInt();
                    for (Student man: students) {
                        if (man.memberId == memberId){
                            if (man.enroll(courses.get(courseId--)));
                            System.out.println("Enrolled successfully");
                        }
                    }
                    break;
                case ("drop"):
                    memberId = sc.nextInt();
                    courseId = sc.nextInt();
                    for (Student man: students) {
                        if (man.memberId == memberId){
                            if (man.drop(courses.get(courseId--)));
                            System.out.println("Dropped successfully");
                        }
                    }
                    break;
                case ("teach"):
                    memberId = sc.nextInt();
                    courseId = sc.nextInt();
                    for (Professor man: professors){
                        if (man.memberId == memberId){
                            if (man.teach(courses.get(courseId--)));
                            System.out.println("Professor is successfully assigned to teach this course");
                        }
                    }
                    break;
                case ("exempt"):
                    memberId = sc.nextInt();
                    courseId = sc.nextInt();
                    for (Professor man: professors){
                        if (man.memberId == memberId){
                            if (man.exempt(courses.get(courseId--)));
                            System.out.println("Professor is exempted");
                        }
                    }
                    break;
                default:
                    Finish("default");
                    break;
            }
        }
    }


    private static boolean CheckName(String name){
        name = name.toLowerCase();
        for (int i=0; i<name.length(); i++){
            if (name.charAt(i) == '_'){
                continue;
            }
            if ('a' > name.charAt(i) || name.charAt(i) >'z'){
                Finish(name);
//                return false;
            }
        }
        return true;
    }

    public static void Finish( String what){
        System.out.println("Wrong Inputs" + what);
        System.exit(0);
    }
    public static void fillInitialData(){
        Course JavaBeginner = new Course("java_beginner", CourseLevel.BACHELOR);
        Course JavaIntermediate = new Course("java_intermediate", CourseLevel.BACHELOR);
        Course PythonBasic = new Course("python_basics", CourseLevel.BACHELOR);
        Course Algorithms = new Course("algorithms", CourseLevel.MASTER);
        Course AdvancedProgramming = new Course("advanced_programming", CourseLevel.MASTER);
        Course MathematicalAnalysis = new Course("mathematical_analysis", CourseLevel.MASTER);
        Course ComputerVision = new Course("computer_vision", CourseLevel.MASTER);
        courses.add(JavaBeginner);
        courses.add(JavaIntermediate);
        courses.add(PythonBasic);
        courses.add(Algorithms);
        courses.add(AdvancedProgramming);
        courses.add(MathematicalAnalysis);
        courses.add(ComputerVision);

        Student Alice = new Student("Alice");
        Student Bob = new Student("Bob");
        Student Alex = new Student("Alex");
        students.add(Alice);
        students.add(Bob);
        students.add(Alex);
        Alice.enroll(JavaBeginner);
        Alice.enroll(JavaIntermediate);
        Alice.enroll(PythonBasic);
        Bob.enroll(JavaBeginner);
        Bob.enroll(Algorithms);
        Alex.enroll(AdvancedProgramming);

        Professor Ali = new Professor("Ali");
        Professor Ahmed = new Professor("Ahmed");
        Professor Andrey = new Professor("Andrey");
        professors.add(Ali);
        professors.add(Ahmed);
        professors.add(Andrey);
        Ali.teach(JavaBeginner);
        Ali.teach(JavaIntermediate);
        Ahmed.teach(PythonBasic);
        Ahmed.teach(AdvancedProgramming);
        Andrey.teach(MathematicalAnalysis);
    }
}

abstract class UniversityMember {
    public static int numberOfMembers;
    protected int memberId;
    protected String memberName;

    public UniversityMember(int memberId, String memberName){
        this.memberId = memberId;
        this.memberName = memberName;
    }

}

interface Enrollable{

    public boolean drop(Course course);
    public boolean enroll(Course course);
}

class Student extends UniversityMember implements Enrollable{
    private static int MAX_ENROLLMENT =3;
    private List<Course> enrolledCourse = new ArrayList<Course>();


    public Student(String memberName){
        super(numberOfMembers++, memberName);
    }

    public boolean drop(Course course){
        for (int i=0; i< enrolledCourse.size(); i++){
            if (enrolledCourse.get(i) == course){
                enrolledCourse.remove(course);
                return true;
            }
        }
        System.out.println("Student is not enrolled in this course");
        return false;
    }
    public boolean enroll(Course course){
        for (int i=0; i< enrolledCourse.size(); i++){
            if (course == enrolledCourse.get(i)){
                System.out.println("Student is already enrolled in this course");
                return false;
            }
            else if(enrolledCourse.size()>= MAX_ENROLLMENT ){
                System.out.println("Maximum enrollment is reached for the student");
            }
            else if (course.isFull()){
                System.out.println("Course is full");
            }

        }
        enrolledCourse.add(course);
        return true;
    }


}

class Professor extends UniversityMember{
    private static int MAX_LOAD = 2;
    private List<Course> assigmentCourses = new ArrayList<Course>();


    public Professor(String memberName){
        super(numberOfMembers++, memberName);
    }
    public boolean teach(Course course){
        for (int i =0 ; i< assigmentCourses.size(); i++){
            if (course == assigmentCourses.get(i)){
                System.out.println("Professor is already teaching this course");
                return false;
            }
            else if(assigmentCourses.size()>=MAX_LOAD){
                System.out.println("Professor's load is complete");
                return false;
            }
        }
        assigmentCourses.add(course);
        return true;
    }
    public boolean exempt(Course course){
        for(int i =0; i<assigmentCourses.size(); i++){
            if(course == assigmentCourses.get(i)){
                assigmentCourses.remove(course);
                return true;
            }
        }
        return false;
    }
}

enum CourseLevel{
    BACHELOR,
    MASTER
}

class Course{
    private static int CAPASITY =3 ;
    private static int numberOfCourses;

    private int courseld;
    private String courseName;
    private List<Student> enrolledStudents = new ArrayList<Student>();
    private CourseLevel courseLevel;


    public Course(String courseName, CourseLevel couseLevel){
        this.courseName = courseName;
        this.courseLevel = couseLevel;

    }

    public boolean isFull(){
        if (enrolledStudents.size() >= CAPASITY){
            return true;
        }
        return false;
    }
}
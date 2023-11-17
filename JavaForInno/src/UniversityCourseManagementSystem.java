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
        while (sc.hasNext()){
            String _currentString = sc.next();
            _currentString = _currentString.toLowerCase();
            switch (_currentString){
                case ("course"):
                    memberName = sc.next();
                    String courseLevel = sc.next();
                    CourseLevel courseLevelEnum = null;
                    if (CheckName(courseLevel)){
                        courseLevel = courseLevel.toLowerCase();
                        if (courseLevel.compareTo("master")==0){
                            courseLevelEnum = CourseLevel.MASTER;
                        }
                        else if(courseLevel.compareTo("bachelor")==0){
                            courseLevelEnum = CourseLevel.BACHELOR;
                        }
                        else{
                            System.out.println("Wrong Inputs");
                            Finish();
                        }
                    }
                    if (CheckName(memberName)){
                        Course _newCourse = new Course(memberName, courseLevelEnum);
                        for (Course other: courses) {
                            if (other.getCourseName().compareTo(memberName)==0 && other.getCourseLevel() == courseLevelEnum){
                                System.out.println("Course exists");
                                return;
                            }
                        }
                        courses.add(_newCourse);
                        System.out.println("Added successfully");
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
                            if (!man.enroll(courses.get(courseId--))){
                                Finish();
                            }
                            else{
                                System.out.println("Enrolled successfully");
                            }
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
                    System.out.println("Wrong Inputs");
                    Finish();
                    break;
            }
        }
    }

//    public static void PrintAll(){
//        for (Course course: courses) {
//            if(course.getCourseld()== 1){
//                System.out.println(course.getCourseName());
//            }
//        }
//        System.out.println(courses.size());
//        System.out.println(students.size());
//        System.out.println(professors.size());
//    }

    private static boolean CheckName(String name){
        name = name.toLowerCase();
        for (int i=0; i<name.length(); i++){
            if (name.charAt(i) == '_'){
                continue;
            }
            if ('a' > name.charAt(i) || name.charAt(i) >'z'){
                System.out.println("Wrong Inputs");
                Finish();
//                return false;
            }
        }
        return true;
    }

    public static void Finish(){
//        System.out.println("Wrong Inputs" + what);

//        PrintAll();
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
        numberOfMembers++;
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
        super(numberOfMembers+1, memberName);
    }

    public boolean drop(Course course){
        for (int i=0; i< enrolledCourse.size(); i++){
            if (enrolledCourse.get(i) == course){
                enrolledCourse.remove(course);
                course.Drop(this);
                return true;
            }
        }
        System.out.println("Student is not enrolled in this course");
        return false;
    }
    public boolean enroll(Course course){
        for (Course enrolled: enrolledCourse){
            if (enrolled.getCourseName().compareTo(course.getCourseName())==0 && (enrolled.getCourseLevel() == course.getCourseLevel())){
                System.out.println("Student is already enrolled in this course");
                return false;
            }
            else if(enrolledCourse.size()>= MAX_ENROLLMENT ){
                System.out.println("Maximum enrollment is reached for the student");
                return false;
            }
            else if (course.isFull()){
                System.out.println("Course is full");
                return false;
            }

        }
        enrolledCourse.add(course);
        course.Enroll(this);
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
        numberOfCourses++;
        this.courseName = courseName;
        this.courseLevel = couseLevel;
        courseld = numberOfCourses;

    }

    public String getCourseName(){
        return courseName;
    }
    public CourseLevel getCourseLevel(){
        return courseLevel;
    }
    public int getCourseld(){
        return courseld;
    }
    public void Drop(Student student){
        enrolledStudents.remove(student);
    }
    public void Enroll(Student student){
        enrolledStudents.add(student);
    }

    public boolean isFull(){
        if (enrolledStudents.size() >= CAPASITY){
            return true;
        }
        return false;
    }
}
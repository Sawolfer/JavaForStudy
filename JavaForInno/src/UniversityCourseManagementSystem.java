import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UniversityCourseManagementSystem {
    static List<Student> students = new ArrayList<Student>();
    static List<Professor> professors = new ArrayList<Professor>();
    static List<Course> courses = new ArrayList<Course>();

    public static void main(String[] args) {
        fillInitialData();
        int memberId = 1000;
        int courseId = 1000;
        boolean found = false;
        String memberName;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            String _currentString = sc.nextLine();
            switch (_currentString){
                case(" "):Finish();
                case ("course"):
                    memberName = sc.nextLine();
                    if (CheckOtherNames(memberName) && memberName.toLowerCase().compareTo("course")!=0 && memberName.toLowerCase().compareTo("student")!=0 &&
                        memberName.toLowerCase().compareTo("professor")!=0 && memberName.toLowerCase().compareTo("enroll")!=0 &&
                        memberName.toLowerCase().compareTo("drop")!=0 && memberName.toLowerCase().compareTo("exempt")!=0 &&
                        memberName.toLowerCase().compareTo("teach")!=0 && !(memberName.compareTo("")==0)) {
                        memberName = memberName.toLowerCase();
                        for (Course other: courses) {
                            if (other.getCourseName().compareTo(memberName) == 0) {
                                System.out.println("Course exists");
                                Finish();
                            }
                        }
                        String courseLevel = sc.nextLine();;
                        courseLevel = courseLevel.toLowerCase();
                        if (courseLevel.compareTo("master")==0){
                            Course _newCourse = new Course(memberName, CourseLevel.MASTER);
                            courses.add(_newCourse);
                            System.out.println("Added successfully");
                            break;
                        }
                        else if(courseLevel.compareTo("bachelor")==0){
                            Course _newCourse = new Course(memberName, CourseLevel.BACHELOR);
                            courses.add(_newCourse);
                            System.out.println("Added successfully");
                            break;
                        }
                        else{
                            System.out.println("Wrong Inputs");
                            Finish();
                        }
                    }
                    else{
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    break;
                case ("student"):
                    memberName = sc.nextLine();
                    if (CheckName(memberName) && memberName.toLowerCase().compareTo("course") != 0 && memberName.toLowerCase().compareTo("student") != 0 &&
                            memberName.toLowerCase().compareTo("professor") != 0 && memberName.toLowerCase().compareTo("enroll") != 0 &&
                            memberName.toLowerCase().compareTo("drop") != 0 && memberName.toLowerCase().compareTo("exempt") != 0 &&
                            memberName.toLowerCase().compareTo("teach") != 0 && !(memberName.compareTo("")==0)) {
                        Student _newStudent = new Student(memberName);
                        students.add(_newStudent);
                        System.out.println("Added successfully");
                    } else {
                        System.out.println("Wrong Inputs");
                        Finish();
                    }

                    break;
                case ("professor"):
                    memberName = sc.nextLine();
                    if (CheckName(memberName) && memberName.toLowerCase().compareTo("course")!=0 && memberName.toLowerCase().compareTo("student")!=0 &&
                            memberName.toLowerCase().compareTo("professor")!=0 && memberName.toLowerCase().compareTo("enroll")!=0 &&
                            memberName.toLowerCase().compareTo("drop")!=0 && memberName.toLowerCase().compareTo("exempt")!=0 &&
                            memberName.toLowerCase().compareTo("teach")!=0 &&  !(memberName.compareTo("")==0)) {
                        Professor _newProfessor = new Professor(memberName);
                        professors.add(_newProfessor);
                        System.out.println("Added successfully");
                    }
                    else{
                        System.out.println("Wrong Inputs");
                        Finish();
                    }

                    break;
                case ("enroll"):
                    found = false;
                    _currentString = sc.nextLine();
                    try{
                        memberId = Integer.parseInt(_currentString);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    if (memberId<1 || memberId> (students.size()+ professors.size())){
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    for (Student man: students) {
                        if (man.memberId == memberId){
                            _currentString = sc.nextLine();
                            try{
                                courseId = Integer.parseInt(_currentString);
                            }catch (NumberFormatException e) {
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (courseId> courses.size()){
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (!man.enroll(courses.get(courseId-1))){
                                Finish();
                            }
                            else{
                                found = true;
                                System.out.println("Enrolled successfully");
                                break;
                            }
                        }
                    }
                    if (!found){
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    break;
                case ("drop"):
                    found = false;
                    _currentString = sc.nextLine();
                    try{
                        memberId = Integer.parseInt(_currentString);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    for (Student man: students) {
                        if (man.memberId == memberId){
                            _currentString = sc.nextLine();
                            try{
                                courseId = Integer.parseInt(_currentString);
                            }catch (NumberFormatException e) {
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (courseId> courses.size()){
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (man.drop(courses.get(courseId-1))){
                                found = true;
                                System.out.println("Dropped successfully");
                            }
                            else {
                                Finish();
                                break;
                            }
                        }
                    }
                    if (!found){
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    break;
                case ("exempt"):
                    found = false;
                    _currentString = sc.nextLine();
                    try{
                        memberId = Integer.parseInt(_currentString);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    for (Professor man: professors){
                        if (man.memberId == memberId){
                            _currentString = sc.nextLine();
                            try{
                                courseId = Integer.parseInt(_currentString);
                            }catch (NumberFormatException e) {
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (courseId> courses.size()){
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (man.exempt(courses.get(courseId-1))){
                                found = true;
                                System.out.println("Professor is exempted");
                            }
                            else{
                                Finish();
                                break;
                            }
                        }
                    }
                    if (!found){
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    break;
                case ("teach"):
                    found = false;
                    _currentString = sc.nextLine();
                    try{
                        memberId = Integer.parseInt(_currentString);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    for (Professor man: professors){
                        if (man.memberId == memberId){
                            _currentString = sc.nextLine();
                            try{
                                courseId = Integer.parseInt(_currentString);
                            }catch (NumberFormatException e) {
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (courseId> courses.size()){
                                System.out.println("Wrong Inputs");
                                Finish();
                            }
                            if (man.teach(courses.get(courseId-1))){
                                found = true;
                                System.out.println("Professor is successfully assigned to teach this course");
                            }
                            else{
                                Finish();
                                break;
                            }
                        }
                    }
                    if (!found){
                        System.out.println("Wrong Inputs");
                        Finish();
                    }
                    break;
                default:
                    System.out.println("Wrong Inputs");
                    Finish();
                    break;
            }
        }
        Finish();
    }

    private static boolean CheckName(String name){
        name = name.toLowerCase();
        for (int i=0; i<name.length(); i++){
            if ('a' > name.charAt(i) || name.charAt(i) >'z'){
                System.out.println("Wrong Inputs");
                Finish();
            }
        }
        return true;
    }

    public static boolean CheckOtherNames(String name){
        name = name.toLowerCase();
//        name.charAt(0)<'a' || name.charAt(name.length()-1)<'a'|| name.charAt(0)>'z'|| name.charAt(name.length()-1)>'z' ||
        if (name.contains("___")){
            System.out.println("Wrong Inputs");
            Finish();
        }
        for (int i=0; i<name.length(); i++){
            if (name.charAt(i) == '_'){
                continue;
            }
            if ('a' > name.charAt(i) || name.charAt(i) >'z'){
                System.out.println("Wrong Inputs");
                Finish();
            }
        }
        return true;
    }

    public static void Finish(){
//        System.out.println("Wrong Inputs" + what);

        System.exit(0);

    }

    public static void fillInitialData(){
        Course JavaBeginner = new Course("java_beginner", CourseLevel.BACHELOR);
        courses.add(JavaBeginner);
        Course JavaIntermediate = new Course("java_intermediate", CourseLevel.BACHELOR);
        courses.add(JavaIntermediate);
        Course PythonBasic = new Course("python_basics", CourseLevel.BACHELOR);
        courses.add(PythonBasic);
        Course Algorithms = new Course("algorithms", CourseLevel.MASTER);
        courses.add(Algorithms);
        Course AdvancedProgramming = new Course("advanced_programming", CourseLevel.MASTER);
        courses.add(AdvancedProgramming);
        Course MathematicalAnalysis = new Course("mathematical_analysis", CourseLevel.MASTER);
        courses.add(MathematicalAnalysis);
        Course ComputerVision = new Course("computer_vision", CourseLevel.MASTER);
        courses.add(ComputerVision);

        Student Alice = new Student("Alice");
        students.add(Alice);
        Student Bob = new Student("Bob");
        students.add(Bob);
        Student Alex = new Student("Alex");
        students.add(Alex);
        Alice.enroll(JavaBeginner);
        Alice.enroll(JavaIntermediate);
        Alice.enroll(PythonBasic);
        Bob.enroll(JavaBeginner);
        Bob.enroll(Algorithms);
        Alex.enroll(AdvancedProgramming);

        Professor Ali = new Professor("Ali");
        professors.add(Ali);
        Professor Ahmed = new Professor("Ahmed");
        professors.add(Ahmed);
        Professor Andrey = new Professor("Andrey");
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
        for (Course enrolled: enrolledCourse){
            if (enrolled.getCourseld() == course.getCourseld()){
                enrolledCourse.remove(course);
                course.Drop(this);
                return true;
            }
        }
        System.out.println("Student is not enrolled in this course");
        return false;
    }
    public boolean enroll(Course course){
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
        if (course.isFull()){
            System.out.println("Course is full");
            return false;
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
        super(numberOfMembers+1, memberName);
    }
    public boolean teach(Course course){
        if(assigmentCourses.size()>=MAX_LOAD){
            System.out.println("Professor's load is complete");
            return false;
        }
        for (Course assigned: assigmentCourses){
            if (assigned.getCourseld() == course.getCourseld()){
                System.out.println("Professor is already teaching this course");
                return false;
            }

        }
        assigmentCourses.add(course);
        return true;
    }
    public boolean exempt(Course course){
        for(Course assigned : assigmentCourses){
            if(course.getCourseld() == assigned.getCourseld()){
                assigmentCourses.remove(course);
                return true;
            }
        }
        System.out.println("Professor is not teaching this course");
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
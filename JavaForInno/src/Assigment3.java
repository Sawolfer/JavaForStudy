import java.security.cert.TrustAnchor;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class UniversityCourseManagementSystem {

    Scanner sc = new Scanner(System.in);
    public void main(String[] args) {
        String _currentString = sc.next();
        fillInitialData();

        switch (_currentString){
            case ("course"):

                break;
            case ("student"):
                String memberNameStud = sc.next();
                Student _newStudent = new Student(memberNameStud);
                break;
            case ("professor"):
                String memberNameProf = sc.next();
                Professor _newProfessor = new Professor(memberNameProf);
                break;
            case ("enroll"):

                break;
            case ("drop"):
                break;
            case ("teach"):
                break;
            case ("exempt"):
                break;
            default:
                Finish();
                break;
        }
    }

    public void Finish(){
        System.exit(0);
    }
    public void fillInitialData(){
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
    private static int MAX_ENROLLMENT;
    private List<Course> enrolledCourse;


    public Student(String memberName){
        super(numberOfMembers++, memberName);
    }

    public boolean drop(Course course){

        return true;
    }
    public boolean enroll(Course course){

        return true;
    }


}

class Professor extends UniversityMember{
    private static int MAX_LOAD;
    private List<Course> assigmentCourses;


    public Professor(String memberName){
        super(numberOfMembers++, memberName);
    }
    public boolean teach(Course course){

        return true;
    }
    public boolean exempt(Course course){
        
        return true;
    }
}

enum CourseLevel{
    BACHELOR,
    MASTER
}

class Course{
    private static int CAPASITY;
    private static int numberOfCourse;

    private int courseld;
    private String courseName;
    private List<Student> enrolledStudents;
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
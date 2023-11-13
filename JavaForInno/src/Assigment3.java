import java.security.cert.TrustAnchor;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class UniversityCourseManagementSystem {
    public void main(String[] args) {

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

class Student extends UniversityMember{
    private static int MAX_ENROLLMENT;
    private List<Course> enrolledCourse;


    public Student(String memberName){
        super(numberOfMembers++, memberName);
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

        return true;
    }
}
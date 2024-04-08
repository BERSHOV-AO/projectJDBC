import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Student> students = CRUDUtils.getStudentData("SELECT * FROM students");

        System.out.println(students);
    }

}

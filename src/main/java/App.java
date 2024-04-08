import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Student> students = CRUDUtils.getStudentData("SELECT * FROM students");

        System.out.println(students);

        Student student = new Student();
        student.setName("Ivan");
        student.setSurname("Ivanov");
        student.setCourse_name("Automation QA");

        System.out.println(CRUDUtils.saveStudent(student));
    }

}

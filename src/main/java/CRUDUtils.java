import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    // Указываем (?, ?, ?), чт бы потом в  preparedStatement передать нужные параметры
    private static String INSERT_STUDENT = "INSERT INTO students(name, surname, course_name) VALUES (?, ?, ?);";
    private static String UPDATE_STUDENT = "UPDATE students SET course_name = ? WHERE id = ?";
    private static String DELETE_STUDENT = "DELETE FROM students WHERE id = ?";

    public static List<Student> getStudentData(String query) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // нужно отправить запрос на получение всех студентов, executeQuery() вернет нам значение которые мы запросили
            ResultSet rs = preparedStatement.executeQuery();

            // пока в этой коллекции что то есть (rs.next()), будем работать
            while (rs.next()) {
                // хотим получить конкретного студента
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                students.add(new Student(id, name, surname, course_name));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }


    public static List<Student> saveStudent(Student student) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            // работаем с нашими (?, ?, ?), так как у нас string, передаем индекс параметра и значение
            // у нас индекс начинается с 1, так как 0 это id
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCourse_name());
            // для обновления тех значений которые мы ввели, вызываем у preparedStatement метод executeUpdate
            preparedStatement.executeUpdate();

            //--------------------------------------------------------------------------------------
            // что бы посмотреть на результат из базы данных
            // Для самого метода saveStudent, это не нужно, это нужно просто что бы посмотреть на то что мы добавили
            // студента в базу данных
            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudent.executeQuery();

            while (rs.next()) {
                // хотим получить конкретного студента
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                students.add(new Student(id, name, surname, course_name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    public static List<Student> updateStudent(int studentId, String courseName) {

        List<Student> updateStudents = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            // работаем с нашими (?, ?, ?), так как у нас string, передаем индекс параметра и значение
            // у нас индекс начинается с 1, так как 0 это id
            preparedStatement.setString(1, courseName);
            preparedStatement.setInt(2, studentId);
            // для обновления тех значений которые мы ввели, вызываем у preparedStatement метод executeUpdate
            preparedStatement.executeUpdate();

            //--------------------------------------------------------------------------------------
            // для проверки
            // Для самого метода updateStudent, это не нужно, это нужно просто что бы посмотреть на то что мы добавили
            // студента в базу данных
            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudent.executeQuery();

            while (rs.next()) {
                // хотим получить конкретного студента
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                updateStudents.add(new Student(id, name, surname, course_name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updateStudents;
    }

    public static List<Student> deleteStudents(int studentId) {
        List<Student> updateStudents = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {

            // работаем с нашими (?, ?, ?), так как у нас string, передаем индекс параметра и значение
            // у нас индекс начинается с 1, так как 0 это id
            preparedStatement.setInt(1, studentId);
            // для обновления тех значений которые мы ввели, вызываем у preparedStatement метод executeUpdate
            preparedStatement.executeUpdate();

            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudent.executeQuery();

            while (rs.next()) {
                // хотим получить конкретного студента
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                updateStudents.add(new Student(id, name, surname, course_name));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updateStudents;

    }

}

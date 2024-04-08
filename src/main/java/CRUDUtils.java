import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CRUDUtils {

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

}

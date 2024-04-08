import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    // "jdbc:h2:mem:test"
    // но для подключения нужно добавить скрипт для создания таблицы INIT=RUNSCRIPT
    // по пути FROM 'classpath:init.sql -- то есть в папке с ресурсами
    // в папке target будет лежать наш скрипт, программа найдет по classpath
    private  static  String dbURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'";
    private  static  String dbUsername = "sa";
    private  static  String dbPassword = ""; // по умолчанию пароля нет


    // метод для подключения базы данных
    public static Connection getConnection() {


        //--------------------------------------
//        String dbURL = null;
//        String dbUsername = "sa";
//        String dbPassword = "";
//
//        FileInputStream fis;
//        Properties properties = new Properties();
//
//        try {
//            fis = new FileInputStream("src/main/resources/config.properties");
//            properties.load(fis);
//            dbURL = properties.getProperty("db.host");
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //--------------------------------------


        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

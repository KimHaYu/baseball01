package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Team {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_name";
        String username = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE table_name ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "stadium_id INT,"
                    + "name VARCHAR(255),"
                    + "created_at TIMESTAMP,"
                    + "FOREIGN KEY (stadium_id) REFERENCES stadium_table(stadium_id)"
                    + ")";

            statement.executeUpdate(sql);
            System.out.println("Table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
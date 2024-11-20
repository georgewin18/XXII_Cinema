package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/xxii_cinema";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void testConnection() {
        try (Connection connect = getConnection()) {
            if (connect != null && !connect.isClosed()) {
                System.out.println("Connected successfully");
            } else {
                System.out.println("Failed to connect");
            }
        } catch (SQLException e) {
            System.out.println("error occured");
            e.printStackTrace();
        }
    }
}

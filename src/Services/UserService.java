package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.DBConnection;

public class UserService {
    private String query;

    public boolean signUpUser(String username, String password, String role) {
        query = "INSERT INTO users (username, password, role) " +
                "VALUES (?, MD5(?), ?)";
        
        try (Connection connect = DBConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(query)) {
            
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

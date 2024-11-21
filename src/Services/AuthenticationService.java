package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBConnection;
import Models.User;

public class AuthenticationService {
    public User authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = MD5(?)";
        
        try (Connection connect = DBConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(query)) {
            
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new User(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

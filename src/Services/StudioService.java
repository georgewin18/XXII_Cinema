package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import Models.Studio;

public class StudioService {
    public List<Studio> getAllStudio() {
        List<Studio> studios = new ArrayList<Studio>();
        String query = "SELECT * FROM studios";

        try (Connection connect = DBConnection.getConnection();
             Statement statement = connect.createStatement();
             ResultSet result = statement.executeQuery(query)){
            while (result.next()) {
                studios.add(new Studio(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("base_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studios;
    }
}

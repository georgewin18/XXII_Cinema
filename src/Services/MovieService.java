package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import Models.Movie;

public class MovieService {
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        String query = "SELECT m.id, m.title, m.genre, m.available_seats, s.name AS studio_name, s.base_price " +
                       "FROM movies m " +
                       "JOIN studios s " + 
                       "ON m.studio_id = s.id";

        try (Connection connect = DBConnection.getConnection();
             Statement statement = connect.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                movies.add(new Movie(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getString("genre"),
                    result.getInt("available_seats"),
                    result.getString("studio_name"),
                    result.getDouble("base_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}

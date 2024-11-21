package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import Models.Movie;

public class MovieService {
    private List<Movie> movies;
    private String query;

    public List<Movie> getAllMovies() {
        movies = new ArrayList<Movie>();
        query = "SELECT m.id, m.title, m.genre, s.name AS studio_name, s.base_price, m.showtime " +
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
                    result.getString("studio_name"),
                    result.getDouble("base_price"),
                    result.getString("showtime")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Movie getMovieById(int movieId) {
        query = "SELECT * FROM (" +
                "SELECT m.id, m.title, m.genre, s.name AS studio_name, s.base_price, m.showtime " +
                "FROM movies m " +
                "JOIN studios s " + 
                "ON m.studio_id = s.id) AS movie " +
                "WHERE movie.id = ?";
        try (Connection connect = DBConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(query)) {
            
            statement.setInt(1, movieId);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                return new Movie(
                    result.getInt("id"),
                    result.getString("title"), 
                    result.getString("genre"),
                    result.getString("studio_name"),
                    result.getDouble("base_price"),
                    result.getString("showtime")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addMovie(String title, String genre, int studioId, String showtime) {
        query = "INSERT INTO movies (title, genre, studio_id, showtime) " + 
                "VALUES (?, ?, ?, ?)";
        
        try (Connection connect = DBConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(query)) {

            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setInt(3, studioId);
            statement.setString(4, showtime);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMovie(int movieId) {
        query = "DELETE FROM movies WHERE id = ?";

        try (Connection connect = DBConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(query)) {
            
            statement.setInt(1, movieId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

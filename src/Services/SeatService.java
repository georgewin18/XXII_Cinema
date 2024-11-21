package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import Models.Seat;

public class SeatService {
    private List<Seat> seats;
    private String query;

    public List<Seat> getSeats(int movieId) {
        seats = new ArrayList<Seat>();
        query = "SELECT * FROM seats WHERE movie_id = ?";

        try (Connection connect = DBConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(query)) {
            
            statement.setInt(1, movieId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                seats.add(new Seat(
                    result.getInt("id"),
                    result.getInt("movie_id"),
                    result.getString("seat_number"),
                    result.getBoolean("is_booked")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    public boolean bookSeat(int seatId) {
        query = "UPDATE seats SET is_booked = TRUE WHERE id = ?";
        try (Connection connect = DBConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(query)) {
            
            statement.setInt(1, seatId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

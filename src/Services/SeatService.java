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
    public List<Seat> getAvailableSeats(int movieId) {
        List<Seat> seats = new ArrayList<Seat>();
        String query = "SELECT * FROM seats WHERE movie_id = ? AND is_booked = FALSE";

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
        String query = "UPDATE seats SET is_booked = TRUE WHERE id = ?";
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

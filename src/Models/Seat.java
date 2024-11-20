package Models;

public class Seat {
    private int id;
    private int movieId;
    private String seatNumber;
    private boolean isBooked;

    public Seat(int id, int movieId, String seatNumber, boolean isBooked) {
        this.id = id;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getId() {
        return this.id;
    }

    public int getMovieId() {
        return this.movieId;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    } 
}

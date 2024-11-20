package Models;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private int availableSeats;
    private String studioName;
    private double basePrice;

    public Movie(int id, String title, String genre, int availableSeats, String studioName, double basePrice) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.availableSeats = availableSeats;
        this.studioName = studioName;
        this.basePrice = basePrice;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getAvailableSeats() {
        return this.availableSeats;
    }

    public String getStudioName() {
        return this.studioName;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}

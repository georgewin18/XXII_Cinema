package Models;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private String studioName;
    private double basePrice;
    private String showtime;

    public Movie(int id, String title, String genre, String studioName, double basePrice, String showtime) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.studioName = studioName;
        this.basePrice = basePrice;
        this.showtime = showtime;
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

    public String getStudioName() {
        return this.studioName;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public String getShowtime() {
        return this.showtime;
    }
}

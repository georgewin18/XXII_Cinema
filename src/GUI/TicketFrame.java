package GUI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Models.Movie;
import Services.MovieService;

public class TicketFrame extends JFrame {
    public TicketFrame(int movieId, String seatNumber) {
        setTitle("Yout Ticket");
        setSize(480, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        MovieService movieService = new MovieService();
        Movie movie = movieService.getMovieById(movieId);

        JLabel titleLabel = new JLabel("Movie Ticket", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel movieLabel = new JLabel(
            "Movie: " + movie.getTitle(), SwingConstants.CENTER
        );

        JLabel studioLabel = new JLabel(
            "Studio: " + movie.getStudioName(), SwingConstants.CENTER
        );

        JLabel showtimeLabel = new JLabel(
            "Showtime: " + movie.getShowtime(), SwingConstants.CENTER
        );

        JLabel seatLabel = new JLabel(
            "Seat: " + seatNumber, SwingConstants.CENTER
        );

        JLabel priceLabel = new JLabel(
            "Price: Rp " + movie.getBasePrice(), SwingConstants.CENTER
        );

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        add(titleLabel);
        add(movieLabel);
        add(studioLabel);
        add(showtimeLabel);
        add(seatLabel);
        add(priceLabel);
        add(closeButton);
    }
}

package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Models.Movie;
import Services.MovieService;

public class TicketFrame extends JFrame {
    private MovieService movieService;
    private Movie movie;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private JLabel ticketLabel;
    private JLabel movieLabel;
    private JLabel studioLabel;
    private JLabel showtimeLabel;
    private JLabel seatLabel;
    private JLabel priceLabel;
    private JButton closeButton;

    public TicketFrame(int movieId, String seatNumber) {
        setTitle("Yout Ticket");
        setSize(360, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 1));

        movieService = new MovieService();
        movie = movieService.getMovieById(movieId);

        headerPanel = new JPanel();
        headerLabel = new JLabel("XXII");
        headerLabel.setFont(new Font("Georgia", 1, 48));
        headerPanel.add(headerLabel);

        ticketLabel = new JLabel("Movie Ticket", SwingConstants.CENTER);
        ticketLabel.setFont(new Font("Arial", Font.BOLD, 20));

        movieLabel = new JLabel(
            "Movie: " + movie.getTitle(), SwingConstants.CENTER
        );

        studioLabel = new JLabel(
            "Studio: " + movie.getStudioName(), SwingConstants.CENTER
        );

        showtimeLabel = new JLabel(
            "Showtime: " + movie.getShowtime(), SwingConstants.CENTER
        );

        seatLabel = new JLabel(
            "Seat: " + seatNumber, SwingConstants.CENTER
        );

        priceLabel = new JLabel(
            "Price: Rp " + movie.getBasePrice(), SwingConstants.CENTER
        );

        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        add(headerPanel, BorderLayout.NORTH);
        add(ticketLabel);
        add(movieLabel);
        add(studioLabel);
        add(showtimeLabel);
        add(seatLabel);
        add(priceLabel);
        add(closeButton);
    }
}

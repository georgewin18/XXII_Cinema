package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.util.List;

import Services.MovieService;
import Services.SeatService;
import Models.Movie;
import Models.Seat;

public class BookingFrame extends JFrame {
    private JTable movieTable;
    private JButton bookButton;
    
    public BookingFrame() {
        setTitle("XXII Cinema Ticket Reservation");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Title", "Genre", "Available Seats", "Studio", "Price"};
        MovieService movieService = new MovieService();
        List<Movie> movies = movieService.getAllMovies();
        String[][] data = new String[movies.size()][6];

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            data[i][0] = String.valueOf(movie.getId());
            data[i][1] = movie.getTitle();
            data[i][2] = movie.getGenre();
            data[i][3] = String.valueOf(movie.getAvailableSeats());
            data[i][4] = movie.getStudioName();
            data[i][5] = String.format("Rp %.2f", movie.getBasePrice());
        }

        movieTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(movieTable);

        bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();

            if (selectedRow != -1) {
                int movieId = Integer.parseInt(data[selectedRow][0]);
                showSeatSelection(movieId);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a movie!");
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);
    }

    private void showSeatSelection(int movieId) {
        SeatService seatService = new SeatService();
        List<Seat> availableSeats = seatService.getAvailableSeats(movieId);

        String[] seatNumbers = availableSeats.stream().map(Seat::getSeatNumber).toArray(String[]::new);

        String selectedSeat = (String) JOptionPane.showInputDialog(
            this,
            "Select a Seat:", 
            "Seat Selection",
            JOptionPane.PLAIN_MESSAGE,
            null,
            seatNumbers,
            seatNumbers[0]
        );

        if (selectedSeat != null) {
            Seat selectedSeatObject = availableSeats.stream()
            .filter(seat -> seat.getSeatNumber().equals(selectedSeat))
            .findFirst()
            .orElse(null);

            if (selectedSeatObject != null && seatService.bookSeat(selectedSeatObject.getId())) {
                JOptionPane.showMessageDialog(this, "Booked seat: " + selectedSeat);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to book seat");
            }
        }
    }

    // public static void main(String [] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         BookingFrame frame = new BookingFrame();
    //         frame.setVisible(true);
    //     });
    // }
}

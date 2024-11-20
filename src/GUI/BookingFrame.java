package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import Services.MovieService;
import Models.Movie;

public class BookingFrame extends JFrame {
    private JTable movieTable;
    private JButton bookButton;
    
    public BookingFrame() {
        setTitle("XXII Cinema Ticket Reservation");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("NOW SHOWING", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(headerLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Title", "Genre", "Studio", "Price", "Showtime"};
        MovieService movieService = new MovieService();
        List<Movie> movies = movieService.getAllMovies();
        String[][] data = new String[movies.size()][6];

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            data[i][0] = String.valueOf(movie.getId());
            data[i][1] = movie.getTitle();
            data[i][2] = movie.getGenre();
            data[i][3] = movie.getStudioName();
            data[i][4] = String.format("Rp %.2f", movie.getBasePrice());
            data[i][5] = movie.getShowtime();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        movieTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(movieTable);

        bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow != -1) {
                int movieId = Integer.parseInt(data[selectedRow][0]);
                SwingUtilities.invokeLater(() -> {
                    SeatSelectionFrame seatSelectionFrame = new SeatSelectionFrame(movieId);
                    seatSelectionFrame.setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "Please select a movie"
                );
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);
    }
}

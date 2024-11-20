package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Models.Movie;
import Services.MovieService;

public class ViewMoviesFrame extends JFrame {
    private JTable movieTable;
    private DefaultTableModel tableModel;

    public ViewMoviesFrame() {
        setTitle("Movies List");
        setSize(720, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("NOW SHOWING", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(headerLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Title", "Studio", "Price", "Showtime"};
        tableModel = new DefaultTableModel(columnNames, 0);

        movieTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(movieTable);
        add(scrollPane, BorderLayout.CENTER);

        populateTable();

        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Selected Movie");
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        deleteButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Pelase select a movie to delete"
                );
                return;
            }

            int movieId = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to delete this movie?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = new MovieService().deleteMovie(movieId);
                if (success) {
                    JOptionPane.showMessageDialog(
                        this, 
                        "Movie deleted successfully"
                    );
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                        this, 
                        "Failed to delete movie"
                    );
                }
            }
        });
    }

    private void populateTable() {
        MovieService movieService = new MovieService();
        List<Movie> movies = movieService.getAllMovies();
        for (Movie movie : movies) {
            Object[] rowData = {
                movie.getId(),
                movie.getTitle(),
                movie.getStudioName(),
                movie.getBasePrice(),
                movie.getShowtime()
            };
            tableModel.addRow(rowData);
        }
    }
}

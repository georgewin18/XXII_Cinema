package GUI;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Services.MovieService;

public class AddMovieFrame extends JFrame {
    private HashMap<String, Integer> studioMapping;
    private JLabel titleLabel;
    private JTextField titleField;
    private JLabel genreLabel;
    private JTextField genreField;
    private JLabel studioLabel;
    private JComboBox<String> studioBox;
    private JLabel showtimeLabel;
    private JTextField showtimeField;
    private JButton addButton;
    private JButton cancelButton;

    public AddMovieFrame() {
        setTitle("Add New Movie");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        studioMapping = new HashMap<>();
        studioMapping.put("Regular", 1);
        studioMapping.put("Premier", 2);
        studioMapping.put("IMAX", 3);

        titleLabel = new JLabel("Movie Title: ");
        titleField = new JTextField(); 

        genreLabel = new JLabel("Genre: ");
        genreField = new JTextField();
        
        studioLabel = new JLabel("Studio: ");
        studioBox = new JComboBox<String>(new String[] {"Regular", "Premier", "IMAX"});

        showtimeLabel = new JLabel("Showtime: ");
        showtimeField = new JTextField();

        addButton = new JButton("Add Movie");
        cancelButton = new JButton("Cancel");

        add(titleLabel);
        add(titleField);
        
        add(genreLabel);
        add(genreField);

        add(studioLabel);
        add(studioBox);

        add(showtimeLabel);
        add(showtimeField);

        add(addButton);
        add(cancelButton);

        addButton.addActionListener(e -> addMovie());

        cancelButton.addActionListener(e -> dispose());
    }

    private void addMovie() {
        String title = titleField.getText();
        String genre = genreField.getText();
        String studio = studioBox.getSelectedItem().toString();
        String showtime = showtimeField.getText();
        int studioId = studioMapping.get(studio);

        if (title.isEmpty() || genre.isEmpty() || showtime.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, 
                "All fields must be filled");
        }

        try {
            boolean success = new MovieService().addMovie(title, genre, studioId, showtime);

            if (success) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Movie added successfully"
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "Failed to add movie"
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

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
    public AddMovieFrame() {
        setTitle("Add New Movie");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        HashMap<String, Integer> studioMapping = new HashMap<>();
        studioMapping.put("Regular", 1);
        studioMapping.put("Premier", 2);
        studioMapping.put("IMAX", 3);

        JLabel titleLabel = new JLabel("Movie Title: ");
        JTextField titleField = new JTextField(); 

        JLabel genreLabel = new JLabel("Genre: ");
        JTextField genreField = new JTextField();
        
        JLabel studioLabel = new JLabel("Studio: ");
        JComboBox<String> studioBox = new JComboBox<String>(new String[] {"Regular", "Premier", "IMAX"});

        JLabel showtimeLabel = new JLabel("Showtime: ");
        JTextField showtimeField = new JTextField();

        JButton addButton = new JButton("Add Movie");
        JButton cancelButton = new JButton("Cancel");

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

        addButton.addActionListener(e -> {
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
        });

        cancelButton.addActionListener(e -> dispose());
    }
}

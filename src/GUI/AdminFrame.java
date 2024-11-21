package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class AdminFrame extends JFrame {
    private JLabel label;
    private JPanel buttonPanel;
    private JButton addMovieButton;
    private JButton viewMoviesButton;
    private JButton logoutButton;

    public AdminFrame() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(label, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        addMovieButton = new JButton("Add Movie");
        viewMoviesButton = new JButton("View Movies");
        logoutButton = new JButton("Logout");

        buttonPanel.add(addMovieButton);
        buttonPanel.add(viewMoviesButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        addMovieButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new AddMovieFrame().setVisible(true));
        });

        viewMoviesButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new ViewMoviesFrame().setVisible(true));
        });

        logoutButton.addActionListener(e -> confimLogOut());
    }

    private void confimLogOut() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to logout?", 
            "Logout Confirmation", 
            JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        }
    }
}

package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Models.User;
import Services.AuthenticationService;

public class LoginFrame extends JFrame {
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel usernamePanel;
    private JTextField usernameField;
    private JPanel passwordPanel;
    private JPasswordField passwordField;
    private JPanel buttonPanel;
    private JButton loginButton;
    private JLabel signUpLabel;
    
    public LoginFrame() {
        setTitle("XXII Cinema Login");
        setSize(480, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        titlePanel = new JPanel();
        titleLabel = new JLabel("XXII");
        titleLabel.setFont(new Font("Georgia", 1, 48));
        titlePanel.add(titleLabel);

        usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Username :"));
        usernameField = new JTextField(20);
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        usernamePanel.add(usernameField);

        passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Password :"));
        passwordField = new JPasswordField(20);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        passwordPanel.add(passwordField);

        buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> performLogin());
        buttonPanel.add(loginButton);

        signUpLabel = new JLabel("<HTML>Don't have an account? Sign Up</HTML>");
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signUpLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    SignUpFrame signUpFrame = new SignUpFrame();
                    signUpFrame.setVisible(true);
                });
            }

            public void mouseEntered(MouseEvent e) {
                signUpLabel.setForeground(Color.GRAY);
            }

            public void mouseExited(MouseEvent e) {
                signUpLabel.setForeground(Color.BLUE);
            }
        });

        add(titlePanel, BorderLayout.NORTH);
        add(usernamePanel, BorderLayout.CENTER);
        add(passwordPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(signUpLabel, BorderLayout.SOUTH);
    }  
    
    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        AuthenticationService authenticationService = new AuthenticationService();
        User user = authenticationService.authenticate(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Welcome, " + user.getUsername() + " (" + user.getRole() + ") ");
            dispose();

            if (user.getRole().equals("Admin")) {
                SwingUtilities.invokeLater(() -> {
                    AdminFrame adminFrame = new AdminFrame();
                    adminFrame.setVisible(true);
                });
            } else {
                SwingUtilities.invokeLater(() -> {
                    BookingFrame bookingFrame = new BookingFrame();
                    bookingFrame.setVisible(true);
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String [] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

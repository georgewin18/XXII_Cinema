package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Models.User;
import Services.AuthService;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("XXII Cinema Login");
        setSize(480, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Username :"));
        usernameField = new JTextField(20);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Password :"));
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> performLogin());
        buttonPanel.add(loginButton);

        add(usernamePanel);
        add(passwordPanel);
        add(buttonPanel);
    }  
    
    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        AuthService authService = new AuthService();
        User user = authService.authenticate(username, password);

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

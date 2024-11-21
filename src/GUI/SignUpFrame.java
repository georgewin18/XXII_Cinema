package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Services.UserService;

public class SignUpFrame extends JFrame {
    private JLabel titleLabel;
    private JPanel usernamePanel;
    private JTextField usernameField;
    private JPanel passwordPanel;
    private JPasswordField passwordField;
    private JPanel rolePanel;
    private JComboBox<String> roleBox;
    private JPanel buttonPanel;
    private JButton signUpButton;

    public SignUpFrame() {
        setTitle("Sign Up");
        setSize(480, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        titleLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 20));

        usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Username"));
        usernameField = new JTextField(20);
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        usernamePanel.add(usernameField);

        passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Password :"));
        passwordField = new JPasswordField(20);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        passwordPanel.add(passwordField);
        
        rolePanel = new JPanel();
        rolePanel.add(new JLabel("Role :"));
        roleBox = new JComboBox<String>(new String[] {"User", "Admin"});
        roleBox.setPreferredSize(new Dimension(190, 20));
        rolePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        rolePanel.add(roleBox);

        buttonPanel = new JPanel();
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> signUpUser());
        buttonPanel.add(signUpButton);

        add(titleLabel);
        add(usernamePanel);
        add(passwordPanel);
        add(rolePanel);
        add(buttonPanel);
    }

    private void signUpUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = roleBox.getSelectedItem().toString();

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, 
                "All fields are required!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        
        try {
            boolean success = new UserService().signUpUser(username, password, role);

            if (success) {
                JOptionPane.showMessageDialog(
                    this, 
                    "You're all set"
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "Failed to Sign Up"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

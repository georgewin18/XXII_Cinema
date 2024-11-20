package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AdminFrame extends JFrame {
    public AdminFrame() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
        add(label);
    }
}

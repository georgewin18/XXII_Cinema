package GUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Models.Seat;
import Services.SeatService;

public class SeatSelectionFrame extends JFrame {
    private int movieId;
    private JPanel seatPanel;
    private SeatService seatService;
    private List<Seat> seats;
    private JPanel screenPanel;

    public SeatSelectionFrame(int movieId) {
        this.movieId = movieId;
        setTitle("Select Seat");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        seatPanel = new JPanel();
        seatPanel.setSize(800, 800);
        seatPanel.setLayout(new GridLayout(8, 8, 5, 5));

        seatService = new SeatService();
        seats = seatService.getSeats(this.movieId);

        for (Seat seat : seats) {
            JButton seatButton = new JButton(seat.getSeatNumber());
            seatButton.setEnabled(!seat.isBooked());
            seatButton.setPreferredSize(new Dimension(90, 90));
            if (seat.isBooked()) {
                seatButton.setBackground(Color.RED);
            } else {
                seatButton.setBackground(Color.GREEN);
            }

            seatButton.addActionListener(e -> bookSeat(seat, seatButton));
            seatPanel.add(seatButton);
        }

        screenPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponents(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawLine(20, getHeight() / 2, getWidth() - 20, getHeight() / 2);
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                g2d.drawString("SCREEN", getWidth() / 2 - 40, getHeight() / 2 - 10);
            }
        };
        screenPanel.setPreferredSize(new Dimension(600, 50));

        add(seatPanel, BorderLayout.NORTH);
        add(screenPanel, BorderLayout.SOUTH);
    }

    private void showTicket(int movieId, String seatNumber) {
        TicketFrame ticketFrame = new TicketFrame(movieId, seatNumber);
        ticketFrame.setVisible(true);
    }

    private void bookSeat(Seat seat, JButton seatButton) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Reserve seat " + seat.getSeatNumber() + " ?",
            "Confirm seat", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (seatService.bookSeat(seat.getId())) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Seat Booked Successully"
                );
                seatButton.setEnabled(false);
                seatButton.setBackground(Color.RED);
                showTicket(this.movieId, seat.getSeatNumber());
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "Failed to book seat");
            }
        }
    }
}

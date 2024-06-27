package banksystem.main;

import javax.swing.*;
import java.awt.*;

public class CardLayoutExample {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public CardLayoutExample() {
        frame = new JFrame("CardLayout Example");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create the panels
        JPanel panel1 = createPanel("Panel 1", Color.RED);
        JPanel panel2 = createPanel("Panel 2", Color.GREEN);
        JPanel panel3 = createPanel("Panel 3", Color.BLUE);

        // Add panels to the main panel with card layout
        mainPanel.add(panel1, "1");
        mainPanel.add(panel2, "2");
        mainPanel.add(panel3, "3");

        // Create buttons to switch panels
        JPanel buttonPanel = new JPanel();
        JButton button1 = new JButton("Show Panel 1");
        JButton button2 = new JButton("Show Panel 2");
        JButton button3 = new JButton("Show Panel 3");

        button1.addActionListener(e -> cardLayout.show(mainPanel, "1"));
        button2.addActionListener(e -> cardLayout.show(mainPanel, "2"));
        button3.addActionListener(e -> cardLayout.show(mainPanel, "3"));

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        // Add main panel and button panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private JPanel createPanel(String text, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.add(new JLabel(text));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CardLayoutExample::new);
    }
}

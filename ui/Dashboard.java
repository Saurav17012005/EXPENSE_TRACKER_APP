package ui;

import javax.swing.*;

public class Dashboard {
    public Dashboard(int userId) {
        JFrame frame = new JFrame("Dashboard");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton expenseTrackerButton = new JButton("Go to Expense Tracker");

        expenseTrackerButton.addActionListener(e -> {
            frame.dispose();
            new ExpenseTrackerUI(userId);
        });

        JPanel panel = new JPanel();
        panel.add(expenseTrackerButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginUI(); // ✅ Starts with Login UI instead of assuming a user ID
    }
}
package ui;

import javax.swing.*;
import java.awt.*;
import dao.ExpenseDAO;
import model.Expense;
import java.util.List;

public class ExpenseTrackerUI {
    private final int userId;

    public ExpenseTrackerUI(int userId) {
        this.userId = userId;
        JFrame frame = new JFrame("Expense Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // ✅ Adds spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Expense Tracker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField amountField = new JTextField(10);
        JTextField categoryField = new JTextField(10);
        JTextField descriptionField = new JTextField(10);
        JButton addExpenseButton = new JButton("Add Expense");
        JButton totalExpensesButton = new JButton("Show Total Expenses");
        JButton logoutButton = new JButton("Log Out");

        addExpenseButton.setBackground(Color.GREEN);
        totalExpensesButton.setBackground(Color.BLUE);
        logoutButton.setBackground(Color.RED);

        addExpenseButton.setForeground(Color.WHITE);
        totalExpensesButton.setForeground(Color.WHITE);
        logoutButton.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        frame.add(new JLabel("Amount:"), gbc);
        gbc.gridx++;
        frame.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.add(new JLabel("Category:"), gbc);
        gbc.gridx++;
        frame.add(categoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.add(new JLabel("Description:"), gbc);
        gbc.gridx++;
        frame.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        frame.add(addExpenseButton, gbc);

        gbc.gridy++;
        frame.add(totalExpensesButton, gbc);

        gbc.gridy++;
        frame.add(logoutButton, gbc);

        addExpenseButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryField.getText();
                String description = descriptionField.getText();

                ExpenseDAO expenseDAO = new ExpenseDAO();
                boolean success = expenseDAO.addExpense(new Expense(userId, amount, category, description));

                JOptionPane.showMessageDialog(frame, success ? "✅ Expense added!" : "❌ Failed to add expense.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "❌ Invalid amount. Please enter a numeric value.");
            }
        });

        totalExpensesButton.addActionListener(e -> {
            ExpenseDAO expenseDAO = new ExpenseDAO();
            List<Expense> expenses = expenseDAO.getAllExpenses(userId);
            StringBuilder expenseList = new StringBuilder("💰 Your Expenses:\n");

            double totalExpenses = 0;
            for (Expense expense : expenses) {
                expenseList.append("📌 Amount: $").append(expense.getAmount())
                        .append(", Category: ").append(expense.getCategory())
                        .append(", Description: ").append(expense.getDescription())
                        .append("\n");
                totalExpenses += expense.getAmount();
            }

            expenseList.append("\n💰 Total Expenses: $").append(totalExpenses);
            JOptionPane.showMessageDialog(frame, expenseList.toString());
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginUI();
        });

        frame.setVisible(true);
    }
}
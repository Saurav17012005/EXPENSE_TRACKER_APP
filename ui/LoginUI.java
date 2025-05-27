package ui;

import javax.swing.*;
import java.awt.*;
import dao.UserDAO;

public class LoginUI {
    public LoginUI() {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Welcome Back!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Log In");
        JButton goToRegisterButton = new JButton("Register");

        loginButton.setBackground(Color.BLUE);
        goToRegisterButton.setBackground(Color.GREEN);

        loginButton.setForeground(Color.WHITE);
        goToRegisterButton.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        frame.add(new JLabel("Username:"), gbc);
        gbc.gridx++;
        frame.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.add(new JLabel("Password:"), gbc);
        gbc.gridx++;
        frame.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        frame.add(loginButton, gbc);

        gbc.gridy++;
        frame.add(goToRegisterButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ Please enter both username and password.");
                return;
            }

            UserDAO userDAO = new UserDAO();
            int userId = userDAO.authenticateUser(username, password);

            if (userId > 0) {
                JOptionPane.showMessageDialog(frame, "✅ Login successful!");
                frame.dispose();
                new Dashboard(userId);
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Invalid credentials.");
            }
        });

        goToRegisterButton.addActionListener(e -> {
            frame.dispose();
            new RegisterUI();
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}
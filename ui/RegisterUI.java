package ui;

import javax.swing.*;
import java.awt.*;
import dao.UserDAO;

public class RegisterUI {
    public RegisterUI() {
        JFrame frame = new JFrame("Register");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Register New Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton registerButton = new JButton("Register");
        JButton goToLoginButton = new JButton("Go to Login");

        registerButton.setBackground(Color.GREEN);
        goToLoginButton.setBackground(Color.BLUE);

        registerButton.setForeground(Color.WHITE);
        goToLoginButton.setForeground(Color.WHITE);

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
        frame.add(registerButton, gbc);

        gbc.gridy++;
        frame.add(goToLoginButton, gbc);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ Please fill in all fields.");
                return;
            }

            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.registerUser(username, password);

            JOptionPane.showMessageDialog(frame, success ? "✅ Registration successful!" : "❌ Username already exists.");
            if (success) {
                frame.dispose();
                new LoginUI();
            }
        });

        goToLoginButton.addActionListener(e -> {
            frame.dispose();
            new LoginUI();
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterUI();
    }
}
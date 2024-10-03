/*package com.modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ChangePasswordForm {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton changePasswordButton;
    private JButton resetButton;

    public ChangePasswordForm() {
        // Set up the frame
        frame = new JFrame("Change Password");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        // Add fields and labels
        frame.add(new JLabel("Username:"));
        usernameField = new JTextField();
        frame.add(usernameField);

        frame.add(new JLabel("Old Password:"));
        oldPasswordField = new JPasswordField();
        frame.add(oldPasswordField);

        frame.add(new JLabel("New Password:"));
        newPasswordField = new JPasswordField();
        frame.add(newPasswordField);

        // Change Password Button
        changePasswordButton = new JButton("Change Password");
        frame.add(changePasswordButton);
        
        // Reset Button
        resetButton = new JButton("Reset");
        frame.add(resetButton);

        // Action listeners
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());

                UserDAO userDAO = new UserDAO();
                
                try {
                    // Validate old password
                    if (userDAO.validateCredentials(username, oldPassword)) {
                        // Change password
                        if (userDAO.changePassword(username, newPassword)) {
                            JOptionPane.showMessageDialog(frame, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error changing password!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or old password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Handle exceptions appropriately
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                oldPasswordField.setText("");
                newPasswordField.setText("");
            }
        });

        // Display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ChangePasswordForm();
    }
}
*/
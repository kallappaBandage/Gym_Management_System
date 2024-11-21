package com.modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

public class LoginForm {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;

    public LoginForm() {
    	// Set up the frame
        frame = new JFrame("Gym Management System");
        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Add background image
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\akash_bandage\\Desktop\\ITEP\\Gym Project Files\\Backgroun Images\\LoginPage.png");
        Image scaledImage = backgroundImg.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH); // Scaling the image
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\akash_bandage\\Desktop\\ITEP\\Gym Project Files\\Backgroun Images\\Logo.jpg"); 
        backgroundImg.setImage(icon);   
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setBounds(0, 0, 1366, 768);  // Setting the size and position to fit the frame

        // Add the background label to the frame's content pane
        frame.setContentPane(new JLabel(new ImageIcon(scaledImage))); 

        // Calculate the center position for the fields
        int fieldWidth = 300;
        int fieldHeight = 30;
        int centerX = (1366 - fieldWidth) / 2;
       //int centerY = (768 - (2 * fieldHeight + 50)) / 2;
        int centerY = (768 - (2 * fieldHeight + 200)) / 2;

        // Add Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(centerX + 350, centerY, 100, 30);
        
        frame.add(usernameLabel);

        // Add Username Text Field
        usernameField = new JTextField();
        usernameField.setBounds(centerX + 450, centerY, fieldWidth, fieldHeight);
        frame.add(usernameField);

        // Add Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBackground(Color.WHITE);
        passwordLabel.setBounds(centerX + 350, centerY + 50, 100, 30);
        frame.add(passwordLabel);

        // Add Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(centerX + 450, centerY + 50, fieldWidth, fieldHeight);
        passwordField.setEchoChar('•');
        frame.add(passwordField);

        // Add "Show Password" Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(centerX + 450, centerY + 90, 150, 30);
        frame.add(showPasswordCheckBox);

        // Action listener to show/hide password
        showPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('•');
                }
            }
        });

        // Add Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(centerX + 450, centerY + 130, fieldWidth / 2 - 10, 30);
        loginButton.setBackground(Color.decode("#4CAF50"));
        loginButton.setForeground(Color.WHITE);
        frame.add(loginButton);

        // Add Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(centerX + fieldWidth / 2 + 10 + 450, centerY + 130, fieldWidth / 2 - 10, 30);
        resetButton.setBackground(Color.decode("#f44336"));
        resetButton.setForeground(Color.WHITE);
        frame.add(resetButton);

        // Add "Forget Password" Button
        JButton forgetPasswordButton = new JButton("Forget Password");
        forgetPasswordButton.setBounds(centerX + 450, centerY + 170, fieldWidth, 30);
        forgetPasswordButton.setForeground(Color.BLUE);
        frame.add(forgetPasswordButton);

        // Add action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password1 = passwordField.getPassword();
                String password = new String(password1);
                
                if (authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new AdminHomePage(); // Go to Admin Home Page on successful login
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        // Action listener for the "Forget Password" button
        forgetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordPopup(); // Open the change password popup
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Placeholder authentication method
    private boolean authenticateUser(String username, String password) {
        UserDAO userDAO = new UserDAO();
        try {
            return userDAO.validateCredentials(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Main method
    public static void main(String[] args) {
        new LoginForm();
    }
}

// Class for Change Password Popup
class ChangePasswordPopup {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton changePasswordButton;
    private JButton resetButton;

    public ChangePasswordPopup() {
        // Set up the frame
        frame = new JFrame("Change Password");
        frame.setSize(550, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        //frame.getContentPane().setBackground(Color.decode("#f0f8ff"));
       
        

        // Calculate the center position for the fields
        int fieldWidth = 300;
        int fieldHeight = 30;
        int centerX = (600 - fieldWidth) / 2;
        int centerY = (400 - (3 * fieldHeight + 100)) / 2;

        // Add fields and labels
        JLabel usernameLabel = new JLabel("Username:");
        //usernameLabel.setBounds(centerX - 80, centerY, 100, 30);
        usernameLabel.setBounds(centerX - 100, centerY, 100, 30);
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(centerX, centerY, fieldWidth, fieldHeight);
        frame.add(usernameField);

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        //oldPasswordLabel.setBounds(centerX - 80, centerY + 40, 100, 30);
        oldPasswordLabel.setBounds(centerX - 100, centerY + 40, 100, 30);
        frame.add(oldPasswordLabel);

        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(centerX, centerY + 40, fieldWidth, fieldHeight);
        oldPasswordField.setEchoChar('•');
        frame.add(oldPasswordField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(centerX - 100, centerY + 80, 100, 30);
        frame.add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(centerX, centerY + 80, fieldWidth, fieldHeight);
        newPasswordField.setEchoChar('•');
        frame.add(newPasswordField);

        // Change Password Button
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(centerX , centerY + 130, fieldWidth / 2-10 , 30);
      //loginButton.setBounds(centerX, centerY + 130, fieldWidth / 2 - 10, 30);
        changePasswordButton.setBackground(Color.decode("#4CAF50"));
        changePasswordButton.setForeground(Color.WHITE);
        frame.add(changePasswordButton);
        
        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.setBounds(centerX + fieldWidth / 2 + 10, centerY + 130, fieldWidth / 2-10, 30);
      //loginButton.setBounds(centerX, centerY + 130, fieldWidth / 2 - 10, 30);
        resetButton.setBackground(Color.decode("#f44336"));
        resetButton.setForeground(Color.WHITE);
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
                            frame.dispose(); // Close the popup after success
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
}





package com.modules;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JFrame frame;
    private String username;

    public HomePage(String username) {
        this.username = username;
        frame = new JFrame("Home Page");
        frame.setSize(800, 600); // Adjusted frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Background Image Placeholder
        // JLabel background = new JLabel(new ImageIcon("path_to_image"));
        // background.setBounds(0, 0, 800, 600);
        // frame.add(background);

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setBounds(50, 50, 300, 30);
        welcomeLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        frame.add(welcomeLabel);

        JButton manageMembersButton = new JButton("Manage Members");
        manageMembersButton.setBounds(50, 120, 200, 40);
        manageMembersButton.setBackground(new java.awt.Color(65, 105, 225));
        manageMembersButton.setForeground(java.awt.Color.WHITE);
        frame.add(manageMembersButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 180, 200, 40);
        logoutButton.setBackground(new java.awt.Color(105, 105, 105));
        logoutButton.setForeground(java.awt.Color.WHITE);
        frame.add(logoutButton);

        manageMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement manage members functionality
                JOptionPane.showMessageDialog(frame, "Manage Members clicked.");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginForm(); // Return to login page
            }
        });

        frame.setVisible(true);
    }
}

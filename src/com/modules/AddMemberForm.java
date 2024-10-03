package com.modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddMemberForm {
    private JFrame frame;
    private JTextField idField, nameField, contactField, addressField, feePaidField, feeRemainingField;
    private JButton addButton, backButton;

    public AddMemberForm() {
        // Set up the frame
        frame = new JFrame("Add Member");
        frame.setSize(1366, 768); // Desktop size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#550000")); // Light background color
        
        
        /*ImageIcon backgroundImg = new ImageIcon("C:\\Users\\akash_bandage\\Desktop\\ITEP\\Gym Project Files\\Backgroun Images\\Group 3.png");
        Image scaledImage = backgroundImg.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH); // Scaling the image
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setBounds(0, 0, 1366, 768);
        frame.add(backgroundLabel);*/
        
        // Labels and Fields
        JLabel idLabel = new JLabel("Member ID:");
        idLabel.setBounds(50, 30, 150, 30);
        idLabel.setForeground(Color.WHITE);
        idLabel.setBackground(Color.WHITE);
        frame.add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 30, 300, 30);
        frame.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 150, 30);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBackground(Color.WHITE);
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 80, 300, 30);
        frame.add(nameField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(50, 130, 150, 30);
        contactLabel.setForeground(Color.WHITE);
        contactLabel.setBackground(Color.WHITE);
        frame.add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(200, 130, 300, 30);
        frame.add(contactField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 180, 150, 30);
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setBackground(Color.WHITE);
        frame.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(200, 180, 300, 30);
        frame.add(addressField);

        JLabel feePaidLabel = new JLabel("Fee Paid:");
        feePaidLabel.setBounds(50, 230, 150, 30);
        feePaidLabel.setForeground(Color.WHITE);
        feePaidLabel.setBackground(Color.WHITE);
        frame.add(feePaidLabel);

        feePaidField = new JTextField();
        feePaidField.setBounds(200, 230, 300, 30);
        frame.add(feePaidField);

        JLabel feeRemainingLabel = new JLabel("Fee Remaining:");
        feeRemainingLabel.setBounds(50, 280, 150, 30);
        feeRemainingLabel.setForeground(Color.WHITE);
        feeRemainingLabel.setBackground(Color.WHITE);
        frame.add(feeRemainingLabel);

        feeRemainingField = new JTextField();
        feeRemainingField.setBounds(200, 280, 300, 30);
        feeRemainingField.setEditable(true); // Auto-calculated
        frame.add(feeRemainingField);

        // Add Button
        addButton = new JButton("Add Member");
        addButton.setBounds(200, 330, 150, 30);
        addButton.setBackground(Color.decode("#4CAF50"));
        addButton.setForeground(Color.WHITE);
        frame.add(addButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(360, 330, 150, 30);
        backButton.setBackground(Color.decode("#f44336"));
        backButton.setForeground(Color.WHITE);
        frame.add(backButton);

        // Add Button Action Listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Input validation
                    if (!nameField.getText().matches("[a-zA-Z ]+")) {
                        JOptionPane.showMessageDialog(frame, "Invalid name, enter only letters.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!contactField.getText().matches("\\d{10}")) {
                        JOptionPane.showMessageDialog(frame, "Invalid contact, enter only 10 numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int id = Integer.parseInt(idField.getText().trim());
                    String name = nameField.getText().trim();
                    String contact = contactField.getText().trim();
                    String address = addressField.getText().trim();
                    double feePaid = Double.parseDouble(feePaidField.getText().trim());
                    double feeRemaining = 1000 - feePaid; // Assume total fee is 1000 for now (adjust logic accordingly)

                    // Create Member object
                    Member member = new Member(id, name, contact, address, feePaid, feeRemaining);

                    // Add member to database
                    MemberDAO memberDAO = new MemberDAO();
                    memberDAO.addMember(member);

                    JOptionPane.showMessageDialog(frame, "Member added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields
                    idField.setText("");
                    nameField.setText("");
                    contactField.setText("");
                    addressField.setText("");
                    feePaidField.setText("");
                    feeRemainingField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for ID and Fees.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "You cannot add this user because this user ID already exists.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back Button Action Listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current window
                new AdminHomePage(); // Navigate back to Admin Home Page
            }
        });

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AddMemberForm();
    }
}

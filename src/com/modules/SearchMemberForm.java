package com.modules;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SearchMemberForm {
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton, backButton;
    private JTable memberTable;
    private DefaultTableModel tableModel;

    public SearchMemberForm() {
        // Set up the frame
        frame = new JFrame("Search Member");
        frame.setSize(1366, 768);  // Desktop size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#550000")); // Light background color "#f0f8ff"
      //  frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        // Search Label
        JLabel searchLabel = new JLabel("Enter Member ID or Name:");
        searchLabel.setBounds(75, 30, 200, 30);
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setBackground(Color.WHITE);
        frame.add(searchLabel);

        // Search Field
        searchField = new JTextField();
        searchField.setBounds(275, 30, 300, 30);
        frame.add(searchField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(625, 30, 100, 30);
        searchButton.setBackground(Color.decode("#4CAF50")); // Trending button color
        searchButton.setForeground(Color.WHITE);
        frame.add(searchButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(745, 30, 100, 30);
        backButton.setBackground(Color.decode("#f44336")); // Trending color for back button
        backButton.setForeground(Color.WHITE);
        frame.add(backButton);

        // Table to display member information
        String[] columnNames = {"ID", "Name", "Contact", "Address", "Fee Paid", "Fee Remaining"};
        tableModel = new DefaultTableModel(columnNames, 0);  // 0 rows initially
        memberTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setBounds(75, 100, 1200, 550);
        frame.add(scrollPane);

        // Search Button Action Listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().trim();
                if (!query.isEmpty()) {
                    searchMember(query);  // Call searchMember method to retrieve and display member data
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID or Name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back Button Action Listener to return to Admin Home Page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();  // Close current window
                new AdminHomePage();  // Navigate back to Admin Home Page
            }
        });

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    // Method to search and display members in the table
    private void searchMember(String query) {
        MemberDAO memberDAO = new MemberDAO();
        try {
            List<Member> members = memberDAO.searchMember(query);
            tableModel.setRowCount(0); // Clear existing rows before populating new data

            // If no members found, show a message
            if (members.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No member found with the given ID or Name.", "No Results", JOptionPane.INFORMATION_MESSAGE);
            }

            // Iterate through the members list and populate the table
            for (Member member : members) {
                tableModel.addRow(new Object[]{
                    member.getId(),
                    member.getName(),
                    member.getContact(),
                    member.getAddress(),
                    member.getFeePaid(),
                    member.getFeeRemaining()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error retrieving data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to run the SearchMemberForm
    public static void main(String[] args) {
        new SearchMemberForm();
    }
}

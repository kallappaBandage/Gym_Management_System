package com.modules;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AdminHomePage {
    private JFrame frame;
    private JTable memberTable;
    private DefaultTableModel tableModel;
    private JButton viewMembersButton, deleteMemberButton, searchMemberButton, manageFeesButton, addMemberButton;

    public AdminHomePage() {
        // Set up the frame
        frame = new JFrame("Admin Home");
        frame.setSize(1366, 768); // Desktop size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#550000")); // Light background color
        

        // Buttons
        addMemberButton = new JButton("Add Member");
        addMemberButton.setBounds(75, 30, 200, 30);
        addMemberButton.setBackground(Color.decode("#eeeeee"));
        addMemberButton.setForeground(Color.BLACK);
        frame.add(addMemberButton);
        
        viewMembersButton = new JButton("View Total Members");
        viewMembersButton.setBounds(325, 30, 200, 30);
        viewMembersButton.setBackground(Color.decode("#eeeeee"));
        viewMembersButton.setForeground(Color.BLACK);
        frame.add(viewMembersButton);
        
        searchMemberButton = new JButton("Search Member");
        searchMemberButton.setBounds(575, 30, 200, 30);
        searchMemberButton.setBackground(Color.decode("#eeeeee"));
        searchMemberButton.setForeground(Color.BLACK);
        frame.add(searchMemberButton);
        
        manageFeesButton = new JButton("Manage Fees");
        manageFeesButton.setBounds(825, 30, 200, 30);
        manageFeesButton.setBackground(Color.decode("#eeeeee"));
        manageFeesButton.setForeground(Color.BLACK);
        frame.add(manageFeesButton);
        
        deleteMemberButton = new JButton("Delete Member");
        deleteMemberButton.setBounds(1075, 30, 200, 30);
        deleteMemberButton.setBackground(Color.decode("#eeeeee"));
        deleteMemberButton.setForeground(Color.BLACK);
        frame.add(deleteMemberButton);

        // Table for displaying member information
        String[] columnNames = {"ID", "Name", "Contact", "Address", "Fee Paid", "Fee Remaining"};
        tableModel = new DefaultTableModel(columnNames, 0);  // 0 rows initially
        memberTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setBounds(75, 100, 1200, 550);
        frame.add(scrollPane);

        // View Members Button Action Listener
        viewMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllMembers();  // Method to load and display all members
            }
        });
          // add member
        addMemberButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new AddMemberForm();
        	}
        });
        
        //manage fee member
        manageFeesButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new  ManageFeesForm();
        	}
        });
        
        //search
        searchMemberButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		new  SearchMemberForm();
        	}
        });
        // Delete Member Button Action Listener
        deleteMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberId = JOptionPane.showInputDialog(frame, "Enter Member ID to delete:");
                if (memberId != null && !memberId.trim().isEmpty()) {
                    try {
                        MemberDAO memberDAO = new MemberDAO();
                        memberDAO.deleteMember(Integer.parseInt(memberId.trim()));
                        JOptionPane.showMessageDialog(frame, "Member deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadAllMembers();  // Reload the member list after deletion
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error deleting member.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Add action listeners for other buttons as needed

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private void loadAllMembers() {
        try {
            MemberDAO memberDAO = new MemberDAO();
            List<Member> members = memberDAO.getAllMembers();
            tableModel.setRowCount(0);  // Clear the table before adding new rows
            for (Member member : members) {
                Object[] row = {
                    member.getId(),
                    member.getName(),
                    member.getContact(),
                    member.getAddress(),
                    member.getFeePaid(),
                    member.getFeeRemaining()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading members.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AdminHomePage();
    }
}

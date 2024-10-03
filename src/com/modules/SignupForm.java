/*package com.modules;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignupForm {
    // Fields for the form (username, password, etc.)
    private JTextField idField;
    private JTextField nameField;
    private JTextField contactField;
    private JTextField addressField; // Add address field
    private JTextField feePaidField;
    private JTextField feeRemainingField;

    public SignupForm() {
        // Initialize form and components...

        // On button click
        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String contact = contactField.getText();
                String address = addressField.getText(); // Retrieve address from field
                double feePaid = Double.parseDouble(feePaidField.getText());
                double feeRemaining = Double.parseDouble(feeRemainingField.getText());

                // Create new Member object
                Member member = new Member(id, name, contact, address, feePaid, feeRemaining);

                // Add member to database (using MemberDAO)
                MemberDAO memberDAO = new MemberDAO();
                try {
					memberDAO.addMember(member);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                JOptionPane.showMessageDialog(null, "Member added successfully!");
            }
        });
    }

    // Other methods...
}
*/
package com.modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManageFeesForm {
    private JFrame frame;
    private JTextField memberIdField, feeRemainingField, feeAmountField;

    public ManageFeesForm() {
        frame = new JFrame("Manage Fees");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); // Background color

        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setBounds(50, 50, 100, 30);
        frame.add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(150, 50, 150, 30);
        frame.add(memberIdField);

        JLabel feeRemainingLabel = new JLabel("Fee Remaining:");
        feeRemainingLabel.setBounds(50, 100, 100, 30);
        frame.add(feeRemainingLabel);

        feeRemainingField = new JTextField();
        feeRemainingField.setBounds(150, 100, 150, 30);
        feeRemainingField.setEditable(false); // Make it read-only
        frame.add(feeRemainingField);

        JLabel feeAmountLabel = new JLabel("Fee Amount:");
        feeAmountLabel.setBounds(50, 150, 100, 30);
        frame.add(feeAmountLabel);

        feeAmountField = new JTextField();
        feeAmountField.setBounds(150, 150, 150, 30);
        frame.add(feeAmountField);

        JButton addFeeButton = new JButton("Add Fee");
        addFeeButton.setBounds(150, 200, 150, 30);
        addFeeButton.setBackground(Color.GREEN); // Button color
        frame.add(addFeeButton);
        frame.setVisible(true);
        addFeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int memberId = Integer.parseInt(memberIdField.getText());
                    double feeAmount = Double.parseDouble(feeAmountField.getText());

                    FeeDAO feeDAO = new FeeDAO();
                    
                    //feeDAO.getFeeRemaining(memberId);
                    
                    feeDAO.addFee(memberId, feeAmount);
                    
                    new SearchMemberForm();
                       
                    JOptionPane.showMessageDialog(frame, "Fee Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid values", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        memberIdField.addActionListener(e -> {
            try {
                int memberId = Integer.parseInt(memberIdField.getText());
                System.out.println(memberId);
                FeeDAO feeDAO = new FeeDAO();
                double remainingFee = feeDAO.getFeeRemaining(memberId);
                System.out.println(remainingFee);
                feeRemainingField.setText(String.valueOf(remainingFee));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid Member ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

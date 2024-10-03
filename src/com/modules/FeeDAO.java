package com.modules;

import java.sql.*;

public class FeeDAO {
    private Connection connection;

    public FeeDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void addFee(int memberId, double feeAmount) throws SQLException {
    	 double feePaid;
    	 Connection con = DatabaseConnection.getConnection();
         
         
         if(feeAmount<=1000)
         {
        	 
         String query = "UPDATE members SET fee_paid = fee_paid + ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, feeAmount);
            statement.setInt(2, memberId);
            int n=statement.executeUpdate();
            System.out.println(n);
            
            
            String query1 = "UPDATE members SET fee_remaining = fee_remaining - ? WHERE id = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            	 
                statement1.setDouble(1, feeAmount);
                statement1.setInt(2, memberId);
                int n1=statement1.executeUpdate();
                System.out.println(n1);
           }
         }
        }
       
            

    public double getFeeRemaining(int memberId) throws SQLException {
        String query = "SELECT fee_remaining FROM members WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, memberId);
            
            System.out.println("FeeDuo");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("fee_remaining");
            }
        }
        return 0;
    }
}

package com.modules;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    // Add a member to the database
    public void addMember(Member member) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        String query = "INSERT INTO members (id, name, contact, address, fee_paid, fee_remaining) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, member.getId());
        pst.setString(2, member.getName());
        pst.setString(3, member.getContact());
        pst.setString(4, member.getAddress());
        pst.setDouble(5, member.getFeePaid());
        pst.setDouble(6, member.getFeeRemaining());
        pst.executeUpdate();
    }

    // Get all members from the database
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT * FROM members";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String contact = rs.getString("contact");
            String address = rs.getString("address");
            double feePaid = rs.getDouble("fee_paid");
            double feeRemaining = rs.getDouble("fee_remaining");

            Member member = new Member(id, name, contact, address, feePaid, feeRemaining);
            members.add(member);
        }
        return members;
    }

    // Delete a member by ID
    public void deleteMember(int id) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        String query = "DELETE FROM members WHERE id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.executeUpdate();
    }

    // Search for a member by ID or Name
    public List<Member> searchMember(String query) throws SQLException {
        List<Member> members = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
        
        // Search by either ID or Name
        String sql = "SELECT * FROM members WHERE id = ? OR name LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);
        try {
            int id = Integer.parseInt(query); // Try to interpret as an ID
            pst.setInt(1, id);
            pst.setString(2, ""); // Leave name empty
        } catch (NumberFormatException e) {
            pst.setInt(1, -1); // Invalid ID, set to -1
            pst.setString(2, "%" + query + "%"); // Interpret as a name and use LIKE clause
        }
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String contact = rs.getString("contact");
            String address = rs.getString("address");
            double feePaid = rs.getDouble("fee_paid");
            double feeRemaining = rs.getDouble("fee_remaining");

            Member member = new Member(id, name, contact, address, feePaid, feeRemaining);
            members.add(member);
        }
        return members;
    }
}

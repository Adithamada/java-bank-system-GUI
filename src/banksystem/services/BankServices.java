package banksystem.services;

import java.sql.Connection;

import javax.swing.*;

import banksystem.database.DatabaseConnection;
import java.sql.*;

public class BankServices {

	public void balanceDeposit(double depositAmount, int id,JFrame frame) {
        String querySelect = "SELECT balance FROM user WHERE id = ?";
        String queryUpdate = "UPDATE user SET balance = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmtSelect = conn.prepareStatement(querySelect);
             PreparedStatement stmtUpdate = conn.prepareStatement(queryUpdate)) {

            // Retrieve current balance
            stmtSelect.setInt(1, id);
            ResultSet rs = stmtSelect.executeQuery();
            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                double newBalance = currentBalance + depositAmount;

                // Update balance
                stmtUpdate.setDouble(1, newBalance);
                stmtUpdate.setInt(2, id);
                int rowsAffected = stmtUpdate.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Deposit successful. New balance: " + newBalance);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update balance.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	
    public void balanceWithdraw(double withdrawAmount, int id,JFrame frame) {
        String querySelect = "SELECT balance FROM user WHERE id = ?";
        String queryUpdate = "UPDATE user SET balance = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmtSelect = conn.prepareStatement(querySelect);
             PreparedStatement stmtUpdate = conn.prepareStatement(queryUpdate)) {

            // Retrieve current balance
            stmtSelect.setInt(1, id);
            ResultSet rs = stmtSelect.executeQuery();
            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                if (currentBalance >= withdrawAmount) {
                    double newBalance = currentBalance - withdrawAmount;

                    // Update balance
                    stmtUpdate.setDouble(1, newBalance);
                    stmtUpdate.setInt(2, id);
                    int rowsAffected = stmtUpdate.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Withdrawal successful. New balance: " + newBalance);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to update balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
	 public double checkBalance(int id) {
	        String query = "SELECT balance FROM user WHERE id = ?";
	        try (Connection conn = DatabaseConnection.connect();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, id);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return rs.getDouble("balance");
	            } else {
	                // Handle user not found case, you may throw an exception or return a special value
	                return -1; // Special value indicating user not found
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle SQL exception, you may throw an exception or return a special value
	            return -1; // Special value indicating an error occurred
	        }
	    }

}

package banksystem.services;

import java.sql.Connection;

import banksystem.database.DatabaseConnection;
import java.sql.*;

public class BankServices {

	public void balanceDeposit(double balance,int id) {
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
	            if (currentBalance >= balance) {
	                double newBalance = currentBalance + balance;
	                
	                // Update balance
	                stmtUpdate.setDouble(1, newBalance);
	                stmtUpdate.setInt(2, id);
	                int rowsAffected = stmtUpdate.executeUpdate();
	                
	                if (rowsAffected > 0) {
	                    System.out.println("Deposit successful. New balance: " + newBalance);
	                } else {
	                    System.out.println("Failed to update balance.");
	                }
	            } else {
	                System.out.println("Insufficient balance.");
	            }
	        } else {
	            System.out.println("User not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void balanceWithdraw(double withdrawAmount, int id) {
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
	                    System.out.println("Withdrawal successful. New balance: " + newBalance);
	                } else {
	                    System.out.println("Failed to update balance.");
	                }
	            } else {
	                System.out.println("Insufficient balance.");
	            }
	        } else {
	            System.out.println("User not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void checkBalance(int id) {
	    String query = "SELECT balance FROM user WHERE id = ?";
	    try (Connection conn = DatabaseConnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            double balance = rs.getDouble("balance");
	            System.out.println("Current balance for user ID " + id + ": " + balance);
	        } else {
	            System.out.println("User not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}

package banksystem.services;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;
import banksystem.database.*;

public class UserServices {
	public void registrationUser(String name, String email, String password)
	{
		String query = "INSERT INTO user (name,email,password) VALUES (?,?,?)";
		try(Connection conn = DatabaseConnection.connect();
				PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, password);
			
			int rowsAffected = stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int loginUser(String name, String password) {
	    String checkUser = "SELECT id, password FROM user WHERE name = ?";
	    
	    try (Connection conn = DatabaseConnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(checkUser)) {
	        
	        stmt.setString(1, name);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                String hashedPassword = rs.getString("password");
	                // Check if the hashed password matches
	                if (BCrypt.checkpw(password, hashedPassword)) {
	                    return rs.getInt("id");
	                }
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1; // Return -1 if login fails
	}


}

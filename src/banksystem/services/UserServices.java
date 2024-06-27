package banksystem.services;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;
import banksystem.database.*;

public class UserServices {
	public void registrationUser(String name, String address, String phone, String email,String password)
	{
		String query = "INSERT INTO user (name,address,phone,email,password) VALUES (?,?,?,?)";
		try(Connection conn = DatabaseConnection.connect();
				PreparedStatement stmt = conn.prepareStatement(query)){
			
			stmt.setString(1, name);
			stmt.setString(2, address);
			stmt.setString(3, phone);
			stmt.setString(4, email);
			stmt.setString(5, password);
			
			int rowsAffected = stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loginUser(String name, String password) {
		String checkUser = "SELECT password FROM user WHERE username = ?";
		
		try{
			Connection conn = (Connection) DatabaseConnection.connect();
			PreparedStatement stmt = conn.prepareStatement(checkUser);
			
			stmt.setString(1, name);
			
			try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    // Check if the hashed password matches
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        return true;
                    }
                }
            }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

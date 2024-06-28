package banksystem.frame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

import banksystem.services.*;

public class RegisterFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton registerBtn = new JButton("Register");
	JButton resetBtn = new JButton("Reset");
	JButton loginMenuBtn = new JButton("Login");
	JTextField userNameField = new JTextField();
	JTextField userEmailField = new JTextField();
	JPasswordField userPassField = new JPasswordField();
	JLabel userNameLabel = new JLabel("Username : ");
	JLabel userEmailLabel = new JLabel("Email : ");
	JLabel userPassLabel = new JLabel("Password : ");
	private UserServices userService;
	
	public RegisterFrame(){
		loginMenuBtn.setBounds(320,0,100,30);
		loginMenuBtn.addActionListener(this);
		userService = new UserServices();
		userNameLabel.setBounds(50,100,75,52);
		userPassLabel.setBounds(50,200,75,52);	
		userEmailLabel.setBounds(50,150,75,52);
		
		userNameField.setBounds(125,115,200,25);
		userEmailField.setBounds(125,165,200,25);
		userPassField.setBounds(125,215,200,25);
		
		registerBtn.setBounds(150,260,100,30);
		registerBtn.addActionListener(this);
		
		frame.add(loginMenuBtn);
		frame.add(userNameLabel);
		frame.add(userEmailLabel);
		frame.add(userPassLabel);
		frame.add(userNameField);
		frame.add(userEmailField);
		frame.add(userPassField);
		frame.add(registerBtn);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== registerBtn) {
			String userName = userNameField.getText();
			String userEmail = userEmailField.getText();
			String userPass = String.valueOf(userPassField.getPassword());
			
			if(userName != null || userPass != null || userEmail != null) {
				String hashedPassword = BCrypt.hashpw(userPass, BCrypt.gensalt());
				userService.registrationUser(userName,userEmail,hashedPassword);
				JOptionPane.showMessageDialog(frame,"Registration Success!");
			}else {
				JOptionPane.showMessageDialog(frame,"Registration Failed, Please try again.");
			}
		}else if(e.getSource() == loginMenuBtn) {
			new LoginFrame();
			frame.dispose();
		}
	}
	
}

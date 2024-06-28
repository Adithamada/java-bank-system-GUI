package banksystem.frame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import banksystem.services.*;

public class LoginFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton loginBtn = new JButton("Login");
	JButton resetBtn = new JButton("Reset");
	JTextField userNameField = new JTextField();
	JPasswordField userPassField = new JPasswordField();
	JLabel userNameLabel = new JLabel("Username : ");
	JLabel userPassLabel = new JLabel("Password : ");
	JButton registerMenuBtn = new JButton("Register");
	int isLogin;
	private UserServices userService;
	
	public LoginFrame(){
		userService = new UserServices();
		registerMenuBtn.setBounds(320,0,100,30);
		registerMenuBtn.addActionListener(this);
		userNameLabel.setBounds(50,100,75,52);
		userPassLabel.setBounds(50,150,75,52);		
		
		userNameField.setBounds(125,115,200,25);
		userPassField.setBounds(125,165,200,25);
		
		loginBtn.setBounds(150,220,100,20);
		loginBtn.addActionListener(this);
		
		frame.add(registerMenuBtn); 
		frame.add(userNameLabel);
		frame.add(userPassLabel);
		frame.add(userNameField);
		frame.add(userPassField);
		frame.add(loginBtn);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== loginBtn) {
			String userName = userNameField.getText();
			String userPass = String.valueOf(userPassField.getPassword());
			
			isLogin =userService.loginUser(userName, userPass);
			
			if(isLogin != -1) {
				JOptionPane.showMessageDialog(frame,"Login Success, Welcome " + userName);
				new BankFrame(isLogin,userName);
				frame.dispose();
			}else {
				JOptionPane.showMessageDialog(frame,"Login Failed, Please check username and password");
			}	
		}else if(e.getSource() == registerMenuBtn) {
			new RegisterFrame();
			frame.dispose();
		}
	}
}

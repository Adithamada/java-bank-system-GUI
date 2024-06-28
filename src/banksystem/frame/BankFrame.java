package banksystem.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import banksystem.services.*;
import javax.swing.*;

public class BankFrame extends JFrame implements ActionListener{

	private JLabel labelTitle;
	private JLabel labelDeposit;
	private JLabel labelUsername;
	private JLabel labelBalance;
	
	private JPanel titlePanel;
	private JPanel navbarPanel;
	private JPanel mainPanel;
	private JPanel depositMenuPanel;
	private JPanel checkBalancePanel;
	private JPanel withdrawMenuPanel;
	
	private JButton depositMenuBtn;
	private JButton withdrawMenuBtn;
	private JButton checkBalanceMenuBtn;
	private JButton depositBtn;
	private JButton withdrawBtn;
	private JButton submitDeposit;
	private JButton submitWithdraw;
	
	private JTextField fieldDeposit;
	private JTextField fieldWithdraw;
	
	private CardLayout cardLayout;
	
	private BankServices bankService;
	private int userID;
	
	public BankFrame(int userID,String userName){
		bankService = new BankServices();
		this.userID = userID;
		setSize(1000,700);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fieldDeposit = new JTextField();
		depositBtn = new JButton("Deposit");
		depositBtn.addActionListener(this);
		fieldWithdraw = new JTextField();
		withdrawBtn = new JButton("Withdraw");
		withdrawBtn.addActionListener(this);
		
		labelTitle = new JLabel("BANK APP");
		labelUsername = new JLabel(userName);
		labelTitle.setBounds(10,15,200,50);
		labelUsername.setBounds(850,15,200,50);
		
		titlePanel = new JPanel();
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		
		titlePanel.setLayout(null);
		titlePanel.add(labelTitle);
		titlePanel.add(labelUsername);
		titlePanel.setSize(1000,150);
		titlePanel.setBounds(0,0,1000,70);
		
		depositMenuPanel = depositCard();
		withdrawMenuPanel = withdrawCard();
		checkBalancePanel = checkBalanceCard(userID);
		
		mainPanel.add(depositMenuPanel,"deposit");
		mainPanel.add(withdrawMenuPanel,"withdraw");
		mainPanel.add(checkBalancePanel,"checkBalance");
		mainPanel.setSize(1000,500);
		mainPanel.setBounds(0,120,1000,580);
		
		depositMenuBtn = new JButton("deposit");
		withdrawMenuBtn = new JButton("withdraw");
		checkBalanceMenuBtn = new JButton("check balance");
		
		navbarPanel = new JPanel();
		navbarPanel.setSize(1000,50);
		navbarPanel.setBounds(0,70,1000,50);
		navbarPanel.setLayout(new GridLayout(1,3,5,5));
		navbarPanel.add(depositMenuBtn);
		navbarPanel.add(withdrawMenuBtn);
		navbarPanel.add(checkBalanceMenuBtn);
		
		depositMenuBtn.addActionListener(e -> cardLayout.show(mainPanel, "deposit"));
		withdrawMenuBtn.addActionListener(e -> cardLayout.show(mainPanel, "withdraw"));
		checkBalanceMenuBtn.addActionListener(e -> cardLayout.show(mainPanel, "checkBalance"));
		
		add(navbarPanel);
		add(mainPanel);
		add(titlePanel);
		
		setResizable(false);
		setVisible(true);
	}
	
	public JPanel panelCard(String text) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(text));
		panel.setBackground(Color.green);
		return panel;
	}
	
	public JPanel depositCard() {
		Font balanceFont = new Font("MONO",Font.BOLD,30);
		JPanel panel = new JPanel();
		panel.setSize(1000,580);
		panel.setLayout(null);
		fieldDeposit.setBounds(250,250,500,80);
		fieldDeposit.setFont(balanceFont);
		depositBtn.setBounds(450,350,100,30);
		panel.add(fieldDeposit);
		panel.add(depositBtn);
		return panel;
	}
	
	public JPanel withdrawCard() {
		Font balanceFont = new Font("MONO",Font.BOLD,30);
		JPanel panel = new JPanel();
		panel.setSize(1000,580);
		panel.setLayout(null);
		fieldWithdraw.setBounds(250,250,500,80);
		fieldWithdraw.setFont(balanceFont);
		withdrawBtn.setBounds(450,350,100,30);
		panel.add(fieldWithdraw);
		panel.add(withdrawBtn);
		return panel;
	}
	
	public JPanel checkBalanceCard(int userID) {
		Font balanceFont = new Font("MONO",Font.BOLD,50);
		JPanel panel = new JPanel();
		panel.setSize(1000,580);
		panel.setLayout(null);

		double currentBalance = bankService.checkBalance(userID);
		labelBalance = new JLabel(String.valueOf(currentBalance));
		labelBalance.setBounds(400,50,1000,400);
		labelBalance.setFont(balanceFont);
		panel.add(labelBalance);
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==depositBtn) {
			double deposit = Double.parseDouble(fieldDeposit.getText());
			if(deposit != 0) {				
				bankService.balanceDeposit(deposit, userID, this);
			}
		}else if(e.getSource()==withdrawBtn) {
			double withdraw = Double.parseDouble(fieldWithdraw.getText());
			if(withdraw != 0) {				
				bankService.balanceDeposit(withdraw, userID, this);
			}
		}else if(e.getSource()==checkBalanceMenuBtn) {
			bankService.checkBalance(userID);
		}
	}

}

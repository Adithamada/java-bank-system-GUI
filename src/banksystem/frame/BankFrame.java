package banksystem.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BankFrame extends JFrame implements ActionListener{

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
	
	public BankFrame(){
		setSize(1000,700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		
		depositMenuPanel = panelCard("deposit");
		withdrawMenuPanel = panelCard("withdraw");
		checkBalancePanel = panelCard("check balance");
		
		mainPanel.add(depositMenuPanel,"deposit");
		mainPanel.add(withdrawMenuPanel,"withdraw");
		mainPanel.add(checkBalancePanel,"checkBalance");
		
		depositMenuBtn = new JButton("deposit");
		withdrawMenuBtn = new JButton("withdraw");
		checkBalanceMenuBtn = new JButton("check balance");
		
		navbarPanel = new JPanel();
		navbarPanel.add(depositMenuBtn);
		navbarPanel.add(withdrawMenuBtn);
		navbarPanel.add(checkBalanceMenuBtn);
		
		depositMenuBtn.addActionListener(e -> cardLayout.show(mainPanel, "deposit"));
		withdrawMenuBtn.addActionListener(e -> cardLayout.show(mainPanel, "withdraw"));
		checkBalanceMenuBtn.addActionListener(e -> cardLayout.show(mainPanel, "checkBalance"));
		
		add(navbarPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		
		setResizable(false);
		setVisible(true);
	}
	
	public JPanel panelCard(String text) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(text));
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

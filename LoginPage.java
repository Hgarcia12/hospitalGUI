package org.fakehospital.itdb;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Canvas;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LoginPage {

	private static JFrame frame;
	private JPasswordField passwordField;
	private JTextField usernameField;
	Connection connection = null;
	static String name;
	static String dateAcc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login Page");
		connection = ConnectToDB.connect();//calls on class to connect to SQLITE localserver
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBackground(SystemColor.window);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Sets the layout manager for the Frame
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{136, 70, 70, 71, 91, 0};
		gridBagLayout.rowHeights = new int[]{50, 43, 35, 0, 19, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		//calls on the method to add the buttons and text fields
		AddElements();
	
	}
	
	static String getName() {
		return name;
	}
	/*void lastLogin() {
		try {
			String query2 = "";
			PreparedStatement ps2 = connection.prepareStatement(query2);
			
		}catch(Exception loginEx) {
			loginEx.printStackTrace();
		}
		
	}
	*/
	void loginTime() {
		Calendar cal = new GregorianCalendar();
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		int am_pm = cal.get(Calendar.AM_PM);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);
		
		String amOrPm = "";
		
		if(am_pm == 1)
			amOrPm = "PM";
		else
			amOrPm = "AM";
		dateAcc = month + "/" + day + "/" + year + " " + hour + ":" + min + ":" + sec + " " + amOrPm;	
	}
	
	String getLastLogin() {
		String dateOf = dateAcc;
		return dateOf;
	}
	
private void AddElements() {
	//presents the logo using a JLabel
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon(LoginPage.class.getResource("/images/fakehospitallogo.png")));//image from image package
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
	gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 1;
	gbc_lblNewLabel.gridy = 1;
	frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	//Username JLabel
	JLabel lblUsername = new JLabel("Username");
	lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
	GridBagConstraints gbc_lblUsername = new GridBagConstraints();
	gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
	gbc_lblUsername.anchor = GridBagConstraints.EAST;
	gbc_lblUsername.gridx = 0;
	gbc_lblUsername.gridy = 2;
	frame.getContentPane().add(lblUsername, gbc_lblUsername);
	//Username JTextField
	usernameField = new JTextField();
	GridBagConstraints gbc_usernameField = new GridBagConstraints();
	gbc_usernameField.gridwidth = 2;
	gbc_usernameField.insets = new Insets(0, 0, 5, 5);
	gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
	gbc_usernameField.gridx = 1;
	gbc_usernameField.gridy = 2;
	frame.getContentPane().add(usernameField, gbc_usernameField);
	usernameField.setColumns(10);
	//Password JLabel
	JLabel lblPassword = new JLabel("Password");
	lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
	GridBagConstraints gbc_lblPassword = new GridBagConstraints();
	gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
	gbc_lblPassword.anchor = GridBagConstraints.EAST;
	gbc_lblPassword.gridx = 0;
	gbc_lblPassword.gridy = 3;
	frame.getContentPane().add(lblPassword, gbc_lblPassword);
	//Password JPasswordField
	passwordField = new JPasswordField();
	GridBagConstraints gbc_passwordField = new GridBagConstraints();
	gbc_passwordField.gridwidth = 2;
	gbc_passwordField.insets = new Insets(0, 0, 5, 5);
	gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
	gbc_passwordField.gridx = 1;
	gbc_passwordField.gridy = 3;
	frame.getContentPane().add(passwordField, gbc_passwordField);
	//button activates an action to validate username and password
	JButton btnLogin = new JButton("Login");
	btnLogin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				/*creates a query to get username and password from table security
				 * The passwords are not encrypted in the table for simplicities sake
				 */
				String query = "SELECT * FROM security WHERE User=? AND Pass=? ";
				
				PreparedStatement pst = connection.prepareStatement(query);
				
				//gets the text from these fields and applies them to the query
				pst.setString(1,usernameField.getText());
				pst.setString(2, passwordField.getText());
				
				ResultSet rs = pst.executeQuery();
				
				int count = 0;
				while(rs.next()) {
					//cycles through and based on count determines what to do
					count = count + 1;
				}
				if (count == 1) {
					JOptionPane.showMessageDialog(null, "Username and Password match");
					
					try {
						String getName = "SELECT FullName FROM security WHERE User=? AND Pass=?";
						String setLastLogin = "UPDATE security SET lastLogin =? WHERE User=? AND Pass=?";
						
						PreparedStatement ps = connection.prepareStatement(getName);
						PreparedStatement psl =connection.prepareStatement(setLastLogin);
						
						ps.setString(1,usernameField.getText());
						ps.setString(2, passwordField.getText());
						
						psl.setString(1,getLastLogin());
						psl.setString(2, usernameField.getText());
						psl.setString(3,passwordField.getText() );
						
						ResultSet rst = ps.executeQuery();
						ResultSet rslt = psl.executeQuery();
						name = rst.getString("FullName");
						JOptionPane.showMessageDialog(null, name);
					}catch(Exception exc) {
						exc.printStackTrace();
					}
					
					frame.setVisible(false);
					MainMenu mainPanel = new MainMenu();

					mainPanel.setVisible(true);
					
					//Home.getContentPane();
					//Home.revalidate();
					//Home.repaint();
					//Home.setVisible(true);
				}
				else if(count > 1) {
					JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
				}
				else {
					JOptionPane.showMessageDialog(null, "Username and Password are not correct, Try again");
				}
					
				rs.close();
				pst.close();
				
				}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	});
	
	GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
	gbc_btnSubmit.gridwidth = 2;
	gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
	gbc_btnSubmit.gridx = 1;
	gbc_btnSubmit.gridy = 4;
	frame.getContentPane().add(btnLogin, gbc_btnSubmit);
	}

	static void CloseLogin() {
		frame.dispose();
	}
}

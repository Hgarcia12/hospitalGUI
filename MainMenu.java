package org.fakehospital.itdb;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTable overviewTable;
	static String nameOfUser;
	JLabel clockLabel;
	static String nameOfStaff;

	
	Connection connection = null;
	private JTextField searchTextField;
	private JTable roomSearchTable;
	private JTextField searchPersonTextField;
	private JTable patientSearchTable;
	private JTextField PatientNameTextField;
	private JTextField RoomTextLabel;
	private JTextField bloodTypeTextField;
	private JTextField heightLabelTextField;
	private JTextField weightTextField;
	private JTextField sexTextField;
	private JTextField birthTextFIeld;
	private JTextField addressTextField;
	private JTextField insuranceTextFIeld;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/*public static void GetName(String name) {
		nameOfStaff = name;
	}*/
	
	static public String getNameOf() {
		
		String nameee = nameOfStaff;
		return nameee;
	}
	public MainMenu() {
		Initialize();
		DigitalClock();
		//comment out InitTable when testing or make a load button
		//InitTable();
	}
	
	private void Initialize() {
		connection = ConnectToDB.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 720);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(7, 7, 68, 50);
		lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/images/fakehospitallogo.png")));
		getContentPane().add(lblNewLabel);
		
		//getContentPane().add(btnLoadTable, "cell 2 5");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 478, 870, 196);
		getContentPane().add(scrollPane);
		
		overviewTable = new JTable();
		scrollPane.setViewportView(overviewTable);
		
		InitTabbedPanes();
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	}
	private void InitTabbedPanes() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(7, 68, 870, 368);
		getContentPane().add(tabbedPane);
		
		JPanel roomSearchPanel = new JPanel();
		tabbedPane.addTab("Room Search", null, roomSearchPanel, null);
		roomSearchPanel.setLayout(new MigLayout("", "[100px][59.00][][94.00][76.00][99.00][89.00][94.00][121.00]", "[][][25px,grow 50]"));
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		roomSearchPanel.add(lblNewLabel_1, "cell 1 1,growx,aligny top");
		
		InitSearchRoomPanel();
		
		roomSearchPanel.add(searchTextField, "cell 2 1,alignx center,aligny center");
		searchTextField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		roomSearchPanel.add(scrollPane_1, "cell 0 2 9 1,grow");
		
		roomSearchTable = new JTable();
		scrollPane_1.setViewportView(roomSearchTable);
		
		JPanel searchPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchPanel, null);
		searchPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Search");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(318, 12, 47, 14);
		searchPanel.add(lblNewLabel_2);
		
		searchPersonTextField = new JTextField();
		searchPersonTextField.setBounds(375, 11, 106, 20);
		searchPanel.add(searchPersonTextField);
		searchPersonTextField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 845, 241);
		searchPanel.add(scrollPane);
		
		patientSearchTable = new JTable();
		scrollPane.setViewportView(patientSearchTable);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Patient", null, panel, null);
		panel.setLayout(null);
		
		JLabel addPatientNameLabel = new JLabel("Patient Name");
		addPatientNameLabel.setBounds(61, 39, 76, 14);
		addPatientNameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addPatientNameLabel);
		
		PatientNameTextField = new JTextField();
		PatientNameTextField.setBounds(204, 36, 118, 20);
		panel.add(PatientNameTextField);
		PatientNameTextField.setColumns(10);
		
		JLabel addPatientRoomLabel = new JLabel("Room");
		addPatientRoomLabel.setBounds(104, 63, 33, 14);
		addPatientRoomLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addPatientRoomLabel);
		
		RoomTextLabel = new JTextField();
		RoomTextLabel.setBounds(204, 60, 118, 20);
		panel.add(RoomTextLabel);
		RoomTextLabel.setColumns(10);
		
		JLabel addBloodTypeLabel = new JLabel("Blood Type");
		addBloodTypeLabel.setBounds(75, 88, 62, 14);
		addBloodTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addBloodTypeLabel);
		
		bloodTypeTextField = new JTextField();
		bloodTypeTextField.setBounds(204, 85, 118, 20);
		panel.add(bloodTypeTextField);
		bloodTypeTextField.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(387, 84, 86, 23);
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(btnInsert);
		
		JLabel addHeightLabel = new JLabel("Height");
		addHeightLabel.setBounds(100, 114, 37, 14);
		addHeightLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addHeightLabel);
		
		heightLabelTextField = new JTextField();
		heightLabelTextField.setBounds(204, 111, 118, 20);
		panel.add(heightLabelTextField);
		heightLabelTextField.setColumns(10);
		
		JLabel addWeightLabel = new JLabel("Weight");
		addWeightLabel.setBounds(97, 139, 40, 14);
		addWeightLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addWeightLabel);
		
		weightTextField = new JTextField();
		weightTextField.setBounds(204, 136, 118, 20);
		panel.add(weightTextField);
		weightTextField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(387, 135, 86, 23);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(btnUpdate);
		
		JLabel addSexLabel = new JLabel("Sex");
		addSexLabel.setBounds(116, 165, 21, 14);
		addSexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addSexLabel);
		
		sexTextField = new JTextField();
		sexTextField.setBounds(204, 162, 118, 20);
		panel.add(sexTextField);
		sexTextField.setColumns(10);
		
		JLabel addBirthLabel = new JLabel("Birth Date");
		addBirthLabel.setBounds(80, 190, 57, 14);
		addBirthLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addBirthLabel);
		
		birthTextFIeld = new JTextField();
		birthTextFIeld.setBounds(204, 187, 118, 20);
		panel.add(birthTextFIeld);
		birthTextFIeld.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(387, 186, 86, 23);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(btnDelete);
		
		JLabel addAddressLabel = new JLabel("Address");
		addAddressLabel.setBounds(91, 216, 46, 14);
		addAddressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addAddressLabel);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(204, 213, 118, 20);
		panel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel addInsuranceLabel = new JLabel("Insurance Provider");
		addInsuranceLabel.setBounds(29, 240, 108, 14);
		addInsuranceLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(addInsuranceLabel);
		
		insuranceTextFIeld = new JTextField();
		insuranceTextFIeld.setBounds(204, 237, 118, 20);
		panel.add(insuranceTextFIeld);
		insuranceTextFIeld.setColumns(10);
		
		String theName = getNameOf();
		
		JLabel welcomeLabel = new JLabel(theName);
		welcomeLabel.setBounds(85, 7, 154, 50);
		getContentPane().add(welcomeLabel);
		
		InitSearchPatientPanel();
	}
	
	private void InitTable() {
		try {
			//puts the command into a string variable to execute through a prepared statement
			String query = "SELECT patientNumber, patientName, sex, birthDate, room FROM hospitaldb";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			overviewTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void DigitalClock() {
		JLabel clockLabel = new JLabel("");
		clockLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		clockLabel.setBounds(766, 36, 111, 34);
		getContentPane().add(clockLabel);
		
		JLabel dateLabel = new JLabel("");
		dateLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		dateLabel.setBounds(766, 7, 108, 34);
		getContentPane().add(dateLabel);
	
		setVisible(true);
		
		new Thread() {
			public void run() {
				while(true) {
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
					
					String date = month + "/" + day + "/" + year;
					String time = hour + ":" + min + ":" + sec + " " + amOrPm;
					clockLabel.setText(time);
					dateLabel.setText(date);
				}
			}
		}.start();
	}
	
	private void InitSearchRoomPanel() {
		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String searchQuery = "SELECT * FROM hospitaldb WHERE room =?";
					PreparedStatement pst = connection.prepareStatement(searchQuery);
					pst.setString(1, searchTextField.getText());
					ResultSet rs =pst.executeQuery();
					roomSearchTable.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}
	
	private void InitSearchPatientPanel() {
		
	}
	
}

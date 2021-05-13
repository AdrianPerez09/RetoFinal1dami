package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import model.Administrator;
import model.AdministratorController;
import model.User;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

public class PVAdministratorWindowMain extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton btnLogout;;
	private JLabel lblWelcome;
	private JTextField tfCodeUser;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfBirthdate;
	private JPasswordField pfpasswordField;
	private JPasswordField pfConfirmPassword;
	private JButton btnCreate;
	private JRadioButton rdbtnManager;
	private JRadioButton rdbtnPolice;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private PVMainWindowLogin login;
	AdministratorController administratorInterface;
	private JTextField tfUserCode;
	private JTable tableModifyUser;
	private JTextField tfTextFieldExtra;
	private JTextField tfSearchUser;
	private JTextField tfDeleteUser;
	private JLabel lblCross;
	private JLabel lblTick;
	private JTable usersTable;
	private JTable queryUserTable;
	private JScrollPane scrollPane;
	private JButton btnSearchUser;
	private JTable table;
	private JPanel panelSearchUser;
	public PVAdministratorWindowMain(Administrator adm, AdministratorController administratorInterface,
			PVMainWindowLogin pvMainWindowLogin) {
		setModal(true);
		setResizable(false);
		this.login = pvMainWindowLogin;
		this.administratorInterface = administratorInterface;
		setBackground(new Color(176, 196, 222));
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Creamos el conjunto de pestañas
		JTabbedPane pestañas = new JTabbedPane();

		// Creamos el panel y lo añadimos a las pestañas
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(176, 196, 222));
		welcomePanel.setLayout(null);

		// Añadimos un nombre de la pestaña y el panel
		pestañas.addTab("Welcome", welcomePanel);

		JLabel lblWelcome = new JLabel("!Welcome \n!");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblWelcome.setBounds(201, 345, 376, 63);
		welcomePanel.add(lblWelcome);

		JLabel lblImgLogo = new JLabel("New label");
		lblImgLogo.setIcon(new ImageIcon(PVPoliceWindowMain.class.getResource("/img/emblem.png")));
		lblImgLogo.setBounds(250, 35, 260, 299);
		welcomePanel.add(lblImgLogo);

		btnLogout = new JButton("Log out");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(618, 476, 135, 33);
		btnLogout.addActionListener(this);
		welcomePanel.add(btnLogout);

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(176, 196, 222));

		pestañas.addTab("Create User", panel3);
		panel3.setLayout(null);

		JLabel lblCodeUser = new JLabel("User Code:");
		lblCodeUser.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCodeUser.setBounds(166, 78, 145, 40);
		panel3.add(lblCodeUser);

		tfCodeUser = new JTextField();
		tfCodeUser.setBounds(294, 78, 174, 35);
		panel3.add(tfCodeUser);
		tfCodeUser.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName.setBounds(215, 124, 145, 40);
		panel3.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(294, 129, 174, 35);
		panel3.add(tfName);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSurname.setBounds(180, 175, 145, 40);
		panel3.add(lblSurname);

		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(294, 175, 174, 35);
		panel3.add(tfSurname);

		JLabel lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblBirthdate.setBounds(180, 221, 145, 40);
		panel3.add(lblBirthdate);

		tfBirthdate = new JTextField();
		tfBirthdate.setColumns(10);
		tfBirthdate.setBounds(294, 221, 174, 35);
		panel3.add(tfBirthdate);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPassword.setBounds(180, 272, 145, 40);
		panel3.add(lblPassword);

		pfpasswordField = new JPasswordField();
		pfpasswordField.setBounds(293, 272, 175, 35);
		panel3.add(pfpasswordField);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblConfirmPassword.setBounds(89, 321, 199, 40);
		panel3.add(lblConfirmPassword);

		pfConfirmPassword = new JPasswordField();
		pfConfirmPassword.setBounds(294, 323, 174, 35);
		panel3.add(pfConfirmPassword);

		JLabel lblPoliceN = new JLabel("Police Number:");
		lblPoliceN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPoliceN.setBounds(180, 430, 174, 40);
		panel3.add(lblPoliceN);
		lblPoliceN.setVisible(false);

		JLabel lblOfficeNumber = new JLabel("Office Number:");
		lblOfficeNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOfficeNumber.setBounds(180, 430, 174, 40);
		panel3.add(lblOfficeNumber);
		lblOfficeNumber.setVisible(false);
		rdbtnPolice = new JRadioButton("Police");
		rdbtnPolice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblOfficeNumber.setVisible(false);
				tfTextFieldExtra.setVisible(true);
				lblPoliceN.setVisible(true);

			}
		});
		buttonGroup.add(rdbtnPolice);
		rdbtnPolice.setBounds(377, 385, 109, 23);
		panel3.add(rdbtnPolice);
		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPoliceN.setVisible(false);
				tfTextFieldExtra.setVisible(true);
				lblOfficeNumber.setVisible(true);
			}
		});

		buttonGroup.add(rdbtnManager);
		rdbtnManager.setBounds(247, 385, 109, 23);
		panel3.add(rdbtnManager);

		btnCreate = new JButton("Create");
		btnCreate.addActionListener(this);
		btnCreate.setBounds(624, 481, 98, 23);
		panel3.add(btnCreate);

		tfTextFieldExtra = new JTextField();
		tfTextFieldExtra.setBounds(347, 430, 152, 36);
		panel3.add(tfTextFieldExtra);
		tfTextFieldExtra.setColumns(10);

		lblCross = new JLabel("New label");
		lblCross.setVisible(false);
		lblCross.setIcon(new ImageIcon(PVAdministratorWindowMain.class.getResource("/img/cross.png")));
		lblCross.setBounds(166, 221, 392, 247);
		panel3.add(lblCross);

		lblTick = new JLabel("New label");
		lblTick.setVisible(false);
		lblTick.setIcon(new ImageIcon(PVAdministratorWindowMain.class.getResource("/img/tick.png")));
		lblTick.setBounds(166, 279, 405, 137);
		panel3.add(lblTick);
		tfTextFieldExtra.setVisible(false);

		// Realizamos lo mismo con el resto
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(176, 196, 222));
		pestañas.addTab("Modify User", panel2);
		panel2.setLayout(null);

		tfUserCode = new JTextField();
		tfUserCode.setBounds(270, 62, 169, 39);
		panel2.add(tfUserCode);
		tfUserCode.setColumns(10);

		JLabel lblUserCode = new JLabel("UserCode:");
		lblUserCode.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserCode.setBounds(152, 62, 155, 39);
		panel2.add(lblUserCode);

		tableModifyUser = new JTable();
		tableModifyUser.setBounds(79, 158, 584, 271);
		panel2.add(tableModifyUser);

		JButton btnModify = new JButton("Modify");
		btnModify.setBackground(new Color(211, 211, 211));
		btnModify.setBounds(297, 112, 103, 23);
		panel2.add(btnModify);

		panelSearchUser = new JPanel();
		panelSearchUser.setBackground(new Color(176, 196, 222));
		pestañas.addTab("Search User", panelSearchUser);
		panelSearchUser.setLayout(null);

		tfSearchUser = new JTextField();
		tfSearchUser.setColumns(10);
		tfSearchUser.setBounds(270, 62, 169, 39);
		panelSearchUser.add(tfSearchUser);

		JLabel lblSearchUser = new JLabel("Search User:");
		lblSearchUser.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSearchUser.setBounds(129, 62, 155, 39);
		panelSearchUser.add(lblSearchUser);

		btnSearchUser = new JButton("Search");
		btnSearchUser.setBounds(280, 112, 94, 23);
		btnSearchUser.addActionListener(this);
		panelSearchUser.add(btnSearchUser);
		
		
		

		ArrayList<User> users = administratorInterface.obtainUsers();
		if (users.size() > 0) {
			String matrizTabla[][] = new String[users.size()][4];
			for (int i = 0; i < users.size(); i++) {

				matrizTabla[i][0] = users.get(i).getCodUser();
				matrizTabla[i][1] = users.get(i).getName();
				matrizTabla[i][2] = users.get(i).getSurname();
				matrizTabla[i][3] = users.get(i).getBirthDate().toString();

			}

			Border blackline;

			blackline = BorderFactory.createLineBorder(Color.black, 1);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(58, 182, 660, 330);
			panelSearchUser.add(scrollPane);

			String titulos[] = { "User code", "Name", "Surname", "Birthdate"};
			usersTable = new JTable(matrizTabla, titulos) {
				public boolean editCellAt(int row, int column, java.util.EventObject e) {
					return false;
				}
			};
			;
			usersTable.setSelectionBackground(new Color(46, 46, 46));
			usersTable.setSelectionForeground(Color.WHITE);
			usersTable.setRowMargin(0);
			usersTable.setRowHeight(25);
			usersTable.setBorder(blackline);
			usersTable.setShowVerticalLines(true);
			usersTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
			if(scrollPane!=null) {
				
				
				scrollPane.setViewportView(usersTable);

			}
			JTableHeader tableHeader = usersTable.getTableHeader();
			tableHeader.setBackground(new Color(20, 57, 122));
			tableHeader.setForeground(Color.WHITE);
			tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
			tableHeader.setBorder(blackline);
			tableHeader.setEnabled(true);
		}
		

		

	

		JPanel panelDeleteUser = new JPanel();
		panelDeleteUser.setBackground(new Color(176, 196, 222));
		pestañas.addTab("Delete User", panelDeleteUser);
		panelDeleteUser.setLayout(null);

		tfDeleteUser = new JTextField();
		tfDeleteUser.setColumns(10);
		tfDeleteUser.setBounds(270, 62, 169, 39);
		panelDeleteUser.add(tfDeleteUser);

		JLabel lblDeleteUser = new JLabel("Delete User:");
		lblDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDeleteUser.setBounds(128, 62, 155, 39);
		panelDeleteUser.add(lblDeleteUser);

		getContentPane().add(pestañas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnCreate)) {
			if (tfBirthdate.getText().equals("") || tfCodeUser.getText().equals("") || tfName.getText().equals("")
					|| tfSurname.getText().equals("") || pfpasswordField.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Error, there is one field empty", "ERROR", JOptionPane.OK_OPTION);
			} else {
				if (!rdbtnManager.isSelected() && !rdbtnPolice.isSelected()) {
					JOptionPane.showMessageDialog(this, "Error, Select one type of user", "ERROR",
							JOptionPane.OK_OPTION);
				} else {
					if (!pfpasswordField.getText().equalsIgnoreCase(pfConfirmPassword.getText())) {
						lblTick.setVisible(false);
						lblCross.setVisible(true);
					} else {
						if (tfTextFieldExtra.getText().equals("")) {
							JOptionPane.showMessageDialog(this, "Error, field empty", "ERROR", JOptionPane.OK_OPTION);
						} else {
							if (e.getSource().equals(btnCreate)) {
								lblCross.setVisible(false);
								lblTick.setVisible(true);
								boolean policeOrManager;
								ArrayList<String> campos = new ArrayList<>();
								campos.add(tfCodeUser.getText());
								campos.add(tfName.getText());
								campos.add(tfSurname.getText());
								campos.add(tfBirthdate.getText());
								campos.add(pfpasswordField.getText());
								campos.add(tfTextFieldExtra.getText());
								if (rdbtnManager.isSelected()) {
									policeOrManager = true;
								} else {
									policeOrManager = false;
								}
								administratorInterface.addUser(campos, policeOrManager);
							}
						}
					}
				}
			}

		}
		if (e.getSource().equals(btnLogout)) {
			this.dispose();
			login.setVisible(true);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - login.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - login.getHeight()) / 2);
			login.setLocation(x, y);
		}
		if(e.getSource().equals(btnSearchUser)){
			User user = new User();

			user = administratorInterface.queryUser(tfSearchUser.getText());
			
			System.out.println(tfCodeUser.getText());
			
			

			if (user != null) {

				
				
				ArrayList<User> users2 = new ArrayList<User>();

				users2.add(user);
				usersTable.setVisible(false);

				
				System.out.println(user.getCodUser());
				if (users2.size() > 0) {
					String matrizTabla[][] = new String[users2.size()][4];
					for (int i = 0; i < users2.size(); i++) {

						matrizTabla[i][0] = users2.get(i).getCodUser();
						matrizTabla[i][1] = users2.get(i).getName();
						matrizTabla[i][2] = users2.get(i).getSurname();
						matrizTabla[i][3] = users2.get(i).getBirthDate().toString();
						

					}

					Border blackline;

					blackline = BorderFactory.createLineBorder(Color.black, 1);

					String titulos[] = { "User Code", "Name", "Surname", "Birthdate"};
					queryUserTable = new JTable(matrizTabla, titulos) {
						public boolean editCellAt(int row, int column, java.util.EventObject e) {
							return false;
						}
					};

					queryUserTable.setSelectionBackground(new Color(46, 46, 46));
					queryUserTable.setSelectionForeground(Color.WHITE);
					queryUserTable.setRowMargin(0);
					queryUserTable.setRowHeight(25);
					queryUserTable.setBorder(blackline);
					queryUserTable.setShowVerticalLines(true);
					queryUserTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
					
				if(scrollPane!=null) {
					
					
					scrollPane.setViewportView(queryUserTable);
					
				}
					
					
					JTableHeader tableHeader = 	queryUserTable.getTableHeader();
					tableHeader.setBackground(new Color(20, 57, 122));
					tableHeader.setForeground(Color.WHITE);
					tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
					tableHeader.setBorder(blackline);
					tableHeader.setEnabled(true);
					
					
				}
				
				
			}else if (user == null) {

				JOptionPane.showMessageDialog(this, "User not found");
			}
			
			
		}
	}
}

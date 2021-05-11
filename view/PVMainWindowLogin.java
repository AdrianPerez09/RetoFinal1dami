package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Administrator;
import model.AdministratorController;
import model.AdministratorUserBDimplementation;
import model.Police;
import model.PoliceUserBDimplementation;
import model.PoliceUserController;
import model.User;
import model.UserController;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PVMainWindowLogin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfUser;
	private JButton btnLogin;
	private JPasswordField passwordField;
	private UserController userData;
	private String wCodUser;
	private String wPassword;
	private Boolean adminLogged;
	private Boolean managerLogged;
	private Boolean policeLogged;
	public PVMainWindowLogin(UserController userData) {
		setResizable(false);
		setBackground(new Color(176, 196, 222));
		
		this.userData=userData;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUser = new JTextField();
		tfUser.setBounds(284, 250, 213, 40);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUser.setBounds(285, 214, 125, 25);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPassword.setBounds(284, 308, 125, 25);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Log in");
		btnLogin.setBackground(new Color(220, 220, 220));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.addActionListener(this);
		
		

		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(618, 495, 135, 33);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(284, 354, 213, 40);
		contentPane.add(passwordField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().equals(btnLogin)){
			if(tfUser.getText().equals("") || passwordField.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Error, Introduzca los datos", "ERROR", JOptionPane.OK_OPTION);
			}
			
		}
		
		if (e.getSource().equals(btnLogin)) {
			
		
			
			wCodUser=tfUser.getText();
			wPassword=passwordField.getText();
			
			adminLogged=userData.loginAdmin(wCodUser, wPassword);
			managerLogged=userData.loginManager(wCodUser, wPassword);
			policeLogged=userData.loginPolice(wCodUser, wPassword);
			
			
			if(adminLogged) {
				AdministratorController administratorInterface=new AdministratorUserBDimplementation();
				PVAdministratorWindowMain vAdmin;
				Administrator adm=new Administrator();
				adm=userData.getAdminData(wCodUser, wPassword);
				vAdmin=new PVAdministratorWindowMain(adm,administratorInterface,this);
				this.setVisible(false);
				vAdmin.setVisible(true);

				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (int) ((dimension.getWidth() - vAdmin.getWidth()) / 2);
				int y = (int) ((dimension.getHeight() - vAdmin.getHeight()) / 2);
				vAdmin.setLocation(x, y);
			}
			
			if(managerLogged) {
				
				
				
			}
			
			if(policeLogged) {
		
				PoliceUserController policeInterface=new PoliceUserBDimplementation();
				
				Police poli=new Police();
				PVPoliceWindowMain vPolice;		
				poli=userData.getPoliceData(wCodUser, wPassword);
				
				vPolice=new PVPoliceWindowMain(poli,policeInterface,this);
				this.setVisible(false);
				vPolice.setVisible(true);
				

				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (int) ((dimension.getWidth() - vPolice.getWidth()) / 2);
				int y = (int) ((dimension.getHeight() - vPolice.getHeight()) / 2);
				vPolice.setLocation(x, y);
								
		
	}
			
			
		}
		
		
		
	}
}

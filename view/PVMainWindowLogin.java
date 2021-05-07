package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


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
import java.awt.FontFormatException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class PVMainWindowLogin extends JFrame implements ActionListener,FocusListener{

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
	public PVMainWindowLogin(UserController userData) throws FontFormatException, IOException {
		setTitle("R.P.D - login ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PVMainWindowLogin.class.getResource("/img/icon.png")));
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
		tfUser.setBounds(310, 224, 213, 40);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		tfUser.setFocusable(true);	
		tfUser.addFocusListener(this);
		
		
		
		InputStream is = PVMainWindowLogin.class.getResourceAsStream("/font/policecruiser.ttf");
		Font loginFont=Font.createFont(Font.TRUETYPE_FONT, is);

		Font sizedLoginFont = loginFont.deriveFont(37f);
	
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setFont(sizedLoginFont);
		lblUser.setBounds(311, 188, 125, 25);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(sizedLoginFont);
		lblPassword.setBounds(310, 282, 173, 35);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Log in");
		btnLogin.setBackground(new Color(220, 220, 220));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.addActionListener(this);
		
		

		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(484, 413, 135, 33);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(310, 328, 213, 40);
		contentPane.add(passwordField);
		
		JLabel loginBackground = new JLabel("");
		loginBackground.setIcon(new ImageIcon(PVMainWindowLogin.class.getResource("/img/loginBackground.jpg")));
		loginBackground.setBounds(0, -14, 794, 614);
		contentPane.add(loginBackground);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().equals(btnLogin)){
			if(tfUser.getText().equals("") || passwordField.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Error, there are empty fields. \n Please fill them.", "Empty Fields", JOptionPane.OK_OPTION);
			}
			
		}
		
		if (e.getSource().equals(btnLogin)) {


		
				tfUser.requestFocus();
					
			
			
			
			wCodUser=tfUser.getText();
			wPassword=passwordField.getText();
			
			adminLogged=userData.loginAdmin(wCodUser, wPassword);
			managerLogged=userData.loginManager(wCodUser, wPassword);
			policeLogged=userData.loginPolice(wCodUser, wPassword);
			
			
			if(adminLogged) {
				
				
				
			}
			
			if(managerLogged) {
				
				
				
			}
			
			if(policeLogged) {
		
				PoliceUserController policeInterface=new PoliceUserBDimplementation();
				
				Police poli=new Police();
				PVPoliceWindowMain vPolice;		
				poli=userData.getPoliceData(wCodUser, wPassword);
				
				try {
					vPolice=new PVPoliceWindowMain(poli,policeInterface,this);
				
				this.dispose();
				vPolice.setVisible(true);
				
				

				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (int) ((dimension.getWidth() - vPolice.getWidth()) / 2);
				int y = (int) ((dimension.getHeight() - vPolice.getHeight()) / 2);
				vPolice.setLocation(x, y);
					
				tfUser.setText("");
				passwordField.setText("");
				
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		
	}
			
			
		}
		
		
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
		

		
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		

	
	
		
		
	}
}

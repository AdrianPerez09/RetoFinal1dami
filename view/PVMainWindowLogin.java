package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PVMainWindowLogin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfUser;
	private JTextField tfPassword;
	private JButton btnLogin;

	public PVMainWindowLogin() {
		setBackground(new Color(176, 196, 222));
		
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
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(284, 344, 213, 40);
		contentPane.add(tfPassword);
		
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().equals(btnLogin)){
			if(tfUser.getText()=="" || e.getSource().equals(tfPassword.getText()=="")) {
				JOptionPane.showMessageDialog(this, "Error, Introduzca los datos", "ERROR", JOptionPane.OK_OPTION);
			}
			
		}
		
	}
}

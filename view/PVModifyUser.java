package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AdministratorController;
import model.User;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PVModifyUser extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsercodmod;
	private JTextField tfNameMod;
	private JTextField tfSurnamemod;
	private JTextField tfBirthDatemod;
	private JPasswordField pfMod;
	private JLabel lblSurname;
	private JLabel lblBirthDate;
	private JLabel lblNewPassword;
	private JButton btnClose;
	private JButton btnModify;
	private AdministratorController administratorInterface;
	public PVModifyUser(AdministratorController administratorInterface, User userMod){
		this.administratorInterface=administratorInterface;
		setBounds(100, 100, 300, 485);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfUsercodmod = new JTextField();
		tfUsercodmod.setBounds(127, 51, 137, 29);
		tfUsercodmod.setText(userMod.getCodUser());
		tfUsercodmod.enable(false);
		contentPanel.add(tfUsercodmod);
		tfUsercodmod.setColumns(10);
		
		tfNameMod = new JTextField();
		tfNameMod.setColumns(10);
		tfNameMod.setText(userMod.getName());
		tfNameMod.setBounds(127, 91, 137, 29);
		contentPanel.add(tfNameMod);
		
		tfSurnamemod = new JTextField();
		tfSurnamemod.setColumns(10);
		tfSurnamemod.setText(userMod.getSurname());
		tfSurnamemod.setBounds(127, 131, 137, 29);
		contentPanel.add(tfSurnamemod);
		
		tfBirthDatemod = new JTextField();
		tfBirthDatemod.setColumns(10);
		tfBirthDatemod.setText(userMod.getBirthDate().toString());
		tfBirthDatemod.setBounds(127, 171, 137, 29);
		contentPanel.add(tfBirthDatemod);
		
		pfMod = new JPasswordField();
		pfMod.setBounds(127, 223, 137, 29);
		contentPanel.add(pfMod);
		
		JLabel lblUserCode = new JLabel("User Code:");
		lblUserCode.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUserCode.setBounds(36, 44, 97, 37);
		contentPanel.add(lblUserCode);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(70, 92, 84, 21);
		contentPanel.add(lblName);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSurname.setBounds(48, 139, 84, 21);
		contentPanel.add(lblSurname);
		
		lblBirthDate = new JLabel("Birthdate:");
		lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBirthDate.setBounds(48, 179, 84, 21);
		contentPanel.add(lblBirthDate);
		
		lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewPassword.setBounds(10, 216, 121, 37);
		contentPanel.add(lblNewPassword);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(this);
		btnModify.setBounds(170, 405, 104, 21);
		contentPanel.add(btnModify);
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setBounds(10, 404, 104, 21);
		contentPanel.add(btnClose);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnClose)){
			this.dispose();
		}
		if(e.getSource().equals(btnModify)){
			
		}
	}
}

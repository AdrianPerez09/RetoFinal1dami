package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import model.Police;
import model.PoliceUserController;
import model.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class PVPoliceWindowMain extends JDialog  implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearchCode;
	private JTable caseTable;
	private JTextArea txtErrorCodeNotFound1;
	private JTextArea txtErrorCodeNotFound2;
	private JButton btnSearchCase;
	private JButton btnSearchSuspect;
	private JButton btnLogout;
	private JTextField txtSuspectCode;
	private JTable suspectTable;
	private PoliceUserController policeInterface;
	private PVMainWindowLogin login;


	
	public PVPoliceWindowMain(Police poli, PoliceUserController policeInterface, PVMainWindowLogin login) {
		setResizable(false);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
	this.policeInterface=policeInterface;
	this.login=login;
	
	
	        //Creamos el conjunto de pestañas
	        JTabbedPane pestañas=new JTabbedPane();
	               
	                      //Creamos el panel y lo añadimos a las pestañas
	                      JPanel welcomePanel=new JPanel();
	                      welcomePanel.setBackground(new Color(176, 196, 222));
	                      welcomePanel.setLayout(null);
	                      
	                             //Añadimos un nombre de la pestaña y el panel
	                             pestañas.addTab("Welcome", welcomePanel);
	                             
	                             JLabel lblWelcome = new JLabel("!Welcome \n"+poli.getName()+" "+poli.getSurname()+"!");
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
	                             
	                             JLabel lblBadgeNumber = new JLabel("Badge number:"+" "+poli.getNumberPolice());
	                             lblBadgeNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
	                             lblBadgeNumber.setBounds(304, 405, 177, 25);
	                             welcomePanel.add(lblBadgeNumber);
	        
	               //Realizamos lo mismo con el resto
	               JPanel panel2=new JPanel();
	               panel2.setBackground(new Color(176, 196, 222));
	               pestañas.addTab("Search case", panel2);
	               panel2.setLayout(null);
	               
	               JLabel lblSearchCase = new JLabel("Case code:");
	               lblSearchCase.setFont(new Font("Tahoma", Font.PLAIN, 24));
	               lblSearchCase.setBounds(58, 94, 157, 77);
	               panel2.add(lblSearchCase);
	               
	               txtSearchCode = new JTextField();
	               txtSearchCode.setBounds(205, 122, 231, 26);
	               panel2.add(txtSearchCode);
	               txtSearchCode.setColumns(10);
	               
	               JLabel lblSearchCodeTitle = new JLabel("Search case");
	               lblSearchCodeTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
	               lblSearchCodeTitle.setBounds(308, 26, 214, 37);
	               panel2.add(lblSearchCodeTitle);
	               
	               caseTable = new JTable();
	               caseTable.setBounds(58, 182, 660, 330);
	               panel2.add(caseTable);
	               
	               btnSearchCase = new JButton("Search");
	               btnSearchCase.setBackground(new Color(211, 211, 211));
	               btnSearchCase.setFont(new Font("Tahoma", Font.PLAIN, 16));
	               btnSearchCase.setBounds(465, 124, 89, 25);
	               btnSearchCase.addActionListener(this);
	               panel2.add(btnSearchCase);
	               
	               txtErrorCodeNotFound1 = new JTextArea();
	               txtErrorCodeNotFound1.setBounds(585, 125, 184, 47);          
	               txtErrorCodeNotFound1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	               txtErrorCodeNotFound1.setText("Error,empty field, \r\nplease enter a case code");
	               txtErrorCodeNotFound1.setEditable(false);
	               txtErrorCodeNotFound1.setBackground(new Color(175, 238, 238));
	    		panel2.add(txtErrorCodeNotFound1);    
	    		txtErrorCodeNotFound1.setVisible(false);
	               
	        JPanel panel3=new JPanel();
	        panel3.setBackground(new Color(176, 196, 222));
	 
	        pestañas.addTab("Search suspect", panel3);
	        panel3.setLayout(null);
	        
	        txtSuspectCode = new JTextField();
	        txtSuspectCode.setColumns(10);
	        txtSuspectCode.setBounds(207, 129, 231, 26);
	        panel3.add(txtSuspectCode);
	        
	        JLabel lblSearchSuspect = new JLabel("Search Suspect");
	        lblSearchSuspect.setFont(new Font("Tahoma", Font.PLAIN, 32));
	        lblSearchSuspect.setBounds(267, 34, 231, 37);
	        panel3.add(lblSearchSuspect);
	        
	        JLabel lblSuspectCode = new JLabel("Suspect code:");
	        lblSuspectCode.setFont(new Font("Tahoma", Font.PLAIN, 24));
	        lblSuspectCode.setBounds(40, 118, 157, 47);
	        panel3.add(lblSuspectCode);
	        
	        btnSearchSuspect = new JButton("Search");
	        btnSearchSuspect.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        btnSearchSuspect.setBounds(469, 130, 89, 25);
	        btnSearchSuspect.addActionListener(this);
	        panel3.add(btnSearchSuspect);
	        
	        suspectTable = new JTable();
	        suspectTable.setBounds(58, 182, 660, 330);
	        panel3.add(suspectTable);
	        
	        txtErrorCodeNotFound2 = new JTextArea();
            txtErrorCodeNotFound2.setBounds(568, 118, 201, 47);          
            txtErrorCodeNotFound2.setFont(new Font("Tahoma", Font.PLAIN, 15));
            txtErrorCodeNotFound2.setText("Error,empty field, \r\nplease enter a Suspect code.");
            txtErrorCodeNotFound2.setEditable(false);
            txtErrorCodeNotFound2.setBackground(new Color(175, 238, 238));
 		panel3.add(txtErrorCodeNotFound2);    
 		txtErrorCodeNotFound2.setVisible(false);
	        
	        
	 
	        JPanel panel4=new JPanel();
	 
	 
	        getContentPane().add(pestañas);
	    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
		if (e.getSource().equals(btnSearchCase) && this.txtSearchCode.getText().equals("")) {

			

			txtErrorCodeNotFound1.setVisible(true);

			Timer time = new Timer();
			TimerTask error = new TimerTask() {

				@Override
				public void run() {
					PVPoliceWindowMain v1 = new PVPoliceWindowMain(null, null, null);

					txtErrorCodeNotFound1.setVisible(false);

				}
			};

			time.schedule(error, 3500);

		}
		
		
		if (e.getSource().equals(btnSearchSuspect) && this.txtSuspectCode.getText().equals("")) {

			

			txtErrorCodeNotFound2.setVisible(true);

			Timer time = new Timer();
			TimerTask error = new TimerTask() {

				@Override
				public void run() {
					PVPoliceWindowMain v1 = new PVPoliceWindowMain(null, null,null);

					txtErrorCodeNotFound2.setVisible(false);

				}
			};

			time.schedule(error, 3500);

		}
		
		
		if(e.getSource().equals(btnLogout)) {
			
			this.dispose();
			
			login.setVisible(true);
			
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - login.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - login.getHeight()) / 2);
			login.setLocation(x, y);
			
			
			
		}
		
		
		
		
	}
}

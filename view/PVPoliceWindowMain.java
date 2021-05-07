package view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import model.Case;
import model.Police;
import model.PoliceUserController;
import model.Suspect;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class PVPoliceWindowMain extends JDialog implements ActionListener, FocusListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearchCode;
	private JTextArea txtErrorCodeNotFound1;
	private JTextArea txtErrorCodeNotFound2;
	private JButton btnSearchCase;
	private JButton btnSearchSuspect;
	private JButton btnLogout;
	private JButton btnListAllSuspects;
	private JButton btnListAll;
	private JTextField txtSuspectCode;
	private JTable caseTable;
	private JTable caseQueryTable;
	private JTable suspectTable;
	private JTable suspectQueryTable;

	private PoliceUserController policeInterface;
	private PVMainWindowLogin login;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private Police poli;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel lblSearchCaseBackground;
	private JLabel lblSearchSuspectBackground;

	public PVPoliceWindowMain(Police poli, PoliceUserController policeInterface, PVMainWindowLogin login)
			throws FontFormatException, IOException {
		setTitle("RPD - Police officer view");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PVPoliceWindowMain.class.getResource("/img/emblem.png")));
		setResizable(false);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		this.policeInterface = policeInterface;
		this.login = login;
		this.poli = poli;

		// Creamos el conjunto de pestañas
		JTabbedPane pestañas = new JTabbedPane();

		// Creamos el panel y lo añadimos a las pestañas
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(176, 196, 222));
		welcomePanel.setLayout(null);

		// Añadimos un nombre de la pestaña y el panel
		pestañas.addTab("Welcome", welcomePanel);

		InputStream is = PVMainWindowLogin.class.getResourceAsStream("/font/MicroExtendFLF.ttf");
		Font PoliceFont = Font.createFont(Font.TRUETYPE_FONT, is);
		Font tinySizedPoliceFont = PoliceFont.deriveFont(12f);
		Font sizedPoliceFont = PoliceFont.deriveFont(30f);
		Font sizedPoliceFont1 = PoliceFont.deriveFont(20f);
		Font sizedPoliceFont2 = PoliceFont.deriveFont(15f);
		Font sizedPoliceFont3 = PoliceFont.deriveFont(32f);
		Font sizedPoliceFont4 = PoliceFont.deriveFont(20f);

		try {

			JLabel lblWelcome = new JLabel("!Welcome \n" + poli.getName() + " " + poli.getSurname() + "!");
			lblWelcome.setForeground(Color.WHITE);
			lblWelcome.setFont(sizedPoliceFont);
			lblWelcome.setBounds(162, 345, 569, 63);
			welcomePanel.add(lblWelcome);

		} catch (Exception e) {
			// TODO: handle exception
		}

		JLabel lblImgLogo = new JLabel("New label");
		lblImgLogo.setIcon(new ImageIcon(PVPoliceWindowMain.class.getResource("/img/emblem.png")));
		lblImgLogo.setBounds(250, 35, 260, 299);
		welcomePanel.add(lblImgLogo);

		btnLogout = new JButton("Log out");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(618, 476, 135, 33);
		btnLogout.addActionListener(this);
		welcomePanel.add(btnLogout);

		JLabel lblBadgeNumber = new JLabel("Badge number:" + " " + poli.getNumberPolice());
		lblBadgeNumber.setForeground(Color.WHITE);
		lblBadgeNumber.setFont(sizedPoliceFont1);
		lblBadgeNumber.setBounds(291, 404, 228, 25);
		welcomePanel.add(lblBadgeNumber);

		JLabel lblWelcomeBackground = new JLabel("");
		lblWelcomeBackground
				.setIcon(new ImageIcon(PVPoliceWindowMain.class.getResource("/img/applicationBackground.png")));
		lblWelcomeBackground.setBounds(0, 0, 789, 543);
		welcomePanel.add(lblWelcomeBackground);

		// Realizamos lo mismo con el resto
		panel2 = new JPanel();
		panel2.setBackground(new Color(176, 196, 222));
		pestañas.addTab("Search case", panel2);
		panel2.setLayout(null);

		JLabel lblSearchCase = new JLabel("Case code:");
		lblSearchCase.setForeground(Color.WHITE);
		lblSearchCase.setFont(sizedPoliceFont4);
		lblSearchCase.setBounds(54, 112, 157, 77);
		panel2.add(lblSearchCase);

		txtSearchCode = new JTextField();
		txtSearchCode.setBounds(204, 139, 231, 26);
		panel2.add(txtSearchCode);
		txtSearchCode.setColumns(10);

		JLabel lblSearchCodeTitle = new JLabel("Search case");
		lblSearchCodeTitle.setForeground(Color.WHITE);
		lblSearchCodeTitle.setFont(sizedPoliceFont3);
		lblSearchCodeTitle.setBounds(247, 11, 269, 37);
		panel2.add(lblSearchCodeTitle);

		ArrayList<Case> cases = policeInterface.obtainCase();
		if (cases.size() > 0) {
			String matrizTabla[][] = new String[cases.size()][4];
			for (int i = 0; i < cases.size(); i++) {

				matrizTabla[i][0] = cases.get(i).getCodCase();
				matrizTabla[i][1] = cases.get(i).getDesc();
				matrizTabla[i][2] = cases.get(i).getNameCase();
				matrizTabla[i][3] = cases.get(i).getLocation();

			}

			Border blackline;

			blackline = BorderFactory.createLineBorder(Color.black, 1);

			scrollPane = new JScrollPane();
			scrollPane.setBounds(58, 182, 660, 330);
			panel2.add(scrollPane);

			String titulos[] = { "Case code", "Description", "Case name", "Location", };
			caseTable = new JTable(matrizTabla, titulos) {
				public boolean editCellAt(int row, int column, java.util.EventObject e) {
					return false;
				}
			};
			;
			caseTable.setSelectionBackground(new Color(46, 46, 46));
			caseTable.setSelectionForeground(Color.WHITE);
			caseTable.setRowMargin(0);
			caseTable.setRowHeight(25);
			caseTable.setBorder(blackline);
			caseTable.setShowVerticalLines(true);
			caseTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
			scrollPane.setViewportView(caseTable);

			JTableHeader tableHeader = caseTable.getTableHeader();
			tableHeader.setBackground(new Color(20, 57, 122));
			tableHeader.setForeground(Color.WHITE);
			tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
			tableHeader.setBorder(blackline);
			tableHeader.setEnabled(true);
		}

		btnSearchCase = new JButton("Search");
		btnSearchCase.setBackground(new Color(220, 220, 220));
		btnSearchCase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchCase.setBounds(459, 138, 89, 25);
		btnSearchCase.addActionListener(this);
		panel2.add(btnSearchCase);

		Border errorBorder;
		Color errorColor = new Color(50, 104, 191);

		errorBorder = BorderFactory.createLineBorder(errorColor, 3);

		txtErrorCodeNotFound1 = new JTextArea();
		txtErrorCodeNotFound1.setBounds(579, 110, 184, 55);
		txtErrorCodeNotFound1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtErrorCodeNotFound1.setText("      Error,empty field.\r\nPlease enter a case code");
		txtErrorCodeNotFound1.setEditable(false);
		txtErrorCodeNotFound1.setBackground(new Color(175, 238, 238, 210));
		txtErrorCodeNotFound1.setBorder(errorBorder);
		panel2.add(txtErrorCodeNotFound1);

		Case cas = new Case();

		cas = policeInterface.caseAssigned(poli.getCodUser());

		JLabel lblCaseAssigned = new JLabel("You are assigned in: " + cas.getCodCase());
		lblCaseAssigned.setForeground(Color.WHITE);
		lblCaseAssigned.setFont(sizedPoliceFont2);
		lblCaseAssigned.setBounds(56, 80, 375, 26);
		panel2.add(lblCaseAssigned);

		btnListAll = new JButton("List all");
		btnListAll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListAll.setBackground(new Color(220, 220, 220));
		btnListAll.setBounds(459, 108, 89, 25);
		btnListAll.addActionListener(this);
		panel2.add(btnListAll);

		lblSearchCaseBackground = new JLabel("");
		lblSearchCaseBackground
				.setIcon(new ImageIcon(PVPoliceWindowMain.class.getResource("/img/applicationBackground.png")));
		lblSearchCaseBackground.setBounds(0, 0, 789, 543);
		panel2.add(lblSearchCaseBackground);
		txtErrorCodeNotFound1.setVisible(false);

		panel3 = new JPanel();
		panel3.setBackground(new Color(176, 196, 222));

		ArrayList<Suspect> suspects = policeInterface.obtainSuspect();
		if (suspects.size() > 0) {
			String matrizTabla[][] = new String[suspects.size()][4];
			for (int i = 0; i < suspects.size(); i++) {

				matrizTabla[i][0] = suspects.get(i).getCodSus();
				matrizTabla[i][1] = suspects.get(i).getName();
				matrizTabla[i][2] = suspects.get(i).getSurname();
				matrizTabla[i][3] = suspects.get(i).getBirthDate().toString();

			}

			Border blackline;

			blackline = BorderFactory.createLineBorder(Color.black, 1);

			scrollPane2 = new JScrollPane();
			scrollPane2.setBounds(58, 182, 660, 330);
			panel3.add(scrollPane2);

			String titulos[] = { "Suspect code", "Name", "Surname", "birth date", };
			suspectTable = new JTable(matrizTabla, titulos){
		         public boolean editCellAt(int row, int column, java.util.EventObject e) {
		             return false;
		          }
		       };
			suspectTable.setSelectionBackground(new Color(46, 46, 46));
			suspectTable.setSelectionForeground(Color.WHITE);
			suspectTable.setRowMargin(0);
			suspectTable.setRowHeight(25);
			suspectTable.setBorder(blackline);
			suspectTable.setShowVerticalLines(true);
			suspectTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
			scrollPane2.setViewportView(suspectTable);

			JTableHeader suspectTableHeader = suspectTable.getTableHeader();
			suspectTableHeader.setBackground(new Color(20, 57, 122));
			suspectTableHeader.setForeground(Color.WHITE);
			suspectTableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
			suspectTableHeader.setBorder(blackline);
			suspectTableHeader.setEnabled(true);
		}

		pestañas.addTab("Search suspect", panel3);
		panel3.setLayout(null);

		txtSuspectCode = new JTextField();
		txtSuspectCode.setColumns(10);
		txtSuspectCode.setBounds(228, 135, 231, 26);
		panel3.add(txtSuspectCode);

		JLabel lblSearchSuspect = new JLabel("Search suspect");
		lblSearchSuspect.setForeground(Color.WHITE);
		lblSearchSuspect.setFont(sizedPoliceFont3);
		lblSearchSuspect.setBounds(248, 11, 379, 37);
		panel3.add(lblSearchSuspect);

		JLabel lblSuspectCode = new JLabel("Suspect code:");
		lblSuspectCode.setForeground(Color.WHITE);
		lblSuspectCode.setFont(sizedPoliceFont4);
		lblSuspectCode.setBounds(48, 122, 249, 47);
		panel3.add(lblSuspectCode);

		btnSearchSuspect = new JButton("Search");
		btnSearchSuspect.setBackground(new Color(220, 220, 220));
		btnSearchSuspect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchSuspect.setBounds(469, 136, 89, 25);
		btnSearchSuspect.addActionListener(this);
		panel3.add(btnSearchSuspect);

		txtErrorCodeNotFound2 = new JTextArea();
		txtErrorCodeNotFound2.setBounds(572, 114, 195, 47);
		txtErrorCodeNotFound2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtErrorCodeNotFound2.setText("       Error,empty field\r\nPlease enter a Suspect code.");
		txtErrorCodeNotFound2.setEditable(false);
		txtErrorCodeNotFound2.setBackground(new Color(175, 238, 238, 210));
		txtErrorCodeNotFound2.setBorder(errorBorder);
		panel3.add(txtErrorCodeNotFound2);

		btnListAllSuspects = new JButton("List All");
		btnListAllSuspects.setBackground(new Color(220, 220, 220));
		btnListAllSuspects.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListAllSuspects.setBounds(469, 105, 89, 25);
		btnListAllSuspects.addActionListener(this);
		panel3.add(btnListAllSuspects);

		lblSearchSuspectBackground = new JLabel("");
		lblSearchSuspectBackground
				.setIcon(new ImageIcon(PVPoliceWindowMain.class.getResource("/img/applicationBackground.png")));
		lblSearchSuspectBackground.setBounds(0, 0, 789, 543);
		panel3.add(lblSearchSuspectBackground);
		txtErrorCodeNotFound2.setVisible(false);

		JPanel panel4 = new JPanel();

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

					txtErrorCodeNotFound1.setVisible(false);

				}
			};

			time.schedule(error, 3500);

		}

		// CASES TABLE

		if (e.getSource().equals(btnSearchCase) && !this.txtSearchCode.getText().equals("")) {


			Case cas = new Case();

			cas = policeInterface.queryCase(txtSearchCode.getText());

			if (cas != null) {

				ArrayList<Case> cases = new ArrayList<Case>();

				cases.add(cas);
				caseTable.setVisible(false);

				if (cases.size() > 0) {
					String matrizTabla[][] = new String[cases.size()][4];
					for (int i = 0; i < cases.size(); i++) {

						matrizTabla[i][0] = cases.get(i).getCodCase();
						matrizTabla[i][1] = cases.get(i).getDesc();
						matrizTabla[i][2] = cases.get(i).getNameCase();
						matrizTabla[i][3] = cases.get(i).getLocation();

					}

					Border blackline;

					blackline = BorderFactory.createLineBorder(Color.black, 1);

					String titulos[] = { "Case code", "Description", "Case name", "Location", };
					caseQueryTable = new JTable(matrizTabla, titulos) {
						public boolean editCellAt(int row, int column, java.util.EventObject e) {
							return false;
						}
					};

					caseQueryTable.setSelectionBackground(new Color(46, 46, 46));
					caseQueryTable.setSelectionForeground(Color.WHITE);
					caseQueryTable.setRowMargin(0);
					caseQueryTable.setRowHeight(25);
					caseQueryTable.setBorder(blackline);
					caseQueryTable.setShowVerticalLines(true);
					caseQueryTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
					scrollPane.setViewportView(caseQueryTable);

					JTableHeader tableHeader = caseQueryTable.getTableHeader();
					tableHeader.setBackground(new Color(20, 57, 122));
					tableHeader.setForeground(Color.WHITE);
					tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
					tableHeader.setBorder(blackline);
					tableHeader.setEnabled(true);
				}

			}

			else if (cas == null) {

				JOptionPane.showMessageDialog(this, "Case not found");
			}

		}

		if (e.getSource().equals(btnListAll)) {

			if (caseQueryTable != null) {

				caseQueryTable.setVisible(false);
			}

			ArrayList<Case> cases = policeInterface.obtainCase();
			if (cases.size() > 0) {
				String matrizTabla[][] = new String[cases.size()][4];
				for (int i = 0; i < cases.size(); i++) {

					matrizTabla[i][0] = cases.get(i).getCodCase();
					matrizTabla[i][1] = cases.get(i).getDesc();
					matrizTabla[i][2] = cases.get(i).getNameCase();
					matrizTabla[i][3] = cases.get(i).getLocation();

				}

				Border blackline;

				blackline = BorderFactory.createLineBorder(Color.black, 1);

				String titulos[] = { "Case code", "Description", "Case name", "Location", };
				caseTable = new JTable(matrizTabla, titulos) {
					public boolean editCellAt(int row, int column, java.util.EventObject e) {
						return false;
					}
				};
				caseTable.setSelectionBackground(new Color(46, 46, 46));
				caseTable.setSelectionForeground(Color.WHITE);
				caseTable.setRowMargin(0);
				caseTable.setRowHeight(25);
				caseTable.setBorder(blackline);
				caseTable.setShowVerticalLines(true);
				caseTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
				scrollPane.setViewportView(caseTable);

				JTableHeader tableHeader = caseTable.getTableHeader();
				tableHeader.setBackground(new Color(20, 57, 122));
				tableHeader.setForeground(Color.WHITE);
				tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
				tableHeader.setBorder(blackline);
				tableHeader.setEnabled(true);
			}

		}

		// SUSPECTS TABLE

		if (e.getSource().equals(btnSearchSuspect) && this.txtSuspectCode.getText().equals("")) {

			txtErrorCodeNotFound2.setVisible(true);

			Timer time = new Timer();
			TimerTask error = new TimerTask() {

				@Override
				public void run() {

					txtErrorCodeNotFound2.setVisible(false);

				}
			};

			time.schedule(error, 3500);

		}

		if (e.getSource().equals(btnSearchSuspect) && !this.txtSuspectCode.getText().equals("")) {

			Suspect sus = new Suspect();

			sus = policeInterface.querySuspect(txtSuspectCode.getText());

			if (sus != null) {

				suspectTable.setVisible(false);

				ArrayList<Suspect> suspects = new ArrayList<Suspect>();

				suspects.add(sus);
				suspectTable.setVisible(false);

				if (suspects.size() > 0) {
					String matrizTabla[][] = new String[suspects.size()][4];
					for (int i = 0; i < suspects.size(); i++) {

						matrizTabla[i][0] = suspects.get(i).getCodSus();
						matrizTabla[i][1] = suspects.get(i).getName();
						matrizTabla[i][2] = suspects.get(i).getSurname();
						matrizTabla[i][3] = suspects.get(i).getBirthDate().toString();

					}

					Border blackline;

					blackline = BorderFactory.createLineBorder(Color.black, 1);

					String titulos[] = { "Suspect code", "Name", "Surname", "birth date" };
					suspectQueryTable = new JTable(matrizTabla, titulos) {
						public boolean editCellAt(int row, int column, java.util.EventObject e) {
							return false;
						}
					};
					suspectQueryTable.setSelectionBackground(new Color(46, 46, 46));
					suspectQueryTable.setSelectionForeground(Color.WHITE);
					suspectQueryTable.setRowMargin(0);
					suspectQueryTable.setRowHeight(25);
					suspectQueryTable.setBorder(blackline);
					suspectQueryTable.setShowVerticalLines(true);
					suspectQueryTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
					scrollPane2.setViewportView(suspectQueryTable);

					JTableHeader tableHeader = suspectQueryTable.getTableHeader();
					tableHeader.setBackground(new Color(20, 57, 122));
					tableHeader.setForeground(Color.WHITE);
					tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
					tableHeader.setBorder(blackline);
					tableHeader.setEnabled(true);
				}

			}

			else if (sus == null) {

				JOptionPane.showMessageDialog(this, "Case not found");

			}

		}

		if (e.getSource().equals(btnListAllSuspects)) {

			if (suspectQueryTable != null) {

				suspectQueryTable.setVisible(false);
			}

			ArrayList<Suspect> suspects = policeInterface.obtainSuspect();
			if (suspects.size() > 0) {
				String matrizTabla[][] = new String[suspects.size()][4];
				for (int i = 0; i < suspects.size(); i++) {

					matrizTabla[i][0] = suspects.get(i).getCodSus();
					matrizTabla[i][1] = suspects.get(i).getName();
					matrizTabla[i][2] = suspects.get(i).getSurname();
					matrizTabla[i][3] = suspects.get(i).getBirthDate().toString();

				}

				Border blackline;

				blackline = BorderFactory.createLineBorder(Color.black, 1);

				String titulos[] = { "Suspect code", "Name", "Surname", "birth date" };
				suspectTable = new JTable(matrizTabla, titulos) {
					public boolean editCellAt(int row, int column, java.util.EventObject e) {
						return false;
					}
				};
				suspectTable.setSelectionBackground(new Color(46, 46, 46));
				suspectTable.setSelectionForeground(Color.WHITE);
				suspectTable.setRowMargin(0);
				suspectTable.setRowHeight(25);
				suspectTable.setBorder(blackline);
				suspectTable.setShowVerticalLines(true);
				suspectTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
				scrollPane2.setViewportView(suspectTable);

				JTableHeader suspectTableHeader = suspectTable.getTableHeader();
				suspectTableHeader.setBackground(new Color(20, 57, 122));
				suspectTableHeader.setForeground(Color.WHITE);
				suspectTableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
				suspectTableHeader.setBorder(blackline);
				suspectTableHeader.setEnabled(true);
			}

		}

		// WELCOME PANEL

		if (e.getSource().equals(btnLogout)) {

			this.dispose();

			login.setVisible(true);

			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - login.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - login.getHeight()) / 2);
			login.setLocation(x, y);

		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(btnLogout)) {

		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}
}

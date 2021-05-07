package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PoliceUserBDimplementation implements PoliceUserController {

	private Connection con;
	private PreparedStatement stmt;
	
	final String listCase ="SELECT * FROM OPENCASE";
	final String caseAssigned="SELECT o.codcase FROM opencase o,case_assigned cs,police p where o.codcase=cs.codcase and p.coduser=cs.coduserp and p.coduser=?";
	final String selectCase ="SELECT * FROM OPENCASE WHERE CODCASE=?";
	final String listSuspect ="SELECT * FROM SUSPECT";
	final String selectSuspect ="SELECT * FROM SUSPECT WHERE CODSUS=?";
	
	private void openConnection(){
		 try {
		  //String url = "jdbc:mysql://localhost/nombreBaseDatos";
		  String url ="jdbc:mysql://localhost:3306/police_department?serverTimezone=Europe/Madrid&useSSL=false";
		  //con =  DriverManager.getConnection(url+"?" +"user=____&password=_____");
		  con =  DriverManager.getConnection(url,"root" ,"abcd*1234");

		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		 }	
		}
	
	private void closeConnection() throws SQLException {
		System.out.println("Conexion Cerrada.");
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
		System.out.println("------------------------");
	}
	
	@Override
	public Case queryCase(String wcodCase) {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Case cas= null;


				this.openConnection();

				try {
					stmt = con.prepareStatement(selectCase);

				
					stmt.setString(1, wcodCase);

					rs = stmt.executeQuery();

					if (rs.next()) {
						cas = new Case();
						cas.setCodCase(rs.getString("codcase"));
						cas.setDesc(rs.getString("description"));
						cas.setNameCase(rs.getString("nameCase"));
						cas.setLocation(rs.getString("location"));
					} else
						cas = null;
				} catch (SQLException e) {
					System.out.println("Error de SQL");
					e.printStackTrace();
				} finally {
			
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException ex) {
							System.out.println("Error en cierre del ResultSet");
						}
					}
					try {
						this.closeConnection();
					} catch (SQLException e) {
						System.out.println("Error en el cierre de la BD");
						e.printStackTrace();
					}
				}

				return cas;

	}

	@Override
	public Suspect querySuspect(String wcodSus) {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Suspect sus= null;


				this.openConnection();

				try {
					stmt = con.prepareStatement(selectSuspect);

				
					stmt.setString(1, wcodSus);

					rs = stmt.executeQuery();

					if (rs.next()) {
						sus = new Suspect();
						sus.setCodSus(rs.getString(1));
						sus.setName(rs.getString("name"));
						sus.setSurname(rs.getString("surname"));
						sus.setBirthDate(rs.getDate("birthDate"));
					
					} else
						sus = null;
				} catch (SQLException e) {
					System.out.println("Error de SQL");
					e.printStackTrace();
				} finally {
			
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException ex) {
							System.out.println("Error en cierre del ResultSet");
						}
					}
					try {
						this.closeConnection();
					} catch (SQLException e) {
						System.out.println("Error en el cierre de la BD");
						e.printStackTrace();
					}
				}

				return sus;

	
	}

	@Override
	public ArrayList<Case> obtainCase() {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Case cas= null;

		ArrayList<Case>cases=new ArrayList<>();
		

				this.openConnection();

				try {
					stmt = con.prepareStatement(listCase);


					rs = stmt.executeQuery();

					while (rs.next()) {
						cas = new Case();
						cas.setCodCase(rs.getString(1));
						cas.setDesc(rs.getString("description"));
						cas.setNameCase(rs.getString("nameCase"));
						cas.setLocation(rs.getString("location"));
						cases.add(cas);
					}
				} catch (SQLException e) {
					System.out.println("Error de SQL");
					e.printStackTrace();
				} finally {
			
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException ex) {
							System.out.println("Error en cierre del ResultSet");
						}
					}
					try {
						this.closeConnection();
					} catch (SQLException e) {
						System.out.println("Error en el cierre de la BD");
						e.printStackTrace();
					}
				}

				return cases;
	

	}

	@Override
	public ArrayList<Suspect> obtainSuspect() {
		// TODO Auto-generated method stub
		
		
		ResultSet rs = null;
		Suspect sus= null;
		ArrayList<Suspect>suspects=new ArrayList<Suspect>();


				this.openConnection();

				try {
					stmt = con.prepareStatement(listSuspect);


					rs = stmt.executeQuery();

					while(rs.next()){  
						sus = new Suspect();
						sus.setCodSus(rs.getString("codSus"));
						sus.setName(rs.getString("name"));
						sus.setSurname(rs.getString("surname"));
						sus.setBirthDate(rs.getDate("birthDate"));				
						suspects.add(sus);
						
					}
				} catch (SQLException e) {
					System.out.println("Error de SQL");
					e.printStackTrace();
				} finally {
			
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException ex) {
							System.out.println("Error en cierre del ResultSet");
						}
					}
					try {
						this.closeConnection();
					} catch (SQLException e) {
						System.out.println("Error en el cierre de la BD");
						e.printStackTrace();
					}
				}

				return suspects;

	
	}

	@Override
	public Case caseAssigned(String wCodUser) {
		// TODO Auto-generated method stub
		

		ResultSet rs = null;
		Case cas= null;


				this.openConnection();

				try {
					stmt = con.prepareStatement(caseAssigned);

				
					stmt.setString(1, wCodUser);

					rs = stmt.executeQuery();

					if (rs.next()) {
						cas = new Case();
						cas.setCodCase(rs.getString(1));					
					} else
						cas = null;
				} catch (SQLException e) {
					System.out.println("Error de SQL");
					e.printStackTrace();
				} finally {
			
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException ex) {
							System.out.println("Error en cierre del ResultSet");
						}
					}
					try {
						this.closeConnection();
					} catch (SQLException e) {
						System.out.println("Error en el cierre de la BD");
						e.printStackTrace();
					}
				}

				return cas;
	}

	
	
	
}

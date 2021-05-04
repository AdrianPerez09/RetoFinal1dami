package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PoliceUserBDimplementation implements PoliceUserController {

	private Connection con;
	private PreparedStatement stmt;
	
	final String listCase ="SELECT * FROM OPENCASE";
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
						cas.setCodCase(wcodCase);
						cas.setDesc(rs.getString("descriptions"));
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
						sus.setCodSus(rs.getString(wcodSus));
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
	public Case obtainCase() {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Case cas= null;


				this.openConnection();

				try {
					stmt = con.prepareStatement(listCase);


					rs = stmt.executeQuery();

					if (rs.next()) {
						cas = new Case();
						cas.setCodCase("codCase");
						cas.setDesc(rs.getString("descriptions"));
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
	public Suspect obtainSuspect() {
		// TODO Auto-generated method stub
		
		
		ResultSet rs = null;
		Suspect sus= null;


				this.openConnection();

				try {
					stmt = con.prepareStatement(listSuspect);


					rs = stmt.executeQuery();

					if (rs.next()) {
						sus = new Suspect();
						sus.setCodSus(rs.getString("codSus"));
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

	
	
	
}

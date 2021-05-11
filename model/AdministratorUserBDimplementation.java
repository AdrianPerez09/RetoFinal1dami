package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class AdministratorUserBDimplementation implements AdministratorController {
	private Connection con;
	private PreparedStatement stmt;
	final String addUser = "INSERT INTO USERUP VALUES(?,?,?,?,?)";
	final String addPolice="INSERT INTO POLICE VALUES(?,?)";
	final String addManager="INSERT INTO MANAGER VALUES(?,?)";
	final String listUser ="SELECT * FROM USERUP where codUser=?";
	final String listUsers="SELECT * FROM USERUP";

	private void openConnection() {
		try {
			// String url = "jdbc:mysql://localhost/nombreBaseDatos";
			String url = "jdbc:mysql://localhost:3306/police_department?serverTimezone=Europe/Madrid&useSSL=false";
			// con = DriverManager.getConnection(url+"?" +"user=____&password=_____");
			con = DriverManager.getConnection(url, "root", "abcd*1234");

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
	public User queryUser(String wcodUser) {
		// TODO Auto-generated method stub
	// TODO Auto-generated method stub
		
		ResultSet rs = null;
		User usr= null;


				this.openConnection();

				try {
					stmt = con.prepareStatement(listUser);

				
					stmt.setString(1, wcodUser);

					rs = stmt.executeQuery();

					if (rs.next()) {
						usr = new User();
						usr.setCodUser(wcodUser);
						usr.setName(rs.getString("name"));
						usr.setSurname(rs.getString("surname"));
						usr.setBirthDate(rs.getDate("birthDate"));
					} else
						usr = null;
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

				return usr;

	}
	@Override
	public ArrayList<User> obtainUsers() {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		User usr= null;

		ArrayList<User>users=new ArrayList<>();
		

				this.openConnection();

				try {
					stmt = con.prepareStatement(listUsers);


					rs = stmt.executeQuery();

					while (rs.next()) {
						usr = new User();
						usr.setCodUser(rs.getString(1));
						usr.setName(rs.getString(2));
						usr.setSurname(rs.getString(3));
						usr.setBirthDate(rs.getDate(4));
						users.add(usr);
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

				return users;
	

	}


	@Override
	public void addUser(ArrayList<String> listado,boolean policeOrManager) {
		// TODO Auto-generated method stub
		System.out.println("Ha entrado");
		openConnection();
	
		try {
			
			stmt = con.prepareStatement(addUser);
			stmt.setString(1, listado.get(0));
			stmt.setString(2, listado.get(1));
			stmt.setString(3, listado.get(2));
			stmt.setString(4, listado.get(3));
			stmt.setString(5, listado.get(4));
			stmt.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error en alta SQL");
			e1.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en cierre de la BD");
				e.printStackTrace();
			}

			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
		openConnection();
		if(policeOrManager){
			stmt=con.prepareStatement(addManager);
			stmt.setString(1, listado.get(0));
			stmt.setString(2, listado.get(5));
			stmt.executeUpdate();
		}else{
			stmt=con.prepareStatement(addPolice);
			stmt.setString(1, listado.get(0));
			stmt.setString(2, listado.get(5));
			stmt.executeUpdate();
		}} catch (SQLException e1) {
			System.out.println("Error en alta SQL");
			e1.printStackTrace();
		}{
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en cierre de la BD");
				e.printStackTrace();
			}
		}
	}

	@Override
	public Map<String, User> listUser() {
		
		
		
		return null;
	}

	@Override
	public void modifyUser(Delinquent del) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Delinquent del) {
		// TODO Auto-generated method stub

	}

}

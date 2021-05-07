package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBDimplementation implements UserController {
	private Connection con;
	private PreparedStatement stmt;

	final String selectUser = ("SELECT * FROM USERUP WHERE CODUSER=? AND USERPASSWORD=?");

	final String selectAdmin = ("SELECT U.* FROM USERUP U, ADMIN A WHERE U.CODUSER=A.CODUSER AND U.CODUSER=? AND U.USERPASSWORD=?");
	final String selectManager = ("SELECT U.* FROM USERUP U, MANAGER M WHERE U.CODUSER=M.CODUSER AND U.CODUSER=? AND U.USERPASSWORD=?");
	final String selectPolice = ("SELECT U.* FROM USERUP U, POLICE P WHERE U.CODUSER=P.CODUSER AND U.CODUSER=? AND U.USERPASSWORD=?");
	final String getPoliceData = ("SELECT U.CODUSER,U.NAME,U.SURNAME,P.POLICENUMBER FROM USERUP U, POLICE P WHERE U.CODUSER=P.CODUSER AND U.CODUSER=? AND U.USERPASSWORD=?");
	final String getManagerData = ("SELECT U.CODUSER,U.SURNAME, M.OFFICENUMBER FROM USERUP U, MANAGER M WHERE U.CODUSER=P.CODUSER AND U.CODUSER=? AND U.USERPASSWORD=?");
	final String getAdminData = ("SELECT U.CODUSER,U.SURNAME FROM USERUP U, ADMIN A WHERE U.CODUSER=A.CODUSER AND U.CODUSER=? AND U.USERPASSWORD=?");
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
	public boolean loginAdmin(String wCodUser, String wPassword) {
		// TODO Auto-generated method stub

		boolean logeado = false;

		ResultSet rs = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(selectAdmin);

			stmt.setString(1, wCodUser);
			stmt.setString(2, wPassword);

			rs = stmt.executeQuery();

			if (rs.next()) {

				logeado = true;

			} else

				logeado = false;

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {

			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("Error en cierre del ResultSet");
			}

			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return logeado;

	}

	@Override
	public boolean loginManager(String wCodUser, String wPassword) {
		// TODO Auto-generated method stub

		boolean logeado = false;

		ResultSet rs = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(selectManager);

			stmt.setString(1, wCodUser);
			stmt.setString(2, wPassword);

			rs = stmt.executeQuery();

			if (rs.next()) {

				logeado = true;

			} else

				logeado = false;

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {

			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("Error en cierre del ResultSet");
			}

			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return logeado;

	}

	@Override
	public boolean loginPolice(String wCodUser, String wPassword) {
		// TODO Auto-generated method stub

		boolean logeado = false;

		ResultSet rs = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(selectPolice);

			stmt.setString(1, wCodUser);
			stmt.setString(2, wPassword);

			rs = stmt.executeQuery();

			if (rs.next()) {

				logeado = true;

			} else

				logeado = false;

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {

			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("Error en cierre del ResultSet");
			}

			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return logeado;

	}

	@Override
	public Police getPoliceData(String wCodUser, String wPassword) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Police aux=null;
	
		this.openConnection();

		try {
			stmt = con.prepareStatement(getPoliceData);

		
			stmt.setString(1, wCodUser);
			stmt.setString(2, wPassword);

			rs = stmt.executeQuery();

			if (rs.next()) {
				aux= new Police();
				aux.setCodUser(rs.getString(1));
				aux.setName(rs.getString(2));
				aux.setSurname(rs.getString(3));
				aux.setNumberPolice(rs.getInt(4));


				
			} else
				aux = null;
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

		return aux;

	}

	@Override
	public Administrator getAdminData(String wCodUser, String wPassword) {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Administrator aux=null;
	
		this.openConnection();

		try {
			stmt = con.prepareStatement(getAdminData);

		
			stmt.setString(1, wCodUser);
			stmt.setString(2, wPassword);

			rs = stmt.executeQuery();

			if (rs.next()) {
				aux= new Administrator();
				
				aux.setName(rs.getString(1));
				aux.setSurname(rs.getString(2));
			
			} else
				aux = null;
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

		return aux;

		
		
	}

	@Override
	public Manager getManagerData(String wCodUser, String wPassword) {
		// TODO Auto-generated method stub
	
		
		
		ResultSet rs = null;
		Manager aux=null;
	
		this.openConnection();

		try {
			stmt = con.prepareStatement(getManagerData);

		
			stmt.setString(1, wCodUser);
			stmt.setString(2, wPassword);

			rs = stmt.executeQuery();

			if (rs.next()) {
				aux= new Manager();
				
				aux.setName(rs.getString(1));
				aux.setSurname(rs.getString(2));
				aux.setOfficeNumber(rs.getInt(3));
			
			} else
				aux = null;
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

		return aux;

		
		
	}
}

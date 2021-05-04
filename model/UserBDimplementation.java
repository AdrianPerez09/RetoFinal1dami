package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserBDimplementation implements UserController {
	private Connection con;
	private PreparedStatement stmt;
	
	final String selectUser=("SELECT * FROM USERUP WHERE CODUSER=?");
	
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
	public boolean loginAdmin(String wCodUser) {
		// TODO Auto-generated method stub
	
		boolean logeado=false;
		
		
		
		
		return logeado;
		
	}
	@Override
	public boolean loginManager(String wCodUser) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean loginPolice(String wCodUser) {
		// TODO Auto-generated method stub
		return false;
	}
}

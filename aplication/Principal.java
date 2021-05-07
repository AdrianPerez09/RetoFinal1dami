package aplication;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;

import model.UserBDimplementation;
import model.UserController;
import view.PVMainWindowLogin;

public class Principal {

	public static void main(String[] args) throws FontFormatException, IOException {
		// TODO Auto-generated method stub

		UserController userData=new UserBDimplementation();
		
		PVMainWindowLogin pvMain=new PVMainWindowLogin(userData);
		pvMain.setVisible(true);
		
		
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - pvMain.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - pvMain.getHeight()) / 2);
		pvMain.setLocation(x, y);
	}

}

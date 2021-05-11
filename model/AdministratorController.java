package model;

import java.util.ArrayList;
import java.util.Map;

public interface AdministratorController {

	public User queryUser(String wcodUser);
	
	public void addUser(ArrayList<String> listado, boolean policeOrManager);
	
	public Map<String,User> listUser();
	
	public void modifyUser(Delinquent del);
	
	public void deleteUser(Delinquent del);

	ArrayList<User> obtainUsers();
	
}

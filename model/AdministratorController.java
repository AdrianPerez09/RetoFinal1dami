package model;

import java.util.Map;

public interface AdministratorController {

	public User queryUser(String wcodUser);
	
	public void addUser(User usr);
	
	public Map<String,User> listUser();
	
	public void modifyUser(Delinquent del);
	
	public void deleteUser(Delinquent del);
	
}

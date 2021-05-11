package model;

public interface UserController {

	public boolean loginAdmin(String wCodUser, String wPassword);
	public boolean loginManager(String wCodUser, String wPassword);
	public boolean loginPolice(String wCodUser, String wPassword);
	public Police getPoliceData(String wCodUser, String wPassword);
	public Administrator getAdminData(String wCodUser, String wPassword);
	public Manager getManagerData(String wCodUser, String wPassword);
	
	
}

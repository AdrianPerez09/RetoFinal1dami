package model;

import java.util.Map;
import java.util.Set;

public interface ManagerController {

	public Delinquent queryDelinquent(String wcodDel);
	
	public void addDelinquent(Delinquent del);
	
	public Map<String,Delinquent> listDelinquents();
	
	public void modifyDelinquent(Delinquent del);
	
	public void deleteDelinquent(Delinquent del);
	
	
	public Suspect querySuspect(String wcodSus);
	
	public void addSuspect(Suspect sus);
	
	public Map<String,Suspect>listSuspect();
	
	public void modifySuspect(Suspect sus);
	
	public void deleteSuspect(Suspect sus);
		
	
	public Case queryCase(String wcodCase);
	
	public void addCase(Case cas);	
	
	public Map<String,Case> listCase();
	
	public void modifyCase(Case cas);
	
	public void deleteCase(Case cas);
	
	
	
}

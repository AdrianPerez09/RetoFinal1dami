package model;

import java.util.ArrayList;

public interface PoliceUserController {	
public Case queryCase(String wcodCase);
public Suspect querySuspect(String wcodSus);
public ArrayList<Case> obtainCase();
public ArrayList<Suspect> obtainSuspect();
public Case caseAssigned(String wCodUser);
}

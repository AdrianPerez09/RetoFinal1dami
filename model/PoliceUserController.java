package model;

public interface PoliceUserController {	
public Case queryCase(String wcodCase);
public Suspect querySuspect(String wcodSus);
public Case obtainCase();
public Suspect obtainSuspect();
}

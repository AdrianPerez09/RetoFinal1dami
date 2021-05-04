package model;

import java.sql.Date;
import java.time.LocalDate;

public class Suspect {
private String codSus;
private Date birthDate;
private String name;
private String surname;
public String getCodSus() {
	return codSus;
}
public void setCodSus(String codSus) {
	this.codSus = codSus;
}
public Date getBirthDate() {
	return birthDate;
}
public void setBirthDate(Date date) {
	this.birthDate = date;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public Suspect() {
	super();
}

}

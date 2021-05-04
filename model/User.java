package model;

import java.time.LocalDate;

public class User {
private String codUser;
private String name;
private String surname;
private LocalDate birthDate;
public String getCodUser() {
	return codUser;
}
public void setCodUser(String codUser) {
	this.codUser = codUser;
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
public LocalDate getBirthDate() {
	return birthDate;
}
public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
}
public User() {
	super();
}

}

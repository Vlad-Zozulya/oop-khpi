package ua.khpi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
    private String surname;
    private Date birthday;
    private ArrayList<String> mobileNumbers = new ArrayList<String>();
    private String address;
    private Date editingTime = new Date();
    public Account() {
    	
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
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public ArrayList<String> getMobileNumbers() {
        return mobileNumbers;
    }
    public void setMobileNumbers(ArrayList<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getEditingTime() {
        return editingTime;
    }
    public String toString() {
    	String result = "";
		result += String.format("%30s%s %s\n", "NAME:  ", name, surname);
		result += String.format("%30s%s.%s.%s\n", "BIRTHDAY:  ", birthday.getDate(), birthday.getMonth(), (birthday.getYear()+1900));
		result += String.format("%30s%s\n", "MOBILE NUMBER(S):  ", mobileNumbers);
		result += String.format("%30s%s\n", "ADDRESS:  ", address);		
		result += String.format("%30s%s\n", "EDITING TIME:  ", editingTime);
		return result;
    }
}

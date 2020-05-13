package test007;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
    private String surname;
    private Date birthday;
    private String birthday_string;
    private ArrayList<String> mobileNumbers = new ArrayList<String>();
    private String mobileNumbers_string = "";
    private String address;
    private Date editingTime = new Date();
    private String editingTime_string;
    private int id;
    
	public void setBirthday_string(String birthday_string) {
		this.birthday_string = birthday_string;
	}
	public void setMobileNumbers_string(String mobileNumbers_string) {
		this.mobileNumbers_string = mobileNumbers_string;
	}
	public void setEditingTime(Date editingTime) {
		this.editingTime = editingTime;
	}
	public void setEditingTime_string(String editingTime_string) {
		this.editingTime_string = editingTime_string;
	}
	
	{
    	editingTime_string = String.format("%s.%s.%s\n", (editingTime.getDate() < 10)?("0"+editingTime.getDate()):(editingTime.getDate() + ""),
    			((editingTime.getMonth()+1) < 10)?("0" + (editingTime.getMonth()+1)):((editingTime.getMonth()+1)+""), 
    			(editingTime.getYear()+1900));
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public String getBirthday_string() {
        return birthday_string;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    	this.birthday_string = String.format("%s.%s.%s\n", (birthday.getDate() < 10)?("0"+birthday.getDate()):(birthday.getDate() + ""), 
    			(birthday.getMonth() < 10)?("0"+birthday.getMonth()):(birthday.getMonth() + ""), 
    			(birthday.getYear()+1900));
    }
    public ArrayList<String> getMobileNumbers() {
        return mobileNumbers;
    }
    public String getMobileNumbers_string() {
        return this.mobileNumbers_string;
    }
    public void setMobileNumbers(ArrayList<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    	for(String o : this.mobileNumbers) {
    		this.mobileNumbers_string += o +"\n";
    	}
    	this.mobileNumbers_string += "\n";
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEditingTime_string() {
        return editingTime_string;
    }
    
    public Date getEditingTime() {
        return editingTime;
    }
    public boolean equals(Object object) {
    	if(!super.equals(object)) return false;
        if (this == object) return true;
        if (object == null) return false;
        if(this.getClass() != object.getClass()) return false;
        Account otherObj = (Account)object;
        return this.id == otherObj.id;
    }
    public int hashCode() {   
        return 76+133*id;
    }
}

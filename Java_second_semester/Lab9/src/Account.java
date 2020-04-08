package ua.khpi;

import java.util.ArrayList;
import java.util.Date;

public class Account{
    private String FullName;
    private String birthday;
    private ArrayList<String> mobileNumbers = new ArrayList<String>();
    private String address;
    private Date editingTime = new Date();

    public String getFullName() {
        return FullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public ArrayList<String> getMobileNumbers() {
        return mobileNumbers;
    }

    public String getAddress() {
        return address;
    }

    public Date getEditingTime() {
        return editingTime;
    }
    
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setMobileNumbers(ArrayList<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

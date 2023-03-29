/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Contact {
    private int AccountID;
    private String FullName;
    private boolean Gender;
    private Date Dob;
    private String Phone;
    private String Address;

    public Contact() {
    }

    public Contact(int AccountID, String FullName, boolean Gender, Date Dob, String Phone, String Address) {
        this.AccountID = AccountID;
        this.FullName = FullName;
        this.Gender = Gender;
        this.Dob = Dob;
        this.Phone = Phone;
        this.Address = Address;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date Dob) {
        this.Dob = Dob;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
}

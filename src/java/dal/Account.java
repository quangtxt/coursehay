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
public class Account {

    private int AccountID;
    private String Email;
    private String Password;
    private boolean status;
    private Date RegisterDate;
    private int RankId;
    private int RoleId;

    public Account() {
    }

    public Account(int AccountID, String Email, String Password, boolean status, Date RegisterDate, int RankId, int RoleId) {
        this.AccountID = AccountID;
        this.Email = Email;
        this.Password = Password;
        this.status = status;
        this.RegisterDate = RegisterDate;
        this.RankId = RankId;
        this.RoleId = RoleId;
    }

    public Account(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(Date RegisterDate) {
        this.RegisterDate = RegisterDate;
    }

    public int getRankId() {
        return RankId;
    }

    public void setRankId(int RankId) {
        this.RankId = RankId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author msi
 */
public class Order {

    private int AccountId;
    private int CourseId;
    private Date PurchaseDate;
    private String Code;
    private Date GiftDate;
    private int GiftAccId;
    private String MessByGifter;
    private boolean Status;
    
    public Order() {
    }

    public Order(int AccountId, int CourseId, Date PurchaseDate, String Code, Date GiftDate, int GiftAccId, String MessByGifter) {
        this.AccountId = AccountId;
        this.CourseId = CourseId;
        this.PurchaseDate = PurchaseDate;
        this.Code = Code;
        this.GiftDate = GiftDate;
        this.GiftAccId = GiftAccId;
        this.MessByGifter = MessByGifter;
    }

    public Order(int AccountId, Date PurchaseDate, String Code, Date GiftDate, int GiftAccId, String MessByGifter,boolean Status) {
        this.AccountId = AccountId;
        this.PurchaseDate = PurchaseDate;
        this.Code = Code;
        this.GiftDate = GiftDate;
        this.GiftAccId = GiftAccId;
        this.MessByGifter = MessByGifter;
        this.Status = Status;
    }

    public Order(int AccountId, int CourseId, Date PurchaseDate, String Code) {
        this.AccountId = AccountId;
        this.CourseId = CourseId;
        this.PurchaseDate = PurchaseDate;
        this.Code = Code;
    }
    public Order(int AccountId, int CourseId, String Code,Date GiftDate,int GiftAccId,String MessByGifter) {
        this.AccountId = AccountId;
        this.CourseId = CourseId;
        this.Code = Code;
        this.GiftDate = GiftDate;
        this.GiftAccId = GiftAccId;
        this.MessByGifter = MessByGifter;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date PurchaseDate) {
        this.PurchaseDate = PurchaseDate;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public Date getGiftDate() {
        return GiftDate;
    }

    public void setGiftDate(Date GiftDate) {
        this.GiftDate = GiftDate;
    }

    public int getGiftAccId() {
        return GiftAccId;
    }

    public void setGiftAccId(int GiftAccId) {
        this.GiftAccId = GiftAccId;
    }

    public String getMessByGifter() {
        return MessByGifter;
    }

    public void setMessByGifter(String MessByGifter) {
        this.MessByGifter = MessByGifter;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

}

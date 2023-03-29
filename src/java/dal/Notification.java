/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Notification {
    private int NotificationId;
    private int AccountId;
    private String NotificationContent;
    private int SendTo;
    private int Status;
    private Date StatusTime;
    private int CourseId;

    public Notification() {
    }

    public Notification(int NotificationId, int AccountId, String NotificationContent, int SendTo, int Status, Date StatusTime, int CourseId) {
        this.NotificationId = NotificationId;
        this.AccountId = AccountId;
        this.NotificationContent = NotificationContent;
        this.SendTo = SendTo;
        this.Status = Status;
        this.StatusTime = StatusTime;
        this.CourseId = CourseId;
    }
    
    public Notification( int AccountId, String NotificationContent, int SendTo, int Status, Date StatusTime, int CourseId) {
        this.AccountId = AccountId;
        this.NotificationContent = NotificationContent;
        this.SendTo = SendTo;
        this.Status = Status;
        this.StatusTime = StatusTime;
        this.CourseId = CourseId;
    }
    public Notification( int AccountId, String NotificationContent, int SendTo, int Status, int CourseId) {
        this.AccountId = AccountId;
        this.NotificationContent = NotificationContent;
        this.SendTo = SendTo;
        this.Status = Status;
        
        this.CourseId = CourseId;
    }

    public int getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(int NotificationId) {
        this.NotificationId = NotificationId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public String getNotificationContent() {
        return NotificationContent;
    }

    public void setNotificationContent(String NotificationContent) {
        this.NotificationContent = NotificationContent;
    }

    public int getSendTo() {
        return SendTo;
    }

    public void setSendTo(int SendTo) {
        this.SendTo = SendTo;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Date getStatusTime() {
        return StatusTime;
    }

    public void setStatusTime(Date StatusTime) {
        this.StatusTime = StatusTime;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    
    
}

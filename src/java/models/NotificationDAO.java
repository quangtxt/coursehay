/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Notification;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NotificationDAO extends DBContext {

    public void createNotification(Notification noti, int status) {
        try {
            String sql = "insert into notification(AccountId,NotifyContent,SendTo,Status,StatuTime,CourseId) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, noti.getAccountId());
            if ("".equals(noti.getNotificationContent()) && status == 0) {
                ps.setString(2, "Your course has been rejected by moderation team");
            } else if ("".equals(noti.getNotificationContent()) && status == 2) {
                ps.setString(2, "Your course has been approved by the moderation team");
            } else {
                ps.setString(2, noti.getNotificationContent());
            }
            ps.setInt(3, noti.getSendTo());
            ps.setInt(4, noti.getStatus());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setInt(6, noti.getCourseId());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<Notification> getListNotiByAccountId(int accountid, int sendto) {
        ArrayList<Notification> notification = new ArrayList<>();
        try {
            String sql = "select NotifyId,AccountId,NotifyContent,SendTo,Status,StatuTime,CourseId from notification where AccountId =? and Sendto = ? order by StatuTime desc;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, sendto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int NotificationId = rs.getInt("NotifyId");
                int AccountId = rs.getInt("AccountId");
                String NotifyContent = rs.getString("NotifyContent");
                int SendTo = rs.getInt("SendTo");
                int Status = rs.getInt("Status");
                Date StatuTime = rs.getDate("StatuTime");
                int CourseId = rs.getInt("CourseId");
                notification.add(new Notification(NotificationId, AccountId, NotifyContent, SendTo, Status, StatuTime, CourseId));
            }
        } catch (SQLException e) {
        }
        return notification;
    }
    public void HasSeenNoti(int NotifyId){
        
        try {
            String sql = "update notification set Status = ?  where NotifyId = ? ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, NotifyId);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        
    }
    public int CountNotiNotSeen(int accountid) {
        int count =0;
        try {
            String sql = "select count(NotifyId) from notification where AccountId =? and Status = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               count = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return count;
    }
    public int CountNotiNotSeenBySendto(int accountid, int sendto) {
        int count =0;
        try {
            String sql = "select count(NotifyId) from notification where AccountId =? and Status = ? and Sendto = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, 0);
            ps.setInt(3, sendto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               count = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return count;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Order;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author msi
 */
public class OrderDAO extends DBContext {

    public Order CourseBuyByAcc(int accId, int courseId) {
        try {
            String sql = "select AccountId, PurchaseDate, Code, GiftDate, GiftAccId, MessByGifter,Status from swp_dtb.order where AccountId = ? and CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                Date PurchaseDate = rs.getDate("PurchaseDate");
                String Code = rs.getString("Code");
                Date GiftDate = rs.getDate("GiftDate");
                int GiftAccId = rs.getInt("GiftAccId");
                String MessByGifter = rs.getString("MessByGifter");
                boolean status = rs.getBoolean("Status");
                return new Order(AccountId, PurchaseDate, Code, GiftDate, GiftAccId, MessByGifter, status);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Order> getOrderbyCourseID(int id) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "select AccountId, PurchaseDate, Code, GiftDate, GiftAccId, MessByGifter,Status from order where CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                Date PurchaseDate = rs.getDate("PurchaseDate");
                String Code = rs.getString("Code");
                Date GiftDate = rs.getDate("GiftDate");
                int GiftAccId = rs.getInt("GiftAccId");
                String MessByGifter = rs.getString("MessByGifter");
                boolean status = rs.getBoolean("Status");
                Order uc = new Order(AccountId, PurchaseDate, Code, GiftDate, GiftAccId, MessByGifter, status);
                list.add(uc);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public String randomString(int n) {
        Random rand = new Random();
        String str = rand.ints(48, 123)
                .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
                .limit(n).toString();
        str = str.substring(str.length() - n);
        if (checkCodeExist(str)) {
            randomString(n);
        }
        return str;
    }

    public boolean checkCodeExist(String code) {
        String sql = "SELECT accountid FROM swp_dtb.order where Code = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkCodeAccount(int accId, String code) {
        String sql = "SELECT accountid FROM swp_dtb.order where accountid =? and Code = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accId);
            st.setString(2, code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public int addOrderBuy(Order ord) {
        int result = 0;
        String sql = "INSERT INTO swp_dtb.order (AccountId,CourseId,PurchaseDate,Code) VALUES (?,?,?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ord.getAccountId());
            st.setInt(2, ord.getCourseId());
            st.setDate(3, ord.getPurchaseDate());
            st.setString(4, ord.getCode());
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public int addOrderGift(Order ord) {
        int result = 0;
        String sql = "INSERT INTO swp_dtb.order (AccountId,CourseId,Code, GiftDate, GiftAccId, MessByGifter) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ord.getAccountId());
            st.setInt(2, ord.getCourseId());
            st.setString(3, ord.getCode());
            st.setDate(4, ord.getGiftDate());
            st.setInt(5, ord.getGiftAccId());
            st.setString(6, ord.getMessByGifter());
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public int updateStatus(boolean status, int accId, int couId) {
        int result = 0;
        String sql = "UPDATE `swp_dtb`.`order` SET `Status` = ? WHERE (`AccountId` = ?) and (`CourseId` = ?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
            st.setInt(2, accId);
            st.setInt(3, couId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public boolean checkCourseExit(int accountID, int courseId) {
        String sql = "SELECT accountid FROM swp_dtb.order where accountid =? and courseid = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.setInt(2, courseId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

}

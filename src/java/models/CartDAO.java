/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Course;
import dal.DBContext;
import dal.Wishlist;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class CartDAO extends DBContext{
    public ArrayList<Integer> getCourseIDInCartByAccountId(int accid){
        ArrayList<Integer> listCourseID = new ArrayList<>();
        try {
            String sql = "select courseid from cart where AccountId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                listCourseID.add(CourseId);
              }
            } catch (SQLException e) {
        }
        return listCourseID;
    }
    public int addToCart(int accId, int course) {
        int result = 0;
        String sql = "INSERT INTO  cart (AccountId, CourseId) VALUES (?, ?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accId);
            st.setInt(2, course);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    public int AmountCourseOfAcc(int accId) {
        int amount = 0;
        String sql = "SELECT count(courseid) FROM cart where accountID = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accId);
             ResultSet  rs = st.executeQuery();
            while (rs.next()) {                
                amount = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return amount;
    }
    
    public int RemoveCoureInCart(int accId, int course) {
        int result = 0;
        String sql = "Delete FROM cart Where AccountId = ? and CourseId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accId);
            st.setInt(2, course);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public boolean checkExistInCart(int accid, int course){
        String sql = "Select AccountId from cart Where `AccountId` = ? and `CourseId` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accid);
            st.setInt(2, course);
            ResultSet  rs = st.executeQuery();
            while (rs.next()) {                
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}

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
public class WishlistDAO extends DBContext{
    public ArrayList<Course> getAllCourseInWishlistByAccountId(int accid){
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "select * from courses as c , wishlist as w where c.CourseId = w.CourseId and w.AccountId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                int AuthorId = rs.getInt("AuthorId");
                int TopicId = rs.getInt("TopicId");
                String CourseName = rs.getString("CourseName");
                String CourseTitle = rs.getString("CourseTitle");
                String CourseDescription = rs.getString("CourseDescription");
                int PublicStatus = rs.getInt("PublicStatus");
                Date PublicDate = rs.getDate("PublicDate");
                byte[] Img = rs.getBytes("Img");
                double Rate = rs.getDouble("Rate");
                int Price = rs.getInt("Price");
                courses.add(new Course(CourseId,AuthorId, TopicId, CourseName, CourseTitle, CourseDescription, PublicStatus, PublicDate, Img, Rate, Price));
            }
            } catch (SQLException e) {
        }
        return courses;
    }
    public int addToWishlist(int accId, int course) {
        int result = 0;
        String sql = "INSERT INTO  wishlist (AccountId, CourseId) VALUES (?, ?);";
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
    
    public int RemoveCoureInWishlist(int accId, int course) {
        int result = 0;
        String sql = "Delete FROM swp_dtb.wishlist Where AccountId = ? and CourseId = ?;";
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
    
    public int checkExistInWishlist(int accid, int course){
        int result = 0;
        ArrayList<Wishlist> list = new  ArrayList<>();
        
        String sql = "Select * from wishlist Where `AccountId` = ? and `CourseId` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accid);
            st.setInt(2, course);
            ResultSet  rs = st.executeQuery();
            while (rs.next()) {                
                int AccountId = rs.getInt("AccountId");
                int CourseId = rs.getInt("CourseId");
                list.add(new Wishlist(AccountId, CourseId));
                result = list.size();   
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}

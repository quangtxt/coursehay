/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.User_Author;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class User_AuthorDAO extends DBContext {

    public void followAuthor(User_Author ua) {
        String sql = "insert into user_author(UserId, AuthorId) values(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ua.getUserId());
            ps.setInt(2, ua.getAuthorId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public User_Author getUserAuthorByID(int userId, int auId) {
        User_Author ua = null;
        try {
            String sql = "SELECT UserId, AuthorId FROM swp_dtb.user_author WHERE UserId = ? and AuthorId = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, auId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int UserId = rs.getInt("UserId");
                int AuthorId = rs.getInt("AuthorId");
                ua = new User_Author(UserId, AuthorId);
            }
        } catch (Exception e) {
        }
        return ua;
    }
    
    public void unfollowAuthor(User_Author ua) {
        String sql = "DELETE FROM swp_dtb.user_author WHERE UserId = ? and AuthorId = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ua.getUserId());
            ps.setInt(2, ua.getAuthorId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new User_AuthorDAO().getUserAuthorByID(5, 11));
    }

    public ArrayList<Integer> getAuIdByUserId(int userId) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT AuthorId FROM swp_dtb.user_author WHERE UserId = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("AuthorId"));
            }
        } catch (SQLException e) {
        }
        return list;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.AuthorCourse;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class AuthorCourseDAO extends DBContext{
   public AuthorCourse getAccountIDByCoursetID(int accID, int courseID) {
        AuthorCourse ac = null;
        try {
            String sql = "select AccountId, CourseId from author_course where AccountId = ? and CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accID);
            ps.setInt(2, courseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                int CourseId = rs.getInt("CourseId");
                ac = new AuthorCourse(AccountId, CourseId);
                return ac;
            }
        } catch (Exception e) {
        }
        return null;
    }

    
}

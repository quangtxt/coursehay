/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.CourseContent;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class CourseContentDAO extends DBContext {

    public ArrayList<CourseContent> getAllCourseContentsByCourseID(int id) {
        ArrayList<CourseContent> contentList = new ArrayList<>();
        try {
            String sql = "SELECT ContentId,ContentName,ContentDescription FROM course_content where CourseId=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ContentId = rs.getInt("ContentId");
                String ContentName = rs.getString("ContentName");
                String ContentDescription = rs.getString("ContentDescription");
                contentList.add(new CourseContent(ContentId, ContentName, ContentDescription));
            }
        } catch (SQLException e) {
        }
        return contentList;
    }

    public int createContent(CourseContent content, int id) {
        int result = 0;
        try {
            String sql = "insert into course_content(ContentId,CourseId,ContentName,ContentDescription) values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, content.getContentId());
            ps.setInt(2, id);
            ps.setString(3, content.getContentName());
            ps.setString(4, content.getContentDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public int getLastContentId() {
        int id = 0;
        try {
            String sql = "select ContentId from course_content order by ContentId desc limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ContentId");
            }
        } catch (SQLException e) {
        }
        return id;
    }

    public int deleteContent(int courseid, int contentid) {
        int result = 0;
        try {
            String sql1 = "delete from lecture where ContentId = ?";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, contentid);
            ps1.executeUpdate();
            String sql2 = "delete from course_content where CourseId = ? and ContentId = ?";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setInt(1, courseid);
            ps2.setInt(2, contentid);
            ps2.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public int updateContent(CourseContent content, int contentid) {
        int result = 0;
        try {
            String sql = "update course_content set ContentName = ?, ContentDescription = ? where ContentId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, content.getContentName());
            ps.setString(2, content.getContentDescription());
            ps.setInt(3, contentid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public ArrayList<CourseContent> getListContentbyCourseId(int courseId) {
        ArrayList<CourseContent> contentlist = new ArrayList<>();
        try {
            String sql = "select ContentId,CourseId,ContentName,ContentDescription from course_content where courseid = ?;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ContentId = rs.getInt("ContentId");
                int CourseId = rs.getInt("CourseId");
                String ContentName = rs.getString("ContentName");
                String ContentDescription = rs.getString("ContentDescription");
                contentlist.add(new CourseContent(ContentId, CourseId, ContentName, ContentDescription));
            }
        } catch (SQLException e) {
        }
        return contentlist;
    }

}

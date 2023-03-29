/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Lecture;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class LectureDAO extends DBContext {

    public int createLecture(Lecture lecture) {
        int result = 0;
        try {
            String sql = "insert into lecture(LectureId,ContentId,LectureName,LectureType) values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, lecture.getLectureId());
            ps.setInt(2, new CourseContentDAO().getLastContentId());
            ps.setString(3, lecture.getLectureName());
            ps.setString(4, lecture.getLectureType());
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        if (result > 0) {
            return 1;
        }
        return 0;
    }

    public ArrayList<Lecture> getAllCourseContentsByContentID(int id) {
        ArrayList<Lecture> lectureList = new ArrayList<>();
        try {
            String sql = "SELECT LectureId,LectureName,LectureType FROM lecture where ContentId=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int LectureId = rs.getInt("LectureId");
                String LectureName = rs.getString("LectureName");
                String LectureType = rs.getString("LectureType");
                lectureList.add(new Lecture(LectureId, LectureName, LectureType));
            }
        } catch (SQLException e) {
        }
        return lectureList;
    }

    public static void main(String[] args) {
        ArrayList<Lecture> list = new LectureDAO().getAllCourseContentsByContentID(19);
        for (Lecture lecture : list) {
            System.out.println(lecture.getLectureType());
        }
    }

    public int deleteLecture(int lectureid) {
        int result = 0;
        try {
            String sql = "delete from lecture where LectureId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, lectureid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public ArrayList<Lecture> getListLeturebyContentId(int contentId) {
        ArrayList<Lecture> lecturelist = new ArrayList<>();
        try {
            String sql = "select LectureId,ContentId,LectureName,LectureType from lecture where contentid = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int LectureId = rs.getInt("LectureId");
                int ContentId = rs.getInt("ContentId");
                String LectureName = rs.getString("LectureName");
                String LectureType = rs.getString("LectureType");
                lecturelist.add(new Lecture(LectureId, ContentId, LectureName, LectureType));
            }
        } catch (SQLException e) {
        }
        return lecturelist;
    }

}

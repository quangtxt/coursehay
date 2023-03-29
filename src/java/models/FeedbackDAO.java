/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Feedback;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class FeedbackDAO extends DBContext {

    public ArrayList<Feedback> getFeedbackbyCourseId(int id) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            String sql = "select FeedbackId,CourseId,AccountId, Star, Comment, DateFeedback from feedback where CourseId = ? order by DateFeedback desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int CourseId = rs.getInt("CourseId");
                int AccountId = rs.getInt("AccountId");
                double Star = rs.getDouble("Star");
                String Comment = rs.getString("Comment");
                Date DateFeedback = rs.getDate("DateFeedback");
                Feedback f = new Feedback(FeedbackId, CourseId, AccountId, Star, Comment, DateFeedback);
                list.add(f);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addFeedback(Feedback f) {
        String sql = "insert into feedback (CourseId,AccountId,Star,Comment,DateFeedback) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, f.getCourseId());
            ps.setInt(2, f.getAccountId());
            ps.setDouble(3, f.getStar());
            ps.setString(4, f.getComment());
            ps.setDate(5, f.getDateFeedback());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Feedback getFeebackbyID(int couId, int accId) {
        Feedback f = null;
        try {
            String sql = "select FeedbackId, CourseId, AccountId, Star, Comment, DateFeedback from feedback where CourseId = ? and AccountId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, couId);
            ps.setInt(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int CourseId = rs.getInt("CourseId");
                int AccountId = rs.getInt("AccountId");
                double Star = rs.getDouble("Star");
                String Comment = rs.getString("Comment");
                Date DateFeedback = rs.getDate("DateFeedback");
                f = new Feedback(FeedbackId, CourseId, AccountId, Star, Comment, DateFeedback);
            }
        } catch (Exception e) {
        }
        return f;
    }

    public double SumStarbyCourseId(int id) {
        double sumStar = 0;
        try {
            String sql = "select sum(Star) from feedback where CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sumStar = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return sumStar;
    }

    public double CountStarbyCourseId(int id) {
        double count = 0;
        try {
            String sql = "select count(Star) from feedback where CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public ArrayList<Feedback> getFeedbackbyCourseIdandStar(int id, double star) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            String sql = "select FeedbackId,CourseId,AccountId, Star, Comment, DateFeedback from feedback where CourseId = ? and Star = ? order by DateFeedback desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setDouble(2, star);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int CourseId = rs.getInt("CourseId");
                int AccountId = rs.getInt("AccountId");
                double Star = rs.getDouble("Star");
                String Comment = rs.getString("Comment");
                Date DateFeedback = rs.getDate("DateFeedback");
                Feedback f = new Feedback(FeedbackId, CourseId, AccountId, Star, Comment, DateFeedback);
                list.add(f);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Feedback> getFeedbackbyCourseIdandAccountId(int couId, int accId) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            String sql = "select FeedbackId,CourseId,AccountId, Star, Comment, DateFeedback from feedback where CourseId = ? and AccountId != ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, couId);
            ps.setInt(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int CourseId = rs.getInt("CourseId");
                int AccountId = rs.getInt("AccountId");
                double Star = rs.getDouble("Star");
                String Comment = rs.getString("Comment");
                Date DateFeedback = rs.getDate("DateFeedback");
                Feedback f = new Feedback(FeedbackId, CourseId, AccountId, Star, Comment, DateFeedback);
                list.add(f);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Feedback> getFeedbackbyCourseIdandAccountIdandStar(int couId, int accId, double star) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            String sql = "select FeedbackId,CourseId,AccountId, Star, Comment, DateFeedback from feedback where CourseId = ? and AccountId != ? and Star = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, couId);
            ps.setInt(2, accId);
            ps.setDouble(3, star);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int CourseId = rs.getInt("CourseId");
                int AccountId = rs.getInt("AccountId");
                double Star = rs.getDouble("Star");
                String Comment = rs.getString("Comment");
                Date DateFeedback = rs.getDate("DateFeedback");
                Feedback f = new Feedback(FeedbackId, CourseId, AccountId, Star, Comment, DateFeedback);
                list.add(f);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updateFeedback(Feedback f) {
        String sql = "update feedback set  CourseId = ?, AccountId = ?, Star = ?, Comment = ?, DateFeedback = ? where FeedbackId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, f.getCourseId());
            ps.setInt(2, f.getAccountId());
            ps.setDouble(3, f.getStar());
            ps.setString(4, f.getComment());
            ps.setDate(5, f.getDateFeedback());
            ps.setInt(6, f.getFeedbackId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int deleteFeedback(int feedbackId) {
        int result = 0;
        try {
            String sql1 = "delete from reply where FeedbackId = ?";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, feedbackId);
            ps1.executeUpdate();
            String sql2 = "delete from feedback where FeedbackId = ?";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setInt(1, feedbackId);
            ps2.executeUpdate();
        } catch (Exception e) {
        }
        return result;
    }
     public int CountFeedBackbyCourseId(int id) {
        int count = 0;
        try {
            String sql = "select count(FeedbackId) from feedback where CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

}

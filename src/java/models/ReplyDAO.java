/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Reply;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class ReplyDAO extends DBContext {

    public ArrayList<Reply> getReplybyCourseId(int id) {
        ArrayList<Reply> list = new ArrayList<>();
        try {
            String sql = "select FeedbackId, AccountId, Reply, DateReply from reply where FeedbackId in (select FeedbackId from feedback where CourseId = ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int AccountId = rs.getInt("AccountId");
                String Reply = rs.getString("Reply");
                Date DateReply = rs.getDate("DateReply");
                Reply r = new Reply(FeedbackId, AccountId, Reply, DateReply);
                list.add(r);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Reply getReplybyId(int feedId, int couId, int accId) {
        Reply r = null;
        try {
            String sql = "select FeedbackId, AccountId, Reply, DateReply from reply where FeedbackId = ? and AccountId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedId);
            ps.setInt(2, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int AccountId = rs.getInt("AccountId");
                String Reply = rs.getString("Reply");
                Date DateReply = rs.getDate("DateReply");
                r = new Reply(FeedbackId, AccountId, Reply, DateReply);
                return r;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Reply getReplybyFeedbackId(int feedId) {
        Reply r = null;
        try {
            String sql = "select FeedbackId, AccountId, Reply, DateReply from reply where FeedbackId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int FeedbackId = rs.getInt("FeedbackId");
                int AccountId = rs.getInt("AccountId");
                String Reply = rs.getString("Reply");
                Date DateReply = rs.getDate("DateReply");
                r = new Reply(FeedbackId, AccountId, Reply, DateReply);
                return r;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void addReply(Reply r) {
        String sql = "insert into reply (FeedbackId, AccountId, Reply, DateReply) values(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, r.getFeedbackId());
            ps.setDouble(2, r.getAccountId());
            ps.setString(3, r.getReply());
            ps.setDate(4, r.getDateReply());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
     public void updateRely(Reply r) {
        String sql = "update reply set Reply = ?, DateReply = ? where FeedbackId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, r.getReply());
            ps.setDate(2, r.getDateReply());
            ps.setInt(3, r.getFeedbackId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
 
    public void deleteReply(int feedbackId) {
        String sql = "delete from reply where FeedbackId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}

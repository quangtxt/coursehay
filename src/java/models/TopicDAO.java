/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Topic;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class TopicDAO extends DBContext {

    public ArrayList<Topic> getAllTopics() {
        ArrayList<Topic> cateList = new ArrayList<>();
        try {
            String sql = "SELECT TopicId, TopicName FROM topic;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int TopicId = rs.getInt("TopicId");
                String TopicName = rs.getString("TopicName");
                cateList.add(new Topic(TopicId, TopicName));
            }
        } catch (SQLException e) {
        }
        return cateList;
    }

    public Topic getTopicById(int Id) {
        try {
            String sql = "SELECT TopicId, TopicName FROM topic where topicId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int TopicId = rs.getInt("TopicId");
                String TopicName = rs.getString("TopicName");
                return new Topic(TopicId, TopicName);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Topic> getAllTopicsByCateId(int id) {
        ArrayList<Topic> topList = new ArrayList<>();
        try {
            String sql = "select t.TopicId, t.TopicName from topic t, categories c, cate_topic ct where c.CateId = ct.CateId and ct.TopicId = t.TopicId and c.CateId = ? order by TopicId";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int TopicId = rs.getInt("TopicId");
                String TopicName = rs.getString("TopicName");
                topList.add(new Topic(TopicId, TopicName));
            }
        } catch (SQLException e) {
        }
        return topList;
    }

    public int countTopic() {
        try {
            String sql = "select count(TopicId) from topic;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Category;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> cateList = new ArrayList<>();
        try {
            String sql = "SELECT CateId, CateName FROM categories;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CateId = rs.getInt("CateId");
                String CateName = rs.getString("CateName");
                cateList.add(new Category(CateId, CateName));
            }
        } catch (SQLException e) {
        }
        return cateList;
    }

    public Category getCategoryById(int Id) {
        try {
            String sql = "SELECT CateId, CateName FROM categories where CateId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CateId = rs.getInt("CateId");
                String CateName = rs.getString("CateName");
                return new Category(CateId, CateName);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Category getCategoryByTopicid(int topicid) {
        Category c = null;
        try {
            String sql = "SELECT CateName,CateId FROM categories where cateid in (SELECT CateId FROM cate_topic where topicid = ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, topicid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CateId = rs.getInt("CateId");
                String CateName = rs.getString("CateName");
                c = new Category(CateId, CateName);
            }
        } catch (SQLException e) {
        }
        return c;
    }
}

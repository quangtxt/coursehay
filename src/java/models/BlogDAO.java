/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import dal.Blog;
import dal.BlogPart;
import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Acer
 */
public class BlogDAO extends DBContext {   
    public ArrayList getBlogBycateID(String sql) {
        ArrayList<Blog> aar = new ArrayList<>();
        try {       
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int BlogID = rs.getInt("BlogId");
                String BlogTitle = rs.getString("BlogTitle");
                String BlogDesciption = rs.getString("BlogDescription");
                byte[] Image = rs.getBytes("Image");
                int CategoryID = rs.getInt("CategoryId");
                Date date = rs.getDate("DateCreated");
               int blogstatus = rs.getInt("BlogStatus");
                int Accountid = rs.getInt("AccountId");
                aar.add(new Blog(BlogID, BlogTitle, BlogDesciption, Image, CategoryID, date, blogstatus,Accountid));             
            }
        } catch (Exception e) {
        }
        return aar;
    }
    public ArrayList getTop3BlogBycateID(int id) {
        ArrayList<Blog> aar = new ArrayList<>();
        try {
            String sql = "SELECT * FROM swp_dtb.blog where categoryid = ? and BlogStatus = 3 limit 3 ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int BlogID = rs.getInt("BlogId");
                String BlogTitle = rs.getString("BlogTitle");
                String BlogDesciption = rs.getString("BlogDescription");
                byte[] Image = rs.getBytes("Image");
                int CategoryID = rs.getInt("CategoryId");
                Date date = rs.getDate("DateCreated");
               int blogstatus = rs.getInt("BlogStatus");
                int Accountid = rs.getInt("AccountId");
                aar.add(new Blog(BlogID, BlogTitle, BlogDesciption, Image, CategoryID, date, blogstatus,Accountid));           
            }
        } catch (Exception e) {
        }
        return aar;
    }
    public ArrayList<Blog> getAllBlogBySql( String sql) {
        ArrayList<Blog> listb = new ArrayList<>();
        try {
           
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int BlogID = rs.getInt("BlogId");
                String BlogTitle = rs.getString("BlogTitle");
                String BlogDesciption = rs.getString("BlogDescription");
                byte[] Image = rs.getBytes("Image");
                int CategoryID = rs.getInt("CategoryId");
                Date date = rs.getDate("DateCreated");
                int blogstatus = rs.getInt("BlogStatus");
                int Accountid = rs.getInt("AccountId");
                listb.add(new Blog(BlogID, BlogTitle, BlogDesciption, Image, CategoryID, date, blogstatus,Accountid));
            }
        } catch (Exception e) {
        }
        return listb;
    }     
    public Blog getBlogBySql(String sql) {
        Blog blog = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int BlogID = rs.getInt("BlogId");
                String BlogTitle = rs.getString("BlogTitle");
                String BlogDesciption = rs.getString("BlogDescription");
                byte[] Image = rs.getBytes("Image");
                int CategoryID = rs.getInt("CategoryId");
                Date date = rs.getDate("DateCreated");
               int blogstatus = rs.getInt("BlogStatus");
                int Accountid = rs.getInt("AccountId");
                blog = new Blog(BlogID, BlogTitle, BlogDesciption, Image, CategoryID, date, blogstatus, Accountid);              
            }
        } catch (Exception e) {
        }
        return blog;
    }
    public ArrayList<BlogPart> getBlogPart(int BlogID) {
        ArrayList<BlogPart> blogParts = new ArrayList<BlogPart>();
        try {
            String sql = "select * from swp_dtb.blogpart where BlogID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, BlogID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int BlogPartID = rs.getInt("BlogPartID");
                int Blogid = rs.getInt("BlogID");
                int Part = rs.getInt("Part");
                String PartHeader = rs.getString("PartHeader");
                String PartContent = rs.getString("PartContent");
                byte[] Image = rs.getBytes("Image");
                BlogPart plogPart = new BlogPart(BlogPartID, Blogid, Part, PartHeader, PartContent, Image);
                blogParts.add(plogPart);
            }
        } catch (Exception e) {
        }
        return blogParts;
    } 
    public int updateBlog(Blog blog) {
        int result =0;
        String sql = "update Blog\n"
                + "set BlogTitle = ?, BlogDescription = ? ,CategoryId = ?, DateCreated = ? ,BlogStatus = ?\n"
                + "where BlogId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, blog.getBlogTitle());
            ps.setString(2, blog.getBlogDesciption());
            ps.setInt(3, blog.getCategoryID());
            ps.setDate(4, blog.getDateCreated());
            ps.setInt(5, blog.getBlogStatus());
            ps.setInt(6, blog.getBlogID());
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result;
    }
    public void setStatus(int id, int status) {
        String sql = "update Blog\n"
                + "set BlogStatus = ?\n"
                + "where BlogId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt( 2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public int updateBlogPart(ArrayList<BlogPart> blogParts) {
        int result = 0;
        String sql;
        try {
            for (BlogPart item : blogParts) {
                sql = "update BlogPart\n"
                        + "set PartHeader = ?, PartContent = ?\n"
                        + "where BlogId = ? and Part = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, item.getPartHeader());
                ps.setString(2, item.getPartContent());
                ps.setInt(3, item.getBlogID());
                ps.setInt(4, item.getPart());
                result = ps.executeUpdate();
            }
        } catch (Exception e) {
        }
        return result;
    }   
    public Blog insertBlog(Blog blog) {
        String sql = "insert into Blog(BlogTitle, BlogDescription, Image,CategoryId , DateCreated, BlogStatus, AccountId)\n"
                + "values(?, ?, ?, ?, ?, ?, ?)";
        dal.Blog newestBlog = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, blog.getBlogTitle());
            ps.setString(2, blog.getBlogDesciption());
            ps.setBytes(3, blog.getImage());
            ps.setInt(4, blog.getCategoryID());
            ps.setDate(5, blog.getDateCreated());
            ps.setInt(6, blog.getBlogStatus());
            ps.setInt(7, blog.getAccountId());
            int result = ps.executeUpdate();
            if (result > 0) {
                newestBlog = new BlogDAO().getBlogBySql("select * from Blog \n"
                        + "order by BlogID desc\n"
                        + "limit 1");
            }
        } catch (Exception e) {
        }
        return newestBlog;
    }
    public void insertBlogPart(BlogPart blogpart) {
        String sql = "insert into swp_dtb.blogpart(BlogID, Part, PartHeader, PartContent , Image)\n"
                + "values(?, ?, ?, ?, ?)";
        dal.Blog newestBlog = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,blogpart.getBlogID());
            ps.setInt(2, blogpart.getPart());
            ps.setString(3, blogpart.getPartHeader());
            ps.setString(4, blogpart.getPartContent());
            ps.setBytes(5, blogpart.getImage());
            int result = ps.executeUpdate();    
        } catch (Exception e) {
        }    
    }
    public int deleteBlog(int BlogID) {
        String deleteBlogSql = "delete from swp_dtb.blog where BlogId = " + BlogID;
        int result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(deleteBlogSql);
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result;
    }
    public void deleteBlogPart(int BlogPartID) {

        String sql = "delete from swp_dtb.blogpart where BlogPartID = " + BlogPartID;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }  
}

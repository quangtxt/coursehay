/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Blog;
import dal.BlogPart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import models.BlogDAO;
import models.CategoryDAO;
import models.CoursesDAO;
/**
 *
 * @author Acer
 */
@MultipartConfig
public class BlogManagerController extends HttpServlet {
    boolean status;
    boolean edit;
    boolean post;
    boolean addPart;
    boolean removePart;
    boolean delete;
    boolean view;
    private static int parts = 1;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        view = Boolean.parseBoolean(request.getParameter("view"));
        status = Boolean.parseBoolean(request.getParameter("status"));
        delete = Boolean.parseBoolean(request.getParameter("delete"));
        addPart = Boolean.parseBoolean(request.getParameter("addPart"));
        removePart = Boolean.parseBoolean(request.getParameter("removePart"));
        String role = request.getParameter("role");
        if (addPart) {
            parts += 1;
        }
        if (removePart) {
            if (parts > 1) {
                parts -= 1;
            }
        }
        if (delete) {
            deleteBlog(request, response);
        }
        if (status) {
            setStatus(request, response);
        }
        request.setAttribute("listCate", new CategoryDAO().getAllCategories());
        request.setAttribute("Parts", parts);
        if (Boolean.parseBoolean(request.getParameter("post"))) {
            if (role.equals("admin")) {
                request.getRequestDispatcher("/createblog.jsp").forward(request, response);
            } else  {
                request.getRequestDispatcher("/createblog1.jsp").forward(request, response);
            }
        } else if (view) {
            if (role.equals("admin")) {
                request.setAttribute("listb", new BlogDAO().getAllBlogBySql("SELECT bl.BlogId, bl.BlogTitle, bl.BlogDescription, bl.Image, bl.CategoryId, bl.DateCreated, bl.BlogStatus, a.AccountId FROM swp_dtb.blog as bl , swp_dtb.accounts as a where bl.AccountId = a.AccountId and a.RoleId = 1"));
                request.setAttribute("listbluser", new BlogDAO().getAllBlogBySql("SELECT bl.BlogId, bl.BlogTitle, bl.BlogDescription, bl.Image, bl.CategoryId, bl.DateCreated, bl.BlogStatus, a.AccountId FROM swp_dtb.blog as bl , swp_dtb.accounts as a where bl.AccountId = a.AccountId and a.RoleId = 2"));          
                request.setAttribute("CoursesDAO", new CoursesDAO());
                request.getRequestDispatcher("/manageblog.jsp").forward(request, response);
            } 
              else if (role.equals("user")) {
                request.setAttribute("CoursesDAO", new CoursesDAO());
               request.getRequestDispatcher("/userblog.jsp").forward(request, response);
            }
        } 
        else  {
            if (role.equals("admin")) {
                request.setAttribute("listb", new BlogDAO().getAllBlogBySql("SELECT bl.BlogId, bl.BlogTitle, bl.BlogDescription, bl.Image, bl.CategoryId, bl.DateCreated, bl.BlogStatus, a.AccountId FROM swp_dtb.blog as bl , swp_dtb.accounts as a where bl.AccountId = a.AccountId and a.RoleId = 1"));
                request.setAttribute("listbluser", new BlogDAO().getAllBlogBySql("SELECT bl.BlogId, bl.BlogTitle, bl.BlogDescription, bl.Image, bl.CategoryId, bl.DateCreated, bl.BlogStatus, a.AccountId FROM swp_dtb.blog as bl , swp_dtb.accounts as a where bl.AccountId = a.AccountId and a.RoleId = 2"));          
                request.setAttribute("CoursesDAO", new CoursesDAO());
                request.getRequestDispatcher("/manageblog.jsp").forward(request, response);
            } 
              else if (role.equals("user")) {
                 
                request.setAttribute("CoursesDAO", new CoursesDAO());
                
                request.getRequestDispatcher("/userblog.jsp").forward(request, response);
            }
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        edit = Boolean.parseBoolean(request.getParameter("edit"));
        post = Boolean.parseBoolean(request.getParameter("post"));
        
        if (post) {
            postBlog(request, response);
        }

        if (edit) {
            editBlog(request, response);
        }
    }

    private void editBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = ((Account) request.getSession().getAttribute("AccSession"));
        Blog blog = new BlogDAO().getBlogBySql("Select * from Blog where BlogID = " + request.getParameter("BlogID"));
        blog.setBlogTitle(request.getParameter("BlogTitle"));
        blog.setBlogDesciption(request.getParameter("BlogDescription"));
        blog.setCategoryID((Integer.parseInt(request.getParameter("BlogCategory"))));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date createdate = new Date();
        formatter.format(createdate);
        java.sql.Date date = new java.sql.Date(createdate.getTime());
        blog.setDateCreated(date);
        blog.setBlogStatus(Integer.parseInt(request.getParameter("status")));
        int message = new BlogDAO().updateBlog(blog);
        ArrayList<BlogPart> blogParts = new BlogDAO().getBlogPart(blog.getBlogID());
        for (BlogPart item : blogParts) {
            item.setPartHeader(request.getParameter("part-" + item.getPart() + "-header"));
            item.setPartContent(request.getParameter("part-" + item.getPart() + "-content"));
        }       
        int message1 = new BlogDAO().updateBlogPart(blogParts);
        if (account.getRoleId() == 1 && message !=0 && message1 !=0 ) {
            response.sendRedirect(request.getContextPath() + "/editblog.jsp?BlogID=" + blog.getBlogID()+"&success=true");
        } else if (account.getRoleId() == 2 && message !=0 && message1 !=0) {
            response.sendRedirect(request.getContextPath() + "/editblog1.jsp?BlogID=" + blog.getBlogID()+"&success=true");
        }
    }
    private void postBlog(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Account account = ((Account) request.getSession().getAttribute("AccSession"));
        Part BlogImg = request.getPart("BlogImage");
        InputStream blogimage = BlogImg.getInputStream();
        byte[] BlogImage =  conertToByteArray(blogimage);
        String BlogTitle = request.getParameter("BlogTitle");
        String BlogDesciption = request.getParameter("BlogDescription");
        int BlogCategory = Integer.parseInt(request.getParameter("BlogCategory"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date createdate = new Date();
        formatter.format(createdate);
        java.sql.Date date = new java.sql.Date(createdate.getTime());
        int blogstatus = Integer.parseInt(request.getParameter("status"));
        Blog blog = new BlogDAO().insertBlog(new Blog(0, BlogTitle, BlogDesciption, BlogImage, BlogCategory, date, blogstatus, account.getAccountID()));
        for (int i = 1; i <= parts; i++) {
            int BlogId = blog.getBlogID();
            int Part = i;
            String PartHeader = request.getParameter("part-" + i + "-header");
            String PartContent = request.getParameter("part-" + i + "-content");
            Part Image = null;
            byte[] BlogPartImage = null;
            Image = request.getPart("part-" + i + "-image");
            InputStream blogpartimage = Image.getInputStream();
            BlogPartImage = conertToByteArray(blogpartimage);
           
            BlogPart bp = new BlogPart(0, BlogId, Part, PartHeader, PartContent, BlogPartImage);
            new BlogDAO().insertBlogPart(bp);
        }
        parts = 1;
        if (account.getRoleId() == 1) {
            request.getRequestDispatcher("/editblog.jsp?BlogID=" + blog.getBlogID()).forward(request, response);
        } else if (account.getRoleId() == 2) {
            request.getRequestDispatcher("/editblog1.jsp?BlogID=" + blog.getBlogID()).forward(request, response);
        }
    }
    private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String BlogID = request.getParameter("BlogID");
        
        ArrayList<BlogPart> PartList = new BlogDAO().getBlogPart(Integer.parseInt(BlogID));
        for (BlogPart item : PartList) {
            new BlogDAO().deleteBlogPart(item.getBlogPartID());
        }
        new BlogDAO().deleteBlog(Integer.parseInt(BlogID));
    }
    private void setStatus(HttpServletRequest request, HttpServletResponse response) {
        int BlogID = Integer.parseInt(request.getParameter("BlogID"));
        int status1 = Integer.parseInt(request.getParameter("status1"));
        new BlogDAO().setStatus(BlogID, status1);
    }
    public byte[] conertToByteArray(InputStream image)
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = image.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }
}

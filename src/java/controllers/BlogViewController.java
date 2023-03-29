/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Blog;
import dal.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.AccountDAO;
import models.BlogDAO;
import models.CategoryDAO;
import models.ContactDAO;
import models.CoursesDAO;
/**
 *
 * @author Acer
 */
public class BlogViewController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cateid = req.getParameter("cateid");
        String blogid = req.getParameter("blogid");
        if (cateid != null) {        
        ArrayList<Category> lisc= new CategoryDAO().getAllCategories();
        Category cate = new CategoryDAO().getCategoryById(Integer.parseInt(cateid));
        req.setAttribute("blogDao", new BlogDAO().getBlogBycateID("SELECT * FROM swp_dtb.blog where categoryid = "+ cateid +" and BlogStatus = 3"));
        req.setAttribute("listc", lisc);
        req.setAttribute("cate", cate);
        req.setAttribute("CoursesDAO", new CoursesDAO());
        req.setAttribute("ContactDAO", new ContactDAO());
        req.setAttribute("AccountDAO", new AccountDAO());
        req.getRequestDispatcher("/blog1.jsp").forward(req, resp);
        }
        else if(blogid != null ){
        Blog blog = new BlogDAO().getBlogBySql("select * from swp_dtb.blog where BlogID = "+ blogid);
        ArrayList partblog = new BlogDAO().getBlogPart(Integer.parseInt(blogid));
        req.setAttribute("blog", blog);
        req.setAttribute("partblog", partblog);
        req.setAttribute("ContactDAO", new ContactDAO());
        req.setAttribute("AccountDAO", new AccountDAO());
        req.getRequestDispatcher("/blog_detail.jsp").forward(req, resp);   
        }
        else{
        ArrayList<Category> lisc= new CategoryDAO().getAllCategories();
        req.setAttribute("blogDao", new BlogDAO());
        req.setAttribute("listc", lisc);
        req.setAttribute("CoursesDAO", new CoursesDAO());
        req.setAttribute("ContactDAO", new ContactDAO());
        req.setAttribute("AccountDAO", new AccountDAO());
        req.getRequestDispatcher("/blog.jsp").forward(req, resp);
        }
    }
}

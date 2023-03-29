/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author ADMIN
 */
public class AuthorEditCoursesController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account)req.getSession().getAttribute("AccSession")).getRoleId() == 2) {
            String coursesid = req.getParameter("coursesid");
            Course courses = new CoursesDAO().getCourseById(Integer.parseInt(coursesid));
            req.setAttribute("courses", courses);
            ArrayList<Category> categories = new CategoryDAO().getAllCategories();
            req.setAttribute("categories", categories);
            req.setAttribute("CateDao", new CategoryDAO());
            req.getRequestDispatcher("/author-editcourses.jsp").forward(req, resp);
         }else{
             resp.sendRedirect("home");
         }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DomParserMessage Me = new DomParserMessage();
        String name = req.getParameter("course-name");
        String title = req.getParameter("course-title");
        String description = req.getParameter("course-desc");
        String category = req.getParameter("course-cate");
        String coursesid = req.getParameter("coursesid");
        Course courses = new CoursesDAO().getCourseByCoursesID(Integer.parseInt(coursesid));
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        req.setAttribute("name", name);
        req.setAttribute("title", title);
        req.setAttribute("description", description);
        req.setAttribute("category", category);
        req.setAttribute("courses", courses);
        req.setAttribute("coursesid", coursesid);
        RequestDispatcher rd = req.getRequestDispatcher("/account/author-edit1");
        rd.forward(requestWrapper, resp);

    }

}

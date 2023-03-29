/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author DELL
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("conDAO", new ContactDAO());
        req.getSession().setAttribute("couDAO", new CoursesDAO());
        req.getSession().setAttribute("cartDAO", new CartDAO());
        req.getSession().setAttribute("topicDAO", new TopicDAO());
        req.getSession().setAttribute("NotiDAO", new NotificationDAO());
        req.getSession().setAttribute("wishlistDAO", new WishlistDAO());
        req.getSession().setAttribute("replyDAO", new ReplyDAO());
        req.getSession().setAttribute("contentDAO", new CourseContentDAO());
        req.getSession().setAttribute("lectureDAO", new LectureDAO());
        req.getSession().setAttribute("fbDAO", new FeedbackDAO());
        
        ArrayList<Course> listCourses = new CoursesDAO().getTopCoursesBuyer(10);
        req.setAttribute("listCourses", listCourses);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Course;
import dal.DomParserMessage;
import dal.Topic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author ADMIN
 */
public class AuthorEditCoursesController1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getAttribute("coursesid") == null) {
            resp.sendRedirect(req.getContextPath() + "/404");
        } else {
            String name = (String) req.getAttribute("name");
            String title = (String) req.getAttribute("title");
            String desc = (String) req.getAttribute("description");
            String id = (String) req.getAttribute("category");
            Course courses = (Course) req.getAttribute("courses");
            String coursesid = (String) req.getAttribute("coursesid");
            ArrayList<Topic> listTopic = new TopicDAO().getAllTopicsByCateId(Integer.parseInt(id));
            req.setAttribute("name", name);
            req.setAttribute("title", title);
            req.setAttribute("desc", desc);
            req.setAttribute("cate", id);
            req.setAttribute("listTopic", listTopic);
            req.setAttribute("coursesid", coursesid);
            req.setAttribute("courses", courses);
            req.getRequestDispatcher("/author-editcourses1.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        int topic = Integer.parseInt(req.getParameter("topic"));
        int coursesid = Integer.parseInt(req.getParameter("coursesid"));
        int price = Integer.parseInt((req.getParameter("price")));
        if ("1".equals(req.getParameter("isSubmit"))) {
            Course c = new Course(topic, name, title, desc, 1, price);
            int x = new CoursesDAO().EditCourse(c, coursesid);
            if (x > 0) {
                req.setAttribute("msgErr", "Update infomation course successfully");
            } else {
                req.setAttribute("msgErr", "Update infomation course fail");
            }
        } else {
            Course c = new Course(topic, name, title, desc, 0, price);
            int x = new CoursesDAO().EditCourse(c, coursesid);
            if (x > 0) {
                req.setAttribute("msgErr", "Update infomation course successfully");
            } else {
                req.setAttribute("msgErr", "Update infomation course fail");
            }
        }
        int accountid = ((Account) req.getSession().getAttribute("AccSession")).getAccountID();
        ArrayList<Course> coursesofAuthor = new CoursesDAO().getCoursesByAccountID(accountid);
        req.setAttribute("coursesofAuthor", coursesofAuthor);
        req.setAttribute("CoursesDAO", new CoursesDAO());
        req.setAttribute("coursesid", coursesid);
        req.getRequestDispatcher("/authorcourses.jsp").forward(req, resp);
    }
}

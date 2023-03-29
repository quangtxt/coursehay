/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Course;
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
 * @author admin
 */
public class CreateCourse1Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getAttribute("name");
        String title = (String) req.getAttribute("title");
        String desc = (String) req.getAttribute("description");
        String id = (String) req.getAttribute("category");
        ArrayList<Topic> listTopic = new TopicDAO().getAllTopicsByCateId(Integer.parseInt(id));
        req.setAttribute("name", name);
        req.setAttribute("title", title);
        req.setAttribute("desc", desc);
        req.setAttribute("cate", id);
        req.setAttribute("listTopic", listTopic);
        req.getRequestDispatcher("./createcourse1.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        int topic = Integer.parseInt(req.getParameter("topic"));
        int price = Integer.parseInt(req.getParameter("price"));
        Course c = new Course(topic, name, title, desc, price);
        int x = new CoursesDAO().createCourse(c, acc.getAccountID());
        if (x > 0) {
            resp.sendRedirect("account/author");
        } else {
            resp.sendRedirect("create-course");
        }
    }
}

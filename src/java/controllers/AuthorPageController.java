/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CoursesDAO;

/**
 *
 * @author ADMIN
 */
public class AuthorPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRoleId() == 2) {
            int accountid = ((Account) req.getSession().getAttribute("AccSession")).getAccountID();
            ArrayList<Course> coursesofAuthor = new CoursesDAO().getCoursesByAccountID(accountid);
            req.setAttribute("coursesofAuthor", coursesofAuthor);
            req.setAttribute("CoursesDAO", new CoursesDAO());
            req.getRequestDispatcher("/authorcourses.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/home").forward(req, resp);
        }
    }

}

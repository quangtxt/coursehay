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
import java.util.List;
import models.AccountDAO;
import models.CoursesDAO;

/**
 *
 * @author ADMIN
 */
public class DeleteCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int coursesid = Integer.parseInt(req.getParameter("id"));
        if (new CoursesDAO().getCourseById(coursesid).getAuthorId() == ((Account) req.getSession().getAttribute("AccSession")).getAccountID()) {
            if (new CoursesDAO().getCourseById(coursesid).getPublicStatus() == 0) {
                int x = new CoursesDAO().DeteteCourse(coursesid);
                if (x > 0) {
                    req.setAttribute("msgDelete", "Delete successfully");
                }
            } else {
                req.setAttribute("msgDelete", "You can only delete courses that are not public");
            }

        } else {
            req.setAttribute("msgDelete", "Delete fail");
        }
        int accountid = ((Account) req.getSession().getAttribute("AccSession")).getAccountID();
        ArrayList<Course> coursesofAuthor = new CoursesDAO().getCoursesByAccountID(accountid);
        req.setAttribute("coursesofAuthor", coursesofAuthor);
        req.setAttribute("CoursesDAO", new CoursesDAO());
        req.getRequestDispatcher("/authorcourses.jsp").forward(req, resp);
    }
}

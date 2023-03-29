/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import models.CoursesDAO;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class EditImgController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filepart = req.getPart("pic");
        InputStream filecontent = filepart.getInputStream();
        int coursesid = Integer.parseInt(req.getParameter("id"));
        int x = new CoursesDAO().EditCourseImg(filecontent, coursesid);
        if (x > 0) {
            req.setAttribute("msgErr", "Update img course successfully");
        } else if (x == 0) {
            req.setAttribute("msgErr", "Update img course fail");       
        }
        int accountid = ((Account) req.getSession().getAttribute("AccSession")).getAccountID();
        ArrayList<Course> coursesofAuthor = new CoursesDAO().getCoursesByAccountID(accountid);
        req.setAttribute("coursesofAuthor", coursesofAuthor);
        req.setAttribute("CoursesDAO", new CoursesDAO());
        req.setAttribute("coursesid", coursesid);
        req.getRequestDispatcher("/authorcourses.jsp").forward(req, resp);
    }

}

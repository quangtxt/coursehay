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
import models.AccountDAO;
import models.CoursesDAO;
import models.OrderDAO;

/**
 *
 * @author DELL
 */
public class GiftCourseController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        String email = req.getParameter("recipient_email");
        String mess = req.getParameter("message");
        int couId = Integer.parseInt(req.getParameter("couId"));
        Account accRec = new AccountDAO().getAccountByEmail(email);
        if (accRec == null) {
            req.getSession().setAttribute("msgMess", "The email you entered is not registered on the system");
            resp.sendRedirect(req.getHeader("referer"));
            return;
        }
        if (acc.getAccountID() == accRec.getAccountID()) {
            req.getSession().setAttribute("msgMess", "You are giving the course to yourself.Please confirm your email or add the course to your cart to pay");
            resp.sendRedirect(req.getHeader("referer"));
            return;
        }
        if (new OrderDAO().checkCourseExit(accRec.getAccountID(), couId)) {
            Course c = new CoursesDAO().getCourseById(couId);
            req.getSession().setAttribute("msgMess", "The account you want to send the course \"" + c.getCourseName() + "\" has already purchased that course");
            resp.sendRedirect(req.getHeader("referer"));
        } else {
            Course c = new CoursesDAO().getCourseById(couId);
            req.setAttribute("c", c);
            req.setAttribute("mess", mess);
            req.setAttribute("recId", accRec.getAccountID());
            req.getRequestDispatcher("./gift-course.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            resp.sendRedirect(req.getContextPath() + "/account");
        } else {
            int courseId = Integer.parseInt(req.getParameter("couId"));
            Course c = new CoursesDAO().getCourseById(courseId);
            req.setAttribute("c", c);
            req.getRequestDispatcher("./gift-course.jsp").forward(req, resp);
        }
    }
}

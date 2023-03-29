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
import models.CoursesDAO;
import models.WishlistDAO;

public class CourseManagerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("ac") == null || req.getSession().getAttribute("AccSession")==null) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("ac",req.getParameter("ac"));
            req.getRequestDispatcher("coursemanager.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountid = Integer.parseInt(req.getParameter("accid"));
        int courseid = Integer.parseInt(req.getParameter("courseid"));
        String action = req.getParameter("action");
        String href = req.getParameter("href");
        if (action.equals("remove")) {
            new WishlistDAO().RemoveCoureInWishlist(accountid, courseid);
            if (href.equals("wishlist")) {
                ArrayList<Course> wishlist = new WishlistDAO().getAllCourseInWishlistByAccountId(accountid);
                req.getSession().setAttribute("wishlistSession", wishlist);
                resp.sendRedirect(req.getHeader("referer"));
            } else if (href.equals("course")) {
                ArrayList<Course> wishlist = new WishlistDAO().getAllCourseInWishlistByAccountId(accountid);
                req.getSession().setAttribute("wishlistSession", wishlist);
                resp.sendRedirect(req.getHeader("referer"));
            }
        } else if (action.equals("add")) {
            new WishlistDAO().addToWishlist(accountid, courseid);
            if (href.equals("course")) {
                ArrayList<Course> wishlist = new WishlistDAO().getAllCourseInWishlistByAccountId(accountid);
                req.getSession().setAttribute("wishlistSession", wishlist);
                resp.sendRedirect(req.getHeader("referer"));
            }
        }
    }
}

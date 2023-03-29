/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.*;
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
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (acc == null) {
            if (req.getParameter("action") == null) {
                ArrayList<Integer> listCourseId = (ArrayList<Integer>) req.getSession().getAttribute("cart");
                req.setAttribute("cart", listCourseId);
            } else {
                String action = req.getParameter("action");
                int courseID = Integer.parseInt(req.getParameter("courseID"));
                ArrayList<Integer> listCourseId = (ArrayList<Integer>) req.getSession().getAttribute("cart") == null ? new ArrayList<>() : (ArrayList<Integer>) req.getSession().getAttribute("cart");
                if (action.equals("add")) {
                    if (listCourseId.indexOf(courseID) == 0) {
                        req.getSession().setAttribute("msgMess", "The course is in the cart");
                    } else {
                        listCourseId.add(courseID);
                    }
                } else if (action.equals("remove")) {
                    if (!listCourseId.isEmpty()) {
                        listCourseId.remove(listCourseId.indexOf(courseID));
                    }
                }
                req.getSession().setAttribute("cart", listCourseId);
                resp.sendRedirect(req.getHeader("referer"));
                return;
            }
        } else {
            if (req.getParameter("action") == null) {
                req.setAttribute("cart", new CartDAO().getCourseIDInCartByAccountId(acc.getAccountID()));
            } else {
                String action = req.getParameter("action");
                int courseID = Integer.parseInt(req.getParameter("courseID"));
                if (action.equals("add")) {
                    if (!(new CartDAO().checkExistInCart(acc.getAccountID(), courseID))) {
                        new CartDAO().addToCart(acc.getAccountID(), courseID);
                        if (new WishlistDAO().checkExistInWishlist(acc.getAccountID(), courseID) == 1) {
                            new WishlistDAO().RemoveCoureInWishlist(acc.getAccountID(), courseID);
                            ArrayList<Course> wishlist = new WishlistDAO().getAllCourseInWishlistByAccountId(acc.getAccountID());
                            req.getSession().setAttribute("wishlistSession", wishlist);
                        }
                    } else {
                        req.getSession().setAttribute("msgMess", "The course is in the cart");
                    }
                } else if (action.equals("remove")) {
                    new CartDAO().RemoveCoureInCart(acc.getAccountID(), courseID);
                } else if (action.equals("removeTWL")) {
                    new CartDAO().RemoveCoureInCart(acc.getAccountID(), courseID);
                    new WishlistDAO().addToWishlist(acc.getAccountID(), courseID);
                    ArrayList<Course> wishlist = new WishlistDAO().getAllCourseInWishlistByAccountId(acc.getAccountID());
                    req.getSession().setAttribute("wishlistSession", wishlist);
                }
                resp.sendRedirect(req.getHeader("referer"));
                return;
            }
        }
        req.setAttribute("couDAO", new CoursesDAO());
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

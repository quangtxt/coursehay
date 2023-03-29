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
public class SigninController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("AccSession");
            req.getSession().removeAttribute("role");
            req.getSession().removeAttribute("wishlist");
            req.getSession().removeAttribute("name");
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DomParserMessage Me = new DomParserMessage();
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        if (email.equals("") && password.equals("")) {
            req.setAttribute("msg", Me.mess("emailPassNull"));
        } else if (email.equals("")) {
            req.setAttribute("msg", Me.mess("emailNull"));
        } else if (password.equals("")) {
            req.setAttribute("msg", Me.mess("passNull"));
        }
        if (email.equals("") || password.equals("")) {
            req.getRequestDispatcher("./signin.jsp").forward(req, resp);
        } else {
            Account acc = new AccountDAO().getAccount(email, password);
            if (acc != null) {
                //cap session
                if (acc.isStatus()) {
                    req.setAttribute("msg", "This Account still banning !!!");
                    req.getRequestDispatcher("./signin.jsp").forward(req, resp);
                } else {
                    req.getSession().setAttribute("AccSession", acc);
                    ArrayList<Integer> listCourseId = (ArrayList<Integer>) req.getSession().getAttribute("cart");
                    if (listCourseId!=null) {
                        for (Integer i : listCourseId) {
                            if (!(new OrderDAO().checkCourseExit(acc.getAccountID(),i))) {
                                new CartDAO().addToCart(acc.getAccountID(), i);
                            }
                        }
                    }
                    req.getSession().removeAttribute("cart");
                    ArrayList<Course> wishlist = new WishlistDAO().getAllCourseInWishlistByAccountId(acc.getAccountID());
                    req.getSession().setAttribute("wishlistSession", wishlist);
                    switch (acc.getRoleId()) {
                        case 1:
                            req.getSession().setAttribute("role", acc.getRoleId());
                            resp.sendRedirect(req.getContextPath() + "/manage-purchaser");
                            break;
                        default:
                            req.getSession().setAttribute("role", acc.getRoleId());
                            req.getSession().setAttribute("username", new ContactDAO().getAccContactById(acc.getAccountID()).getFullName());
                            resp.sendRedirect(req.getContextPath() + "/home");
                            break;
                    }
                }
            } else {
                req.setAttribute("msg", "This account does not exist.");
                req.getRequestDispatcher("./signin.jsp").forward(req, resp);
            }
        }
    }
}

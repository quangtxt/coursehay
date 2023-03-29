/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Notification;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CoursesDAO;
import models.NotificationDAO;

/**
 *
 * @author ADMIN
 */
public class NotificationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            resp.sendRedirect("home");
        } else {
            ArrayList<Notification> listnotification = new ArrayList<>();
            int accountid = ((Account) req.getSession().getAttribute("AccSession")).getAccountID();
            if (((Account) req.getSession().getAttribute("AccSession")).getRoleId() == 2 && "instructor".equals(req.getParameter("role"))) {
                if (req.getParameter("NotifyId") == null) {
                    listnotification = new NotificationDAO().getListNotiByAccountId(accountid, 1);
                } else {
                    int NotifyId = Integer.parseInt(req.getParameter("NotifyId"));
                    new NotificationDAO().HasSeenNoti(NotifyId);
                    resp.sendRedirect(req.getContextPath() + "/account/author");
                    return;
                }
            } else if (((Account) req.getSession().getAttribute("AccSession")).getRoleId() == 2 && "student".equals(req.getParameter("role"))) {
                if (req.getParameter("NotifyId") == null) {
                    listnotification = new NotificationDAO().getListNotiByAccountId(accountid, 0);
                } else {
                    int NotifyId = Integer.parseInt(req.getParameter("NotifyId"));
                    new NotificationDAO().HasSeenNoti(NotifyId);
                    resp.sendRedirect(req.getContextPath() + "/coursedetail");
                    return;
                }
            }
            req.setAttribute("listnotification", listnotification);
            req.setAttribute("CoursesDAO", new CoursesDAO());
            req.getRequestDispatcher("notification.jsp").forward(req, resp);
        }
    }

}

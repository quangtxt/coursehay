/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dal.*;
import models.*;

/**
 *
 * @author DELL
 */
public class ProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (acc == null) {
            resp.sendRedirect(req.getContextPath() + "/account");
        } else {
            Contact con = new ContactDAO().getAccContactById(acc.getAccountID());
            req.setAttribute("con", con);
            req.getRequestDispatcher("../userprofile.jsp").forward(req, resp);
        }
    }
}

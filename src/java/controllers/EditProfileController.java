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
import java.sql.Date;
import models.*;

/**
 *
 * @author msi
 */
public class EditProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        String FullName = req.getParameter("txtName");
        String Gender = req.getParameter("txtGender");
        Date Dob = Date.valueOf(req.getParameter("txtDob"));
        String Phone = req.getParameter("txtPhone");
        String Address = req.getParameter("txtAddress");
        String reg = "^(0[1-9])+([0-9]{8})$";
        boolean check = Phone.matches(reg);
        java.util.Date date = new java.util.Date();
        String msgFullName = "", msgPhone = "", msgDob = "";
        if (FullName.equals("")) {
            msgFullName += "Full name is required";
            req.setAttribute("msgFullName", msgFullName);
        }
        if (Phone.equals("") || check == false) {
            msgPhone += "Invalid phone number";
            req.setAttribute("msgPhone", msgPhone);
        }
        if (Dob.equals(date) || Dob.after(date) || Dob.equals("")) {
            msgDob += "Invalid date of birth";
            req.setAttribute("msgDob", msgDob);
        }
        if (!msgFullName.equals("") || !msgPhone.equals("") || !msgDob.equals("")) {
            req.getRequestDispatcher("../editprofile.jsp").forward(req, resp);
        } else {
            Contact c = new Contact();
            if (Gender.equals("Male")) {
                c.setGender(true);
            } else if (Gender.equals("Female")) {
                c.setGender(false);
            }
            Contact con = new Contact(0, FullName, c.isGender(), Dob, Phone, Address);
            new ContactDAO().updateProfile(acc, con);
            resp.sendRedirect(req.getContextPath() + "/account/profile");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            Contact con = new ContactDAO().getAccContactById(acc.getAccountID());
            req.getSession().setAttribute("con", con);
            req.getRequestDispatcher("../editprofile.jsp").forward(req, resp);
        }
    }

}

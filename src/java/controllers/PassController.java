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
import java.util.Random;
import models.AccountDAO;

/**
 *
 * @author ADMIN
 */
public class PassController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String otp = req.getParameter("otp");
        if (otp == null) {
            resp.sendRedirect("./404");
        } else if (otp.equals("change")) {
            req.getRequestDispatcher("/changepass.jsp").forward(req, resp);
        } else if (otp.equals("forgot")) {
            req.getRequestDispatcher("/forgot.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp = req.getParameter("otp");
        Account a = (Account) req.getSession().getAttribute("AccSession");
        if (otp == null) {
            resp.sendRedirect("./404");
        } else if (otp.equals("change")) {
            boolean flag = true;
            String oldPass = req.getParameter("txtOldPass");
            String newPass = req.getParameter("txtNewPass");
            String reNewPass = req.getParameter("txtReNewPass");
            String msg = "";
            if (!oldPass.equals(a.getPassword())) {
                flag = false;
                msg = "Your old password is not correct";
            } else if (newPass.equals("") || newPass.equals(a.getPassword())) {
                flag = false;
                msg = "The new password cannot be blank or the same as the old password";
            } else if (!reNewPass.equals(newPass)) {
                flag = false;
                msg = "2 new passwords do not match";
            }
            if (flag) {
                if (new AccountDAO().changePass(a.getAccountID(), newPass) == 0) {
                    msg = "Password change failed";
                } else {
                    req.setAttribute("msgSuccess", "Change pass successfully");
                }
                req.setAttribute("msg", msg);
                req.getRequestDispatcher("/changepass.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", msg);
                req.getRequestDispatcher("/changepass.jsp").forward(req, resp);
            }
        } else if (otp.equals("forgot")) {
            String email = req.getParameter("txtEmail");
            Account acc = new AccountDAO().getAccountByEmail(email);
            String msg = "";
            if (acc != null) {
                String newpass = String.valueOf(passwordNew(8));
                if (new AccountDAO().changePass(acc.getAccountID(), newpass) == 0) {
                    msg = "Change Password failed!";
                    req.setAttribute("msg", msg);
                    req.getRequestDispatcher("/forgot.jsp").forward(req, resp);
                } else {
                    req.setAttribute("msgSuccess", "Change Password successfull!");
                    Mail.SenMailResetPass(email, newpass, acc);
                    req.getRequestDispatcher("/forgot.jsp").forward(req, resp);
                }
            } else {
                msg = "Account not exsited !";
                req.setAttribute("msg", msg);
                req.getRequestDispatcher("/forgot.jsp").forward(req, resp);
            }
        }
    }
    static char[] passwordNew(int len) {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";
        String values = Capital_chars + Small_chars + numbers + symbols;
        Random rndm_method = new Random();
        char[] password = new char[len];
        for (int i = 0; i < len; i++) {
            password[i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        return password;
    }

}

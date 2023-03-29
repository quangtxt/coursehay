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
 * @author admin
 */
public class SignupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("txtFullName");
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPassword");
        String copass = req.getParameter("txtConfirmPassword");
        String phone = req.getParameter("txtPhone");
        String msgSignup = "";
        ArrayList<Account> listA = new AccountDAO().getAllAccounts();
        int duplicate = 0;
        for (Account account : listA) {
            if (email.equals(account.getEmail())) {
                duplicate = 1;
            }
        }
        if (duplicate == 1 || fullname.equals("") || !email.matches("[a-zA-Z]\\w+@\\w+(\\.\\w+){1,3}") || pass.equals("") || copass.equals("") || !copass.equals(pass) || !phone.matches("^(84|0[3|5|7|8|9])+([0-9]{8})$")) {
            msgSignup = "Invalid!!!";
            req.setAttribute("msgSignup", msgSignup);
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        } else {
            Account acc = new Account(email, pass);
            int x = new AccountDAO().addAccount(acc, fullname);
            if (x > 0) {
                req.getSession().setAttribute("AccSession", new AccountDAO().getAccountByEmail(email));
                req.getSession().setAttribute("username", fullname);
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }
        }
    }
}

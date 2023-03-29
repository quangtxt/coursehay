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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import models.*;

/**
 *
 * @author Acer
 */
public class LockAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account accAdm = (Account) req.getSession().getAttribute("AccSession");
        int accid = Integer.parseInt(req.getParameter("accid"));
        ArrayList<AccountBanHistory> hislist = new HistoryAccountDAO().getHistoryBanByIdAcc(accid);
        req.setAttribute("hislist", hislist);
        req.setAttribute("acc", new AccountDAO().getAccountById(accid));
        req.setAttribute("accContact", new ContactDAO().getAccContactById(accid));
        req.setAttribute("admContact", new ContactDAO().getAccContactById(accAdm.getAccountID()));
        req.setAttribute("hislistSize", hislist.size());
        req.getRequestDispatcher("/lock-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountid = Integer.parseInt(req.getParameter("accid"));
        int adminid = Integer.parseInt(req.getParameter("adminid"));
        boolean status = Boolean.valueOf(req.getParameter("status"));
        String reason = req.getParameter("ReasonBan");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date lockdate = new Date();
        formatter.format(lockdate);
        java.sql.Date lockDate = new java.sql.Date(lockdate.getTime());
        if (status == true) {
            //lock account
            if (reason.isEmpty() || reason.equals("") || reason.length() == 0) {
                req.setAttribute("reason", "Reason can not empty !!!");
                req.setAttribute("action", "not baned");
                req.setAttribute("accid", accountid);
                req.getRequestDispatcher("/ban-notice.jsp").forward(req, resp);
            } else {
                new AccountDAO().SetSatus(status, accountid);
                ArrayList<AccountBanHistory> hislist = new HistoryAccountDAO().getHistoryBanByIdAcc(accountid);
                int timeslock = hislist.size() + 1;
                new HistoryAccountDAO().AddToListHistoryBan(accountid, adminid, lockDate, timeslock, reason);
                dal.Mail.SendMailBan(accountid, reason);
                req.setAttribute("action", "baned");
                req.getRequestDispatcher("/ban-notice.jsp").forward(req, resp);
            }
        } else if (status == false) {
            new AccountDAO().SetSatus(status, accountid);
            ArrayList<AccountBanHistory> hislist = new HistoryAccountDAO().getHistoryBanByIdAcc(accountid);
            int timeslock = hislist.size();
            new HistoryAccountDAO().upDateUnban(accountid, lockDate, timeslock);
            dal.Mail.SendMailUnBan(accountid, "Your account has been unban");
            req.setAttribute("action", "unbaned");
            req.getRequestDispatcher("/ban-notice.jsp").forward(req, resp);
        }
    }

}

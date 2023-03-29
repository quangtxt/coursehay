package controllers;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import models.*;

public class AdminPurchaserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Integer> AccountIdList = new ArrayList<>();
        ArrayList<Course> couselist = new CoursesDAO().getCourseRateLow();
        int count = 0;
        for (Course course : couselist) {
            count = new FeedbackDAO().CountFeedBackbyCourseId(course.getCourseId());
            if (count > 100) {
                AccountIdList.add(course.getAuthorId());
            }
        }
        req.setAttribute("purlist", AccountIdList);
        req.setAttribute("contactDAO", new ContactDAO());
        req.setAttribute("accDAO", new AccountDAO());
        req.getRequestDispatcher("/admin-purchaser-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("txtSearch");
        String typeSearch = req.getParameter("typeSearch");
        int status = Integer.parseInt(req.getParameter("status"));
        List<Integer> list = new ArrayList<>();
        if (typeSearch.equals("email")) {
            list = new AccountDAO().getAccountsBySql("select * from accounts where Email like '%" + keyword + "%' and status = "+ status +";") ;
        }
        if (typeSearch.equals("name")) {
            list = new AccountDAO().getAccountsBySql("select a.* from accounts as a , contact as c where a.AccountId = c.AccountId and c.FullName like '%"+ keyword +"%' and status = " + status + ";") ;
        }
        req.setAttribute("accountList", list);
        req.setAttribute("contactDAO", new ContactDAO());
        req.setAttribute("accDAO", new AccountDAO());
        req.setAttribute("txtSearch", keyword);
        req.setAttribute("typeSearch", typeSearch);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/admin-purchaser-account.jsp").forward(req, resp);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Contact;
import dal.Course;
import dal.Paging;
import dal.User_Author;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.AccountDAO;
import models.CategoryDAO;
import models.ContactDAO;
import models.CoursesDAO;
import models.TopicDAO;
import models.User_AuthorDAO;

/**
 *
 * @author msi
 */
public class AuthorProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null) {
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            int AuthorId = Integer.parseInt(req.getParameter("AuthorId"));
            User_Author ua = new User_Author(acc.getAccountID(), AuthorId);
            User_Author ua1 = new User_AuthorDAO().getUserAuthorByID(acc.getAccountID(), AuthorId);
            if (ua1 == null) {
                new User_AuthorDAO().followAuthor(ua);
            } else {
                new User_AuthorDAO().unfollowAuthor(ua);
            }
            resp.sendRedirect(req.getContextPath() + "/authorprofile?authorId=" + AuthorId);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int AuthorId = Integer.parseInt(req.getParameter("authorId"));
        String Course = req.getParameter("Course");
        req.setAttribute("Course", Course);
        Contact con = new ContactDAO().getAccContactById(AuthorId);
        Account acc = new AccountDAO().getAccountById(AuthorId);
        if (req.getSession().getAttribute("AccSession") != null) {
            Account accS = (Account) req.getSession().getAttribute("AccSession");
            User_Author ua = new User_AuthorDAO().getUserAuthorByID(accS.getAccountID(), AuthorId);
            req.setAttribute("checkFollowed", ua);
        }
        req.setAttribute("con", con);
        req.setAttribute("acc", acc);
        if (Course != null) {
            ArrayList listCa = new CategoryDAO().getAllCategories();
            req.setAttribute("listCa", listCa);
            req.setAttribute("couDAO", new CoursesDAO());
            req.setAttribute("conDAO", new ContactDAO());
            req.setAttribute("topicDAO", new TopicDAO());
            ArrayList<Course> listCourses = new ArrayList<>();
            String cateId = req.getParameter("cateID");
            String topicId = req.getParameter("topicId");
            if (cateId == null && topicId == null) {
                ArrayList<Integer> listId = new CoursesDAO().getCourseIdByAuID(AuthorId);
                try {
                    for (Integer i : listId) {
                        Course c = new CoursesDAO().getCourseById(i);
                        listCourses.add(c);
                    }
                } catch (Exception e) {
                }
            } else if (cateId != null) {
                req.setAttribute("cateId", cateId);
                req.setAttribute("nameFilter", new CategoryDAO().getCategoryById(Integer.parseInt(cateId)).getCateName().toUpperCase());
                listCourses = new CoursesDAO().getCourseByCateIdandAuId(Integer.parseInt(cateId), AuthorId);
            } else {
                req.setAttribute("topicId", topicId);
                req.setAttribute("nameFilter", new TopicDAO().getTopicById(Integer.parseInt(topicId)).getTopicName().toUpperCase());
                listCourses = new CoursesDAO().getCourseByTopicIdandAuId(Integer.parseInt(topicId), AuthorId);
            }
            if (listCourses.isEmpty()) {
                req.setAttribute("emptyErr", "No result");
            } else {
                req.setAttribute("listCourses", listCourses);
            }
            int[] amountCourseIn1Page = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            req.setAttribute("sizeL", listCourses.size());
            Paging pag = new Paging();
            pag.setSize(listCourses.size());
            pag.setRange(Integer.toString(6), amountCourseIn1Page);
            pag.setPage(req.getParameter("page"));
            req.setAttribute("search", listCourses);
            req.setAttribute("page", pag.getPage());
            req.setAttribute("endP", pag.getEndPage());
            req.setAttribute("range", pag.getRange());
            listCourses = pag.getListPani(listCourses);
            req.setAttribute("listCourses", listCourses);
            String query = req.getQueryString() == null ? "" : req.getQueryString();
            String url = req.getRequestURL() + "?" + query;
            if (url.contains("page")) {
                url = url.substring(0, url.indexOf("page") - 1);
            }
            req.setAttribute("url", url);
        }
        req.getRequestDispatcher("author_profile.jsp").forward(req, resp);
    }
}

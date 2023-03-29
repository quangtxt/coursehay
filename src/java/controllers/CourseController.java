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
public class CourseController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = ((Account) req.getSession().getAttribute("AccSession"));
        if (acc == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            String txtCode = req.getParameter("txtCode");
            int courseId = Integer.parseInt(req.getParameter("courseId"));
            if (new OrderDAO().checkCodeAccount(acc.getAccountID(), txtCode)) {
                req.getSession().setAttribute("msgMess", "Enroll course successfully");
                new OrderDAO().updateStatus(true, acc.getAccountID(), courseId);
            } else {
                req.getSession().setAttribute("msgMess", "Enroll course fail! Please check the code again.");
            }
            resp.sendRedirect(req.getHeader("referer"));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList listCa = new CategoryDAO().getAllCategories();
        req.setAttribute("listCa", listCa);
        req.setAttribute("couDAO", new CoursesDAO());
        req.setAttribute("conDAO", new ContactDAO());
        req.setAttribute("topicDAO", new TopicDAO());
        ArrayList<Course> listCourses = new ArrayList<>();
        String cateId = req.getParameter("cateID");
        String topicId = req.getParameter("topicId");
        if (cateId == null && topicId == null) {
            listCourses = new CoursesDAO().getTopCoursesBuyer(10);
        } else if (cateId != null) {
            req.setAttribute("cateId", cateId);
            req.setAttribute("nameFilter", new CategoryDAO().getCategoryById(Integer.parseInt(cateId)).getCateName().toUpperCase());
            listCourses = new CoursesDAO().getCourseByCateId(Integer.parseInt(cateId));
        } else {
            req.setAttribute("topicId", topicId);
            req.setAttribute("nameFilter", new TopicDAO().getTopicById(Integer.parseInt(topicId)).getTopicName().toUpperCase());
            listCourses = new CoursesDAO().getCourseByTopicId(Integer.parseInt(topicId));
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
        req.getRequestDispatcher("courses.jsp").forward(req, resp);
    }
}

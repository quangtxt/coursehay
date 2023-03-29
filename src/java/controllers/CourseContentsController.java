/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.CourseContent;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.CourseContentDAO;
import models.LectureDAO;

/**
 *
 * @author admin
 */
public class CourseContentsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (acc == null) {
            resp.sendRedirect(req.getContextPath() + "/account");
        } else {
            boolean add = Boolean.parseBoolean(req.getParameter("add"));
            boolean update = Boolean.parseBoolean(req.getParameter("update"));
            if (add == true) {
                String contentname = req.getParameter("txtcontent-name");
                String contentdesc = req.getParameter("txtcontent-desc");
                if (contentname.equals("") || contentdesc.equals("")) {
                    req.getSession().setAttribute("msgMess", "Information is required!");
                } else {
                    String courseid = req.getParameter("id");
                    CourseContent content = new CourseContent();
                    content.setCourseId(Integer.parseInt(courseid));
                    content.setContentName(contentname);
                    content.setContentDescription(contentdesc);
                    new CourseContentDAO().createContent(content, Integer.parseInt(courseid));
                }
            }
            if (update == true) {
                String contentid = req.getParameter("contentid");
                String contentname = req.getParameter("content-name");
                String contentdesc = req.getParameter("content-desc");
                CourseContent content = new CourseContent();
                content.setContentName(contentname);
                content.setContentDescription(contentdesc);
                new CourseContentDAO().updateContent(content, Integer.parseInt(contentid));
            }
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (acc == null) {
            resp.sendRedirect(req.getContextPath() + "/account");
        } else {
            String status = req.getParameter("deletecontent");
            if (Boolean.parseBoolean(status)) {
                String lectureid = req.getParameter("lectureid");
                new LectureDAO().deleteLecture(Integer.parseInt(lectureid));
            } else {
                String courseid = req.getParameter("courseid");
                String contentid = req.getParameter("contentid");
                new CourseContentDAO().deleteContent(Integer.parseInt(courseid), Integer.parseInt(contentid));
            }
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}

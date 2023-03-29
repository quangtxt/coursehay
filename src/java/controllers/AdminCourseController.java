/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Course;
import dal.CourseContent;
import dal.Notification;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoryDAO;
import models.ContactDAO;
import models.CourseContentDAO;
import models.CoursesDAO;
import models.LectureDAO;
import models.NotificationDAO;
import models.TopicDAO;

/**
 *
 * @author ADMIN
 */
public class AdminCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRoleId() == 1 && "unapproved_course".equals(req.getParameter("pos"))) {
            ArrayList<Course> listcourses_notpublic = new CoursesDAO().getCoursesisSubmit();
            req.setAttribute("listcourses_notpublic", listcourses_notpublic);
            req.setAttribute("CoursesDAO", new CoursesDAO());
            req.setAttribute("ContactDAO", new ContactDAO());
            req.getRequestDispatcher("/admin-courses.jsp").forward(req, resp);
        } else if (((Account) req.getSession().getAttribute("AccSession")).getRoleId() == 1 && "unapproved_course_detail".equals(req.getParameter("pos"))) {
            String courseid = req.getParameter("courseid");
            Course courseDetail = new CoursesDAO().getCourseById(Integer.parseInt(courseid));
            ArrayList<CourseContent> contentlist = new CourseContentDAO().getListContentbyCourseId(Integer.parseInt(courseid));
            req.setAttribute("courseDetail", courseDetail);
            req.setAttribute("CategoryDAO", new CategoryDAO());
            req.setAttribute("TopicDAO", new TopicDAO());
            req.setAttribute("courseid", courseid);
            req.setAttribute("contentlist", contentlist);
            req.setAttribute("LectureDAO", new LectureDAO());
            req.getRequestDispatcher("/course_unapproved_detail.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/home").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseid = Integer.parseInt(req.getParameter("courseid"));
        String review = req.getParameter("Review");
        Course c ;
        if ("2".equals(req.getParameter("isPublic"))) {
            new CoursesDAO().EditPublic(2, courseid);
            req.getSession().setAttribute("msgMess", "The course has been public");
        } else if ("0".equals(req.getParameter("notPublic"))) {
            new CoursesDAO().EditPublic(0, courseid);
            req.getSession().setAttribute("msgMess", "The course is denied public");
        }
        c = new CoursesDAO().getCourseById(courseid);
        int authorid = new CoursesDAO().getCourseById(courseid).getAuthorId();
        Notification ni = new Notification(authorid, review, 1, 0, courseid);
        new NotificationDAO().createNotification(ni, c.getPublicStatus());
        
        ArrayList<Course> listcourses_notpublic = new CoursesDAO().getCoursesisSubmit();
        req.setAttribute("listcourses_notpublic", listcourses_notpublic);
        req.setAttribute("CoursesDAO", new CoursesDAO());
        req.setAttribute("ContactDAO", new ContactDAO());
        req.getRequestDispatcher("/admin-courses.jsp").forward(req, resp);
    }

}

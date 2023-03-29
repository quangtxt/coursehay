/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Course;
import dal.Feedback;
import dal.Order;
import dal.Reply;
import dal.Topic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import models.ContactDAO;
import models.CoursesDAO;
import models.FeedbackDAO;
import models.TopicDAO;
import models.OrderDAO;
import models.ReplyDAO;

/**
 *
 * @author msi
 */
public class CourseDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        int CourseId = Integer.parseInt(req.getParameter("CourseId"));
        int FeedbackId = Integer.parseInt(req.getParameter("FeedbackId"));
        String action = req.getParameter("action");
        if (action == null) {
            if (acc.getAccountID() == new CoursesDAO().getCourseById(CourseId).getAuthorId()) {
                String Reply = req.getParameter("txtReply");
                Reply rep = new Reply(FeedbackId, acc.getAccountID(), Reply, Date.valueOf(LocalDate.now()));
                new ReplyDAO().addReply(rep);
            } else {
                double Star = Double.parseDouble(req.getParameter("rating"));
                String Comment = req.getParameter("txtComment");
                Feedback f = new Feedback(CourseId, acc.getAccountID(), Star, Comment, Date.valueOf(LocalDate.now()));
                new FeedbackDAO().addFeedback(f);
            }
        } else {
            if (action.equals("edit")) {
                double Star = Double.parseDouble(req.getParameter("updaterating"));
                String Comment = req.getParameter("txtUpdateComment");
                Feedback f = new Feedback(FeedbackId, CourseId, acc.getAccountID(), Star, Comment, Date.valueOf(LocalDate.now()));
                new FeedbackDAO().updateFeedback(f);
            }
            if (action.equals("delete")) {
                new FeedbackDAO().deleteFeedback(FeedbackId);
            }
            if (action.equals("editreply")) {
                String Reply = req.getParameter("txtUpdateReply");
                Reply r = new Reply(FeedbackId, CourseId, Reply, Date.valueOf(LocalDate.now()));
                new ReplyDAO().updateRely(r);
            }
            if (action.equals("deletereply")) {
                new ReplyDAO().deleteReply(FeedbackId);
            }
        }
        resp.sendRedirect(req.getHeader("referer"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int CourseId = Integer.parseInt(req.getParameter("CourseId"));
        String star = req.getParameter("star");
        double avg = new FeedbackDAO().SumStarbyCourseId(CourseId) / new FeedbackDAO().CountStarbyCourseId(CourseId);
        double rate = (double) Math.round(avg * 10) / 10;
        new CoursesDAO().UpdateRate(rate, CourseId);
        Course cou = new CoursesDAO().getCourseById(CourseId);
        Topic t = new TopicDAO().getTopicById(cou.getCourseId());
        if (req.getSession().getAttribute("AccSession") != null) {
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            req.setAttribute("id", acc.getAccountID());
            Order uc = new OrderDAO().CourseBuyByAcc(acc.getAccountID(), CourseId);
            Feedback f = new FeedbackDAO().getFeebackbyID(CourseId, acc.getAccountID());
            req.setAttribute("uc", uc);
            req.setAttribute("f", f);
            if (acc.getAccountID() == new CoursesDAO().getCourseById(CourseId).getAuthorId()) {
                req.setAttribute("checkAuthor", true);
            }
            if (f != null) {
                req.setAttribute("checkFeedbacked", true);
            }
        }
        ArrayList<Feedback> listF = new ArrayList<>();
        if (star == null && req.getSession().getAttribute("AccSession") == null) {
            listF = new FeedbackDAO().getFeedbackbyCourseId(CourseId);
        } else if (star != null && req.getSession().getAttribute("AccSession") == null) {
            listF = new FeedbackDAO().getFeedbackbyCourseIdandStar(CourseId, Double.parseDouble(star));
        } else if (star == null && req.getSession().getAttribute("AccSession") != null) {
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            listF = new FeedbackDAO().getFeedbackbyCourseIdandAccountId(CourseId, acc.getAccountID());
        } else if (star != null && req.getSession().getAttribute("AccSession") != null) {
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            listF = new FeedbackDAO().getFeedbackbyCourseIdandAccountIdandStar(CourseId, acc.getAccountID(), Double.parseDouble(star));
        }
        if (listF.isEmpty()) {
            req.setAttribute("msg", "There is no feedback.");
        } else {
            req.setAttribute("listF", listF);
        }
        ArrayList<Course> listCourseOfAuthor = new CoursesDAO().getCoursesByNonCourseIDandAccountID(CourseId, new CoursesDAO().getCourseById(CourseId).getAuthorId());
        if (listCourseOfAuthor.isEmpty()) {
            req.setAttribute("msg1", "There are no other courses available.");
        } else {
            req.setAttribute("listCourseOfAuthor", listCourseOfAuthor);
        }
        ArrayList<Course> listCourseTopic = new CoursesDAO().getCourseByNonCourseIdandTopicId(CourseId, t.getTopicId());
        if (listCourseTopic.isEmpty()) {
            req.setAttribute("msg2", "There are no related courses.");
        } else {
            req.setAttribute("listCourseTopic", listCourseTopic);
        }
        req.setAttribute("cou", cou);
        req.setAttribute("t", t);
        req.setAttribute("couDAO", new CoursesDAO());
        req.setAttribute("conDAO", new ContactDAO());
        req.getRequestDispatcher("./course_detail.jsp").forward(req, resp);
    }
}
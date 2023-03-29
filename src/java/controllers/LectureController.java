/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Lecture;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.LectureDAO;

/**
 *
 * @author admin
 */
public class LectureController extends HttpServlet {

    boolean addLecture;
    boolean removeLecture;
    private static int lecs = 1;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (acc == null) {
            resp.sendRedirect(req.getContextPath() + "/account");
        } else {
            int lectures = Integer.parseInt(req.getParameter("lecture"));
            for (int i = 1; i <= lectures; i++) {
                String lecturename = req.getParameter("lecture-name" + i);
                String lecturetype = req.getParameter("lecture-type" + i);
                req.setAttribute("lecturename", lecturename);
                req.setAttribute("lecturetype", lecturetype);
                Lecture lecture = new Lecture();
                lecture.setLectureName(lecturename);
                lecture.setLectureType(lecturetype);
                new LectureDAO().createLecture(lecture);
            }
            req.setAttribute("lecs", lectures);
            String url = (String) req.getSession().getAttribute("url");
            req.getSession().removeAttribute("url");
            resp.sendRedirect(url);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (acc == null) {
            resp.sendRedirect(req.getContextPath() + "/account");
        } else {
            addLecture = Boolean.parseBoolean(req.getParameter("addLecture"));
            removeLecture = Boolean.parseBoolean(req.getParameter("removeLecture"));
            if (!addLecture && !removeLecture) {
                req.getSession().setAttribute("url", req.getHeader("referer"));
            }
            if (addLecture) {
                lecs += 1;
            }
            if (removeLecture) {
                if (lecs > 1) {
                    lecs -= 1;
                }
            }
            req.setAttribute("lecs", lecs);
            req.getRequestDispatcher("./lecture.jsp").forward(req, resp);
        }
    }

}

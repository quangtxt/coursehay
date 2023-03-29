/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Category;
import dal.Course;
import dal.DomParserMessage;
import dal.Topic;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.mail.Part;
import models.*;

/**
 *
 * @author admin
 */
public class CreateCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Category> listCate = new CategoryDAO().getAllCategories();
        req.setAttribute("listCate", listCate);
        req.getRequestDispatcher("createcourse.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DomParserMessage Me = new DomParserMessage();
        String name = req.getParameter("course-name");
        String title = req.getParameter("course-title");
        String description = req.getParameter("course-desc");
        String category = req.getParameter("course-cate");
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        req.setAttribute("name", name);
        req.setAttribute("title", title);
        req.setAttribute("description", description);
        req.setAttribute("category", category);
        RequestDispatcher rd = req.getRequestDispatcher("/create-course1");
        rd.forward(requestWrapper, resp);
    }

}

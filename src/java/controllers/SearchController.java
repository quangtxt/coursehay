/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Course;
import dal.Paging;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import models.ContactDAO;
import models.CoursesDAO;

/**
 *
 * @author DELL
 */
public class SearchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("q");
        ArrayList<Course> listSearch;
        if ((search == null || search.equals("")) ) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        } 
        req.setAttribute("q",search);
        listSearch = new CoursesDAO().Search(search.toLowerCase());
        int[] amountCourseIn1Page = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        req.setAttribute("amountCourseIn1Page", amountCourseIn1Page);
        String str;
        try {
            str = req.getParameter("sort");
            if (str.isEmpty()) {
                str = "most-buyer";
            }
        } catch (Exception e) {
            str = "most-buyer";
        }
        listSearch = new SearchController().sortByStr(str, listSearch);
        req.setAttribute("sizeL", listSearch.size());
        Paging pag = new Paging();
        pag.setSize(listSearch.size());
        pag.setRange(req.getParameter("a"), amountCourseIn1Page);
        pag.setPage(req.getParameter("page"));
        req.setAttribute("search", search);
        req.setAttribute("page", pag.getPage());
        req.setAttribute("endP", pag.getEndPage());
        req.setAttribute("range", pag.getRange());
        listSearch = pag.getListPani(listSearch);
        req.setAttribute("listSearch", listSearch);
        req.setAttribute("str", str);
        String url = req.getRequestURL() + "?" + req.getQueryString();
        if (url.contains("page")) {
            url = url.substring(0, url.indexOf("page") - 1);
        }
        req.setAttribute("url", url);
        req.setAttribute("conDAO", new ContactDAO());
        req.getRequestDispatcher("./search.jsp").forward(req, resp);
    }

    private ArrayList<Course> sortByStr(String str, ArrayList<Course> listSearch) {
        CoursesDAO couDAO = new CoursesDAO();
        switch (str) {
            case "highest-rated":
                Collections.sort(listSearch, (Course lhs, Course rhs) -> lhs.getRate() > rhs.getRate() ? -1 : lhs.getRate() < rhs.getRate() ? 1 : 0);
                break;
            case "newest":
                Collections.sort(listSearch, (Course lhs, Course rhs) -> lhs.getPublicDate().after(rhs.getPublicDate()) ? -1 : lhs.getPublicDate().before(rhs.getPublicDate()) ? 1 : 0);
                break;
            default:
                Collections.sort(listSearch, (Course lhs, Course rhs) -> couDAO.CountUserBuyCourse(lhs.getCourseId()) > couDAO.CountUserBuyCourse(lhs.getCourseId()) ? -1 : (couDAO.CountUserBuyCourse(lhs.getCourseId()) < couDAO.CountUserBuyCourse(rhs.getCourseId())) ? 1 : 0);
                break;
        }
        return listSearch;
    }
}

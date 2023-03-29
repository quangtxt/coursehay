<%-- 
    Document   : editblog1.jsp
    Created on : Mar 9, 2023, 2:44:09 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <% String path = request.getContextPath();%>
        <title>Edit a Blog</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=path%>/css/style.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <style>
            .create-course-container {
                width: 80%;
                margin: 0 auto;
            }

            .create-course-header {
                text-align: center;
                font-size: 36px;
                margin-top: 50px;
            }

            .create-course-form {
                margin-top: 50px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                font-size: 18px;
                display: block;
                margin-bottom: 10px;
            }

            input[type="text"], textarea {
                width: 100%;
                padding: 10px;
                font-size: 18px;
                border-radius: 5px;
                border: 1px solid gray;
            }

            textarea {
                height: 200px;
            }


            .form-group input, textArea, label, select{

                text-align: left;
            }
        </style>
    </head>
    <body>
        


<%
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        int blogID = Integer.parseInt(request.getParameter("BlogID"));
        ArrayList<BlogPart> BlogPartList = new BlogDAO().getBlogPart(blogID);
        dal.Blog blog = new BlogDAO().getBlogBySql("Select * from Blog where BlogID = " + blogID);
        ArrayList<Category> allItem = new CategoryDAO().getAllCategories();         
        request.setAttribute("Blog", blog);
        request.setAttribute("CoursesDAO",new CoursesDAO());
        request.setAttribute("success", success);
        request.setAttribute("listCate", allItem);
        request.setAttribute("BlogParts", BlogPartList);
%>
<div class="create-course-container">
    <div class="create-course-header">
        Edit Blog
    </div>
    <c:if test="${success == true}">
        <div id="alert" class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Edit Successfully !!!</strong> 
        </div>
    </c:if>
    <c:if test="${fail ne null}">
        <div id="alert" class="alert alert-danger alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Failed!</strong> ${fail}.
        </div>
    </c:if>
    <form action="manageblog?edit=true" method="post" >
        <div class="form-group">
            <label for="blog-cate">Blog Category <br>
                <br>
                <select id="blog-cate" name="BlogCategory">
                    <c:forEach items="${listCate}" var="c">
                        
                        <c:choose>
                                                    <c:when test="${c.getCateId() == Blog.getCategoryID()}">
                                                        <option selected value="${c.getCateId()}">${c.getCateName()}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${c.getCateId()}">${c.getCateName()}</option>
                                                    </c:otherwise>
                                                </c:choose>
                    </c:forEach>
                </select></label>

        </div>
        <input type="text" name="BlogID" value="${Blog.getBlogID()}" hidden>
        <div class="form-group">
            <label>Blog Detail</label>
            
        </div>
        <div class="form-group">
            <label for="blog-title">Blog Title</label>
            <br>
            <textarea  id="blog-title" name="BlogTitle" required>${Blog.getBlogTitle()}</textarea>
        </div>
        <div class="form-group">
            <label for="blog-desc">Blog Description</label>
            <br>
            <textarea id="blog-desc" name="BlogDescription" required>${Blog.getBlogDesciption()}</textarea>
        </div>
        <div class="form-group">
            <label for="blog-image">Blog Image</label>
            <br>
              <img class="img-fluid" src="data:image/jpg;base64,${CoursesDAO.createImgurl(Blog.getImage())}" alt="">
            <br>
            
        </div>

              <c:forEach items="${BlogParts}" var="bp">       
            <div class="form-group">
                <label for="blog-desc">Part ${bp.getPart()}</label>
                <br>
                <label for="blog-part-header">Part ${pb.getPart()} Header</label>
                <br>
                <textarea  id="blog-part-header" name="part-${bp.getPart()}-header">${bp.getPartHeader()}</textarea>
                <br>
                <label for="blog-part-content">Part ${bp.getPart()} Content</label>
                <br>
                <textarea id="blog-part-content" name="part-${bp.getPart()}-content">${bp.getPartContent()}</textarea>
                <br>
                <label for="blog-part-image">Part ${bp.getPart()} Image</label>
                <br>
                  <img  src="data:image/jpg;base64,${CoursesDAO.createImgurl(bp.getImage())}" alt="">   
                <br>
            </div>
        </c:forEach>  

        
              <label for="status"> Set Blog Private           <input type="radio" id="status" name="status" value="0" <c:if test="${Blog.getBlogStatus()==0}"> checked</c:if>></label><br>
        
                  <label for="status"> Send request to admin for approval           <input type="radio" id="status" name="status" value="1" <c:if test="${Blog.getBlogStatus()==1}"> checked</c:if>></label><br>
        

        
           <input style="background-color: blue;
           text-decoration: none;
           color: white;
           padding: 11px 20px;
           border-radius: 5px;
           cursor: pointer;
           margin-top: 20px;" type="submit" value="Save changes">
        

        <a href="manageblog?view=true&role=user" style="background-color: red;
           text-decoration: none;
           color: white;
           padding: 11px 20px;
           border-radius: 5px;
           cursor: pointer;
           margin-top: 20px;"> Cancel </a>
           
    </form>
</div>
</body>
<script>
    var alert = document.querySelector("#alert");
    if (alert !== null) {
        setTimeout(function () {
            alert.style.display = "none";
        }, 3000);
    }

</script>
</html>


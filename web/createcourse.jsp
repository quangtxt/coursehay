<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <% String path = request.getContextPath();%>
        <title>Create a New Course</title>
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

            input[type="submit"] {
                background-color: blue;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="create-course-container">
            <div class="create-course-header">
                Create a New Course
            </div>
            
            <form class="create-course-form" action="create-course" method="post">
                <div class="form-group">
                    <label for="course-title">Course Name</label>
                    <input type="text" id="course-name" name="course-name">
                </div>
                <div class="form-group">
                    <label for="course-title">Course Title</label>
                    <input type="text" id="course-title" name="course-title">
                </div>
                <div class="form-group">
                    <label for="course-desc">Course Description</label>
                    <textarea id="course-desc" name="course-desc"></textarea>
                </div>
                <div class="form-group">
                    <label for="course-cate">Category</label>
                    <select id="course-cate" name="course-cate">
                        <c:forEach items="${listCate}" var="c">
                            <option value="${c.getCateId()}">${c.getCateName()}</option>
                        </c:forEach>
                    </select>
                </div>

                <input type="submit" value="Next Step">
            </form>
        </div>
    </body>
</html>

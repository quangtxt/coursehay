<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <% String path = request.getContextPath();
            
        %>
        <title>Edit Course</title>
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
        <%
            
        %>
    </head>
    <body>
        <div class="create-course-container">
            <div class="create-course-header">
                Edit Course
            </div>
            <form class="edit-ourse-form" action="<%=path%>/account/author-edit" method="post">

                <div class="form-group">
                    <label for="course-title">Course Name</label>
                    <input type="text" id="course-name" name="course-name" value="${courses.getCourseName()}">
                </div>
                <div class="form-group">
                    <label for="course-title">Course Title</label>
                    <input type="text" id="course-title" name="course-title" value="${courses.getCourseTitle()}">
                </div>
                <div class="form-group">
                    <label for="course-desc">Course Description</label>
                    <textarea id="course-desc" name="course-desc" >${courses.getCourseDescription()}</textarea>
                </div>
                <div class="form-group">
                    <label for="course-cate">Category</label>
                    <select id="course-cate" name="course-cate">
                        <c:forEach items="${categories}" var="c">
                            <option value="${c.getCateId()}"<c:if test="${CateDao.getCategoryByTopicid(courses.getTopicId()).getCateId() == c.getCateId()}">selected</c:if>>${c.getCateName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <input style="float: right;
                       margin-bottom: 20px;"  type="submit"  value="Next Step" >
                <input type="hidden" name="coursesid" value="${courses.getCourseId()}">
                <c:if test="${msg != null}">
                    <span class="msg-error">${msg}<br/></span>
                    </c:if>
            </form>
        </div>
    </body>
</html>


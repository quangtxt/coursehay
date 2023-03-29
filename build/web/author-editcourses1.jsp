<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"> 
        <title>Edit Course</title>
        <% String path = request.getContextPath();
            
        %>
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

            #course-price {
                width: 80px;
                height: 20px;
            }
        </style>
    </head>
    <body>
        <div class="create-course-container">
            <div class="create-course-header">
                Edit Course
            </div>
            <form class="create-course-form" action="<%=path%>/account/author-edit1" method="post">
                <input type="hidden" id="course-name" name="name" value="${name}">
                <input type="hidden" id="course-title" name="title" value="${title}">
                <textarea id="course-desc" name="description" hidden="">${desc}</textarea>
                <input type="hidden"  name="coursesid" value="${coursesid}">
                <div class="form-group">
                    <label for="course-topic">Course Topic</label>
                    <select id="course-topic" name="topic">
                        <c:forEach items="${listTopic}" var="t">
                            <option value="${t.getTopicId()}"<c:if test="${t.getTopicId() == courses.getTopicId()}">selected</c:if>>${t.getTopicName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="course-price">Price</label>
                    <input type="number" id="course-price" name="price" value="${courses.getPrice()}">
                </div>
                <label><input type="checkbox" name="isSubmit" value="1"  />Submit course for Public </label>
                <input type="submit" value="Submit">
                <c:if test="${msg != null}">
                    <span style="color: green" class="">${msg}<br/></span>
                </c:if>
            </form>
        </div>
    </body>
</html>

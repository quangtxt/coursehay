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
        <title>Course_Unapproved_Detail</title>
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
                Course Details
            </div>

            <div class="form-group">
                <label for="course-title">Course Name</label>
                <input type="text" id="course-name" name="course-name" value="${courseDetail.getCourseName()}" readonly="">
            </div>
            <div class="form-group">
                <label for="course-title">Course Title</label>
                <input type="text"  id="course-title" name="course-title" value="${courseDetail.getCourseTitle()}" readonly="">
            </div>
            <div class="form-group">
                <label for="course-desc">Course Description</label>
                <textarea id="course-desc" name="course-desc" readonly="" >${courseDetail.getCourseDescription()}</textarea>
            </div>
            <div class="form-group">
                <label for="course-cate">Category: ${CategoryDAO.getCategoryByTopicid(courseDetail.getTopicId()).getCateName()}</label>
            </div>
            <div class="form-group">
                <label for="course-topic">Topic: ${TopicDAO.getTopicById(courseDetail.getTopicId()).getTopicName()}</label>

            </div>
            <div class="form-group">
                <label for="course-price">Price: ${courseDetail.getPrice()} VNÐ</label>
            </div>
            <div class="form-group" style="border:  1px solid black">
                <h1> Content and Lecture </h1>
                <c:if test="${contentlist.isEmpty()}">
                    <h3>Don't have any Content or Lecture </h3> 
                </c:if>
                <c:forEach items="${contentlist}" var="c">
                    <button style=margin-bottom: 10px" onclick="displayLuctureName(${c.getContentId()})">${c.getContentName()}</button>
                    <br/>
                    <br/>
                    <div style="display: none " id="Le${c.getContentId()}"> 
                        <c:forEach items="${LectureDAO.getListLeturebyContentId(c.getContentId())}" var="l">
                            <label>${l.getLectureName()}</label>
                        </c:forEach>
                    </div>
                </c:forEach>

            </div>
            <form action="<%=path%>/admin/courses" method="post" onsubmit="return validateForm()">
                <div class="form-group">
                    <label>Review and evaluation</label>
                    <textarea  name="Review"  ></textarea>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name="isPublic" value="2"  />Public this course</label>
                    <label><input type="checkbox" name="notPublic" value="0"  />Reject Public this course</label>
                    <input style="float: right;
                           margin-bottom: 20px;"  type="submit"  value="Submit" >
                    <input type="hidden" name="courseid" value="${courseid}">
                    </form>

                    <a href="<%=path%>/admin/courses?pos=unapproved_course">Back</a>

                </div>





                <%@include file="template/footerAdmin.jsp" %>

                <script>
                    function validateForm() {
                        var checkbox1 = document.getElementsByName('isPublic')[0];
                        var checkbox2 = document.getElementsByName('notPublic')[0];

                        if (checkbox1.checked === false && checkbox2.checked === false) {
                            alert('You must check one of them!');
                            return false;
                        } else if (checkbox1.checked === true && checkbox2.checked === true) {
                            alert('you can not check both of them!');
                            return false;
                        }
                    }
                    function displayLuctureName(id) {
                        if ($('#Le' + id).css("display") === "none") {
                            $('#Le' + id).css({'display': 'block'});
                        } else {
                            $('#Le' + id).css({'display': 'none'});
                        }
                    }
                </script>


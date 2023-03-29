<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <% String path = request.getContextPath();%>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
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
        <c:if test="${role != 1}">
            <c:redirect url="index.jsp"/>
        </c:if>
        <div id="container">
            <div id="header">
                <div style="color: blue" id="logo-admin">
                    Ecommerce Admin
                </div>
                <div id="banner-admin">
                    <ul>

                        <li ><a href="<%=request.getContextPath()%>/account" style="margin: 0 5px" ><button style="padding: 10px; font-size: 20px"><i class="fa fa-sign-out"></i></button></a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="content-left">
                    <ul>
                        <li><a href="<%=path%>/manage-purchaser">User Account</a>  </li>                     
                        <li><a href="<%=path%>/admin/courses?pos=unapproved_course">Courses are publicly requested </a></li>
                        <li><a href="<%=path%>/manageblog?view=true&role=admin">Manage Blog</a></li>
                    </ul>
                </div>
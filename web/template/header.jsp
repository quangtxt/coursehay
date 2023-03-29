<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page import="dal.*" %>
<%@page import="models.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>CourseHay</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">
        <% String path = request.getContextPath();%>
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600;700&family=Open+Sans:wght@400;600&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="<%=path%>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="<%=path%>/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="lib/jquery/dist/jquery-1.11.3.min.js"></script>
        <!-- Customized Bootstrap Stylesheet -->
        <link href="<%=path%>/css/style.css" rel="stylesheet">
        <link href="<%=path%>/css/jumbotron-narrow.css" rel="stylesheet">
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid bg-dark">
            <div class="row py-2 px-lg-5">
                <div class="col-lg-6 text-center text-lg-left mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center text-white">
                        <small><i class="fa fa-phone-alt mr-2"></i>0866405629</small>
                        <small class="px-3">|</small>
                        <small><i class="fa fa-envelope mr-2"></i>coursehay@example.com</small>
                    </div>
                </div>
                <div class="col-lg-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-white px-2" href="https://www.facebook.com/CodegangZ" target="_blank">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a class="text-white px-2" href="https://www.instagram.com/codegangz/" target="_blank">
                            <i class="fab fa-instagram"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->



        <!-- Navbar Start -->
        <div class="container-fluid p-0">
            <nav class="navbar navbar-expand-lg bg-white navbar-light py-3 py-lg-0 px-lg-3">
                <a href="<%=path%>/home" class="navbar-brand ml-lg-3">
                    <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-book-reader mr-3"></i>CoursesHay</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between px-lg-3" id="navbarCollapse">
                    <div class="navbar-nav" style="margin: 0 10px">
                        <a href="<%=path%>/course" class="nav-item nav-link">Courses</a>
                    </div>
                    <div class="navbar-nav" style="margin: 0 10px">
                        <form action="<%=path%>/search">

                            <input style="width: 250px;text-align: left;" type="text" name="q" value="${q}" placeholder="Search for anything">
                            <input type="submit" value="Search">
                        </form>
                    </div>
                    <c:if test="${AccSession!=null}">

                    </c:if>
                    <c:choose>
                        <c:when test="${AccSession == null}">
                            <c:if test="${cart==null}">
                                <a style="margin: 0 5px" href="<%=path%>/cart">Cart:0</a>
                            </c:if>
                            <c:if test="${cart!=null}">
                                <a style="margin: 0 5px" href="<%=path%>/cart">Cart: ${cart.size()}</a>
                            </c:if>
                            <a href="<%=path%>/account" style="margin: 0 5px" class="btn btn-primary py-2 px-4 d-none d-lg-block">SignIn</a>
                        </c:when>
                        <c:otherwise>
                            <a style="margin: 0 5px" href="<%=path%>/account/author">Instructor</a>
                            <a style="margin: 0 5px" href="<%=path%>/cart">Cart: ${cartDAO.AmountCourseOfAcc(AccSession.getAccountID())}</a>
                            <a style="margin: 0 5px" href="<%=path%>/coursemanager?ac=mylearning">My Learning</a>
                            <a style="margin: 0 5px" href="<%=path%>/account/followedlist">Followed</a>

                            <div class="wishlist">
                                <span><a href="<%=path%>/coursemanager?ac=wishlist"><i style="font-size:24px" class="fa">&#xf004;</i></a></span>

                                <c:if test="${wishlistSession.size() == 0}">
                                    <div class="wishlist-content" style="height: 150px">
                                        <p style="color: gray">Your wishlist is empty.</p>
                                        <a href="<%=path%>/course" class="button" style="display: block;
                                           width: 80%;
                                           height: 55px;
                                           background: #4E9CAF;
                                           padding: 10px;
                                           text-align: center;
                                           border-radius: 5px;
                                           color: white;
                                           font-weight: bold;
                                           line-height: 25px;">Explore courses</a>
                                    </div>
                                </c:if>
                                <c:if test="${wishlistSession.size() > 0}">
                                    <div class="wishlist-content" style=" ">
                                        <c:forEach items="${wishlistSession}" var="w">
                                            <div class="wishlist-topic">
                                                <div class="wishlist-content-item">
                                                    <div class="wishlist-content-left">
                                                        <img src="data:image/jpg;base64,${couDAO.createImgurl(w.getImg())}" />
                                                    </div>
                                                    <div class="wishlist-content-right">
                                                        <p style=" width: 100%;color: black; font-size:14px; font-weight: 700;text-overflow: ellipsis;
                                                           overflow: hidden;
                                                           width: 160px;
                                                           height: 1.2em;
                                                           white-space: nowrap;text-align: left ">  ${w.getCourseName()}</p>
                                                        <p style="font-size:11px; font-weight: 700;text-overflow: ellipsis;
                                                           overflow: hidden;
                                                           width: 160px;
                                                           height: 1.2em;
                                                           white-space: nowrap;text-align: left">  ${conDAO.getAccContactById(w.getAuthorId()).getFullName()}</p>
                                                        <p style="color: black; font-size:14px; font-weight: 700;text-overflow: ellipsis;
                                                           overflow: hidden;
                                                           width: 160px;
                                                           height: 1.2em;
                                                           white-space: nowrap;text-align: left ">  $${w.getPrice()}</p>
                                                    </div>     
                                                </div>   
                                                <a class="btn btn-block btn-secondary py-3 px-5" href="<%=path%>/cart?action=add&courseID=${w.getCourseId()}&from=wishlish">Add To Cart</a>
                                            </div>
                                        </c:forEach>
                                        <div class="wishlist-topic1">
                                            <a href="<%=path%>/coursemanager?ac=wishlist" target="_blank">Go to Wishlist</a>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <a href="<%=path%>/notifications?role=instructor" style="font-size:24px;margin: 10px" class="fa fa-bell badge-wrapper" >
                                <c:if test="${NotiDAO.CountNotiNotSeen(AccSession.getAccountID()) != 0}">
                                    <span  class='badge badge-secondary' >
                                        ${NotiDAO.CountNotiNotSeen(AccSession.getAccountID())}
                                    </span>
                                </c:if>
                            </a>
                            <a href="<%=path%>/account/profile" style="margin: 0 5px" class="btn btn-primary py-2 px-4 d-none d-lg-block">${username}</a>
                            <a href="<%=path%>/account" style="margin: 0 5px" class="btn btn-primary py-2 px-3 d-none d-lg-block"><i class="fas fa-sign-out-alt"></i></a>
                            </c:otherwise>
                        </c:choose>
                </div>
            </nav>
        </div>
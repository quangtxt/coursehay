<%@include file="template/header.jsp" %>

<div class="wishlist-container">
    <div class="wishlist-title" style="background-color: black; color: white; width: 100%; height: 200px">
        <h1 style="color: white; text-align: left; margin: 0 20%; padding: 20px">My Learning</h1>
        <ul style="list-style-type: none;
            padding: 0;
            overflow: hidden;
            background-color: black;margin: 2px 20%;">
            <li style="float: left;"><a style="display: block;
                                        color: white;
                                        text-align: center;
                                        padding: 16px;
                                        text-decoration: none;" href="<%=path%>/coursemanager?ac=mylearning">All Courses</a>
            </li> 
            <li style="float: left;"><a style="display: block;
                                        color: white;
                                        text-align: center;
                                        padding: 16px;
                                        text-decoration: none;" href="<%=path%>/coursemanager?ac=wishlist">Wishlist</a></li>
        </ul>
    </div>
    <div class="wishlist-title1" style="margin-top: 10px">
        <c:if test="${ac eq 'mylearning'}">
            <div class="row" >
                <c:forEach items="${couDAO.getEnrolledCourseByAccountID(AccSession.getAccountID())}" var="w">
                    <c:set var="c" value="${couDAO.getCourseById(w)}"/>
                    <div class="col-lg-4 col-md-6 pb-4">
                        <a class="courses-list-item position-relative d-block overflow-hidden mb-2" href="<%=path%>/coursedetail?CourseId=${c.getCourseId()}">
                            <img class="img-fluid" src="data:image/jpg;base64,${couDAO.createImgurl(c.getImg())}" alt="">
                            <div class="courses-text">
                                <h4 class="text-center text-white px-3">${c.getCourseName()}</h4>
                                <div class="border-top w-100 mt-3">
                                    <div class="d-flex justify-content-between p-4">
                                        <span class="text-white"><i class="fa fa-user mr-2"></i>
                                            ${conDAO.getAccContactById(c.getAuthorId()).getFullName()}
                                        </span>
                                    </div>    
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${ac eq 'wishlist'}">
            <div class="row" >
                <c:if test="${wishlistSession.size() ==0}">
                    <a href="<%=path%>/course" class="button" style="display: block;
                       width: 40%;
                       height: 55px;
                       margin-top: 20px;
                       background: #000;
                       padding: 10px;
                       text-align: center;
                       border-radius: 5px;
                       color: white;
                       font-weight: bold;
                       line-height: 25px;">Browse courses now</a>
                </c:if>
                <c:if test="${wishlistSession.size() >=0}">
                    <c:forEach items="${wishlistSession}" var="w">
                        <div class="col-lg-4 col-md-6 pb-4">
                            <a class="courses-list-item position-relative d-block overflow-hidden mb-2" href="<%=path%>/coursedetail?CourseId=${w.getCourseId()}">
                                <img class="img-fluid" src="data:image/jpg;base64,${couDAO.createImgurl(w.getImg())}" alt="">
                                <div class="courses-text">
                                    <h4 class="text-center text-white px-3">${w.getCourseName()}</h4>
                                    <div class="border-top w-100 mt-3">
                                        <div class="d-flex justify-content-between p-4">
                                            <span class="text-white"><i class="fa fa-user mr-2"></i>
                                                ${conDAO.getAccContactById(w.getAuthorId()).getFullName()}
                                            </span>
                                            <div>
                                                <c:if test="${couDAO.checkExistInWishlist(AccSession.getAccountID(),w.getCourseId()) > 0}">
                                                    <form action="<%=path%>/coursemanager?ac=wishlist" method="post" >  
                                                        <div class="input-icons">
                                                            <input type="hidden" name="accid" value="${AccSession.getAccountID()}"/>
                                                            <input type="hidden" name="courseid" value="${w.getCourseId()}"/>
                                                            <input type="hidden" name="action" value="remove"/>
                                                            <input type="hidden" name="href" value="wishlist"/>
                                                            <i class="fa fa-heart" style="font-size:48px; color: pink">
                                                            </i>
                                                            <input class="input-field" type="submit">
                                                        </div>
                                                    </form>
                                                </c:if>
                                                <c:if test="${couDAO.checkExistInWishlist( AccSession.getAccountID(),w.getCourseId() ) == 0}">
                                                    <div class="input-icons">
                                                        <i class="fa fa-heart" style="font-size:48px; color: white">
                                                        </i>
                                                        <input class="input-field" type="submit">
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <span class="text-white">
                                            Price: $${w.getPrice()}
                                        </span>    
                                    </div>
                                </div>
                            </a>
                            <a class="btn btn-block btn-secondary py-3 px-5" href="<%=path%>/cart?action=add&courseID=${w.getCourseId()}&from=wishlish">Add To Cart</a>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </c:if>

    </div>
</div>
<%@include file="template/footer.jsp" %>
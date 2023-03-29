<%@include file="template/header.jsp" %>
<div class="wishlist-container">
    <div class="wishlist-title" style="background-color: black; color: white; width: 100%; height: 200px;text-align: left">
        <a href="<%=path%>/blogview" style="font-size: 50px;text-decoration: none; color: white; text-align: left; margin: 0 20%; padding: 20px">Our Blog</a>
        <br>
        <br>
        <ul style="list-style-type: none;
            padding: 0;
            overflow: hidden;
            background-color: black;margin: 2px 10%;">
            <c:forEach var="c" items="${listc}">
                <li style="float: left;">
                    <a style="display: block;
                       color: white;
                       text-align: center;
                       padding: 16px;
                       text-decoration: none;" href="<%=path%>/blogview?cateid=${c.getCateId()}">${c.getCateName()}</a></li>
                </c:forEach>
        </ul>
    </div>
    <br>
    <div class="wishlist-title1" style="margin-top: 10px">
        <div >
            <h1 style="text-align: left">
                ${cate.getCateName()} Articles
            </h1>
        </div>
        <br>
        <div class="row" >
            <c:forEach var="bl" items="${blogDao}">

                <div class="col-lg-4 col-md-6 pb-4">
                    <a class="courses-list-item position-relative d-block overflow-hidden mb-2" href="<%=path%>/blogview?blogid=${bl.getBlogID()}">
                        <img class="img-fluid" src="data:image/jpg;base64,${CoursesDAO.createImgurl(bl.getImage())}" alt="">
                        <div class="courses-text">
                            <h4 class="text-center text-white px-3">${bl.getBlogTitle()}</h4>
                            <div class="border-top w-100 mt-3">
                                <div class="d-flex justify-content-between p-4">
                                    <span class="text-white"><i class="fa fa-user mr-2"></i>
                                        <c:if test="${AccountDAO.getAccountById(bl.getAccountId()).getRoleId() == 1}">
                                                COURSESHAY Team
                                            </c:if>
                                            <c:if test="${AccountDAO.getAccountById(bl.getAccountId()).getRoleId() == 2}">
                                                ${ContactDAO.getAccContactById(bl.getAccountId()).getFullName()}
                                            </c:if>
                                    </span>

                                </div>
                                <span class="text-white">

                                </span>    
                            </div>
                        </div>
                    </a>
                </div> 

            </c:forEach>
        </div>
        
    </div>
</div>
<%@include file="template/footer.jsp" %>
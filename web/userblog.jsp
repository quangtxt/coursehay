<%@include file="template/header.jsp" %>
<%
    int accid =((Account) request.getSession().getAttribute("AccSession")).getAccountID();
    request.setAttribute("listbluser", new BlogDAO().getAllBlogBySql("SELECT bl.BlogId, bl.BlogTitle, bl.BlogDescription, bl.Image, bl.CategoryId, bl.DateCreated, bl.BlogStatus, a.AccountId FROM swp_dtb.blog as bl , swp_dtb.accounts as a where bl.AccountId = a.AccountId and a.AccountId =" +accid)); 
%>
<!-- About Start -->
<div style="height: 100%">
    <div id="content">
        <div id="content-left" style="width: 20%; margin-right: 5px;">
            <h1 style="text-align: center; border-bottom: 1px solid #2878EB; padding: 10px 15px; ">Author Detail</h1>
            <ul>
                <a href="<%=path%>/account/author" style=" "><li style="padding: 30px 0px ">Courses</li></a>
                <a href="<%=path%>/manageblog?view=true&role=user"><li style="padding: 30px 0px ">Blog</li></a>
                <a href="#"><li style="padding: 30px 0px ">Performance</li></a>
            </ul>
        </div>
        <div id="content-right">
            <div class="path" style=";
                 background-color: #f5f5f5;
                 color: black;
                 font-size: 30px;
                 margin: 35px 60px;
                 text-align: left;
                 ">Blog
            </div>
            <c:if test="${CoursesDAO.CheckUserHasCourseIsPublic(AccSession.getAccountID()) != 0}">
                <div style="text-align: center
                     ; margin-right: 20px; margin-bottom: 20px"> <a style="background-color: #1c87c9;
                      color: white;
                      border-radius: 20px;
                      padding: 13px 20px;
                      text-align: center;
                      text-decoration: none;
                      display: inline-block;
                      font-size: 20px;
                      margin: 4px 2px;
                      cursor: pointer;" href="manageblog?post=true&role=user" class="button">Create Blog</a>
                </div>     
            </c:if>
            <c:if test="${CoursesDAO.CheckUserHasCourseIsPublic(AccSession.getAccountID()) == 0}">
                <p> You don't have any public course, so cant not create Blog !!!</p>
            </c:if>
            <c:if test="${CoursesDAO.CheckUserHasCourseIsPublic(AccSession.getAccountID()) != 0}">
                <div>

                    <h2 style="color: gray">User Blog</h2>
                    <br>
                </div>
                <div class="cart-item">
                    <div class="cart-item-infor">
                        <div class="cart-item-img">
                            Image Blog
                        </div>
                        <div class="cart-item-name">
                            Title
                        </div>
                        <div class="cart-item-price" style="color: gray">
                            Date Created
                        </div>
                        <div class="cart-item-price" style="color: gray">
                            Status
                        </div>
                        <div class="cart-item-price">

                        </div>
                        <div class="cart-item-button">

                        </div>
                    </div>
                    <div class="cart-item-function">

                    </div>
                </div>
                <c:forEach var="b" items="${listbluser}">
                    <div class="cart-item">
                        <div class="cart-item-infor">
                            <div class="cart-item-img">
                                <img class="img-fluid" src="data:image/jpg;base64,${CoursesDAO.createImgurl(b.getImage())}" alt="">
                            </div>
                            <div class="cart-item-name">
                                <p>${b.getBlogTitle()}</p>
                            </div>
                            <div class="cart-item-price" style="color: gray">
                                <p>${b.getDateCreated()}</p>
                            </div>
                            <div class="cart-item-price" style="color: gray">
                                <c:if test="${b.getBlogStatus() == 3}">
                                    Public
                                </c:if>
                                <c:if test="${b.getBlogStatus() == 0}">
                                    Private
                                </c:if>
                                <c:if test="${b.getBlogStatus() == 1}">
                                    Wait for admin to approve the blog
                                </c:if>
                                <c:if test="${b.getBlogStatus() == 2}">
                                    Admin refuse to public blog !!!
                                </c:if>
                            </div>
                            <div class="cart-item-price">
                                <c:if test="${b.getBlogStatus() == 1}">
                                    <a href="editblog1.jsp?BlogID=${b.getBlogID()}"/><i class='fa fa-edit' style='font-size:24px'></i></a>
                                </c:if>
                                <c:if test="${b.getBlogStatus() == 2}">
                                    <a href="editblog1.jsp?BlogID=${b.getBlogID()}"/><i class='fa fa-edit' style='font-size:24px'></i></a>
                                </c:if>
                                <c:if test="${b.getBlogStatus() == 0}">
                                    <a href="editblog1.jsp?BlogID=${b.getBlogID()}"/><i class='fa fa-edit' style='font-size:24px'></i></a>
                                </c:if>
                                <c:if test="${b.getBlogStatus() == 3}">
                                    <a href="<%=path%>/blogdetail?blogid=${b.getBlogID()}"/><i class='fa fa-eye' style='font-size:24px'></i></a>
                                </c:if>
                            </div>
                            <div class="cart-item-button">
                                <a onclick="checker()" href="<c:url value="manageblog?delete=true&BlogID=${b.getBlogID()}&role=user"/>">Remove</a>
                            </div>
                        </div>
                        <div class="cart-item-function">

                        </div>
                    </div>
                </c:forEach> 
            </c:if>
        </div>
    </div>
</div>
                
<!-- Contact End -->
<%@include file="template/footer.jsp" %>
<script>
                    function checker() {
        var result = confirm("Are you sure ?");
        if (result === false) {
            event.preventDefault();
        }
    }
</script>
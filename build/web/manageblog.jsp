<%@include file="template/headerAdmin.jsp" %>

<div id="cart-content">
    <div class="create-course-header">
        Manage Blog<br><br>
    </div>


    <div style="text-align: right; margin-right: 20px; margin-bottom: 20px"> <a style="background-color: #1c87c9;

                                                                                color: white;
                                                                                border-radius: 20px;
                                                                                padding: 13px 20px;
                                                                                text-align: center;
                                                                                text-decoration: none;
                                                                                display: inline-block;
                                                                                font-size: 20px;
                                                                                margin: 4px 2px;
                                                                                cursor: pointer;" href="manageblog?post=true&role=admin" class="button">Create Blog</a>
    </div> 
    <div>
        <h2 style="color: gray">Admin Blog</h2>
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


    <c:forEach var="blogadmin" items="${listb}">
        <div class="cart-item">
            <div class="cart-item-infor">
                <div class="cart-item-img">
                    <img class="img-fluid" src="data:image/jpg;base64,${CoursesDAO.createImgurl(blogadmin.getImage())}" alt="">
                </div>
                <div class="cart-item-name">
                    <p>${blogadmin.getBlogTitle()}</p>
                </div>
                <div class="cart-item-price" style="color: gray">
                    <p>${blogadmin.getDateCreated()}</p>
                </div>
                <div class="cart-item-price" style="color: gray">
                    
                    <c:if test="${blogadmin.getBlogStatus() == 0}">
                        <a onclick="checker()" href="<c:url value="manageblog?status=true&BlogID=${blogadmin.getBlogID()}&status1=3&role=admin"/>"><i style="font-size: 30px" class="fa fa-toggle-off"></i></a>
                        </c:if>
                    <c:if test="${blogadmin.getBlogStatus() == 3}">
                        <a onclick="checker()" href="<c:url value="manageblog?status=true&BlogID=${blogadmin.getBlogID()}&status1=0&role=admin"/>"><i style="font-size: 30px" class="fa fa-toggle-on"></i></a>
                        </c:if>
                </div>
                <div class="cart-item-price">
                    <c:if test="${blogadmin.getBlogStatus() == 0}">
                        <a href="editblog.jsp?BlogID=${blogadmin.getBlogID()}"/><i class='fa fa-edit' style='font-size:24px'></i></a>
                    </c:if>
                    
                <c:if test="${blogadmin.getBlogStatus() == 3}">
                    <a  href="<c:url value="viewDemoBlog.jsp?BlogID=${blogadmin.getBlogID()}"/>"><i style="font-size: 24px" class="fa fa-eye"></i></i></a>
                        </c:if>
                </div>
                <div class="cart-item-button">
                    <a onclick="checker()" href="<c:url value="manageblog?delete=true&BlogID=${blogadmin.getBlogID()}&role=admin"/>">Remove</a>
                </div>
            </div>
            <div class="cart-item-function">

            </div>
        </div>
    </c:forEach>

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
                Status(Public/Reject)
            </div>
            <div class="cart-item-price">

            </div>
            
        </div>
        
    </div>
    <c:forEach var="b" items="${listbluser}">
        <c:if test="${b.getBlogStatus() == 1}">
        <div class="cart-item">
            <div class="cart-item-infor">
                <div class="cart-item-img">
                    <img class="img-fluid"  src="data:image/jpg;base64,${CoursesDAO.createImgurl(b.getImage())}" alt="">
                </div>
                <div class="cart-item-name">
                    <p>${b.getBlogTitle()}</p>
                </div>
                <div class="cart-item-price" style="color: gray">
                    <p>${b.getDateCreated()}</p>
                </div>
                <div class="cart-item-price" style="color: gray">
                        <a onclick="checker1()" href="<c:url value="manageblog?status=true&BlogID=${b.getBlogID()}&status1=3&role=admin"/>"><i class="fa fa-check" style="font-size:48px;color:green"></i></a>
                       
                    
                        <a onclick="checker2()" href="<c:url value="manageblog?status=true&BlogID=${b.getBlogID()}&status1=2&role=admin"/>"><i class="fa fa-close" style="font-size:48px;color:red"></i></a>
                    
                </div>
                <div class="cart-item-price">
                     <a  href="<c:url value="viewDemoBlog.jsp?BlogID=${b.getBlogID()}"/>"><i style="font-size: 24px" class="fa fa-eye"></i></a>
                </div>
                
            </div>
            
        </div>
        </c:if>
    </c:forEach>
</div>





<%@include file="template/footerAdmin.jsp" %>
<script>
    function checker() {
        var result = confirm("Are you sure ?");
        if (result === false) {
            event.preventDefault();
        }
    }
    function checker1() {
        var result = confirm("Are you Public this Blog ?");
        if (result === false) {
            event.preventDefault();
        }
    }
    function checker2() {
        var result = confirm("Are you Reject this Blog ?");
        if (result === false) {
            event.preventDefault();
        }
    }
    
</script>

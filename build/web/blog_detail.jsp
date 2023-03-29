<%@include file="template/header.jsp" %>

<div class="header-blog">
    <h2>${blog.getBlogTitle()}</h2>
</div>

<div class="row-blog">

    <div class="card-blog">
        <h5>Date Created: ${blog.getDateCreated()}</h5>
        
            <h5 style="border-bottom: 2px solid gray;"><c:if test="${AccountDAO.getAccountById(blog.getAccountId()).getRoleId() == 1}">
               Author: COURSESHAY Team
            </c:if>
            <c:if test="${AccountDAO.getAccountById(blog.getAccountId()).getRoleId() == 2}">
               Author: ${ContactDAO.getAccContactById(blog.getAccountId()).getFullName()}
            </c:if>
        </h5>
        
        <br><br><br><br>
        <h5>${blog.getBlogDesciption()}</h5>
        

        <img style="width: 500px" src="data:image/jpg;base64,${CoursesDAO.createImgurl(blog.getImage())}" alt="">

    </div>
    <c:forEach items="${partblog}" var="bp">      
        <div class="card-blog">
            <h3>${bp.getPartHeader()}</h3>

            <img style="width: 500px" src="data:image/jpg;base64,${CoursesDAO.createImgurl(bp.getImage())}" alt="">

            <h5>${bp.getPartContent()}</h5>
        </div>
    </c:forEach>  
</div>


<%@include file="template/footer.jsp" %>
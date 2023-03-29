<%@include file="template/headerAdmin.jsp" %>
<%@page import="java.util.*" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<%
        int blogID = Integer.parseInt(request.getParameter("BlogID"));
        ArrayList<BlogPart> BlogPartList = new BlogDAO().getBlogPart(blogID);
        dal.Blog blog = new BlogDAO().getBlogBySql("Select * from Blog where BlogID = " + blogID);    
        request.setAttribute("CoursesDAO",new CoursesDAO());
        request.setAttribute("CateDAO", new CategoryDAO());
        request.setAttribute("Blog", blog);
        request.setAttribute("BlogParts", BlogPartList);
%>

<div class="create-course-container">
    
    <div class="create-course-header">
        <a href="<%=path%>/manageblog?view=true&role=admin" style="background-color: brown;
           text-decoration: none;
           color: white;
           padding: 11px 20px;
           border-radius: 5px;
           cursor: pointer;
           margin-top: 20px;"> Back </a>
           <br>
    </div>
    <div class="create-course-header">
        View Demo Blog
    </div>
    
    <c:if test="${success == true}">
        <div id="alert" class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Edit Successfully !!!</strong> 
        </div>
    </c:if>
    <c:if test="${fail ne null}">
        <div id="alert" class="alert alert-danger alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Failed!</strong> ${fail}.
        </div>
    </c:if>
    
        <div class="form-group">
            <label>Category:  ${CateDAO.getCategoryById(Blog.getCategoryID()).getCateName()}</label>
        </div>
        <div class="form-group">
            <label>Blog Detail</label>
        
        </div>
        
        <div class="form-group">
            <label for="blog-title">Blog Title</label>
            <br>
            <textarea  id="blog-title" name="BlogTitle" readonly>${Blog.getBlogTitle()}</textarea>
        </div>
        <div class="form-group">
            <label for="blog-desc">Blog Description</label>
            <br>
            <textarea id="blog-desc" name="BlogDescription" readonly>${Blog.getBlogDesciption()}</textarea>
        </div>
        <div class="form-group">
            <label for="blog-image">Blog Image</label>
            <br>
            <img style="width: 500px" src="data:image/jpg;base64,${CoursesDAO.createImgurl(Blog.getImage())}" alt="">
            <br>

        </div>

        <c:forEach items="${BlogParts}" var="bp">       
            <div class="form-group">
                <label for="blog-desc">Part ${bp.getPart()}</label>
                <br>
                <label for="blog-part-header">Part ${pb.getPart()} Header</label>
                <br>
                <textarea  id="blog-part-header" name="part-${bp.getPart()}-header" readonly>${bp.getPartHeader()}</textarea>
                <br>
                <label for="blog-part-content">Part ${bp.getPart()} Content</label>
                <br>
                <textarea id="blog-part-content" name="part-${bp.getPart()}-content" readonly>${bp.getPartContent()}</textarea>
                <br>
                <label for="blog-part-image">Part ${bp.getPart()} Image</label>
                <br>

                <img style="width: 500px" src="data:image/jpg;base64,${CoursesDAO.createImgurl(bp.getImage())}" alt="">


                <br>
            </div>
        </c:forEach>  





       


        

   
</div>
</body>
<script>
    var alert = document.querySelector("#alert");
    if (alert !== null) {
        setTimeout(function () {
            alert.style.display = "none";
        }, 3000);
    }

</script>

</html>


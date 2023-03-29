<%@include file="template/headerAdmin.jsp" %>
<%@page import="java.util.*" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<%
        int blogID = Integer.parseInt(request.getParameter("BlogID"));
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        ArrayList<BlogPart> BlogPartList = new BlogDAO().getBlogPart(blogID);
        dal.Blog blog = new BlogDAO().getBlogBySql("Select * from Blog where BlogID = " + blogID);    
        request.setAttribute("CoursesDAO",new CoursesDAO()); 
        ArrayList<Category> allItem = new CategoryDAO().getAllCategories();
        request.setAttribute("Blog", blog);
        request.setAttribute("listCate", allItem);
        request.setAttribute("success", success);
        request.setAttribute("BlogParts", BlogPartList);
%>
<div class="create-course-container">
    <div class="create-course-header">
        Edit Blog
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
    <form action="manageblog?edit=true" method="post" >
        <input type="text" name="BlogID" value="${Blog.getBlogID()}" hidden>
        <div class="form-group">
            <label for="blog-cate">Blog Category <br>
                <br>
                <select id="blog-cate" name="BlogCategory">
                    <c:forEach items="${listCate}" var="c">
                        
                        <c:choose>
                                                    <c:when test="${c.getCateId() == Blog.getCategoryID()}">
                                                        <option selected value="${c.getCateId()}">${c.getCateName()}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${c.getCateId()}">${c.getCateName()}</option>
                                                    </c:otherwise>
                                                </c:choose>
                    </c:forEach>
                </select></label>

        </div>
        <div class="form-group">
            <label>Blog Detail</label>

        </div>
        <div class="form-group">
            <label for="blog-title">Blog Title</label>
            <br>
            <textarea  id="blog-title" name="BlogTitle" required>${Blog.getBlogTitle()}</textarea>
        </div>
        <div class="form-group">
            <label for="blog-desc">Blog Description</label>
            <br>
            <textarea id="blog-desc" name="BlogDescription" required>${Blog.getBlogDesciption()}</textarea>
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
                <textarea  id="blog-part-header" name="part-${bp.getPart()}-header">${bp.getPartHeader()}</textarea>
                <br>
                <label for="blog-part-content">Part ${bp.getPart()} Content</label>
                <br>
                <textarea id="blog-part-content" name="part-${bp.getPart()}-content">${bp.getPartContent()}</textarea>
                <br>
                <label for="blog-part-image">Part ${bp.getPart()} Image</label>
                <br>

                <img style="width: 500px" src="data:image/jpg;base64,${CoursesDAO.createImgurl(bp.getImage())}" alt="">


                <br>
            </div>
        </c:forEach>  

        <input type="radio" id="status" name="status" value="0" <c:if test="${Blog.getBlogStatus()==0}"> checked=""</c:if>>
        <label for="status"> Set Blog Private</label><br>
        <input type="radio" id="status" name="status" value="3" <c:if test="${Blog.getBlogStatus()==3}"> checked=""</c:if>>
        <label for="status"> Set Blog Public</label><br>



        <input style="background-color: blue;
               text-decoration: none;
               color: white;
               padding: 11px 20px;
               border-radius: 5px;
               cursor: pointer;
               margin-top: 20px;" type="submit" value="Save changes">


        <a href="<%=path%>/manageblog?view=true&role=admin" style="background-color: red;
           text-decoration: none;
           color: white;
           padding: 11px 20px;
           border-radius: 5px;
           cursor: pointer;
           margin-top: 20px;"> Cancel </a>

    </form>
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

<%@include file="template/headerAdmin.jsp" %>
<%@page import="java.util.*" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<%
    ArrayList<Category> allItem = new CategoryDAO().getAllCategories();
    request.setAttribute("listCate", allItem);
%>
<div class="create-course-container">
    <div class="create-course-header">
        Create a New Blog
        <p class="text-danger mt-2" style="font-size: 25px">Commit your number of part before writing blog!</p>
    </div>

    <form action="manageblog?post=true" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="blog-cate">Blog Category <br>
                <br>
                <select id="blog-cate" name="BlogCategory">
                    <c:forEach items="${listCate}" var="c">
                        <option value="${c.getCateId()}">${c.getCateName()}</option>
                    </c:forEach>
                </select></label>

        </div>
        <div class="form-group">
            <label for="blog-title">Blog Title</label>
            <br>
            <textarea onchange="commitPart()" required id="blog-title" name="BlogTitle"></textarea>
        </div>
        <div class="form-group">
            <label for="blog-desc">Blog Description</label>
            <br>
            <textarea id="blog-desc" required name="BlogDescription"></textarea>
        </div>
        <div class="form-group">
            <label for="blog-image">Blog Image</label>
            <br>
            <input type="file" id="blog-img" required accept="image/jpeg, image/jpg, image/png" name="BlogImage">
        </div>
        <div class="form-group">
                <label for="blog-desc">Part 1</label>
                <br>
                <label for="blog-part-header">Part 1 Header</label>
                <br>
                <textarea onchange="commitPart()" required id="blog-part-header" name="part-1-header"></textarea>
                <br>
                <label for="blog-part-content">Part 1 Content</label>
                <br>
                <textarea id="blog-part-content" required name="part-1-content"></textarea>
                <br>
                <label for="blog-part-image">Part 1 Image</label>
                <br>
                <input type="file" id="blog-part-image" accept="image/jpeg, image/png" name="part-1-image" >  
                <br>
            </div>
        <c:forEach begin="2" end="${Parts}" var="item">       
            <div class="form-group">
                <label for="blog-desc">Part ${item}</label>
                <br>
                <label for="blog-part-header">Part ${item} Header</label>
                <br>
                <textarea onchange="commitPart()" id="blog-part-header" name="part-${item}-header"></textarea>
                <br>
                <label for="blog-part-content">Part ${item} Content</label>
                <br>
                <textarea id="blog-part-content" name="part-${item}-content"></textarea>
                <br>
                <label for="blog-part-image">Part ${item} Image</label>
                <br>
                <input type="file" id="blog-part-image" accept="image/jpeg, image/png" name="part-${item}-image" >  
                <br>
            </div>
        </c:forEach>  
        <label for="status"> Set Blog Private            <input style="margin-right: 810px" type="radio" id="status" name="status" value="0" checked></label>
        
        <br>
        
        <label for="status"> Set Blog Public            <input type="radio" id="status" name="status" value="3"></label>
        
        <br>

        <a href="manageblog?post=true&addPart=true&role=admin" class="change-part"/> Add Part</a>
    
        <c:if test="${Parts gt 1}">
        <a href="manageblog?post=true&removePart=true&&role=admin" class="change-part"/>Remove Part</a>
        </c:if><br>
        <p class="text-danger mt-2" style="font-size: 25px">Commit your number of part before writing blog!</p>
        <input type="submit" style="color: white;
               padding: 10px 25px;
               border-radius: 5px;
               cursor: pointer;
               margin-top: 20px;
               background-color: blue;
               border:none" value="Post">

        <a href="manageblog?view=true&role=admin"   style="background-color: red;
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
    
    function commitPart() {
        var changeParts = document.querySelectorAll(".change-part");
        for (var item in changeParts) {
            console.log(changeParts[item]);
            changeParts[item].style.display = "none";
        }
    };
</script>
</html>

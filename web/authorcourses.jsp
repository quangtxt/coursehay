<%@include file="template/header.jsp" %>

<!-- About Start -->
<div style="height: 100%">
    <div id="content">
        <div id="content-left" style="width: 20%; margin-right: 5px;">
            <h1 style="text-align: center; border-bottom: 1px solid #2878EB; padding: 10px 15px; ">Author Detail</h1>
            <ul>
                <a href="#" style=" "><li style="padding: 30px 0px ">Courses</li></a>
                <a href="<%=path%>/manageblog?view=true&role=user"><li style="padding: 30px 0px ">Blog</li></a>
            </ul>
        </div>
        <div id="content-right">
            <div class="path" style=";
                 background-color: #f5f5f5;
                 color: black;
                 font-size: 30px;
                 margin: 35px 60px;
                 text-align: left;
                 ">Courses
            </div>
            <div>
                <a href="<%=path%>/create-course" style="background-color: #2878EB;
                   color: white;text-align: center; padding: 14px 26px;margin-left: 10px;
                   font-family: 'Jost';">Create your Courses</a> 
            </div>
            <br>
            <c:if test="${msgDelete != null}">
                ${msgDelete}
            </c:if>
            <div style="width: 100%" class="content-author">

                <c:forEach items="${coursesofAuthor}" var="c">
                    <div class="product" style="border: 1px solid saddlebrown; margin: 0px 10px; background-color: #fff; display: flex">
                        <c:if test="${c.getImg()==null}">
                            <c:set var="srcImg" value="../img/error.jpg"/>
                        </c:if>
                        <c:if test="${c.getImg()!=null}">
                            <c:set var="srcImg" value="data:image/jpg;base64,${CoursesDAO.createImgurl(c.getImg())}"/>
                        </c:if>
                        <form class="img_form" action="<%=path%>/account/editimg" method="post" enctype="multipart/form-data">
                            <img src="${srcImg}" width="20%" style="width: 100%;"/>
                            <div class="img_form_hover">
                                <label for="class_picture" class="form-label">Edit image</label>
                                <input type="file" class="form-control" name="pic" id="class_picture"
                                       accept="image/png, image/jpeg, image/jpg">
                                <label id="class_picture-error" class="error mt-1" for="class_picture">${requestScope["class_picture-error"]}</label>
                                <input type="submit" value="Submit">
                                <input type="hidden" name="id" value="${c.getCourseId()}">
                            </div>
                        </form>
                        <div style="margin: 5% 10px"><a href="">${c.getCourseName()}</a></div>
                        <div style="margin: 5% auto">Status: 
                            <c:if test="${c.getPublicStatus() == 2}">
                                Public
                            </c:if>
                            <c:if test="${c.getPublicStatus() == 0}">
                                Not Public
                            </c:if>
                            <c:if test="${c.getPublicStatus() == 1}">
                                Waiting for admin to approve
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${c.getPublicStatus() == 2}">
                            </c:when>
                            <c:otherwise>
                                <div style="margin: 5% 10px">
                                    <button onclick="displayContents(${c.getCourseId()})">Contents Manager</button>
                                </div>
                                <div style="margin: 5% auto">
                                    <a href="<%=path%>/account/author-edit?coursesid=${c.getCourseId()}">Edit/Manage</a>
                                    <c:if test="${coursesid==c.getCourseId()}">
                                        ${msgErr}
                                    </c:if>
                                </div>
                                <div style="margin: 5% 10px"><a  onclick="return confirm('Do u want to delete')" eq true ? href="<%=path%>/account/detecourse?id=${c.getCourseId()}" :href="" ; style="cursor: pointer">Delete</a></div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="content-list ${c.getCourseId()}" style="display: none">
                        <c:forEach items="${contentDAO.getAllCourseContentsByCourseID(c.getCourseId())}" var="content">               
                            <div style="border-bottom: solid #999999; margin-top: 10px">
                                <form method="POST" action="<%=path%>/content?update=true">
                                    <input type="hidden" name="contentid" value="${content.getContentId()}">
                                    <input type="hidden" name="id" value="${c.getCourseId()}">
                                    <div class="form-group">
                                        <label>Content Name:</label>
                                        <input type="text" name="content-name" value="${content.getContentName()}">
                                    </div>

                                    <div class="form-group">
                                        <label>Content Description:</label>
                                        <input type="text" name="content-desc" value="${content.getContentDescription()}">
                                    </div>
                                    <input type="submit" value="Update Content" />
                                </form>
                                <button onclick="displayLectures(${content.getContentId()},${c.getCourseId()})">Lecture</button>
                                <div class="le${content.getContentId()}">
                                    <c:forEach items="${lectureDAO.getAllCourseContentsByContentID(content.getContentId())}" var="item">
                                        <div style="border: #3399ff solid 2px; width: 750px; border-radius: 15px">
                                            <div class="form-group" style="margin-left: 20px; margin-top: 10px">
                                                <label>Lecture Name:</label>
                                                <input type="text" name="lecture-name" required="" value="${item.getLectureName()}">
                                            </div>
                                            <div class="form-group" style="margin-left: 20px">
                                                <label>Lecture Type:</label>
                                                <select name="lecture-type">
                                                    <option value="multiple-choice" >Multiple choice</option>
                                                    <option value="document" >Document</option>
                                                    <option value="video" >Video</option>
                                                </select>
                                            </div>
                                            <a href="<%=path%>/content?lectureid=${item.getLectureId()}&deletecontent=true">Delete Lecture</a>
                                        </div>
                                    </c:forEach>
                                </div>
                                <a href="<%=path%>/content?courseid=${c.getCourseId()}&contentid=${content.getContentId()}">Delete Content</a>

                                <a href="<%=path%>/lecture">Create lecture</a>
                            </div>
                        </c:forEach>

                        <div style="margin-top: 10px">
                            <form method="POST" action="<%=path%>/content?add=true">
                                <input type="hidden" name="id" value="${c.getCourseId()}">
                                <div style="border-bottom: solid #999999">
                                    <div class="form-group">
                                        <label>Content Name:</label>
                                        <input type="text" name="txtcontent-name">
                                    </div>
                                    <div class="form-group">
                                        <label>Content Description:</label>
                                        <input type="text" name="txtcontent-desc">
                                    </div>
                                </div>
                                <input type="submit" value="Add Content" />
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!-- Contact End -->
<%@include file="template/footer.jsp" %>
<script>
    function displayContents(ID) {
        if ($('.' + ID).css("display") === "none") {
            $('.' + ID).css({'display': 'block'});
        } else {
            $('.' + ID).css({'display': 'none'});
        }
    }

    function displayLectures(ID, courseid) {
        $('.' + courseid).css({'display': 'block'});
        if ($('.le' + ID).css("display") === "none") {
            $('.le' + ID).css({'display': 'block'});
        } else {
            $('.le' + ID).css({'display': 'none'});
        }
    }
</script>

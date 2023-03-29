<%@include file="template/header.jsp" %>
<div class="create-course-container">
    <div class="create-course-header">
        Contents of course
    </div>
    <form class="create-content-form" action="content" method="post">
        <input type="hidden" value="${courseid}" name="courseid">
        <div class="form-group">
            <label>Content Name</label>
            <input type="text" required="" name="content-name" value="">
        </div>
        <div class="form-group">
            <label>Content Description</label>
            <input type="text" required="" name="content-desc" value="">
        </div>

        <input type="text" value="${lecs}" name="lecture" hidden="">
        <c:forEach begin="1" end="${lecs}" var="item">
            <label>Lecture ${item}</label>
            <div style="border: #3399ff solid 2px; width: 750px; border-radius: 15px">
                <div class="form-group" style="margin-left: 20px; margin-top: 10px">
                    <label>Lecture ${item} Name</label>
                    <input type="text" required="" name="lecture-name${item}" value="">
                </div>
                <div class="form-group" style="margin-left: 20px">
                    <label>Lecture ${item} Type</label>
                    <select name="lecture-type${item}">
                        <option value="multiple-choice">Multiple choice</option>
                        <option value="document">Document</option>
                        <option value="video">Video</option>
                    </select>
                </div>
            </div>
        </c:forEach>
        <a href="<c:url value="/content?courseid=${courseid}&addLecture=true"/>" class=" change-part btn btn-light px-4 mx-auto my-2 text-primary">&#9535; Add Lecture</a>
        <c:if test="${lecs gt 1}">
            <a href="<c:url value="/content?courseid=${courseid}&removeLecture=true"/>" class="change-part btn btn-light px-4 mx-auto my-2 text-danger">&#9644; Remove Lecture</a>
        </c:if>
        <input onclick="checkInput()" type="submit" value="Create content">
    </form>
</div>
</body>
</html>

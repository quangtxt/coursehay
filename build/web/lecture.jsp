<%@include file="template/header.jsp" %>

<div class="create-course-container">
    <div class="create-course-header">
        Lectures of content
    </div>
    <form class="create-content-form" action="lecture" method="post">
        <input type="hidden" value="${lecs}" name="lecture">
        <c:forEach begin="1" end="${lecs}" var="item">
            <label>Lecture ${item}</label>
            <div style="border: #3399ff solid 2px; width: 750px; border-radius: 15px">
                <div class="form-group" style="margin-left: 20px; margin-top: 10px">
                    <label>Lecture ${item} Name</label>
                    <input type="text" name="lecture-name${item}" required="">
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
        <a href="<c:url value="/lecture?addLecture=true"/>" class=" change-part btn btn-light px-4 mx-auto my-2 text-primary">&#9535; Add Lecture</a>
        <c:if test="${lecs gt 1}">
            <a href="<c:url value="/lecture?removeLecture=true"/>" class="change-part btn btn-light px-4 mx-auto my-2 text-danger">&#9644; Remove Lecture</a>
        </c:if>
        <input type="submit" value="Create lecture(s)">
    </form>
</div>

<%@include file="template/footer.jsp" %>
<%@include file="template/headerAdmin.jsp" %>

<div id="content-right">
    <div class="path-admin">Author Account</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-2" style="width: 55%; padding: 16px">
                </div>
                <div id="author-table-admin">
                    <c:forEach items="${listcourses_notpublic}" var="c">
                        <div class="product" style="border: 1px solid saddlebrown; margin: 0px 10px; background-color: #fff; display: flex">
                            <c:if test="${c.getImg()==null}">
                                <c:set var="srcImg" value="${path}/img/error.jpg"/>
                            </c:if>
                            <c:if test="${c.getImg()!=null}">
                                <c:set var="srcImg" value="data:image/jpg;base64,${CoursesDAO.createImgurl(c.getImg())}"/>
                            </c:if>
                            <div class="img_form">
                                <img src="${srcImg}" width="20%" style="width: 90%"/>
                            </div>
                            <div style="margin: 5% 10px"><a href="<%=path%>/admin/courses?pos=unapproved_course_detail&courseid=${c.getCourseId()}">${c.getCourseName()}</a></div>
                            <div style="margin: 5% 10px">Author:  ${ContactDAO.getAccContactById(c.getAuthorId()).getFullName()} </div>
                            <div style="margin: 5% auto">Status: 
                                <c:if test="${c.getPublicStatus() == 2}">
                                    Public
                                </c:if>
                                <c:if test="${c.getPublicStatus() ==0}">
                                    Not Public
                                </c:if>
                                <c:if test="${c.getPublicStatus() ==1}">
                                    Waiting for admin to approve
                                </c:if>
                            </div>
                        </div>
                        <br/>
                    </c:forEach>
                    <br/>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="template/footerAdmin.jsp" %>
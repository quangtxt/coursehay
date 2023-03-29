<%@include file="template/header.jsp" %>

<!-- About Start -->
<div style="height: 100%">
    <div id="content">
        <div id="content-left" style="width: 20%; margin-right: 5px;">
            <h1 style="text-align: center; border-bottom: 1px solid #2878EB; padding: 10px 15px; ">Author Detail</h1>
            <ul>
                <a href="#" style=" "><li style="padding: 30px 0px ">Courses</li></a>
                <a href="#"><li style="padding: 30px 0px ">Communication</li></a>
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
                 ">Notifications
            </div>

            <div>
                <a href="<%=path%>/notifications?role=instructor" style="background-color: #2878EB;
                   color: white;text-align: center; padding: 14px 26px;margin-left: 10px;
                   font-family: 'Jost';">Instructor
                    <c:if test="${NotiDAO.CountNotiNotSeenBySendto(AccSession.getAccountID(), 1) != 0}">
                        <span style="margin: 1px" class='badge badge-secondary' >
                            ${NotiDAO.CountNotiNotSeenBySendto(AccSession.getAccountID(), 1)}
                        </span>
                    </c:if>
                </a> 
                <a href="<%=path%>/notifications?role=student" style="background-color: #2878EB;
                   color: white;text-align: center; padding: 14px 26px;margin-left: 10px;
                   font-family: 'Jost';">Student
                    <c:if test="${NotiDAO.CountNotiNotSeenBySendto(AccSession.getAccountID(), 0) != 0}">
                        <span style="margin: 1px" class='badge badge-secondary' >
                            ${NotiDAO.CountNotiNotSeenBySendto(AccSession.getAccountID(), 0)}
                        </span>
                    </c:if>
                </a> 
            </div>

            <div style="border-top: 1px solid black ;width: 100%; margin-top: 30px" class="content-author" >
                <br/>
                <c:choose>
                    <c:when test="${listnotification.size() == 0}">
                        <label>No notifications.</label>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${listnotification}" var="n">
                            <div class="product" style="border: 1px solid saddlebrown; margin: 0px 10px; background-color:
                                 <c:if test="${n.getStatus() == 0}">
                                     #D6D6D6
                                 </c:if>
                                 ; display: flex; height: 100px">
                                <c:if test="${n.getSendTo() == 1}">
                                    <div style="margin: 1% 10px"><a href="<%=path%>/notifications?role=instructor&NotifyId=${n.getNotificationId()}">Notification</a></div>
                                </c:if>
                                <c:if test="${n.getSendTo() == 0}">
                                    <div style="margin: 1% 10px"><a href="<%=path%>/notifications?role=student&NotifyId=${n.getNotificationId()}">Notification</a></div>
                                </c:if>
                                <label style="padding: 20px ">${CoursesDAO.getCourseById(n.getCourseId()).getCourseName()} is
                                    <c:if test="${CoursesDAO.getCourseById(n.getCourseId()).getPublicStatus() == 0}">
                                        has been declined
                                    </c:if>
                                    <c:if test="${CoursesDAO.getCourseById(n.getCourseId()).getPublicStatus() == 2}">
                                        has been public
                                    </c:if></label>
                                <div style="margin: 1% 15%;width: 500px; word-wrap: break-word">
                                    Notification Content:
                                    ${n.getNotificationContent()}
                                </div>
                            </div>
                            <br/>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


            </div>
        </div>
    </div>
</div>
<!-- Contact End -->
<%@include file="template/footer.jsp" %>
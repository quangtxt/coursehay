<%@include file="template/header.jsp" %> 
<div class="container">
    <div class="row">
        <div class="col-sm-4"><a href="<%=path%>/authorprofile?authorId=${con.getAccountID()}" class="btn btn-custom waves-effect waves-light mb-4" data-animation="fadein" data-plugin="custommodal" data-overlayspeed="200" data-overlaycolor="#36404a"><i class="mdi mdi-plus"></i>Information</a></div>
        <div class="col-sm-4"><a href="<%=path%>/authorprofile?authorId=${con.getAccountID()}&Course=display" class="btn btn-custom waves-effect waves-light mb-4" data-animation="fadein" data-plugin="custommodal" data-overlayspeed="200" data-overlaycolor="#36404a"><i class="mdi mdi-plus"></i>Courses</a></div>
        <!-- end col -->
    </div>
    <c:if test="${Course == null}">
        <div class="main-body_profile btn-info">
            <div class="row gutters-sm">
                <div class="col-md-4 mb-3">
                    <div class="card_profile">
                        <div class="card-body_profile" style="width: auto;">
                            <div class="d-flex flex-column align-items-center text-center">
                                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                                <div class="mt-3">
                                    <h4>${con.getFullName()}</h4>
                                    <c:if test="${checkFollowed == null}">
                                        <form action="" method="post">
                                            <input type="hidden" name="AuthorId" value="${acc.getAccountID()}">
                                            <input class="btn btn-primary" type="submit" value="Follow">
                                        </form>
                                    </c:if>
                                    <c:if test="${checkFollowed != null}">
                                        <form action="" method="post">
                                            <input type="hidden" name="AuthorId" value="${acc.getAccountID()}">
                                            <input class="btn btn-outline-primary" type="submit" value="Unfollow">
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card_profile mb-3">
                        <div class="card-body_profile">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Email</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${acc.getEmail()}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Gender</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <c:if test="${con.isGender() == true}">
                                        Female
                                    </c:if>
                                    <c:if test="${con.isGender() == false}">
                                        Male
                                    </c:if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Date of birth</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${con.getDob()}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Phone</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${con.getPhone()}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Address</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${con.getAddress()}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
<c:if test="${Course != null}">
    <div id="content" style="margin: 20px 50px;">
        <div id="content-right">
            <c:if test="${emptyErr!=null}">
                <div style="padding: 2.4rem 4.8rem;text-align: left;    margin: 200px 100px">
                    <h1 style="text-align: left">
                        Sorry, We don't have any ${nameFilter} courses
                    </h1>
                    <div >

                    </div>
                </div>
            </c:if>
            <c:if test="${emptyErr==null}">
                <div class="container-fluid py-5">
                    <div class="container py-5">
                        <div class="row mx-0 justify-content-center">
                            <div class="col-lg-8">
                                <div class="section-title text-center position-relative mb-5">
                                    <c:if test="${cateId==null&& topicId==null}">
                                        <c:set var="str" value="All course of ${con.getFullName()}"/>
                                    </c:if>
                                    <c:if test="${cateId!=null || topicId!=null}">
                                        <c:set var="str" value="${nameFilter} Courses"/>
                                    </c:if>
                                    <h6 class="d-inline-block position-relative text-secondary text-uppercase pb-2">${str}</h6>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <c:forEach items="${listCourses}" var="c">
                                <div class="col-lg-4 col-md-6 pb-4">
                                    <a style="height: 200px;" class="courses-list-item position-relative d-block overflow-hidden mb-2" href="<%=path%>/coursedetail?CourseId=${c.getCourseId()}">
                                        <img class="img-fluid" src="data:image/jpg;base64,${couDAO.createImgurl(c.getImg())}" alt="">
                                        <div class="courses-text">
                                            <h4 class="text-center text-white px-3">${c.getCourseName()}</h4>
                                            <div class="border-top w-100 mt-3">
                                                <div class="d-flex justify-content-between p-3">
                                                    <span class="text-white"><i class="fa fa-user mr-2"></i>
                                                        ${conDAO.getAccContactById(c.getAuthorId()).getFullName()}
                                                    </span>
                                                    <h6 class="text-white my-3">${c.getRate()}<i class="fa fa-star mr-2"></i></h6>
                                                    <div>
                                                        <c:if test="${AccSession!=null}">
                                                            <c:if test="${couDAO.checkExistInWishlist(AccSession.getAccountID(),c.getCourseId()) > 0}">
                                                                <form action="<%=path%>/coursemanager?ac=wishlist" method="post" >
                                                                    <div class="input-icons">
                                                                        <input type="hidden" name="accid" value="${AccSession.getAccountID()}"/>
                                                                        <input type="hidden" name="courseid" value="${c.getCourseId()}"/>
                                                                        <input type="hidden" name="action" value="remove"/>
                                                                        <input type="hidden" name="href" value="course"/>
                                                                        <button class="btn">
                                                                            <i class="fa fa-heart" style="font-size:48px; color: pink" aria-hidden="true"></i>
                                                                        </button>
                                                                    </div>
                                                                </form>
                                                            </c:if>
                                                            <c:if test="${couDAO.checkExistInWishlist( AccSession.getAccountID(),c.getCourseId() ) == 0}">
                                                                <form action="<%=path%>/coursemanager?ac=wishlist" method="post" >
                                                                    <div class="input-icons">
                                                                        <input type="hidden" name="accid" value="${AccSession.getAccountID()}"/>
                                                                        <input type="hidden" name="courseid" value="${c.getCourseId()}"/>
                                                                        <input type="hidden" name="action" value="add"/>
                                                                        <input type="hidden" name="href" value="course"/>
                                                                        <button class="btn">
                                                                            <i class="fa fa-heart" style="font-size:48px; color: white" aria-hidden="true"></i>
                                                                        </button>
                                                                    </div>
                                                                </form>
                                                            </c:if>
                                                        </c:if>.
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <div id="paging">
                            <div class="pagi">
                                <c:if test="${endP!=1}">
                                    <c:if test="${page!=null}" >
                                        <c:choose>
                                            <c:when test="${Integer.parseInt(page)>3 && Integer.parseInt(endP)>5}">
                                                <a href="${url}&page=1">&laquo;</a>
                                                <a href="${url}&page=${page-1}">&lt;</a>
                                            </c:when>
                                            <c:when test="${Integer.parseInt(page)>=2&& Integer.parseInt(endP)>5}">
                                                <a href="${url}&page=${page-1}">&lt;</a>
                                            </c:when> 
                                        </c:choose>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${Integer.parseInt(endP)>5}">
                                            <c:choose>
                                                <c:when test="${Integer.parseInt(page)<Integer.parseInt(endP)-2&&Integer.parseInt(page)>3}">
                                                    <c:set var="start" value="${Integer.parseInt(page)-2}"/>
                                                    <c:set var="end" value="${Integer.parseInt(page)+2}"/>
                                                </c:when>
                                                <c:when test="${Integer.parseInt(page)<=3}">
                                                    <c:set var="start" value="1"/>
                                                    <c:set var="end" value="${Integer.parseInt(page)+2}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="start" value="${Integer.parseInt(page)-2}"/>
                                                    <c:set var="end" value="${endP}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="start" value="1"/>
                                            <c:set var="end" value="${endP}"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="i" begin="${start}" end="${end}">
                                        <c:if test="${i==page}">
                                            <a href="${url}&page=${i}" class="active">${i}</a>
                                        </c:if>
                                        <c:if test="${i!=page}">
                                            <a href="${url}&page=${i}">${i}</a>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${Integer.parseInt(endP)>5}" >
                                        <c:choose>
                                            <c:when test="${Integer.parseInt(page)==Integer.parseInt(endP)}">
                                            </c:when>
                                            <c:when test="${Integer.parseInt(page)>=Integer.parseInt(endP)-2}">
                                                <a href="${url}&page=${page+1}">&gt;</a>
                                            </c:when> 
                                            <c:otherwise>
                                                <a href="${url}&page=${page+1}">&gt;</a>
                                                <a href="${url}&page=${endP}">&raquo;</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</c:if>        
<%@include file="template/footer.jsp"%>
<script>
    function display(ID) {
        $("." + ID).toggle(1000);
    }
</script>
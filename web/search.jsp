<%@include file="template/header.jsp" %>
<c:if test="${sizeL==0}">
    <div style="padding: 2.4rem 4.8rem;text-align: left;">
        <h1 style="text-align: left;">
            Sorry, we couldn't find any results for "${q}"

        </h1>
        <div >
            <h2 style="text-align: left">Try adjusting your search. Here are some ideas:</h2>
            <ul >
                <li style="text-align: left">Make sure all words are spelled correctly</li>
                <li style="text-align: left">Try different search terms</li>
                <li style="text-align: left">Try more general search terms</li>
            </ul>
        </div>
    </div>
</c:if>
<c:if test="${sizeL!=0}">
    <div id="content">
        <div id="content-left" style="width: 25%;margin-top: 50px;border-right: 4px solid blue;">
            <form action="<%=path%>/search">
                Sort by:
                <select name="sort">
                    <option value="most-buyer">Most Buyers</option>
                    <option value="highest-rated">Highest Rated</option>
                    <option value="newest">Newest</option>
                </select>
                <input type="submit" value="Sort">
                <input type="hidden" value="${q}" name="q">
                <input type="hidden" value="${range}" name="a">
            </form>
            <div style="margin:10px ;margin-top: 50px">
                <h6>Select the number of courses you want displayed on each page </h6>
                <form action="${url}">
                    <select name="a">
                        <c:forEach items="${amountCourseIn1Page}" var="i">
                            <c:if test="${i==range}">
                                <option value="${i}" selected>${i}</option>
                            </c:if>
                            <c:if test="${i!=range}">
                                <option value="${i}">${i}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="q" value="${q}">
                    <input type="hidden" value="${str}" name="sort">
                    <input type="submit" value="Submit">
                </form>
            </div>
        </div>
        <div id="content-right" style="margin: 10px 20px">
            <div class="row mx-0 justify-content-center">
                <div class="col-lg-8">
                    <div class="section-title text-center position-relative mb-5">
                        <h6 class="d-inline-block position-relative text-secondary text-uppercase pb-2">${sizeL} results for "${q}"</h6>
                    </div>
                </div>
            </div>
            <div class="row">
                <c:forEach items="${listSearch}" var="c">
                    <div class="col-lg-4 col-md-6 pb-4">
                        <a class="courses-list-item position-relative d-block overflow-hidden mb-2" href="<%=path%>/coursedetail?CourseId=${c.getCourseId()}">
                            <c:if test="${c.getImg()==null}">
                                <c:set var="srcImg" value="${path}/img/error.jpg"/>
                            </c:if>
                            <c:if test="${c.getImg()!=null}">
                                <c:set var="srcImg" value="data:image/jpg;base64,${CoursesDAO.createImgurl(c.getImg())}"/>
                            </c:if>
                            <img class="img-fluid" src="${srcImg}" alt="">
                            <div class="courses-text">
                                <h4 class="text-center text-white px-3">${c.getCourseName()}</h4>
                                <div class="border-top w-100 mt-3">
                                    <div class="d-flex justify-content-between p-3">
                                        <span class="text-white"><i class="fa fa-user mr-2"></i>
                                            ${conDAO.getAccContactById(c.getAuthorId()).getFullName()}
                                        </span>
                                        <h6 class="text-white my-3">${c.getRate()}<i class="fa fa-star mr-2"></i></h6>

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
</div>
</c:if>


<%@include file="template/footer.jsp" %>
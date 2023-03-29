<%@include file="template/header.jsp" %>
<!-- Detail Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-8">
                <div class="mb-5">
                    <div class="section-title position-relative mb-5">
                        <h6 class="d-inline-block position-relative text-secondary text-uppercase pb-2">Course Detail</h6>
                        <h1 class="display-4">${cou.getCourseName()}</h1>
                    </div>
                    <img class="img-fluid rounded w-100 mb-4" src="data:image/jpg;base64,${CoursesDAO.createImgurl(cou.getImg())}" alt="Image">
                    <p>${cou.getCourseTitle()}</p>
                    <p>${cou.getCourseDescription()}</p>
                </div>
            </div>
            <div class="col-lg-4 mt-5 mt-lg-0">
                <div class="bg-primary mb-5 py-3">
                    <h3 class="text-white py-3 px-4 m-0">Course Features</h3>
                    <div class="d-flex justify-content-between border-bottom px-4">
                        <h6 class="text-white my-3">Topic</h6>
                        <a href="<%=path%>/course?topicId=${t.getTopicId()}">
                            <h6 class="text-white my-3">${t.getTopicName()}</h6>
                        </a>
                    </div>
                    <div class="d-flex justify-content-between border-bottom px-4">
                        <h6 class="text-white my-3">Instructor</h6>
                        <a href="<%=path%>/authorprofile?authorId=${cou.getAuthorId()}">
                            <h6 class="text-white my-3">
                                ${conDAO.getAccContactById(cou.getAuthorId()).getFullName()}
                            </h6>
                        </a>
                    </div>
                    <div class="d-flex justify-content-between border-bottom px-4">
                        <h6 class="text-white my-3">Rating</h6>
                        <h6 class="text-white my-3">${cou.getRate()}<i class="fa fa-star mr-2"></i></h6>
                    </div>

                    <div class="d-flex justify-content-between border-bottom px-4">
                        <h6 class="text-white my-3">Price</h6>
                        <h6 class="text-white my-3">${cou.getPrice()} VND</h6>
                    </div>
                    <c:choose>
                        <c:when test="${checkAuthor != null}">
                            <div class="d-flex justify-content-between border-bottom px-4">
                                <h6 class="text-white my-3">Public Date</h6>
                                <h6 class="text-white my-3">${cou.getPublicDate()}</h6>
                            </div>
                            <div class="py-3 px-4">
                                <a class="btn btn-block btn-secondary py-3 px-5" href="<%=path%>/account/author-edit?coursesid=${cou.getCourseId()}">Edit Course</a>
                            </div>
                        </c:when>             
                        <c:when test="${uc != null}">
                            <div class="py-3 px-4">
                                <c:if test="${!uc.isStatus()}">
                                    <button class="btn btn-block btn-secondary py-3 px-5" onclick="displayEnterCode()">Enter code to enroll</button>
                                    <form id="form_code" action="<%=path%>/course" method="post" style="margin-top: 10px; display: none">
                                        <input type="text" name="txtCode">
                                        <input type="submit" value="Check">
                                        <input type="hidden" name="courseId" value="${cou.getCourseId()}">
                                    </form>
                                </c:if>
                                <c:if test="${uc.isStatus()}">
                                    <a class="btn btn-block btn-secondary py-3 px-5" href="">Go to course</a>
                                </c:if>            
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="py-3 px-4" style="display: flex">
                                <a class="btn btn-block btn-secondary py-3 px-5" style="width: 200px" href="<%=path%>/cart?action=add&courseID=${cou.getCourseId()}">Add To Cart</a>
                                <div>
                                    <c:if test="${AccSession!=null}">
                                        <c:if test="${couDAO.checkExistInWishlist(AccSession.getAccountID(),c.getCourseId()) > 0}">
                                            <form action="<%=path%>/coursemanager?ac=wishlist"" method="post" >
                                                <div class="input-icons">
                                                    <input type="hidden" name="accid" value="${AccSession.getAccountID()}"/>
                                                    <input type="hidden" name="courseid" value="${cou.getCourseId()}"/>
                                                    <input type="hidden" name="action" value="remove"/>
                                                    <input type="hidden" name="href" value="course"/>
                                                    <button class="btn">
                                                        <i class="fa fa-heart" style="font-size:48px; color: pink" aria-hidden="true"></i>
                                                    </button>
                                                </div>
                                            </form>
                                        </c:if>
                                        <c:if test="${couDAO.checkExistInWishlist( AccSession.getAccountID(),c.getCourseId() ) == 0}">
                                            <form action="<%=path%>/coursemanager?ac=wishlist"" method="post" >
                                                <div class="input-icons">
                                                    <input type="hidden" name="accid" value="${AccSession.getAccountID()}"/>
                                                    <input type="hidden" name="courseid" value="${cou.getCourseId()}"/>
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

                        </c:otherwise>
                    </c:choose>
                    <a href="<%=path%>/gift-course?couId=${cou.getCourseId()}" class="text-white">Gift this course</a>
                </div>
            </div>
        </div>
        <div class="comments-container">
            <h2>Feedback</h2>
            <ul id="comments-list" class="comments-list">
                <c:if test="${uc!=null && !checkFeedbacked}">
                    <form action="" method="post">
                        <li>
                            <div class="comment-main-level">
                                <!-- Avatar -->
                                <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                                <!-- Contenedor del Comentario -->
                                <div class="comment-box">
                                    <div class="comment-head">
                                        <h6 class="comment-name"><a href="http://creaticode.com/blog">Me</a></h6>
                                        <span style="width: 100%">
                                            <div class="rating">               
                                                <input type="radio" id="star5" name="rating" value="5" checked/><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                                                <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                                                <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                                                <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                                                <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                            </div>
                                        </span>
                                    </div>
                                    <div class="comment-content">
                                        <input type="text" name="txtComment" placeholder="Send your feedback" style="width: 93%; text-align: left">
                                        <input type="submit" name="name" value="Send">
                                    </div>
                                    <input type="hidden" name="CourseId" value="${cou.getCourseId()}">
                                    <input type="hidden" name="FeedbackId" value="1">
                                </div>
                            </div>
                        </li>
                    </form>
                </c:if>
                <c:if test="${checkFeedbacked}">
                    <c:set var="r" value="${replyDAO.getReplybyFeedbackId(f.getFeedbackId())}"/>
                    <li>
                        <div class="comment-main-level">
                            <!-- Avatar -->
                            <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_2_zps7de12f8b.jpg" alt=""></div>
                            <!-- Contenedor del Comentario -->
                            <div class="comment-box">
                                <div class="comment-head">
                                    <h6 class="comment-name">
                                        Me
                                    </h6>
                                    <span>${f.getDateFeedback()}</span>
                                    <c:if test="${r != null}">
                                        <i onclick="display(${f.getFeedbackId()})" class="fa fa-reply"></i>
                                    </c:if>
                                    <i class="fa fa-star">${f.getStar()}</i>
                                    <i class="fa" onclick="displayDeleteFeedback()">Delete</i>
                                    <i class="fa" onclick="displayEditFeedback()">Edit</i>
                                </div> 
                                <div class="comment-content">
                                    ${f.getComment()}
                                </div>
                            </div>
                        </div>
                    </li>
                    <c:if test="${r!=null}">
                        <ul class="comments-list reply-list" >
                            <li>
                                <!-- Avatar -->
                                <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                                <!-- Contenedor del Comentario -->
                                <div class="comment-box">
                                    <div class="comment-head">
                                        <h6 class="comment-name by-author">
                                            ${conDAO.getAccContactById(r.getAccountId()).getFullName()}
                                        </h6>
                                        <span>${r.getDateReply()}</span>
                                    </div>
                                    <div class="comment-content">
                                        ${r.getReply()}
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </c:if>
                    <form id="form-edit" action="coursedetail" method="post" style="display: none">
                        <li>
                            <div class="comment-main-level">
                                <div class="comment-box">
                                    <div class="comment-head">
                                        <h6 class="comment-name">Edit your feedback</h6>
                                        <span style="width: 100%">
                                            <div class="rating">               
                                                <input type="radio" id="star5" name="updaterating" value="5" ${f.getStar() == 5?"checked":""}/><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                                                <input type="radio" id="star4" name="updaterating" value="4" ${f.getStar() == 4?"checked":""}/><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                                                <input type="radio" id="star3" name="updaterating" value="3" ${f.getStar() == 3?"checked":""}/><label class = "full" for="star3" title="Meh - 3 stars"></label>
                                                <input type="radio" id="star2" name="updaterating" value="2" ${f.getStar() == 2?"checked":""}/><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                                                <input type="radio" id="star1" name="updaterating" value="1" ${f.getStar() == 1?"checked":""}/><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                            </div>
                                        </span>
                                    </div>
                                    <div class="comment-content">
                                        <input type="text" name="txtUpdateComment" placeholder="Send your feedback" value="${f.getComment()}" style="width: 80%; text-align: left">
                                        <input type="hidden" name="FeedbackId" value="${f.getFeedbackId()}">
                                        <input type="hidden" name="CourseId" value="${f.getCourseId()}">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="submit" name="name" value="Update">
                                    </div>
                                </div>
                            </div>
                        </li>
                    </form>
                    <form id="form-delete" action="coursedetail" method="post" style="display: none">
                        <li>
                            <div class="comment-main-level">
                                <div class="comment-box">
                                    <div class="comment-head">
                                        <h6 class="comment-name">Do you want to delete feedback?</h6>
                                    </div>
                                    <div class="comment-content">
                                        <input type="hidden" name="FeedbackId" value="${f.getFeedbackId()}">
                                        <input type="hidden" name="CourseId" value="${cou.getCourseId()}">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="submit" name="name" value="Yes" style="margin-right: 100px; width: 30%">
                                        <input type="button" name="name" value="No" style="width: 30%" onclick="displayDeleteFeedback()">
                                    </div>
                                </div>
                            </div>
                        </li>
                    </form>
                </c:if>
                <div style="margin-bottom: 10px">
                    <button class="filter-star"><a href="<%=path%>/coursedetail?CourseId=${cou.getCourseId()}">All</a></button>
                    <button class="filter-star"><a href="<%=path%>/coursedetail?CourseId=${cou.getCourseId()}&star=5">5 &starf;</a></button>
                    <button class="filter-star"><a href="<%=path%>/coursedetail?CourseId=${cou.getCourseId()}&star=4">4 &starf;</a></button>
                    <button class="filter-star"><a href="<%=path%>/coursedetail?CourseId=${cou.getCourseId()}&star=3">3 &starf;</a></button>
                    <button class="filter-star"><a href="<%=path%>/coursedetail?CourseId=${cou.getCourseId()}&star=2">2 &starf;</a></button>
                    <button class="filter-star"><a href="<%=path%>/coursedetail?CourseId=${cou.getCourseId()}&star=1">1 &starf;</a></button>
                </div>
                <c:if test="${msg == null}">
                    <c:forEach items="${listF}" var="f">
                        <c:set var="r" value="${replyDAO.getReplybyFeedbackId(f.getFeedbackId())}"/>
                        <div class="rely-container">
                            <li>
                                <div class="comment-main-level">
                                    <!-- Avatar -->
                                    <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_2_zps7de12f8b.jpg" alt=""></div>
                                    <!-- Contenedor del Comentario -->
                                    <div class="comment-box">
                                        <div class="comment-head">
                                            <c:if test="${f.getAccountId()!=id}">
                                                <h6 class="comment-name">
                                                    ${conDAO.getAccContactById(f.getAccountId()).getFullName()}
                                                </h6>
                                            </c:if>
                                            <span>${f.getDateFeedback()}</span>
                                            <c:if test="${r != null || checkAuthor}">
                                                <i onclick="display(${f.getFeedbackId()})" class="fa fa-reply"></i>
                                            </c:if>
                                            <i class="fa fa-star">${f.getStar()}</i>
                                        </div>
                                        <div class="comment-content">
                                            ${f.getComment()}
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <ul class="comments-list reply-list" >
                                <c:if test="${r!=null}">
                                    <li>
                                        <!-- Avatar -->
                                        <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                                        <!-- Contenedor del Comentario -->
                                        <div class="comment-box">
                                            <div class="comment-head">
                                                <c:if test="${!checkAuthor}">
                                                    <h6 class="comment-name by-author">
                                                        ${conDAO.getAccContactById(r.getAccountId()).getFullName()}
                                                    </h6>
                                                </c:if>
                                                <c:if test="${checkAuthor}">
                                                    <h6 class="comment-name by-author">
                                                        Me
                                                    </h6>
                                                </c:if>
                                                <span>${r.getDateReply()}</span>
                                                <c:if test="${checkAuthor}">
                                                    <i class="fa" onclick="displayDeleteReply()">Delete</i>
                                                    <i class="fa" onclick="displayEditReply()">Edit</i>
                                                </c:if>
                                            </div>
                                            <div class="comment-content">
                                                ${r.getReply()}
                                            </div>
                                        </div>
                                    </li>
                                    <form id="edit-form" action="coursedetail" method="post" style="display: none">
                                        <li>
                                            <!-- Avatar -->
                                            <!-- Contenedor del Comentario -->
                                            <div class="comment-box">
                                                <div class="comment-head">
                                                    <h6 class="comment-name"><a href="http://creaticode.com/blog">Edit your reply</a></h6>
                                                </div>
                                                <div class="comment-content">
                                                    <input type="text" name="txtUpdateReply" placeholder="Reply this feedback" value="${r.getReply()}" style="width: 90%; text-align: left">
                                                    <input type="hidden" name="FeedbackId" value="${f.getFeedbackId()}">
                                                    <input type="hidden" name="CourseId" value="${cou.getCourseId()}">
                                                    <input type="hidden" name="action" value="editreply">
                                                    <input type="submit" name="name" value="Update">    
                                                </div>
                                            </div>
                                        </li>
                                    </form>
                                    <form id="delete-form" action="coursedetail" method="post" style="display: none">
                                        <li>
                                            <!-- Avatar -->
                                            <!-- Contenedor del Comentario -->
                                            <div class="comment-box">
                                                <div class="comment-head">
                                                    <h6 class="comment-name"><a href="http://creaticode.com/blog">Do you want to delete reply?</a></h6>
                                                </div>
                                                <div class="comment-content">
                                                    <input type="hidden" name="FeedbackId" value="${f.getFeedbackId()}">
                                                    <input type="hidden" name="CourseId" value="${cou.getCourseId()}">
                                                    <input type="hidden" name="action" value="deletereply">
                                                    <input type="submit" name="name" value="Yes" style="margin-right: 100px; width: 30%">
                                                    <input type="button" name="name" value="No" style="width: 30%" onclick="displayDeleteReply()">
                                                </div>
                                            </div>
                                        </li>
                                    </form>
                                </c:if>
                                <c:if test="${r == null && checkAuthor}">
                                    <form id="Rep${f.getFeedbackId()}" action="" method="post" style="display: none">
                                        <li>
                                            <!-- Avatar -->
                                            <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg" alt=""></div>
                                            <!-- Contenedor del Comentario -->
                                            <div class="comment-box">
                                                <div class="comment-head">
                                                    <h6 class="comment-name by-author"><a href="http://creaticode.com/blog">Me</a></h6>
                                                </div>
                                                <div class="comment-content">
                                                    <input type="text" name="txtReply" style="width: 93%; text-align: left">
                                                    <input type="submit" name="name" placeholder="Reply this feedback" value="Send">    
                                                    <input type="hidden" name="FeedbackId" value="${f.getFeedbackId()}">
                                                </div>
                                            </div>
                                        </li>
                                    </form>
                                </c:if>
                            </ul>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${msg != null}">
                    <h3>${msg}</h3>
                </c:if>
            </ul>
        </div>
        <div style="margin-top: 100px">
            <c:if test="${msg1 == null}">
                <h2>More courses by ${conDAO.getAccContactById(cou.getAuthorId()).getFullName()}</h2>
                <div class="row">
                    <c:forEach items="${listCourseOfAuthor}" var="c" begin="0" end="3">
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

                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${msg1 == null}">
                <h2>Related courses</h2>
                <div class="row">
                    <c:forEach items="${listCourseTopic}" var="c" begin="0" end="3">
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
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>      
    </div>
</div>


<!-- Detail End -->
<style>@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
    /*    .rely{
            display: none;
        }
        .rely--show{
            display: block;
        }*/
    fieldset, label {
        margin: 0;
        padding: 0;
    }
    body{
        margin: 20px;
    }
    h1 {
        font-size: 1.5em;
        margin: 10px;
    }

    /****** Style Star Rating Widget *****/

    .rating {
        border: none;
        float: left;
    }

    .rating > input {
        display: none;
    }
    .rating > label:before {
        margin: 5px;
        font-size: 1.25em;
        font-family: FontAwesome;
        display: inline-block;
        content: "\f005";
    }

    .rating > .half:before {
        content: "\f089";
        position: absolute;
    }

    .rating > label {
        color: #ddd;
        float: right;
    }

    /***** CSS Magic to Highlight Stars on Hover *****/

    .rating > input:checked ~ label, /* show gold star when clicked */
    .rating:not(:checked) > label:hover, /* hover current star */
    .rating:not(:checked) > label:hover ~ label {
        color: #FFD700;
    } /* hover previous stars in list */

    .rating > input:checked + label:hover, /* hover current star when changing rating */
    .rating > input:checked ~ label:hover,
    .rating > label:hover ~ input:checked ~ label, /* lighten current selection */
    .rating > input:checked ~ label:hover ~ label {
        color: #FFED85;
    }
    /**
        * Oscuro: #283035
        * Azul: #03658c
        * Detalle: #c7cacb
        * Fondo: #dee1e3
        ----------------------------------*/
    * {
        margin: 0;
        padding: 0;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    a {
        color: #03658c;
        text-decoration: none;
    }

    ul {
        list-style-type: none;
    }

    body {
        font-family: 'Roboto', Arial, Helvetica, Sans-serif, Verdana;
        background: #dee1e3;
    }

    /** ====================
     * Lista de Comentarios
     =======================*/
    .comments-container {
        margin: 60px auto 15px;
        width: 768px;
    }

    .comments-container h1 {
        font-size: 36px;
        color: #283035;
        font-weight: 400;
    }

    .comments-container h1 a {
        font-size: 18px;
        font-weight: 700;
    }

    .comments-list {
        margin-top: 30px;
        position: relative;
    }

    /**
     * Lineas / Detalles
     -----------------------*/
    .comments-list:before {
        content: '';
        width: 2px;
        height: 100%;
        background: #c7cacb;
        position: absolute;
        left: 32px;
        top: 0;
    }

    .comments-list:after {
        content: '';
        position: absolute;
        background: #c7cacb;
        bottom: 0;
        left: 27px;
        width: 7px;
        height: 7px;
        border: 3px solid #dee1e3;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        border-radius: 50%;
    }

    .reply-list:before, .reply-list:after {
        display: none;
    }
    .reply-list li:before {
        content: '';
        width: 60px;
        height: 2px;
        background: #c7cacb;
        position: absolute;
        top: 25px;
        left: -55px;
    }


    .comments-list li {
        margin-bottom: 15px;
        display: block;
        position: relative;
    }

    .comments-list li:after {
        content: '';
        display: block;
        clear: both;
        height: 0;
        width: 0;
    }

    .reply-list {
        padding-left: 88px;
        clear: both;
        margin-top: 15px;
    }
    /**
     * Avatar
     ---------------------------*/
    .comments-list .comment-avatar {
        width: 65px;
        height: 65px;
        position: relative;
        z-index: 99;
        float: left;
        border: 3px solid #FFF;
        -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        border-radius: 4px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
        -moz-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
        box-shadow: 0 1px 2px rgba(0,0,0,0.2);
        overflow: hidden;
    }

    .comments-list .comment-avatar img {
        width: 100%;
        height: 100%;
    }

    .reply-list .comment-avatar {
        width: 50px;
        height: 50px;
    }

    .comment-main-level:after {
        content: '';
        width: 0;
        height: 0;
        display: block;
        clear: both;
    }
    /**
     * Caja del Comentario
     ---------------------------*/
    .comments-list .comment-box {
        width: 680px;
        float: right;
        position: relative;
        -webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
        -moz-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
        box-shadow: 0 1px 1px rgba(0,0,0,0.15);
    }

    .comments-list .comment-box:before, .comments-list .comment-box:after {
        content: '';
        height: 0;
        width: 0;
        position: absolute;
        display: block;
        border-width: 10px 12px 10px 0;
        border-style: solid;
        border-color: transparent #FCFCFC;
        top: 8px;
        left: -11px;
    }

    .comments-list .comment-box:before {
        border-width: 11px 13px 11px 0;
        border-color: transparent rgba(0,0,0,0.05);
        left: -12px;
    }

    .reply-list .comment-box {
        width: 610px;
    }
    .comment-box .comment-head {
        background: #FCFCFC;
        padding: 10px 12px;
        border-bottom: 1px solid #E5E5E5;
        overflow: hidden;
        -webkit-border-radius: 4px 4px 0 0;
        -moz-border-radius: 4px 4px 0 0;
        border-radius: 4px 4px 0 0;
    }

    .comment-box .comment-head i {
        float: right;
        margin-left: 14px;
        position: relative;
        top: 2px;
        color: #A6A6A6;
        cursor: pointer;
        -webkit-transition: color 0.3s ease;
        -o-transition: color 0.3s ease;
        transition: color 0.3s ease;
    }

    .comment-box .comment-head i:hover {
        color: #03658c;
    }

    .comment-box .comment-name {
        color: #283035;
        font-size: 14px;
        font-weight: 700;
        float: left;
        margin-right: 10px;
    }

    .comment-box .comment-name a {
        color: #283035;
    }

    .comment-box .comment-head span {
        float: left;
        color: #999;
        font-size: 13px;
        position: relative;
        top: 1px;
    }

    .comment-box .comment-content {
        background: #FFF;
        padding: 12px;
        font-size: 15px;
        color: #595959;
        -webkit-border-radius: 0 0 4px 4px;
        -moz-border-radius: 0 0 4px 4px;
        border-radius: 0 0 4px 4px;
    }

    .comment-box .comment-name.by-author, .comment-box .comment-name.by-author a {
        color: #03658c;
    }
    .comment-box .comment-name.by-author:after {
        content: 'autor';
        background: #03658c;
        color: #FFF;
        font-size: 12px;
        padding: 3px 5px;
        font-weight: 700;
        margin-left: 10px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
    /** =====================
     * Responsive
     ========================*/
    @media only screen and (max-width: 766px) {
        .comments-container {
            width: 480px;
        }

        .comments-list .comment-box {
            width: 390px;
        }

        .reply-list .comment-box {
            width: 320px;
        }
    }
</style>

<%@include file="template/footer.jsp" %>
<script>
    function display(ID) {
        if ($('#Rep' + ID).css("display") === "none") {
            $('#Rep' + ID).css({'display': 'block'});
        } else {
            $('#Rep' + ID).css({'display': 'none'});
        }
    }
    function displayEnterCode() {
        if ($('#form_code').css("display") === "none") {
            $('#form_code').css({'display': 'block'});
        } else {
            $('#form_code').css({'display': 'none'});
        }
    }
    function displayEditFeedback() {
        if ($('#form-edit').css("display") === "none") {
            $('#form-edit').css({'display': 'block'});
        } else {
            $('#form-edit').css({'display': 'none'});
        }
    }
    function displayDeleteFeedback() {
        if ($('#form-delete').css("display") === "none") {
            $('#form-delete').css({'display': 'block'});
        } else {
            $('#form-delete').css({'display': 'none'});
        }
    }
    function displayEditReply() {
        if ($('#edit-form').css("display") === "none") {
            $('#edit-form').css({'display': 'block'});
        } else {
            $('#edit-form').css({'display': 'none'});
        }
    }
    function displayDeleteReply() {
        if ($('#delete-form').css("display") === "none") {
            $('#delete-form').css({'display': 'block'});
        } else {
            $('#delete-form').css({'display': 'none'});
        }
    }

</script>

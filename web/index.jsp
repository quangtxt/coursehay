<%@include file="template/header.jsp" %>
<!-- About Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-5 mb-5 mb-lg-0" style="min-height: 500px;">
                <div class="position-relative h-100">
                    <img class=" w-100 h-100" src="<%=path%>/img/about.jpg" style="object-fit: cover;">
                </div>
            </div>
            <div class="col-lg-7">
                <div class="section-title position-relative mb-4">
                    <h6 class="d-inline-block position-relative text-secondary text-uppercase pb-2">About Us</h6>
                    <h1 class="display-4">Learning that gets you</h1>
                </div>
                <p>Skills for your present (and your future). Get started with us.</p>
                <div class="row pt-3 mx-0">
                    <div class="col-3 px-0">
                        <div class="bg-success text-center p-4">
                            <h1 class="text-white" data-toggle="counter-up">${topicDAO.countTopic()}</h1>
                            <h6 class="text-uppercase text-white">Available<span class="d-block">Topic</span></h6>
                        </div>
                    </div>
                    <div class="col-3 px-0">
                        <div class="bg-primary text-center p-4">
                            <h1 class="text-white" data-toggle="counter-up">${couDAO.countCoursePublic()}</h1>
                            <h6 class="text-uppercase text-white">Online<span class="d-block">Courses</span></h6>
                        </div>
                    </div>
                    <div class="col-3 px-0">
                        <div class="bg-secondary text-center p-4">
                            <h1 class="text-white" data-toggle="counter-up">${couDAO.countAuthorInCoursePublic()}</h1>
                            <h6 class="text-uppercase text-white">Skilled<span class="d-block">Instructors</span></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- About End -->

<!-- Courses Start -->
<div class="container-fluid px-0 py-5">
    <div class="row mx-0 justify-content-center pt-5">
        <div class="col-lg-6">
            <div class="section-title text-center position-relative mb-4">
                <h6 class="d-inline-block position-relative text-secondary text-uppercase pb-2">Our Courses</h6>
                <h1 class="display-4">Checkout New Releases Of Our Courses</h1>
            </div>
        </div>
    </div>
    <div class="owl-carousel courses-carousel" >
        <c:forEach items="${listCourses}" var="c">
            <div class="courses-item position-relative" style="height: 300px;margin: 5px">
                <img class="img-fluid" src="data:image/jpg;base64,${couDAO.createImgurl(c.getImg())}" alt="">
                <div class="courses-text">
                    <h4 class="text-center text-white px-3">${c.getCourseName()}</h4>
                    <div class="border-top w-100 mt-3">
                        <div class="d-flex justify-content-between p-4">
                            <span class="text-white"><i class="fa fa-user mr-2"></i>${conDAO.getAccContactById(c.getAuthorId()).getFullName()}</span>
                            <span class="text-white"><i class="fa fa-star mr-2"></i>${c.getRate()} <small>(${fbDAO.CountFeedBackbyCourseId(c.getCourseId())})</small></span>
                        </div>
                    </div>
                    <div class="w-100 bg-white text-center p-4" >
                        <a class="btn btn-primary" href="<%=path%>/coursedetail?CourseId=${c.getCourseId()}">Course Detail</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
<!-- Courses End -->
<!-- Contact Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="row align-items-center">
            <div class="col-lg-5 mb-5 mb-lg-0">
                <div class="bg-light d-flex flex-column justify-content-center px-5" style="height: 450px;">
                    <div class="d-flex align-items-center mb-5">
                        <div class="btn-icon bg-primary mr-4">
                            <i class="fa fa-2x fa-map-marker-alt text-white"></i>
                        </div>
                        <div class="mt-n1">
                            <h4>Our Location</h4>
                            <p class="m-0">SE1641,FPT university, Ha Noi</p>
                        </div>
                    </div>
                    <div class="d-flex align-items-center mb-5">
                        <div class="btn-icon bg-secondary mr-4">
                            <i class="fa fa-2x fa-phone-alt text-white"></i>
                        </div>
                        <div class="mt-n1">
                            <h4>Call Us</h4>
                            <p class="m-0">0866405629</p>
                        </div>
                    </div>
                    <div class="d-flex align-items-center">
                        <div class="btn-icon bg-warning mr-4">
                            <i class="fa fa-2x fa-envelope text-white"></i>
                        </div>
                        <div class="mt-n1">
                            <h4>Email Us</h4>
                            <p class="m-0">coursehay@gmail.com</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-7">
                <img class="123" src="<%=path%>/img/contact-us.png" style="height: 90%;width: 90%; margin-left: 20%;margin-bottom: 10%;">
            </div>
        </div>
    </div>
</div>
<!-- Contact End -->
<%@include file="template/footer.jsp" %>
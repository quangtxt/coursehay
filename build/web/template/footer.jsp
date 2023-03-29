<!-- Footer Start -->
<div class="container-fluid position-relative bg-dark text-white-50 py-5" style="margin-top: 90px;">
    <div class="container mt-5 pt-5">
        <div class="row">
            <div class="col-md-6 mb-5">
                <a href="index.html" class="navbar-brand">
                    <h1 class="mt-n2 text-uppercase text-white"><i class="fa fa-book-reader mr-3"></i>CourseHay</h1>
                </a>
                <p class="m-0">Accusam nonumy clita sed rebum kasd eirmod elitr. Ipsum ea lorem at et diam est, tempor rebum ipsum sit ea tempor stet et consetetur dolores. Justo stet diam ipsum lorem vero clita diam</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-5">
                <h3 class="text-white mb-4">Get In Touch</h3>
                <p><i class="fa fa-map-marker-alt mr-2"></i>SE1641,FPT university, Ha Noi</p>
                <p><i class="fa fa-phone-alt mr-2"></i>0866405629</p>
                <p><i class="fa fa-envelope mr-2"></i>coursehay@gmail.com</p>
                <div class="d-flex justify-content-start mt-4">
                    <a class="text-white mr-4" href="https://www.facebook.com/CodegangZ" target="_blank"><i class="fab fa-2x fa-facebook-f"></i></a>
                    <a class="text-white" href="https://www.instagram.com/codegangz/" target="_blank"><i class="fab fa-2x fa-instagram"></i></a>
                </div>
            </div>
            <div class="col-md-4 mb-5">
                <h3 class="text-white mb-4">Our Courses</h3>
                <div class="d-flex flex-column justify-content-start">
                    <a class="text-white-50 mb-2" href="<%=path%>/course"><i class="fa fa-angle-right mr-2"></i>Courses</a>
                    <a class="text-white-50" href="<%=path%>/blogview"><i class="fa fa-angle-right mr-2"></i>Blog</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid bg-dark text-white-50 border-top py-4" style="border-color: rgba(256, 256, 256, .1) !important;">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center text-md-left mb-3 mb-md-0">
                <p class="m-0">Copyright &copy; <a class="text-white" href="#">CourseHay</a>. All Rights Reserved.
                </p>
            </div>
            <div class="col-md-6 text-center text-md-right">
                <p class="m-0">Designed by <a class="text-white" href="https://htmlcodex.com">HTML Codex</a>
                </p>
            </div>
        </div>
    </div>
</div>
<!-- Footer End -->


<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary rounded-0 btn-lg-square back-to-top"><i class="fa fa-angle-double-up"></i></a>


<!-- JavaScript Libraries -->
<link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
<script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/counterup/counterup.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<!-- Template Javascript -->
<script src="js/main.js"></script>
<script src="js/myJs.js"></script>
</body>

</html>
<script>
    window.onload = function () {
        if ('${msgMess}' !== '') {
            alert('${msgMess}');
        }
        if ('${recId}' !== '') {
            $('#frmInfor').css({'display': 'none'});
            $('#frmCreateOrder1').css({'display': 'block'});
        } else {
            $('#frmInfor').css({'display': 'block'});
            $('#frmCreateOrder1').css({'display': 'none'});
        }
    };
</script>
<%request.getSession().removeAttribute("msgMess");%>
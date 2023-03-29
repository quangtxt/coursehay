<div id="footer-admin"></div>
</div>
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
    };
</script>
<%request.getSession().removeAttribute("msgMess");%>
<%@include file="template/header.jsp" %>
<div style="padding: 0 4.8rem;margin-bottom: 6.4rem">
    <div class="">
        <h1 class="text-left">Shopping Cart</h1>
    </div>
    <div>
        <c:choose>
            <c:when test="${cart==null || cart.size() ==0}">
                <h3 class="text-left">0 Courses in Cart</h3>
                <div style="display: flex;justify-content: center;flex-direction: column; align-items: center;
                     margin-bottom: 3rem;box-shadow: 0 0 2px #d1d7dc;padding: 2.4rem;text-align: center;">
                    <img alt="" width="240" height="180" srcset="https://s.udemycdn.com/browse_components/flyout/empty-shopping-cart-v2.jpg 1x, https://s.udemycdn.com/browse_components/flyout/empty-shopping-cart-v2-2x.jpg 2x">
                    <p >Your cart is empty. Keep shopping to find a course!</p>
                    <a href="<%=path%>/home" class="ud-btn ud-btn-large ud-btn-brand ud-heading-md">
                        <span>Keep shopping</span>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <h3 class="text-left">${cart.size()} Courses in Cart</h3>
                <div style="display: flex">
                    <div style="margin: 10px">
                        <c:set var="totalPrice" value="0"/>
                        <c:forEach items="${cart}" var="id">
                            <c:set var="c" value="${couDAO.getCourseById(id)}"/>
                            <c:set var="totalPrice" value="${totalPrice+c.getPrice()}"/>
                            <div style="padding: 1.6rem 0;border-top: 1px solid #d1d7dc;display: flex;">
                                <div style="width: 130px;height: 130px;margin: 5px 10px 5px 5px;">
                                    <img class="img-fluid" src="data:image/jpg;base64,${couDAO.createImgurl(c.getImg())}" alt="">
                                </div>
                                <div class="courses-text" style="margin: 10px;width: 500px">
                                    <h3 class="text-left">${c.getCourseName()}</h3>
                                    <div class="text-left">
                                        <span ><i class="fa fa-user mr-2 text-left"></i>
                                            ${conDAO.getAccContactById(c.getAuthorId()).getFullName()}
                                        </span>
                                    </div>
                                    <h6 class="text-left" style="margin-top: 5px;">Rate: ${c.getRate()}<i class="fa fa-star mr-2"></i></h6>
                                </div>
                                <div style="margin-top: 0;padding-left: 2.4rem; grid-area: actions;gap: 0; display: flex;flex-direction: column;align-items: flex-end;justify-content: flex-start;">
                                    <a href="cart?action=remove&courseID=${c.getCourseId()}">remove</a><br/>
                                    <a href="cart?action=removeTWL&courseID=${c.getCourseId()}">remove to wishlist</a>
                                </div>
                                <div style="width: 200px">
                                    ${c.getPrice()} VND
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div>
                        <form action="vnpay_payment" id="frmCreateOrder" method="post" style="width: 350px">
                            <div style="display: flex;align-items: center;flex-wrap: wrap;padding-bottom: 0.8rem;">
                                <div style="width: 100%;flex-direction: column;align-items: flex-start;margin: 10px">
                                    <div >Total:</div>
                                    <input class="form-control" data-val="true" id="amount" name="amount" type="number" value="${totalPrice}" readonly/>
                                </div>
                            </div>
                            <h4 class="text-left">Select a payment method</h4>
                            <div class="form-group text-left">
                                <input type="radio" Checked="True" id="bankCode" name="bankCode" value="">
                                <label for="bankCode">VNPAYQR payment gateway</label><br>
                                <input type="radio" id="bankCode" name="bankCode" value="VNPAYQR">
                                <label for="bankCode">Pay with applications that support VNPAYQR</label><br>
                                <input type="radio" id="bankCode" name="bankCode" value="VNBANK">
                                <label for="bankCode">Payment via ATM card/Domestic account</label><br>
                                <input type="radio" id="bankCode" name="bankCode" value="INTCARD">
                                <label for="bankCode">Payment via international card</label><br>
                            </div>
                            <input class="text-white" style="width: 100%;background-color: #a435f0;" type="submit" value="Checkout"> 
                        </form>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script type="text/javascript">
    $("#frmCreateOrder").submit(function () {
        var postData = $("#frmCreateOrder").serialize();
        var submitUrl = $("#frmCreateOrder").attr("action");
        $.ajax({
            type: "POST",
            url: submitUrl,
            data: postData,
            dataType: 'JSON',
            success: function (x) {
                if (x.code === '00') {
                    if (window.vnpay) {
                        vnpay.open({width: 768, height: 600, url: x.data});
                    } else {
                        location.href = x.data;
                    }
                    return false;
                } else {
                    alert(x.Message);
                }
            }
        });
        return false;
    });
</script>
<%@include file="template/footer.jsp" %>

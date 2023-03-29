<%@include file="template/header.jsp" %>
<div style="padding: 0 4.8rem;margin-bottom: 6.4rem">
    <div class="">
        <h1 >Gift a course</h1>
    </div>
    <div>
        <div style="display: flex">
            <div>
                <form action="" method="post" id="frmInfor">
                    <div style="min-width: 18rem;max-width: 60rem;margin-top: 10px">
                        <label style="display: flex;align-items: center;margin: 0;padding-bottom: 0.8rem;min-height: 2.8rem;">Recipient's Name:</label>
                        <input aria-invalid="false" name="recipient_name" maxlength="64" minlength="2" required="" placeholder="Van Quang" id="form-group--1" type="text" style="width:100%;padding: 0 1.6rem" class="text-left" value="">
                    </div>
                    <div style="min-width: 18rem;max-width: 60rem;margin-top: 10px">
                        <label style="display: flex;align-items: center;margin: 0;padding-bottom: 0.8rem;min-height: 2.8rem;">Recipient's Email:</label>
                        <input aria-invalid="false" name="recipient_email" maxlength="64" minlength="2" required="" placeholder="course@mail.com" id="form-group--1" type="text" style="width:100%;padding: 0 1.6rem" class="text-left" value="">
                    </div>
                    <div style="min-width: 18rem;max-width: 60rem;margin-top: 10px">
                        <label style="display: flex;align-items: center;margin: 0;padding-bottom: 0.8rem;min-height: 2.8rem;">Your Message (optional):</label>
                        <textarea rows="2" aria-invalid="false" name="message" type="text" maxlength="256" placeholder="Add your personal message here" style="width:100%;padding: 0 1.6rem" class="text-left"></textarea>
                    </div>
                    <input class="btn text-white" style="background-color: #a435f0;margin-top: 10px" type="submit" value="Proceed to Checkout"> 
                    <input type="hidden" name="couId" value="${c.getCourseId()}">
                </form>
                <form action="vnpay_payment" id="frmCreateOrder1" method="post" style="width: 30rem">
                    <div style="display: flex;align-items: center;flex-wrap: wrap;padding-bottom: 0.8rem;">
                        <div style="width: 100%;flex-direction: column;align-items: flex-start;margin: 10px">
                            <div >Price:</div>
                            <input class="form-control" data-val="true" id="amount" name="amount" type="number" value="${c.getPrice()}" readonly/>
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
                    <c:if test="${mess!=null}">
                        <input type="hidden" name="message" value="${mess}">
                    </c:if>
                    <input type="hidden" name="recId" value="${recId}">
                    <input type="hidden" name="couId" value="${c.getCourseId()}">
                </form>
            </div>
            <div style="margin: 10px">
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
                    <div style="width: 200px">
                        ${c.getPrice()} VND
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#frmCreateOrder1").submit(function () {
        var postData = $("#frmCreateOrder1").serialize();
        var submitUrl = $("#frmCreateOrder1").attr("action");
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

<%@include file="template/header.jsp" %>
<div style="height: 620px">
    <div class="cotn_principal">
        <div class="cont_centrar">
            <div class="cont_forgot">
                <div class="cont_info_forgot">
                    <div class="col_md_forgot">
                        <div class="cont_ba_opcitiy">
                            <h2>Change Password</h2>
                            <p></p>
                            <c:if test="${msg!=null}">
                                <body onload="cambiar_forgot()">
                                </c:if>
                                <c:if test="${msg==null}">
                                    <button class="btn_forgot" onclick="cambiar_forgot()">Change</button>
                                </c:if>
                        </div>
                    </div>
                </div>
                <div class="cont_back_info">
                    <div class="cont_img_back_grey">
                        <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
                             alt="" />
                    </div>
                </div>
                <div class="cont_forms_forgot">
                    <div class="cont_img_back_">
                        <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
                             alt="" />
                    </div>
                    <div  class="cont_form_forgot">
                        <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
                        <h2 style="margin-top: 70px">Change Password</h2>
                        <form action="pass" method="post" class="form_content">
                            <c:if test="${msg != null}">
                                <span class="msg-error" style="color: red">${msg}<br/></span>
                                </c:if>
                                <c:if test="${msgSuccess != null}">
                                <span style="color: green">${msgSuccess}<br/></span>
                                </c:if>
                            <input style="width: 320px" type="text" placeholder="Old Password"  name="txtOldPass"/>
                            <input style="width: 320px" type="text" placeholder="New Password" name="txtNewPass"/>
                            <input style="width: 320px" type="text" placeholder="Re Password" name="txtReNewPass"/>
                            <div><input class="btn_login" type="submit" value="Submit"/><br/></div>
                            <div class="error" style="color: red">
                            </div>
                            <input type="hidden" name="otp" value="change">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>    
<script>

    function cambiar_forgot() {
        document.querySelector('.cont_forms_forgot').className = "cont_forms_forgot cont_forms_active_forgot";
        document.querySelector('.cont_form_forgot').style.display = "block";
        setTimeout(function () {
            document.querySelector('.cont_form_forgot').style.opacity = "1";
        }, 400);
    }
    function ocultar_login_sign_up() {
        document.querySelector('.cont_forms_forgot').className = "cont_forms_forgot";
        document.querySelector('.cont_form_forgot').style.opacity = "0";
        setTimeout(function () {
            document.querySelector('.cont_form_forgot').style.display = "none";
        }, 500);

    }
</script>
<%@include file="template/footer.jsp" %>
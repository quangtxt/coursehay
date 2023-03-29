<%@include file="template/header.jsp" %>
<div style="height: 566px">
    <div class="cotn_principal">
        <div class="cont_centrar">
            <div class="cont_login">
                <div class="cont_info_log_sign_up">
                    <div class="col_md_login">
                        <div class="cont_ba_opcitiy">
                            <h2>LOGIN</h2>
                            <p></p>
                            <button class="btn_login" onclick="cambiar_login()">LOGIN</button>
                        </div>
                    </div>
                    <div class="col_md_sign_up">
                        <div class="cont_ba_opcitiy">
                            <h2>SIGN UP</h2>
                            <p></p>
                            <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>
                        </div>
                    </div>
                </div>
                <div class="cont_back_info">
                    <div class="cont_img_back_grey">
                        <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
                             alt="" />
                    </div>
                </div>
                <div class="cont_forms">
                    <div class="cont_img_back_">
                        <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
                             alt="" />
                    </div>
                    <div  class="cont_form_login">
                        <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
                        <h2>LOGIN</h2>
                        <!--                        <button>
                                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:9999/SWP_Project/account&response_type=code
                                                       &client_id=534420992852-gm2j7ih1jluh0cl5o92eroo7sj18krpp.apps.googleusercontent.com&approval_prompt=force">Google login</a>
                                                </button>-->
                        <form action="<%=path%>/account" method="post" class="form_content">
                            <c:if test="${msg != null}">
                                <span class="msg-error">${msg}<br/></span>
                                </c:if>
                            <input type="text" name="txtEmail" placeholder="email"/><br/>
                            <input type="password" name="txtPassword" placeholder="password"/><br/>
                            <div><input class="btn_login" type="submit" value="LOGIN"/><br/></div>
                            <div><a href="<%=path%>/account/pass?otp=forgot">Forgot password?</a></div>
                            <div class="error" style="color: red">
                            </div>
                        </form>
                    </div>
                    <div class="cont_form_sign_up">
                        <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
                        <h2>SIGN UP</h2>
                        <form action="<%=path%>/account/signup" method="POST" class="form_content">
                            <c:if test="${msgSignup != null}">
                                <span>${msgSignup}</span>
                            </c:if><br/>
                            <input type="text" placeholder="Full name" name="txtFullName" /><br/>
                            <input type="text" placeholder="Email" name="txtEmail" /><br/>
                            <input type="password" placeholder="Password" name="txtPassword" /><br/>
                            <input type="password" placeholder="Confirm Password" name="txtConfirmPassword" /><br/>
                            <input type="text" placeholder="Phone" name="txtPhone" /><br/>
                            <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    /* ------------------------------------ Click on login and Sign Up to  changue and view the effect
     ---------------------------------------
     */

    function cambiar_login() {
        document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
        document.querySelector('.cont_form_login').style.display = "block";
        document.querySelector('.cont_form_sign_up').style.opacity = "0";
        setTimeout(function () {
            document.querySelector('.cont_form_login').style.opacity = "1";
        }, 400);

        setTimeout(function () {
            document.querySelector('.cont_form_sign_up').style.display = "none";
        }, 200);
    }

    function cambiar_sign_up(at) {
        document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
        document.querySelector('.cont_form_sign_up').style.display = "block";
        document.querySelector('.cont_forms_active_sign_up').style.height = "560px";
        document.querySelector('.cont_form_login').style.opacity = "0";

        setTimeout(function () {
            document.querySelector('.cont_form_sign_up').style.opacity = "1";
        }, 100);

        setTimeout(function () {
            document.querySelector('.cont_form_login').style.display = "none";
        }, 400);


    }



    function ocultar_login_sign_up() {

        document.querySelector('.cont_forms').className = "cont_forms";
        document.querySelector('.cont_form_sign_up').style.opacity = "0";
        document.querySelector('.cont_form_login').style.opacity = "0";

        setTimeout(function () {
            document.querySelector('.cont_form_sign_up').style.display = "none";
            document.querySelector('.cont_form_login').style.display = "none";
        }, 500);

    }




</script>
<%@include file="template/footer.jsp" %>
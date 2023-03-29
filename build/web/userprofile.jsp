<%@include file="template/header.jsp" %> 
<div class="container">
    <div class="main-body_profile btn-info">

        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card_profile">
                    <div class="card-body_profile" style="width: auto;">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${con.getFullName()}</h4>
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
                                <h6 class="mb-0">Full Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${con.getFullName()}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${AccSession.getEmail()}
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
                        <hr>
                        <div class="row">
                            <div class="col-sm-6">
                                <a class="btn btn-info " href="editprofile">Edit profile</a>
                            </div>
                            <div class="col-sm-6">
                                <a class="btn btn-info " href="pass?otp=change">Change password</a>
                            </div>
                            ${msg}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp"%>
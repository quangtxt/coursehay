<%@include file="template/header.jsp" %> 
<div class="container">
    <div class="main-body">
        <div class="row">
            <div class="col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="Admin" class="rounded-circle p-1 bg-primary" width="110">
                            <div class="mt-3">
                                <h4>${con.getFullName()}</h4>
                            </div>
                        </div>
                        <hr class="my-4">
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card">
                    <form action="" method="post">
                        <div class="card-body">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Full Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="txtName" value="${con.getFullName()}">
                                    <c:if test="${msgFullName != null}">
                                        <span class="msg-error">${msgFullName}<br/></span>
                                        </c:if>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Gender</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="radio" name="txtGender"  value="Male" ${con.isGender() == "true"?"checked":""}> Female
                                    <input type="radio" name="txtGender" value="Female" ${con.isGender() == "false"?"checked":""}> Male
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Date of Birth</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="date" class="form-control" name="txtDob" value="${con.getDob()}">
                                    <c:if test="${msgDob != null}">
                                        <span class="msg-error">${msgDob}<br/></span>
                                        </c:if>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Phone</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="txtPhone" value="${con.getPhone()}">
                                    <c:if test="${msgPhone != null}">
                                        <span class="msg-error">${msgPhone}<br/></span>
                                        </c:if>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 style="width: 60px" class="mb-0">Address</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="txtAddress" value="${con.getAddress()}">
                                </div>
                            </div>
                            <div>
                                <div class=" text-secondary">
                                    <input type="submit" class="btn btn-primary px-4" value="Save Changes">
                                    <a href="<%=path%>/account/profile"><input type="button" class="btn btn-primary px-4" value="Cancel"></a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<style type="text/css">
    body{
        background: #f7f7ff;
    }
    .card {
        margin-top: 20px;
        position: relative;
        display: flex;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 0 solid transparent;
        border-radius: .25rem;
        margin-bottom: 1.5rem;
        box-shadow: 0 2px 6px 0 rgb(218 218 253 / 65%), 0 2px 6px 0 rgb(206 206 238 / 54%);
    }
    .me-2 {
        margin-right: .5rem!important;
    }
</style>

<script type="text/javascript">

</script>
<%@include file="template/footer.jsp" %>
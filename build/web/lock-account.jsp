<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <c:if test="${role != 1}">
            <c:redirect url="index.jsp"/>
        </c:if>

        <div id="container">
            <div id="header">
                <div style="color: blue" id="logo-admin">
                    <a href="admin-dashboard.jsp" style="font-size: 20px">Ecommerce Admin</a>
                    
                </div>
                <div id="banner-admin">
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/account">SignOut</a></li>
                    </ul>
                </div>
            </div>
            <div id="content" style="background-color: #2b2b59">
                <div class="student-profile py-4">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-3">
                                <div class="card shadow-sm">
                                    <div class="card-header bg-transparent text-center">
                                        <img class="profile_img" src="img/team-2.jpg" alt="student dp" style="width: 100%">

                                        <h3></h3>
                                    </div>
                                    <div class="card-body">
                                        <p class="mb-0"><strong class="pr-1">Name:</strong>${accContact.getFullName()}</p>

                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-9">
                                <div class="card shadow-sm">
                                    <div class="card-header bg-transparent border-0">
                                        <h3 class="mb-0"><i class="far fa-clone pr-1"></i>History Lock</h3>
                                    </div>
                                    <div class="card-body pt-0" style="padding: 0; margin: 0">
                                        <table class="table" style="border: none; color: black">

                                            <tr>
                                                <th width="30%" style="background-color: #886fad; color: white">Ban By ADMIN</th>
                                                <th width="30%" style="background-color: #60b0aa; color: white">Date Lock	</th>
                                                <th width="30%" style="background-color: #886fad; color: white">Date UnLock	</th>
                                                <th width="10%" style="background-color: #60b0aa; color: white">Times Lock	</th>
                                                <th width="50%" style="background-color: #60b0aa; color: white">Reason	</th>                                                
                                            </tr>
                                            <c:if test="${hislistSize <= 0}">
                                                <tr><th colspan="6">No results</th></tr>
                                                    </c:if>
                                                    <c:if test="${hislistSize > 0 }">
                                                        <c:forEach items="${hislist}" var="c">
                                                    <tr>
                                                        <td>${admContact.getFullName()}</td>
                                                        <td>${c.getLockDate()}</td> 
                                                        <td>${c.getUnlockDate()}</td>
                                                        <td>${c.getLockTimes()}</td>
                                                        <td>${c.getLockReason()}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if> 
                                        </table>
                                    </div>
                                </div>
                                <div style="height: 26px"></div>
                                <div class="card shadow-sm">
                                    <div class="card-header bg-transparent border-0">

                                    </div>
                                    <div class="card-body pt-0">
                                        <c:if test="${!acc.isStatus()}">
                                            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Do you want to ban this account ??</h3>
                                            <form action="lock_acc" method="post">
                                            <textarea style="width: 100%;
                                                      height: 150px;
                                                      padding: 12px 20px;
                                                      box-sizing: border-box;
                                                      border: 2px solid #ccc;
                                                      border-radius: 4px;
                                                      background-color: #f8f8f8;
                                                      font-size: 16px;
                                                      resize: none;" name="ReasonBan" placeholder="( REQUIRED!!! )Typing the reason here .... "></textarea>
                                                <input type="hidden" name="accid" value="${acc.getAccountID()}"/>
                                                <input type="hidden" name="adminid" value="${admContact.getAccountID()}"/>
                                                <input type="hidden" name="status" value="true"/>
                                                <input style="display: inline-block;
                                                   padding: 10px 20px;
                                                   font-size: 24px;
                                                   cursor: pointer;
                                                   text-align: center;
                                                   text-decoration: none; 
                                                   outline: none;
                                                   color: #fff;
                                                   background-color: #4CAF50;
                                                   border: none;
                                                   border-radius: 15px;
                                                   box-shadow: 0 9px #999;" type="submit" value="BAN" name="ban"id="button-ban" />
                                            </form>
                                        </c:if>
                                        <c:if test="${acc.isStatus()}">
                                            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>This account still baning !!<br> Do you want to unban ??</h3>
                                            
                                            <form action="lock_acc" method="post">
                                                <textarea style="width: 100%;
                                                      height: 150px;
                                                      padding: 12px 20px;
                                                      box-sizing: border-box;
                                                      border: 2px solid #ccc;
                                                      border-radius: 4px;
                                                      background-color: #f8f8f8;
                                                      font-size: 16px;
                                                      resize: none;" name="ReasonBan" placeholder="( REQUIRED!!! )Typing the reason here .... " hidden></textarea>
                                            <input type="hidden" name="status" value="false"/>
                                            <input type="hidden" name="accid" value="${acc.getAccountID()}"/>
                                            <input type="hidden" name="adminid" value="${admContact.getAccountID()}"/>
                                            <input style="display: inline-block;
                                                   padding: 10px 20px;
                                                   font-size: 24px;
                                                   cursor: pointer;
                                                   text-align: center;
                                                   text-decoration: none; 
                                                   outline: none;
                                                   color: #fff;
                                                   background-color: #4CAF50;
                                                   border: none;
                                                   border-radius: 15px;
                                                   box-shadow: 0 9px #999;" type="submit" value="UNBAN" name="ban"id="button-ban" />
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>    

            </div>


            <div id="footer-admin"></div>
        </div>

    </body>
</html>
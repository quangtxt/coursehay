<%@include file="template/headerAdmin.jsp" %>

<div id="content-right">
    <div class="path-admin">Author Account</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-2" style="width: 55%; padding: 16px">
                    <form action="manage-author" method="post">
                        <input type="text" name="txtSearch" placeholder="Enter name to search" value="${txtSearch}"/>
                        <input type="submit" value="Search"/>
                    </form>
                </div>
                <div id="author-table-admin">
                    <table id="authors" border="1">
                        <tr>
                            <th>FullName</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>   Dob   </th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${aulist}" var="c">
                            <tr>
                                <td>${accDAO.getAccountByAuID(c.getAuthorId()).getFullName()}</td>
                                <td>${accDAO.getAccountByAuID(c.getAuthorId()).getEmail()}</td>
                                <td>${c.getGender()}</td>
                                <td>${c.getDob()}</td>
                                <td>${c.getPhone()}</td>
                                <td>${c.getAddress()}</td> 
                                <td>                                    
                                        <a href="lock_acc?accid=${accDAO.getAccountByAuID(c.getAuthorId()).getAccountID()}">
                                            <c:if test="${accDAO.getStatusByAccId(accDAO.getAccountByAuID(c.getAuthorId()).getAccountID()).isStatus()== true}">
                                                <img src="img/lock_img.png" width="40" height="40" alt="lock_img"/>
                                            </c:if>
                                            <c:if test="${accDAO.getStatusByAccId(accDAO.getAccountByAuID(c.getAuthorId()).getAccountID()).isStatus()== false}">
                                                <img src="img/unlock_img.png" width="40" height="40" alt="lock_img"/>
                                            </c:if>                                            
                                        </a>

                                </td>
                                
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="template/footerAdmin.jsp" %>
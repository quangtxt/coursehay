<%@include file="template/headerAdmin.jsp" %>

<div id="content-right">
    <div class="path-admin">Purchaser Account</br></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-2" style="width: 55%; padding: 16px">
                    <form action="manage-purchaser" method="post">
                        <input style="width: 300px" type="text" name="txtSearch" placeholder="Enter to search" value="${txtSearch}"/>
                         
                        <select style="padding: 12px; border-radius: 5px " name="typeSearch">
                            <option <c:if test="${typeSearch == 'name'}"> selected </c:if> value="name">Name</option>
                            <option  <c:if test="${typeSearch == 'email'}"> selected </c:if>value="email">Email</option>
                        </select>
                        <select style="padding: 12px; border-radius: 5px " name="status">
                            <option <c:if test="${status == 0}"> selected </c:if> value="0">Not Ban</option>
                            <option <c:if test="${status == 1}"> selected </c:if> value="1">Baned</option>
                        </select><br>
                        <input style="padding: 8px; border-radius: 5px ;margin-top: 20px" type="submit" value="Filter"/>
                    </form>
                </div>
                <c:if test="${accountList.size()>0}">
                <div id="purchaser-table-admin">
                    <table id="purchaser" border="1">
                        <tr>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>   Dob   </th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${accountList}" var="c">
                            <tr>
                                <td>${contactDAO.getAccContactById(c).getFullName()}</td>
                                <td>${accDAO.getAccountById(c).getEmail()}</td>
                                <td>${!contactDAO.getAccContactById(c).isGender()?"Male":"Female"}</td>
                                <td>${contactDAO.getAccContactById(c).getDob()}</td>
                                <td>${contactDAO.getAccContactById(c).getPhone()}</td>
                                <td>${contactDAO.getAccContactById(c).getAddress()}</td> 
                                <td>                                    
                                        <a href="lock_acc?accid=${c}">
                                            <c:if test="${accDAO.getAccountById(c).isStatus()}">
                                                <img src="img/lock_img.png" width="40" height="40" alt="lock_img"/>
                                            </c:if>
                                            <c:if test="${!accDAO.getAccountById(c).isStatus()}">
                                                <img src="img/unlock_img.png" width="40" height="40" alt="lock_img"/>
                                            </c:if>                                            
                                        </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                </c:if>
                <c:if test="${purlist.size()>0}">
                    <h3 style="color: gray">The list of accounts with courses with low votes.</h3>
                <div id="purchaser-table-admin">
                    <table id="purchaser" border="1">
                        <tr>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>   Dob   </th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${purlist}" var="c">
                            <tr>
                                <td>${contactDAO.getAccContactById(c).getFullName()}</td>
                                <td>${accDAO.getAccountById(c).getEmail()}</td>
                                <td>${!contactDAO.getAccContactById(c).isGender()?"Male":"Female"}</td>
                                <td>${contactDAO.getAccContactById(c).getDob()}</td>
                                <td>${contactDAO.getAccContactById(c).getPhone()}</td>
                                <td>${contactDAO.getAccContactById(c).getAddress()}</td> 
                                <td>                                    
                                        <a href="lock_acc?accid=${c}">
                                            <c:if test="${accDAO.getAccountById(c).isStatus()}">
                                                <img src="img/lock_img.png" width="40" height="40" alt="lock_img"/>
                                            </c:if>
                                            <c:if test="${!accDAO.getAccountById(c).isStatus()}">
                                                <img src="img/unlock_img.png" width="40" height="40" alt="lock_img"/>
                                            </c:if>                                            
                                        </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                </c:if>
                
            </div>
        </div>
    </div>
</div>

<%@include file="template/footerAdmin.jsp" %>
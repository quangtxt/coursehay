package controllers;

import dal.Config;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.Account;
import dal.Course;
import dal.Mail;
import dal.Notification;
import dal.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import models.AccountDAO;
import models.CartDAO;
import models.ContactDAO;
import models.CoursesDAO;
import models.NotificationDAO;
import models.OrderDAO;

/**
 *
 * @author DELL
 */
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = ((Account) req.getSession().getAttribute("AccSession"));
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = req.getParameter("ordertype");
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;
        String vnp_Returnurl = Config.vnp_Returnurl;
        int amount = Integer.parseInt(req.getParameter("amount")) * 100;
        Map vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        String bank_code = req.getParameter("bankCode");
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Payment orders:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "en");
        }
        if (req.getParameter("recId") == null) {
            vnp_Returnurl += "id=" + acc.getAccountID();
        } else {
            vnp_Returnurl += "id=" + acc.getAccountID() + "&recId=" + req.getParameter("recId") + "&couId=" + req.getParameter("couId") + "&mess=" + req.getParameter("message");
        }
        vnp_Params.put("vnp_ReturnUrl", vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        com.google.gson.JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(job));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("vnp_Amount") == null) {
            resp.sendRedirect("home");
        } else {
            if (req.getParameter("vnp_ResponseCode").equals("00")) {
                int accountId = Integer.parseInt(req.getParameter("id"));
                String text = "";
                if (req.getParameter("recId") != null) {
                    int recId = Integer.parseInt(req.getParameter("recId"));
                    int couId = Integer.parseInt(req.getParameter("couId"));
                    Course c = new CoursesDAO().getCourseById(couId);
                    String mess = req.getParameter("mess");
                    Order ord = new Order(recId, couId, new OrderDAO().randomString(8), Date.valueOf(LocalDate.now()), accountId, mess);
                    new OrderDAO().addOrderGift(ord);
                    Account giftAcc = new AccountDAO().getAccountById(ord.getGiftAccId());
                    text = "Account:" + new ContactDAO().getAccContactById(giftAcc.getAccountID()).getFullName()
                            + " with email: " + giftAcc.getEmail() + " just gave you the course: \"" + c.getCourseName() + "\".\n"
                            + "You can use the following code to activate the course.\n"
                            + "Code: " + ord.getCode() + "\n";
                    Mail.SenMailCourseCode(ord.getAccountId(), text);
                    req.getSession().setAttribute("msgMess", "Successfully gifted the course");
                    resp.sendRedirect(req.getContextPath() + "/gift-course?couId=" + couId);
                } else {
                    List<Integer> list = new CartDAO().getCourseIDInCartByAccountId(accountId);
                    int course0 = list.get(0);
                    for (Integer couId : list) {
                        Course c = new CoursesDAO().getCourseById(couId);
                        Order ord = new Order(accountId, couId, Date.valueOf(LocalDate.now()), new OrderDAO().randomString(8));
                        new OrderDAO().addOrderBuy(ord);
                        text += "Courses Hay send you code to enroll course \"" + c.getCourseName() + "\": " + ord.getCode() + "\n";
                        new CartDAO().RemoveCoureInCart(accountId, couId);
                        String m = new ContactDAO().getAccContactById(accountId).getFullName()+" just bought your \""+c.getCourseName()+"\" course";
                        Notification ni = new Notification(c.getAuthorId(),m , 0, 0, c.getCourseId());
                        new NotificationDAO().createNotification(ni, 2);
                    }
                    Mail.SenMailCourseCode(accountId, text);
                    req.getSession().setAttribute("msgMess", "The system has sent the code to your email."
                            + "Please check your email and enter the code of each course to enroll the course");
                    resp.sendRedirect(req.getContextPath() + "/coursedetail?CourseId=" + course0);
                }
            } else {
                req.getSession().setAttribute("msgMess", "Transaction failed due to: Customer cancels transaction");
                resp.sendRedirect(req.getContextPath() + "/cart");
            }
        }
    }
}

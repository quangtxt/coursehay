/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author admin
 */
public class CoursesDAO extends DBContext {

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM courses";
            PreparedStatement ps = connection.prepareStatement(sql);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public Course getCourseById(int id) {
        Course c = null;
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM courses WHERE CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                int AuthorId = rs.getInt("AuthorId");
                int TopicId = rs.getInt("TopicId");
                String CourseName = rs.getString("CourseName");
                String CourseTitle = rs.getString("CourseTitle");
                String CourseDescription = rs.getString("CourseDescription");
                int PublicStatus = rs.getInt("PublicStatus");
                Date PublicDate = rs.getDate("PublicDate");
                byte[] Img = rs.getBytes("Img");
                double Rate = rs.getDouble("Rate");
                int Price = rs.getInt("Price");
                c = new Course(CourseId, AuthorId, TopicId, CourseName, CourseTitle, CourseDescription, PublicStatus, PublicDate, Img, Rate, Price);
            }
        } catch (SQLException e) {
        }
        return c;
    }

    public int CountUserBuyCourse(int courseId) {
        try {
            String sql = "select count(AccountId) from order where CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int createCourse(Course course, int id) {
        int result = 0;
        try {
            String sql = "insert into courses(AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,Price) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, course.getTopicId());
            ps.setString(3, course.getCourseName());
            ps.setString(4, course.getCourseTitle());
            ps.setString(5, course.getCourseDescription());
            ps.setDouble(6, course.getPrice());
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public int EditCourse(Course course, int courseid) {
        int result = 0;
        try {
            String sql = "update courses set topicid= ?, courseName = ?, CourseTitle=?, CourseDescription= ?,PublicStatus = ?,Price= ?,SubmitDate =?  where courseid = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, course.getTopicId());
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getCourseTitle());
            ps.setString(4, course.getCourseDescription());
            ps.setInt(5, course.getPublicStatus());
            if (course.getPublicStatus() == 1) {
                ps.setDouble(6, course.getPrice());
                ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setDouble(6, course.getPrice());
                ps.setTimestamp(7, null);
            }
            ps.setInt(8, courseid);
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public static String createImgurl(byte[] inputStream) throws IOException {
        String Img = null;
        try {

            Img = Base64.getEncoder().encodeToString(inputStream);

        } catch (Exception e) {

        }
        return Img;
    }

    public ArrayList<Course> getCoursesByAccountID(int accountid) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "select CourseId,CourseName,img, PublicStatus from courses where authorid =? ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                String CourseName = rs.getString("CourseName");
                int PublicStatus = rs.getInt("PublicStatus");
                byte[] Img = rs.getBytes("Img");
                courses.add(new Course(CourseId, CourseName, PublicStatus, Img));
            }
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getTopCoursesBuyer(int i) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "select b.CourseId,a.AuthorId,a.CourseName,a.img,a.Rate,a.Price from courses as a right join swp_dtb.order as b on a.CourseId=b.CourseId group by b.CourseId order by count(b.AccountId) desc limit ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                int AuthorId = rs.getInt("AuthorId");
                String CourseName = rs.getString("CourseName");
                byte[] Img = rs.getBytes("Img");
                double Rate = rs.getDouble("Rate");
                int Price = rs.getInt("Price");
                courses.add(new Course(CourseId, AuthorId, CourseName, Img, Rate, Price));
            }
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> Search(String txtSearch) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses where CourseName like ? and PublicStatus = 2;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getCourseByCateId(int cateId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses where PublicStatus = 2 and TopicId in (select TopicId from cate_topic where CateId = ? );";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cateId);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getCourseByTopicId(int topicId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses where PublicStatus = 2 and TopicId  = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, topicId);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getArrayListCourses(PreparedStatement ps) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                int AuthorId = rs.getInt("AuthorId");
                int TopicId = rs.getInt("TopicId");
                String CourseName = rs.getString("CourseName");
                String CourseTitle = rs.getString("CourseTitle");
                String CourseDescription = rs.getString("CourseDescription");
                int PublicStatus = rs.getInt("PublicStatus");
                Date PublicDate = rs.getDate("PublicDate");
                byte[] Img = rs.getBytes("Img");
                double Rate = rs.getDouble("Rate");
                int Price = rs.getInt("Price");
                courses.add(new Course(CourseId, AuthorId, TopicId, CourseName, CourseTitle, CourseDescription, PublicStatus, PublicDate, Img, Rate, Price));
            }
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getAllCourseInWishlistByAccountId(int accid) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "select * from courses as c , wishlist as w where c.CourseId = w.CourseId and w.AccountId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accid);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public int RemoveCoureInWishlist(int accId, int course) {
        int result = 0;
        String sql = "Delete FROM swp_dtb.wishlist Where AccountId = ? and CourseId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accId);
            st.setInt(2, course);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public int checkExistInWishlist(int accid, int course) {
        int result = 0;
        ArrayList<Wishlist> list = new ArrayList<>();

        String sql = "Select * from wishlist Where `AccountId` = ? and `CourseId` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accid);
            st.setInt(2, course);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                int CourseId = rs.getInt("CourseId");
                list.add(new Wishlist(AccountId, CourseId));
                result = list.size();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public byte[] conertToByteArray(InputStream image)
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = image.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    public int EditCourseImg(InputStream filecontent, int courseid) {
        byte[] img = null;
        try {
            img = new CoursesDAO().conertToByteArray(filecontent);
        } catch (Exception e) {
        }
        int result = 0;
        try {
            String sql = "update courses set img = ?  where courseid = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBytes(1, img);
            ps.setInt(2, courseid);
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public int DeteteCourse(int courseid) {
        int result = 0;
        try {
            String sql = "DELETE FROM courses WHERE courseid = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseid);
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public Course getCourseByCoursesID(int courseid) throws IOException {
        Course c = null;
        try {
            String sql = "SELECT CourseId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,Img,Price FROM courses WHERE CourseId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                int TopicId = rs.getInt("TopicId");
                String CourseName = rs.getString("CourseName");
                String CourseTitle = rs.getString("CourseTitle");
                String CourseDescription = rs.getString("CourseDescription");
                int PublicStatus = rs.getInt("PublicStatus");
                byte[] inputStream = rs.getBytes("Img");
                int Price = rs.getInt("Price");
                c = new Course(CourseId, TopicId, CourseName, CourseTitle, CourseDescription, PublicStatus, inputStream, Price);
            }
        } catch (SQLException e) {
        }
        return c;
    }

    public ArrayList<Course> getCoursesnotPublic() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM courses where PublicStatus = 0;";
            PreparedStatement ps = connection.prepareStatement(sql);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public void UpdateRate(double rate, int courseid) {
        String sql = "update courses set Rate = ? where courseid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, rate);
            ps.setInt(2, courseid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<Course> getCoursesisSubmit() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM courses where PublicStatus = 1 order by submitdate asc;";
            PreparedStatement ps = connection.prepareStatement(sql);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public void EditPublic(int PublicStatus, int courseid) {
        try {
            String sql = "update courses set PublicStatus = ? where courseid = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, PublicStatus);
            ps.setInt(2, courseid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public int countCoursePublic() {
        String sql = "select count(courseId) from courses where PublicStatus = 2;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int countAuthorInCoursePublic() {
        String sql = "select count(distinct AuthorId) from courses where PublicStatus = 2;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public ArrayList<Integer> getEnrolledCourseByAccountID(int accID) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT CourseId FROM swp_dtb.order where AccountId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("CourseId"));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Course> getCourseByCateIdandAuId(int cateId, int auId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses "
                    + "where PublicStatus = 2 "
                    + "and TopicId in (select TopicId from cate_topic where CateId = ? ) "
                    + "and AuthorId = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cateId);
            ps.setInt(2, auId);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getCourseByTopicIdandAuId(int topicId, int auId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses "
                    + "where PublicStatus = 2 "
                    + "and TopicId  = ? "
                    + "and AuthorId = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.setInt(2, auId);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Integer> getCourseIdByAuID(int auID) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT CourseId FROM swp_dtb.courses where AuthorId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, auID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("CourseId"));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Course> getCoursesByNonCourseIDandAccountID(int couId, int accountId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "select CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price from courses where PublicStatus = 2 and CourseId != ? and AuthorId =? order by Rate desc;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, couId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                int AuthorId = rs.getInt("AuthorId");
                int TopicId = rs.getInt("TopicId");
                String CourseName = rs.getString("CourseName");
                String CourseTitle = rs.getString("CourseTitle");
                String CourseDescription = rs.getString("CourseDescription");
                int PublicStatus = rs.getInt("PublicStatus");
                Date PublicDate = rs.getDate("PublicDate");
                byte[] Img = rs.getBytes("Img");
                double Rate = rs.getDouble("Rate");
                int Price = rs.getInt("Price");
                courses.add(new Course(CourseId, AuthorId, TopicId, CourseName, CourseTitle, CourseDescription, PublicStatus, PublicDate, Img, Rate, Price));
            }
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getCourseByNonCourseIdandTopicId(int couId, int topicId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses where PublicStatus = 2 and CourseId != ? and TopicId  = ? order by Rate desc;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, couId);
            ps.setInt(2, topicId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CourseId = rs.getInt("CourseId");
                int AuthorId = rs.getInt("AuthorId");
                int TopicId = rs.getInt("TopicId");
                String CourseName = rs.getString("CourseName");
                String CourseTitle = rs.getString("CourseTitle");
                String CourseDescription = rs.getString("CourseDescription");
                int PublicStatus = rs.getInt("PublicStatus");
                Date PublicDate = rs.getDate("PublicDate");
                byte[] Img = rs.getBytes("Img");
                double Rate = rs.getDouble("Rate");
                int Price = rs.getInt("Price");
                courses.add(new Course(CourseId, AuthorId, TopicId, CourseName, CourseTitle, CourseDescription, PublicStatus, PublicDate, Img, Rate, Price));
            }
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Integer> getFollowedCourseByAccountID(int accID) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT CourseId FROM swp_dtb.courses WHERE AuthorId IN (SELECT AuthorId FROM user_author WHERE UserId = ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("CourseId"));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Course> getFollowedCourseByCateIdandAuId(int cateId, int auId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses "
                    + "where PublicStatus = 2 "
                    + "and TopicId in (select TopicId from cate_topic where CateId = ? ) "
                    + "and AuthorId IN (SELECT AuthorId FROM user_author WHERE UserId = ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cateId);
            ps.setInt(2, auId);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public ArrayList<Course> getFollowedCourseByTopicIdandAuId(int topicId, int auId) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId,AuthorId,TopicId,CourseName,CourseTitle,CourseDescription,PublicStatus,PublicDate,img,Rate,Price FROM swp_dtb.courses "
                    + "where PublicStatus = 2 "
                    + "and TopicId  = ? "
                    + "and AuthorId IN (SELECT AuthorId FROM user_author WHERE UserId = ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.setInt(2, auId);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

    public int CheckUserHasCourseIsPublic(int accid) {
        int result = 0;
        String sql = "SELECT * FROM swp_dtb.courses where AuthorId = ? and PublicStatus = 2";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result++;
            }
        } catch (SQLException e) {
        }
        return result;
    }
    public ArrayList<Course> getCourseRateLow() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * from swp_dtb.courses Where Rate < 2  and PublicStatus = 2;";
            PreparedStatement ps = connection.prepareStatement(sql);
            courses = new CoursesDAO().getArrayListCourses(ps);
        } catch (SQLException e) {
        }
        return courses;
    }

}

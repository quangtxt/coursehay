/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Course {

    private int CourseId;
    private int AuthorId;
    private int TopicId;
    private String CourseName, CourseTitle, CourseDescription;
    private int PublicStatus;
    private Date PublicDate;
    private byte[] Img;
    private double Rate;
    private int Price;
    private Date SubmitDate;

    public Course() {
    }

    public Course(String CourseName, String CourseTitle, String CourseDescription) {
        this.CourseName = CourseName;
        this.CourseTitle = CourseTitle;
        this.CourseDescription = CourseDescription;
    }

    public Course(int CourseId, int AuthorId, int TopicId, String CourseName, String CourseTitle, String CourseDescription, int PublicStatus, Date PublicDate, byte[] Img, double Rate, int Price) {
        this.CourseId = CourseId;
        this.AuthorId = AuthorId;
        this.TopicId = TopicId;
        this.CourseName = CourseName;
        this.CourseTitle = CourseTitle;
        this.CourseDescription = CourseDescription;
        this.PublicStatus = PublicStatus;
        this.PublicDate = PublicDate;
        this.Img = Img;
        this.Rate = Rate;
        this.Price = Price;
    }

    public Course(int CourseId, int TopicId, String CourseName, String CourseTitle, String CourseDescription, int PublicStatus, byte[] Img, int Price) {
        this.CourseId = CourseId;
        this.TopicId = TopicId;
        this.CourseName = CourseName;
        this.CourseTitle = CourseTitle;
        this.CourseDescription = CourseDescription;
        this.PublicStatus = PublicStatus;
        this.Img = Img;
        this.Price = Price;
    }

    public Course(int CourseId, int AuthorId, String CourseName, byte[] Img, double Rate, int Price) {
        this.CourseId = CourseId;
        this.AuthorId = AuthorId;
        this.CourseName = CourseName;
        this.Img = Img;
        this.Rate = Rate;
        this.Price = Price;
    }

    public Course(int CourseId, String CourseName, int PublicStatus, byte[] Img) {
        this.CourseId = CourseId;
        this.CourseName = CourseName;
        this.PublicStatus = PublicStatus;
        this.Img = Img;
    }

    public Course(int TopicId, String CourseName, String CourseTitle, String CourseDescription, int PublicStatus, byte[] Img) {
        this.TopicId = TopicId;
        this.CourseName = CourseName;
        this.CourseTitle = CourseTitle;
        this.CourseDescription = CourseDescription;
        this.PublicStatus = PublicStatus;
        this.Img = Img;
    }

    public Course(int TopicId, String CourseName, String CourseTitle, String CourseDescription, byte[] Img, int Price) {
        this.TopicId = TopicId;
        this.CourseName = CourseName;
        this.CourseTitle = CourseTitle;
        this.CourseDescription = CourseDescription;
        this.Img = Img;
        this.Price = Price;
    }

    public Course(int TopicId, String CourseName, String CourseTitle, String CourseDescription, int Price) {
        this.TopicId = TopicId;
        this.CourseName = CourseName;
        this.CourseTitle = CourseTitle;
        this.CourseDescription = CourseDescription;
        this.Price = Price;
    }

    public Course(int TopicId, String CourseName, String CourseTitle, String CourseDescription, int PublicStatus, int Price) {
        this.TopicId = TopicId;
        this.CourseName = CourseName;
        this.CourseTitle = CourseTitle;
        this.CourseDescription = CourseDescription;
        this.PublicStatus = PublicStatus;
        this.Price = Price;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int AuthorId) {
        this.AuthorId = AuthorId;
    }

    public int getTopicId() {
        return TopicId;
    }

    public void setTopicId(int TopicId) {
        this.TopicId = TopicId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String CourseTitle) {
        this.CourseTitle = CourseTitle;
    }

    public String getCourseDescription() {
        return CourseDescription;
    }

    public void setCourseDescription(String CourseDescription) {
        this.CourseDescription = CourseDescription;
    }

    public int getPublicStatus() {
        return PublicStatus;
    }

    public void setPublicStatus(int PublicStatus) {
        this.PublicStatus = PublicStatus;
    }

    public byte[] getImg() {
        return Img;
    }

    public void setImg(byte[] Img) {
        this.Img = Img;
    }

    public Date getPublicDate() {
        return PublicDate;
    }

    public void setPublicDate(Date PublicDate) {
        this.PublicDate = PublicDate;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double Rate) {
        this.Rate = Rate;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public Date getSubmitDate() {
        return SubmitDate;
    }

    public void setSubmitDate(Date SubmitDate) {
        this.SubmitDate = SubmitDate;
    }

}

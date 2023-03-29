/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author msi
 */
public class Feedback {
    private int FeedbackId;
    private int CourseId;
    private int AccountId;
    private double Star;
    private String Comment;
    private Date DateFeedback;

    public Feedback() {
    }

    public Feedback(int FeedbackId, int CourseId, int AccountId, double Star, String Comment, Date DateFeedback) {
        this.FeedbackId = FeedbackId;
        this.CourseId = CourseId;
        this.AccountId = AccountId;
        this.Star = Star;
        this.Comment = Comment;
        this.DateFeedback = DateFeedback;
    }

    public Feedback(int AccountId, double Star, String Comment, Date DateFeedback) {
        this.AccountId = AccountId;
        this.Star = Star;
        this.Comment = Comment;
        this.DateFeedback = DateFeedback;
    }
    public Feedback( int CourseId, int AccountId, double Star, String Comment, Date DateFeedback) {
        this.CourseId = CourseId;
        this.AccountId = AccountId;
        this.Star = Star;
        this.Comment = Comment;
        this.DateFeedback = DateFeedback;
    }
    

    public int getFeedbackId() {
        return FeedbackId;
    }

    public void setFeedbackId(int FeedbackId) {
        this.FeedbackId = FeedbackId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public double getStar() {
        return Star;
    }

    public void setStar(double Star) {
        this.Star = Star;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public Date getDateFeedback() {
        return DateFeedback;
    }

    public void setDateFeedback(Date DateFeedback) {
        this.DateFeedback = DateFeedback;
    }
}

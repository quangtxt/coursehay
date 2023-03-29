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
public class Reply {

    private int FeedbackId;
    private int AccountId;
    private String Reply;
    private Date DateReply;

    public Reply(int FeedbackId, int AccountId, String Reply, Date DateReply) {
        this.FeedbackId = FeedbackId;
        this.AccountId = AccountId;
        this.Reply = Reply;
        this.DateReply = DateReply;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String Reply) {
        this.Reply = Reply;
    }

    public Date getDateReply() {
        return DateReply;
    }

    public void setDateReply(Date DateReply) {
        this.DateReply = DateReply;
    }

    public int getFeedbackId() {
        return FeedbackId;
    }

    public void setFeedbackId(int FeedbackId) {
        this.FeedbackId = FeedbackId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

}

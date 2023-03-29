/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Blog {
    private int BlogID;
    private String BlogTitle;
    private String BlogDesciption;
    private byte[] Image;
    private int CategoryID;
    private Date DateCreated;
    private int BlogStatus;
    private int AccountId;
    public Blog() {
        
    }

    public Blog(int BlogID, String BlogTitle, String BlogDesciption, byte[] Image, int CategoryID, Date DateCreated, int BlogStatus, int AccountId) {
        this.BlogID = BlogID;
        this.BlogTitle = BlogTitle;
        this.BlogDesciption = BlogDesciption;
        this.Image = Image;
        this.CategoryID = CategoryID;
        this.DateCreated = DateCreated;
        this.BlogStatus = BlogStatus;
        this.AccountId = AccountId;
    }

    public int getBlogID() {
        return BlogID;
    }

    public void setBlogID(int BlogID) {
        this.BlogID = BlogID;
    }

    public String getBlogTitle() {
        return BlogTitle;
    }

    public void setBlogTitle(String BlogTitle) {
        this.BlogTitle = BlogTitle;
    }

    public String getBlogDesciption() {
        return BlogDesciption;
    }

    public void setBlogDesciption(String BlogDesciption) {
        this.BlogDesciption = BlogDesciption;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] Image) {
        this.Image = Image;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date DateCreated) {
        this.DateCreated = DateCreated;
    }

    public int getBlogStatus() {
        return BlogStatus;
    }

    public void setBlogStatus(int BlogStatus) {
        this.BlogStatus = BlogStatus;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    @Override
    public String toString() {
        return "Blog{" + "BlogID=" + BlogID + ", BlogTitle=" + BlogTitle + ", BlogDesciption=" + BlogDesciption + ", Image=" + Image + ", CategoryID=" + CategoryID + ", DateCreated=" + DateCreated + ", BlogStatus=" + BlogStatus + ", AccountId=" + AccountId + '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author msi
 */
public class AuthorCourse {
    private int AccountId;
    private int CourseId;

    public AuthorCourse() {
    }

    public AuthorCourse(int AccountId, int CourseId) {
        this.AccountId = AccountId;
        this.CourseId = CourseId;
    }

    public AuthorCourse(int AccountId) {
        this.AccountId = AccountId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }
}

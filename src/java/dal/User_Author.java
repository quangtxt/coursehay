/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author msi
 */
public class User_Author {
    private int UserId;
    private int AuthorId;

    public User_Author(int UserId, int AuthorId) {
        this.UserId = UserId;
        this.AuthorId = AuthorId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int AuthorId) {
        this.AuthorId = AuthorId;
    }
}


package dal;

public class Wishlist {
    public int AccountId;
    public int CourseId;

    public Wishlist() {
    }

    public Wishlist(int AccountId, int CourseId) {
        this.AccountId = AccountId;
        this.CourseId = CourseId;
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

    @Override
    public String toString() {
        return "Wishlist{" + "AccountId=" + AccountId + ", CourseId=" + CourseId + '}';
    }
    
}

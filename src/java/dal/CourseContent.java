/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author admin
 */
public class CourseContent {
    private int ContentId, CourseId;
    private String ContentName, ContentDescription;

    public CourseContent() {
    }

    public CourseContent(int ContentId, int CourseId, String ContentName, String ContentDescription) {
        this.ContentId = ContentId;
        this.CourseId = CourseId;
        this.ContentName = ContentName;
        this.ContentDescription = ContentDescription;
    }
    public CourseContent(int ContentId, String ContentName, String ContentDescription) {
        this.ContentId = ContentId;
        this.ContentName = ContentName;
        this.ContentDescription = ContentDescription;
    }

    public int getContentId() {
        return ContentId;
    }

    public void setContentId(int ContentId) {
        this.ContentId = ContentId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public String getContentName() {
        return ContentName;
    }

    public void setContentName(String ContentName) {
        this.ContentName = ContentName;
    }

    public String getContentDescription() {
        return ContentDescription;
    }

    public void setContentDescription(String ContentDescription) {
        this.ContentDescription = ContentDescription;
    }
    
}

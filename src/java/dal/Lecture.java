/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author admin
 */
public class Lecture {
    private int LectureId, ContentId;
    private String LectureName,LectureType;

    public Lecture() {
    }

    public Lecture(int LectureId, int ContentId, String LectureName, String LectureType) {
        this.LectureId = LectureId;
        this.ContentId = ContentId;
        this.LectureName = LectureName;
        this.LectureType = LectureType;
    }
    public Lecture(int LectureId,String LectureName, String LectureType) {
        this.LectureId = LectureId;
        this.LectureName = LectureName;
        this.LectureType = LectureType;
    }

    public int getLectureId() {
        return LectureId;
    }

    public void setLectureId(int LectureId) {
        this.LectureId = LectureId;
    }

    public int getContentId() {
        return ContentId;
    }

    public void setContentId(int ContentId) {
        this.ContentId = ContentId;
    }

    public String getLectureName() {
        return LectureName;
    }

    public void setLectureName(String LectureName) {
        this.LectureName = LectureName;
    }

    public String getLectureType() {
        return LectureType;
    }

    public void setLectureType(String LectureType) {
        this.LectureType = LectureType;
    }
    
}

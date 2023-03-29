/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author admin
 */
public class Category {

    private int CateId;
    private String CateName;
    
    public Category() {
    }

    public Category(int CateId, String CateName) {
        this.CateId = CateId;
        this.CateName = CateName;
    }

    public int getCateId() {
        return CateId;
    }

    public void setCateId(int CateId) {
        this.CateId = CateId;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }

    @Override
    public String toString() {
        return "Category{" + "CateId=" + CateId + ", CateName=" + CateName + '}';
    }
    
}

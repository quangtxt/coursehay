/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author Admin
 */
public class BlogPart {
    private int BlogPartID;
    private int BlogID;
    private int Part;
    private String PartHeader;
    private String PartContent;
    private byte[] Image;

    public BlogPart() {
    }

    public BlogPart(int BlogPartID, int BlogID, int Part, String PartHeader, String PartContent, byte[] Image) {
        this.BlogPartID = BlogPartID;
        this.BlogID = BlogID;
        this.Part = Part;
        this.PartHeader = PartHeader;
        this.PartContent = PartContent;
        this.Image = Image;
    }

    public int getBlogPartID() {
        return BlogPartID;
    }

    public void setBlogPartID(int BlogPartID) {
        this.BlogPartID = BlogPartID;
    }

    public int getBlogID() {
        return BlogID;
    }

    public void setBlogID(int BlogID) {
        this.BlogID = BlogID;
    }

    public int getPart() {
        return Part;
    }

    public void setPart(int Part) {
        this.Part = Part;
    }

    public String getPartHeader() {
        return PartHeader;
    }

    public void setPartHeader(String PartHeader) {
        this.PartHeader = PartHeader;
    }

    public String getPartContent() {
        return PartContent;
    }

    public void setPartContent(String PartContent) {
        this.PartContent = PartContent;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "BlogPart{" + "BlogPartID=" + BlogPartID + ", BlogID=" + BlogID + ", Part=" + Part + ", PartHeader=" + PartHeader + ", PartContent=" + PartContent + ", Image=" + Image + '}';
    }
}

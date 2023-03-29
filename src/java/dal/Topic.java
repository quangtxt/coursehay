/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author admin
 */
public class Topic {
    
    private int TopicId;
    private String TopicName;

    public Topic() {
    }

    public Topic(int TopicId, String TopicName) {
        this.TopicId = TopicId;
        this.TopicName = TopicName;
    }

    public int getTopicId() {
        return TopicId;
    }

    public void setTopicId(int TopicId) {
        this.TopicId = TopicId;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String TopicName) {
        this.TopicName = TopicName;
    }

    @Override
    public String toString() {
        return "Topic{" + "TopicId=" + TopicId + ", TopicName=" + TopicName + '}';
    }
    
}

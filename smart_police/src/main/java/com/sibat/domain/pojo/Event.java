package com.sibat.domain.pojo;

/**
 * Created by tgw61 on 2017/5/4.
 */
public class Event {
    private String id;
    private String category;
    private String type;
    private String content;
    private String time;

    public Event() {
    }

    public Event(String id, String category, String type, String content, String time) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.content = content;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

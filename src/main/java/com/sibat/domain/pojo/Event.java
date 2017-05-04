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
}

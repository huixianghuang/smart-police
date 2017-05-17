package com.sibat.domain.other;

import lombok.Data;

import javax.persistence.*;

/**
 * 数据表
 * Created by tgw61 on 2017/5/3.
 *
 *  select * from subway_event where date(event_time)='2012/12/29'; 提取只有日期的时间
 */
@Data
@Entity
@Table(name = "subway_event")
public class SubwayEvent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String stationId;
    private String stationName;
    private String lineId;
    private String lineName;
    private String eventId;//事件id
    private String category;//事件类型
    private String type;//事件类别
    private String content;//时间内容
    private String eventTime;//事件发生时间
    private String police;//所属派出所
    private String policeId;//所属派出所id
    private String time;//yyyy-MM

    public SubwayEvent() {
    }

    public SubwayEvent(String stationId, String stationName, String lineId, String lineName, String eventId, String category, String type, String content, String eventTime, String police,String policeId, String time) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.lineId = lineId;
        this.lineName = lineName;
        this.eventId = eventId;
        this.category = category;
        this.type = type;
        this.content = content;
        this.eventTime = eventTime;
        this.police = police;
        this.policeId =policeId;
        this.time = time;
    }

    public SubwayEvent(String stationId, String stationName, String category, String type, String content, String eventTime) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.category = category;
        this.type = type;
        this.content = content;
        this.eventTime = eventTime;
    }

}

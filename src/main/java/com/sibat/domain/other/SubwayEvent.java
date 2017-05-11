package com.sibat.domain.other;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据表
 * Created by tgw61 on 2017/5/3.
 *
 *  select * from subway_event where date(event_time)='2012/12/29'; 提取只有日期的时间
 */
@Entity
@Table(name = "subway_event")
public class SubwayEvent {
    @Id
    @GeneratedValue
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}

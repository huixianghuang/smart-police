package com.sibat.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/10.
 */
@Entity
@Table
public class BusEvent {
    @Id
    private String id;
    private String stationName;//站点名 ddmc
    private String lineId;//线路id XL_IDv
    private String eventId;//事件id JCJ_ID
    private String category;//事件类型 JQXZ
    private String type;//事件类别 JQLB
    private String content;//内容 remark
    private String eventTime;//事件发生时间 aftime
    private String police;//所属派出所 jjdw_name
    private String policeId;//所属派出所id jjdw_id
    private String submitTime;//案件上报时间;submit_time
    private String dataFlowStatus;//审批状态

    public BusEvent(String id, String stationName, String lineId, String eventId, String category, String type, String content, String eventTime, String police, String policeId, String submitTime, String dataFlowStatus) {
        this.id = id;
        this.stationName = stationName;
        this.lineId = lineId;
        this.eventId = eventId;
        this.category = category;
        this.type = type;
        this.content = content;
        this.eventTime = eventTime;
        this.police = police;
        this.policeId = policeId;
        this.submitTime = submitTime;
        this.dataFlowStatus = dataFlowStatus;
    }

    public BusEvent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getDataFlowStatus() {
        return dataFlowStatus;
    }

    public void setDataFlowStatus(String dataFlowStatus) {
        this.dataFlowStatus = dataFlowStatus;
    }
}

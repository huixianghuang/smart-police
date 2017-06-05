package com.sibat.domain.pojo;

import lombok.Data;

/**
 * Created by tgw61 on 2017/4/28.
 */
@Data
public class WarningCondition {
    //接警单位
    private String department;
    //分局管辖所
    private String administer;
    //警情性质
    private String property;
    //警情类别
    private String category;
    //地铁线路
    private String trafficRoute;
    //接警处编号
    private String departmentId;
    //案发时间起
    private String crimeStart;//时间格式:yyyy-MM-dd hh:mm;ss
    //案发时间止
    private String crimeEnd;
    //流程状态
    private String processState;
    //简要警情
    private String briefAlert;
    //反馈类型
    private String feedbackType;

    public WarningCondition() {
    }

    public WarningCondition(String department, String administer, String property, String category, String trafficRoute, String departmentId, String crimeStart, String crimeEnd, String processState, String briefAlert, String feedbackType) {
        this.department = department;
        this.administer = administer;
        this.property = property;
        this.category = category;
        this.trafficRoute = trafficRoute;
        this.departmentId = departmentId;
        this.crimeStart = crimeStart;
        this.crimeEnd = crimeEnd;
        this.processState = processState;
        this.briefAlert = briefAlert;
        this.feedbackType = feedbackType;
    }
}

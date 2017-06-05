package com.sibat.domain.pojo;

/**
 * Created by tgw61 on 2017/4/28.
 */
public class WarningResult {
    //接警处编号
    private String departmentId;
    //分局管辖所
    private String administer;
    //警情性质
    private String property;
    //警情类别
    private String category;
    //线路
    private String trafficRoute;
    //站点
    private String trafficStation;
    //案发时间
    private String crimeTime;
    //操作人
    private String operator;
    //操作时间
    private String operateTime;
    //审批状态
    private String approvalStatus;

    public WarningResult() {
    }

    public WarningResult(String departmentId, String administer, String property, String category, String trafficRoute, String trafficStation, String crimeTime, String operator, String operateTime, String approvalStatus) {
        this.departmentId = departmentId;
        this.administer = administer;
        this.property = property;
        this.category = category;
        this.trafficRoute = trafficRoute;
        this.trafficStation = trafficStation;
        this.crimeTime = crimeTime;
        this.operator = operator;
        this.operateTime = operateTime;
        this.approvalStatus = approvalStatus;
    }
}

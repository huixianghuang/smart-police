package com.sibat.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/10.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private String dataFlowStatus;//审批状态 DATA_PRV_STATUS


}

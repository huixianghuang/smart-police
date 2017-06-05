package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by tgw61 on 2017/5/23.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dt_jlb")
public class TDtJlb {
    @Id
    private Integer SECID;//主键
    private Timestamp  FBRQ;//发布日期
    private String PCSID;// 派出所id
    private String PCSNAME;//派出所名称
    private String ZQID;//站区id
    private String ZQNAME;//站区名称
    private String RYID;//人员id
    private String RYNAME;//人员名称
    private String RYJH;// 人员警号
    private String RYSJHM;//人员手机号
    private String STATUS;// 状态
    private Timestamp CREATE_DATE;//创建日期
    private String CREATE_ID;//创建人id
    private Timestamp  UPDATE_DATE;//更新日期
    private Integer UPDATE_ID;// 更新人id
    private Integer PB_TYPE;//0=早班 1=晚班
    private Integer CHECK_STATUS;//NUMBER考勤情况 0=正常，1=迟到，2=严重迟到，3=旷工
    private String CHECK_POINT_ID;//VARCHAR2(80)	Y 打卡记录ID
}

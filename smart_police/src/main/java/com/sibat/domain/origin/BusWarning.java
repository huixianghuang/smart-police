package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by tgw61 on 2017/5/2.
 * 公交警情
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jqfx_jqlr_gj")
public class BusWarning implements Serializable {
    private static final long serialVersionUID = 7247760613254L;
    @Id
    private Integer GJ_ID;
    private String JJDW_ID;
    private String JJDW_NAME;
    private String WFJGXS_ID;//wfjgxsId
    private String WFJGXS_NAME;//wfjgxsName
    private String JQXZ_ID;//jqxzId
    private String JQXZ;//jqxz
    private String JQLB_ID;//jqlbId
    private String JQLB;//jqlb
    private Timestamp AF_TIME;//afTime
    private String SACW_ID;//sacwId
    private String SACW;//sacw
    private String XL_ID;//xlId
    private String XL;//xl
    private String JD_ID;//jdId
    private String JD;//jd
    private String DDMC_ID;//ddmcId
    private String DDMC;//ddmc
    private String FZR_ID;//fzrId
    private String FZR_NAME;//fzrName
    private String REMARK;//remark
    private String JCJ_ID;//jcjId
    private Timestamp CREATE_TIME;//createTime
    private String PD_PERSON_ID;//pdPersonId
    private String PD_PERSON_NAME;//pdPersonName
    private String PD_DEPT_CODE;//pdDeptCode
    private String PD_DEPT_NAME;//pdDeptName
    private Timestamp PD_TIME;//pdTime
    private String PD_LX;//pdLx
    private String PD_LX_NAME;//pdLxName
    private String PD_START_TIME;//pdStartTime
    private Timestamp PD_END_TIME;//pdEndTime
    private String FK_TOTAL;//fkTotal
    private String FK_COUNT;//fkCount
    private String PD_OBJECT;//pdObject
    private Timestamp UPDATE_TIME;//updateTime
    private String DATA_PRV_STATUS;//dataPrvStatus
    private String DATA_FLOW_STATUS;//dataFlowStatus
    private String SUBMIT_UNIT_ID;//submitUnitId
    private String SUBMIT_UNIT_NAME;//submitUnitName
    private String SUBMIT_DEPT_ID;//submitDeptId
    private String SUBMIT_DEPT_NAME;//submitDeptName
    private String SUBMIT_PERSON_ID;//submitPersonId
    private String SUBMIT_PERSON_NAME;//submitPersonName
    private Timestamp SUBMIT_TIME;//submitTime
    private String MODIFY_UNIT_ID;//modifyUnitId
    private String MODIFY_UNIT_NAME;//modifyUnitName
    private String MODIFY_DEPT_ID;//modifyDeptId
    private String MODIFY_DEPT_NAME;//modifyDeptName
    private String MODIFY_PERSON_ID;//modifyPersonId
    private String MODIFY_PERSON_NAME;//modifyPersonName
    private String IS_VALID;//isValid
    private Timestamp MODIFY_TIME;//modifyTime
    private String DUTY_MASTER_ID;//dutyMasterId
    private String DUTY_MASTER_NAME;//duty
    private String DUTY_POLICE_ID;
    private String DUTY_POLICE_NAME;
    private String DUTY_DEPT_CODE;
    private String DUTY_DEPT_NAME;
    private String PD_REMARK;
    private String SP_PERSON_ID;
    private String SP_PERSON_NAME;
    private String XZQH_ID;
    private String XZQH_NAME;
    private String BZ;
    private String FK_TYPE;
    private String TEL_DS;
    private String RETURN_OPNION;
    private String JQ_ADRESS;
    private String ISMARK;
    private String MONTHDATE;
    private String AFTIMEDATE;
    private String AFTIMEYEAR;
    private String GJXLSSGS;
    private String GROUP_PERSON_ID;
    private String GROUP_PERSON_NAME;
    private Timestamp GROUP_TIME;
    private Timestamp BJ_TIME;
}

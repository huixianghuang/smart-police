package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tgw61 on 2017/4/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jqfx_jqlr_dt")
public class JqfxJqlrDt implements Serializable{
    private static final long serialVersionUID = 72477613254L;
    @Id
    private Integer DT_ID;
    private String JJDW_ID;
    private String JJDW_NAME;
    private String JQXZ_ID;
    private String JQXZ;
    private String JQLB_ID;
    private String JQLB;
    @Temporal(TemporalType.DATE)
    private Date AF_TIME;
    private String SACW_ID;
    private String SACW;
    private String XL_ID;
    private String XL;
    private String DDMC_ID;
    private String DDMC;
    private String FZR_ID;
    private String FZR_NAME;
    private String REMARK;
    private String JCJ_ID;
    private Date CREATE_TIME;
    private String PD_PERSON_ID;
    private String PD_PERSON_NAME;
    private String PD_DEPT_CODE;
    private String PD_DEPT_NAME;
    private Date PD_TIME;
    private Date UPDATE_TIME;
    private String SUBMIT_DEPT_ID;
    private String SUBMIT_DEPT_NAME;
    private String SUBMIT_PERSON_ID;
    private String SUBMIT_PERSON_NAME;
    private String DATA_PRV_STATUS;
    private String DATA_FLOW_STATUS;
    private String SUBMIT_UNIT_ID;
    private String SUBMIT_UNIT_NAME;
    private Date SUBMIT_TIME;
    private String MODIFY_UNIT_ID;
    private String MODIFY_UNIT_NAME;
    private String MODIFY_DEPT_ID;
    private String MODIFY_DEPT_NAME;
    private String MODIFY_PERSON_ID;
    private String MODIFY_PERSON_NAME;
    private String IS_VALID;
    private String PD_LX;
    private String PD_LX_NAME;
    private Date PD_START_TIME;
    private Date PD_END_TIME;
    private String FK_TOTAL;
    private String FK_COUNT;
    private String PD_OBJECT;
    private Date MODIFY_TIME;
    private String DUTY_MASTER_ID;
    private String DUTY_MASTER_NAME;
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
    private String WFJGXS_ID;
    private String WFJGXS_NAME;//派出所名
    private String FK_TYPE;
    private String TEL_DS;
    private String JQ_ADRESS;
    private String ISMARK;
    private String MONTHDATE;
    private String AFTIMEDATE;
    private String AFTIMEYEAR;
    private String GROUP_PERSON_ID;
    private String GROUP_PERSON_NAME;
    private Date GROUP_TIME;
    private Date BJ_TIME;
}

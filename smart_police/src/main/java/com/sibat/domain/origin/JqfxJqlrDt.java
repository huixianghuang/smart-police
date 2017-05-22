package com.sibat.domain.origin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by tgw61 on 2017/4/28.
 */
@Data
@Entity
@Table(name = "jqfx_jqlr_dt")
public class JqfxJqlrDt implements Serializable{
    private static final long serialVersionUID = 72477613254L;
    @Id
    private String DT_ID;
    private String JJDW_ID;
    private String JJDW_NAME;
    private String JQXZ_ID;
    private String JQXZ;
    private String JQLB_ID;
    private String JQLB;
    private String AF_TIME;
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
    private String CREATE_TIME;
    private String PD_PERSON_ID;
    private String PD_PERSON_NAME;
    private String PD_DEPT_CODE;
    private String PD_DEPT_NAME;
    private String PD_TIME;
    private String UPDATE_TIME;
    private String SUBMIT_DEPT_ID;
    private String SUBMIT_DEPT_NAME;
    private String SUBMIT_PERSON_ID;
    private String SUBMIT_PERSON_NAME;
    private String DATA_PRV_STATUS;
    private String DATA_FLOW_STATUS;
    private String SUBMIT_UNIT_ID;
    private String SUBMIT_UNIT_NAME;
    private String SUBMIT_TIME;
    private String MODIFY_UNIT_ID;
    private String MODIFY_UNIT_NAME;
    private String MODIFY_DEPT_ID;
    private String MODIFY_DEPT_NAME;
    private String MODIFY_PERSON_ID;
    private String MODIFY_PERSON_NAME;
    private String IS_VALID;
    private String PD_LX;
    private String PD_LX_NAME;
    private String PD_START_TIME;
    private String PD_END_TIME;
    private String FK_TOTAL;
    private String FK_COUNT;
    private String PD_OBJECT;
    private String MODIFY_TIME;
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
    private String GROUP_TIME;
    private String BJ_TIME;

    public JqfxJqlrDt() {
    }

    public JqfxJqlrDt(String DT_ID, String JJDW_ID, String JJDW_NAME, String JQXZ_ID, String JQXZ, String JQLB_ID, String JQLB, String AF_TIME, String SACW_ID, String SACW, String XL_ID, String XL, String DDMC_ID, String DDMC, String FZR_ID, String FZR_NAME, String REMARK, String JCJ_ID, String CREATE_TIME, String PD_PERSON_ID, String PD_PERSON_NAME, String PD_DEPT_CODE, String PD_DEPT_NAME, String PD_TIME, String UPDATE_TIME, String SUBMIT_DEPT_ID, String SUBMIT_DEPT_NAME, String SUBMIT_PERSON_ID, String SUBMIT_PERSON_NAME, String DATA_PRV_STATUS, String DATA_FLOW_STATUS, String SUBMIT_UNIT_ID, String SUBMIT_UNIT_NAME, String SUBMIT_TIME, String MODIFY_UNIT_ID, String MODIFY_UNIT_NAME, String MODIFY_DEPT_ID, String MODIFY_DEPT_NAME, String MODIFY_PERSON_ID, String MODIFY_PERSON_NAME, String IS_VALID, String PD_LX, String PD_LX_NAME, String PD_START_TIME, String PD_END_TIME, String FK_TOTAL, String FK_COUNT, String PD_OBJECT, String MODIFY_TIME, String DUTY_MASTER_ID, String DUTY_MASTER_NAME, String DUTY_POLICE_ID, String DUTY_POLICE_NAME, String DUTY_DEPT_CODE, String DUTY_DEPT_NAME, String PD_REMARK, String SP_PERSON_ID, String SP_PERSON_NAME, String XZQH_ID, String XZQH_NAME, String BZ, String WFJGXS_ID, String WFJGXS_NAME, String FK_TYPE, String TEL_DS, String JQ_ADRESS, String ISMARK, String MONTHDATE, String AFTIMEDATE, String AFTIMEYEAR, String GROUP_PERSON_ID, String GROUP_PERSON_NAME, String GROUP_TIME, String BJ_TIME) {
        this.DT_ID = DT_ID;
        this.JJDW_ID = JJDW_ID;
        this.JJDW_NAME = JJDW_NAME;
        this.JQXZ_ID = JQXZ_ID;
        this.JQXZ = JQXZ;
        this.JQLB_ID = JQLB_ID;
        this.JQLB = JQLB;
        this.AF_TIME = AF_TIME;
        this.SACW_ID = SACW_ID;
        this.SACW = SACW;
        this.XL_ID = XL_ID;
        this.XL = XL;
        this.DDMC_ID = DDMC_ID;
        this.DDMC = DDMC;
        this.FZR_ID = FZR_ID;
        this.FZR_NAME = FZR_NAME;
        this.REMARK = REMARK;
        this.JCJ_ID = JCJ_ID;
        this.CREATE_TIME = CREATE_TIME;
        this.PD_PERSON_ID = PD_PERSON_ID;
        this.PD_PERSON_NAME = PD_PERSON_NAME;
        this.PD_DEPT_CODE = PD_DEPT_CODE;
        this.PD_DEPT_NAME = PD_DEPT_NAME;
        this.PD_TIME = PD_TIME;
        this.UPDATE_TIME = UPDATE_TIME;
        this.SUBMIT_DEPT_ID = SUBMIT_DEPT_ID;
        this.SUBMIT_DEPT_NAME = SUBMIT_DEPT_NAME;
        this.SUBMIT_PERSON_ID = SUBMIT_PERSON_ID;
        this.SUBMIT_PERSON_NAME = SUBMIT_PERSON_NAME;
        this.DATA_PRV_STATUS = DATA_PRV_STATUS;
        this.DATA_FLOW_STATUS = DATA_FLOW_STATUS;
        this.SUBMIT_UNIT_ID = SUBMIT_UNIT_ID;
        this.SUBMIT_UNIT_NAME = SUBMIT_UNIT_NAME;
        this.SUBMIT_TIME = SUBMIT_TIME;
        this.MODIFY_UNIT_ID = MODIFY_UNIT_ID;
        this.MODIFY_UNIT_NAME = MODIFY_UNIT_NAME;
        this.MODIFY_DEPT_ID = MODIFY_DEPT_ID;
        this.MODIFY_DEPT_NAME = MODIFY_DEPT_NAME;
        this.MODIFY_PERSON_ID = MODIFY_PERSON_ID;
        this.MODIFY_PERSON_NAME = MODIFY_PERSON_NAME;
        this.IS_VALID = IS_VALID;
        this.PD_LX = PD_LX;
        this.PD_LX_NAME = PD_LX_NAME;
        this.PD_START_TIME = PD_START_TIME;
        this.PD_END_TIME = PD_END_TIME;
        this.FK_TOTAL = FK_TOTAL;
        this.FK_COUNT = FK_COUNT;
        this.PD_OBJECT = PD_OBJECT;
        this.MODIFY_TIME = MODIFY_TIME;
        this.DUTY_MASTER_ID = DUTY_MASTER_ID;
        this.DUTY_MASTER_NAME = DUTY_MASTER_NAME;
        this.DUTY_POLICE_ID = DUTY_POLICE_ID;
        this.DUTY_POLICE_NAME = DUTY_POLICE_NAME;
        this.DUTY_DEPT_CODE = DUTY_DEPT_CODE;
        this.DUTY_DEPT_NAME = DUTY_DEPT_NAME;
        this.PD_REMARK = PD_REMARK;
        this.SP_PERSON_ID = SP_PERSON_ID;
        this.SP_PERSON_NAME = SP_PERSON_NAME;
        this.XZQH_ID = XZQH_ID;
        this.XZQH_NAME = XZQH_NAME;
        this.BZ = BZ;
        this.WFJGXS_ID = WFJGXS_ID;
        this.WFJGXS_NAME = WFJGXS_NAME;
        this.FK_TYPE = FK_TYPE;
        this.TEL_DS = TEL_DS;
        this.JQ_ADRESS = JQ_ADRESS;
        this.ISMARK = ISMARK;
        this.MONTHDATE = MONTHDATE;
        this.AFTIMEDATE = AFTIMEDATE;
        this.AFTIMEYEAR = AFTIMEYEAR;
        this.GROUP_PERSON_ID = GROUP_PERSON_ID;
        this.GROUP_PERSON_NAME = GROUP_PERSON_NAME;
        this.GROUP_TIME = GROUP_TIME;
        this.BJ_TIME = BJ_TIME;
    }

}

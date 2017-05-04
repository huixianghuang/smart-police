package com.sibat.domain.origin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by tgw61 on 2017/5/2.
 */
@Entity
@Table(name = "bus_warning")
public class BusWarning implements Serializable{
    private static final long serialVersionUID = 7247760613254L;
    @Id
    private String GJ_ID;
    private String JJDW_ID;
    private String JJDW_NAME;
    private String WFJGXS_ID;
    private String WFJGXS_NAME;
    private String JQXZ_ID;
    private String JQXZ;
    private String JQLB_ID;
    private String JQLB;
    private String AF_TIME;
    private String SACW_ID;
    private String SACW;
    private String XL_ID;
    private String XL;
    private String JD_ID;
    private String JD;
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
    private String PD_LX;
    private String PD_LX_NAME;
    private String PD_START_TIME;
    private String PD_END_TIME;
    private String FK_TOTAL;
    private String FK_COUNT;
    private String PD_OBJECT;
    private String UPDATE_TIME;
    private String DATA_PRV_STATUS;
    private String DATA_FLOW_STATUS;
    private String SUBMIT_UNIT_ID;
    private String SUBMIT_UNIT_NAME;
    private String SUBMIT_DEPT_ID;
    private String SUBMIT_DEPT_NAME;
    private String SUBMIT_PERSON_ID;
    private String SUBMIT_PERSON_NAME;
    private String SUBMIT_TIME;
    private String MODIFY_UNIT_ID;
    private String MODIFY_UNIT_NAME;
    private String MODIFY_DEPT_ID;
    private String MODIFY_DEPT_NAME;
    private String MODIFY_PERSON_ID;
    private String MODIFY_PERSON_NAME;
    private String IS_VALID;
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
    private String GROUP_TIME;
    private String BJ_TIME;

    public BusWarning() {
    }

    public BusWarning(String GJ_ID, String JJDW_ID, String JJDW_NAME, String WFJGXS_ID, String WFJGXS_NAME, String JQXZ_ID, String JQXZ, String JQLB_ID, String JQLB, String AF_TIME, String SACW_ID, String SACW, String XL_ID, String XL, String JD_ID, String JD, String DDMC_ID, String DDMC, String FZR_ID, String FZR_NAME, String REMARK, String JCJ_ID, String CREATE_TIME, String PD_PERSON_ID, String PD_PERSON_NAME, String PD_DEPT_CODE, String PD_DEPT_NAME, String PD_TIME, String PD_LX, String PD_LX_NAME, String PD_START_TIME, String PD_END_TIME, String FK_TOTAL, String FK_COUNT, String PD_OBJECT, String UPDATE_TIME, String DATA_PRV_STATUS, String DATA_FLOW_STATUS, String SUBMIT_UNIT_ID, String SUBMIT_UNIT_NAME, String SUBMIT_DEPT_ID, String SUBMIT_DEPT_NAME, String SUBMIT_PERSON_ID, String SUBMIT_PERSON_NAME, String SUBMIT_TIME, String MODIFY_UNIT_ID, String MODIFY_UNIT_NAME, String MODIFY_DEPT_ID, String MODIFY_DEPT_NAME, String MODIFY_PERSON_ID, String MODIFY_PERSON_NAME, String IS_VALID, String MODIFY_TIME, String DUTY_MASTER_ID, String DUTY_MASTER_NAME, String DUTY_POLICE_ID, String DUTY_POLICE_NAME, String DUTY_DEPT_CODE, String DUTY_DEPT_NAME, String PD_REMARK, String SP_PERSON_ID, String SP_PERSON_NAME, String XZQH_ID, String XZQH_NAME, String BZ, String FK_TYPE, String TEL_DS, String RETURN_OPNION, String JQ_ADRESS, String ISMARK, String MONTHDATE, String AFTIMEDATE, String AFTIMEYEAR, String GJXLSSGS, String GROUP_PERSON_ID, String GROUP_PERSON_NAME, String GROUP_TIME, String BJ_TIME) {
        this.GJ_ID = GJ_ID;
        this.JJDW_ID = JJDW_ID;
        this.JJDW_NAME = JJDW_NAME;
        this.WFJGXS_ID = WFJGXS_ID;
        this.WFJGXS_NAME = WFJGXS_NAME;
        this.JQXZ_ID = JQXZ_ID;
        this.JQXZ = JQXZ;
        this.JQLB_ID = JQLB_ID;
        this.JQLB = JQLB;
        this.AF_TIME = AF_TIME;
        this.SACW_ID = SACW_ID;
        this.SACW = SACW;
        this.XL_ID = XL_ID;
        this.XL = XL;
        this.JD_ID = JD_ID;
        this.JD = JD;
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
        this.PD_LX = PD_LX;
        this.PD_LX_NAME = PD_LX_NAME;
        this.PD_START_TIME = PD_START_TIME;
        this.PD_END_TIME = PD_END_TIME;
        this.FK_TOTAL = FK_TOTAL;
        this.FK_COUNT = FK_COUNT;
        this.PD_OBJECT = PD_OBJECT;
        this.UPDATE_TIME = UPDATE_TIME;
        this.DATA_PRV_STATUS = DATA_PRV_STATUS;
        this.DATA_FLOW_STATUS = DATA_FLOW_STATUS;
        this.SUBMIT_UNIT_ID = SUBMIT_UNIT_ID;
        this.SUBMIT_UNIT_NAME = SUBMIT_UNIT_NAME;
        this.SUBMIT_DEPT_ID = SUBMIT_DEPT_ID;
        this.SUBMIT_DEPT_NAME = SUBMIT_DEPT_NAME;
        this.SUBMIT_PERSON_ID = SUBMIT_PERSON_ID;
        this.SUBMIT_PERSON_NAME = SUBMIT_PERSON_NAME;
        this.SUBMIT_TIME = SUBMIT_TIME;
        this.MODIFY_UNIT_ID = MODIFY_UNIT_ID;
        this.MODIFY_UNIT_NAME = MODIFY_UNIT_NAME;
        this.MODIFY_DEPT_ID = MODIFY_DEPT_ID;
        this.MODIFY_DEPT_NAME = MODIFY_DEPT_NAME;
        this.MODIFY_PERSON_ID = MODIFY_PERSON_ID;
        this.MODIFY_PERSON_NAME = MODIFY_PERSON_NAME;
        this.IS_VALID = IS_VALID;
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
        this.FK_TYPE = FK_TYPE;
        this.TEL_DS = TEL_DS;
        this.RETURN_OPNION = RETURN_OPNION;
        this.JQ_ADRESS = JQ_ADRESS;
        this.ISMARK = ISMARK;
        this.MONTHDATE = MONTHDATE;
        this.AFTIMEDATE = AFTIMEDATE;
        this.AFTIMEYEAR = AFTIMEYEAR;
        this.GJXLSSGS = GJXLSSGS;
        this.GROUP_PERSON_ID = GROUP_PERSON_ID;
        this.GROUP_PERSON_NAME = GROUP_PERSON_NAME;
        this.GROUP_TIME = GROUP_TIME;
        this.BJ_TIME = BJ_TIME;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGJ_ID() {
        return GJ_ID;
    }

    public void setGJ_ID(String GJ_ID) {
        this.GJ_ID = GJ_ID;
    }

    public String getJJDW_ID() {
        return JJDW_ID;
    }

    public void setJJDW_ID(String JJDW_ID) {
        this.JJDW_ID = JJDW_ID;
    }

    public String getJJDW_NAME() {
        return JJDW_NAME;
    }

    public void setJJDW_NAME(String JJDW_NAME) {
        this.JJDW_NAME = JJDW_NAME;
    }

    public String getWFJGXS_ID() {
        return WFJGXS_ID;
    }

    public void setWFJGXS_ID(String WFJGXS_ID) {
        this.WFJGXS_ID = WFJGXS_ID;
    }

    public String getWFJGXS_NAME() {
        return WFJGXS_NAME;
    }

    public void setWFJGXS_NAME(String WFJGXS_NAME) {
        this.WFJGXS_NAME = WFJGXS_NAME;
    }

    public String getJQXZ_ID() {
        return JQXZ_ID;
    }

    public void setJQXZ_ID(String JQXZ_ID) {
        this.JQXZ_ID = JQXZ_ID;
    }

    public String getJQXZ() {
        return JQXZ;
    }

    public void setJQXZ(String JQXZ) {
        this.JQXZ = JQXZ;
    }

    public String getJQLB_ID() {
        return JQLB_ID;
    }

    public void setJQLB_ID(String JQLB_ID) {
        this.JQLB_ID = JQLB_ID;
    }

    public String getJQLB() {
        return JQLB;
    }

    public void setJQLB(String JQLB) {
        this.JQLB = JQLB;
    }

    public String getAF_TIME() {
        return AF_TIME;
    }

    public void setAF_TIME(String AF_TIME) {
        this.AF_TIME = AF_TIME;
    }

    public String getSACW_ID() {
        return SACW_ID;
    }

    public void setSACW_ID(String SACW_ID) {
        this.SACW_ID = SACW_ID;
    }

    public String getSACW() {
        return SACW;
    }

    public void setSACW(String SACW) {
        this.SACW = SACW;
    }

    public String getXL_ID() {
        return XL_ID;
    }

    public void setXL_ID(String XL_ID) {
        this.XL_ID = XL_ID;
    }

    public String getXL() {
        return XL;
    }

    public void setXL(String XL) {
        this.XL = XL;
    }

    public String getJD_ID() {
        return JD_ID;
    }

    public void setJD_ID(String JD_ID) {
        this.JD_ID = JD_ID;
    }

    public String getJD() {
        return JD;
    }

    public void setJD(String JD) {
        this.JD = JD;
    }

    public String getDDMC_ID() {
        return DDMC_ID;
    }

    public void setDDMC_ID(String DDMC_ID) {
        this.DDMC_ID = DDMC_ID;
    }

    public String getDDMC() {
        return DDMC;
    }

    public void setDDMC(String DDMC) {
        this.DDMC = DDMC;
    }

    public String getFZR_ID() {
        return FZR_ID;
    }

    public void setFZR_ID(String FZR_ID) {
        this.FZR_ID = FZR_ID;
    }

    public String getFZR_NAME() {
        return FZR_NAME;
    }

    public void setFZR_NAME(String FZR_NAME) {
        this.FZR_NAME = FZR_NAME;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getJCJ_ID() {
        return JCJ_ID;
    }

    public void setJCJ_ID(String JCJ_ID) {
        this.JCJ_ID = JCJ_ID;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getPD_PERSON_ID() {
        return PD_PERSON_ID;
    }

    public void setPD_PERSON_ID(String PD_PERSON_ID) {
        this.PD_PERSON_ID = PD_PERSON_ID;
    }

    public String getPD_PERSON_NAME() {
        return PD_PERSON_NAME;
    }

    public void setPD_PERSON_NAME(String PD_PERSON_NAME) {
        this.PD_PERSON_NAME = PD_PERSON_NAME;
    }

    public String getPD_DEPT_CODE() {
        return PD_DEPT_CODE;
    }

    public void setPD_DEPT_CODE(String PD_DEPT_CODE) {
        this.PD_DEPT_CODE = PD_DEPT_CODE;
    }

    public String getPD_DEPT_NAME() {
        return PD_DEPT_NAME;
    }

    public void setPD_DEPT_NAME(String PD_DEPT_NAME) {
        this.PD_DEPT_NAME = PD_DEPT_NAME;
    }

    public String getPD_TIME() {
        return PD_TIME;
    }

    public void setPD_TIME(String PD_TIME) {
        this.PD_TIME = PD_TIME;
    }

    public String getPD_LX() {
        return PD_LX;
    }

    public void setPD_LX(String PD_LX) {
        this.PD_LX = PD_LX;
    }

    public String getPD_LX_NAME() {
        return PD_LX_NAME;
    }

    public void setPD_LX_NAME(String PD_LX_NAME) {
        this.PD_LX_NAME = PD_LX_NAME;
    }

    public String getPD_START_TIME() {
        return PD_START_TIME;
    }

    public void setPD_START_TIME(String PD_START_TIME) {
        this.PD_START_TIME = PD_START_TIME;
    }

    public String getPD_END_TIME() {
        return PD_END_TIME;
    }

    public void setPD_END_TIME(String PD_END_TIME) {
        this.PD_END_TIME = PD_END_TIME;
    }

    public String getFK_TOTAL() {
        return FK_TOTAL;
    }

    public void setFK_TOTAL(String FK_TOTAL) {
        this.FK_TOTAL = FK_TOTAL;
    }

    public String getFK_COUNT() {
        return FK_COUNT;
    }

    public void setFK_COUNT(String FK_COUNT) {
        this.FK_COUNT = FK_COUNT;
    }

    public String getPD_OBJECT() {
        return PD_OBJECT;
    }

    public void setPD_OBJECT(String PD_OBJECT) {
        this.PD_OBJECT = PD_OBJECT;
    }

    public String getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public String getDATA_PRV_STATUS() {
        return DATA_PRV_STATUS;
    }

    public void setDATA_PRV_STATUS(String DATA_PRV_STATUS) {
        this.DATA_PRV_STATUS = DATA_PRV_STATUS;
    }

    public String getDATA_FLOW_STATUS() {
        return DATA_FLOW_STATUS;
    }

    public void setDATA_FLOW_STATUS(String DATA_FLOW_STATUS) {
        this.DATA_FLOW_STATUS = DATA_FLOW_STATUS;
    }

    public String getSUBMIT_UNIT_ID() {
        return SUBMIT_UNIT_ID;
    }

    public void setSUBMIT_UNIT_ID(String SUBMIT_UNIT_ID) {
        this.SUBMIT_UNIT_ID = SUBMIT_UNIT_ID;
    }

    public String getSUBMIT_UNIT_NAME() {
        return SUBMIT_UNIT_NAME;
    }

    public void setSUBMIT_UNIT_NAME(String SUBMIT_UNIT_NAME) {
        this.SUBMIT_UNIT_NAME = SUBMIT_UNIT_NAME;
    }

    public String getSUBMIT_DEPT_ID() {
        return SUBMIT_DEPT_ID;
    }

    public void setSUBMIT_DEPT_ID(String SUBMIT_DEPT_ID) {
        this.SUBMIT_DEPT_ID = SUBMIT_DEPT_ID;
    }

    public String getSUBMIT_DEPT_NAME() {
        return SUBMIT_DEPT_NAME;
    }

    public void setSUBMIT_DEPT_NAME(String SUBMIT_DEPT_NAME) {
        this.SUBMIT_DEPT_NAME = SUBMIT_DEPT_NAME;
    }

    public String getSUBMIT_PERSON_ID() {
        return SUBMIT_PERSON_ID;
    }

    public void setSUBMIT_PERSON_ID(String SUBMIT_PERSON_ID) {
        this.SUBMIT_PERSON_ID = SUBMIT_PERSON_ID;
    }

    public String getSUBMIT_PERSON_NAME() {
        return SUBMIT_PERSON_NAME;
    }

    public void setSUBMIT_PERSON_NAME(String SUBMIT_PERSON_NAME) {
        this.SUBMIT_PERSON_NAME = SUBMIT_PERSON_NAME;
    }

    public String getSUBMIT_TIME() {
        return SUBMIT_TIME;
    }

    public void setSUBMIT_TIME(String SUBMIT_TIME) {
        this.SUBMIT_TIME = SUBMIT_TIME;
    }

    public String getMODIFY_UNIT_ID() {
        return MODIFY_UNIT_ID;
    }

    public void setMODIFY_UNIT_ID(String MODIFY_UNIT_ID) {
        this.MODIFY_UNIT_ID = MODIFY_UNIT_ID;
    }

    public String getMODIFY_UNIT_NAME() {
        return MODIFY_UNIT_NAME;
    }

    public void setMODIFY_UNIT_NAME(String MODIFY_UNIT_NAME) {
        this.MODIFY_UNIT_NAME = MODIFY_UNIT_NAME;
    }

    public String getMODIFY_DEPT_ID() {
        return MODIFY_DEPT_ID;
    }

    public void setMODIFY_DEPT_ID(String MODIFY_DEPT_ID) {
        this.MODIFY_DEPT_ID = MODIFY_DEPT_ID;
    }

    public String getMODIFY_DEPT_NAME() {
        return MODIFY_DEPT_NAME;
    }

    public void setMODIFY_DEPT_NAME(String MODIFY_DEPT_NAME) {
        this.MODIFY_DEPT_NAME = MODIFY_DEPT_NAME;
    }

    public String getMODIFY_PERSON_ID() {
        return MODIFY_PERSON_ID;
    }

    public void setMODIFY_PERSON_ID(String MODIFY_PERSON_ID) {
        this.MODIFY_PERSON_ID = MODIFY_PERSON_ID;
    }

    public String getMODIFY_PERSON_NAME() {
        return MODIFY_PERSON_NAME;
    }

    public void setMODIFY_PERSON_NAME(String MODIFY_PERSON_NAME) {
        this.MODIFY_PERSON_NAME = MODIFY_PERSON_NAME;
    }

    public String getIS_VALID() {
        return IS_VALID;
    }

    public void setIS_VALID(String IS_VALID) {
        this.IS_VALID = IS_VALID;
    }

    public String getMODIFY_TIME() {
        return MODIFY_TIME;
    }

    public void setMODIFY_TIME(String MODIFY_TIME) {
        this.MODIFY_TIME = MODIFY_TIME;
    }

    public String getDUTY_MASTER_ID() {
        return DUTY_MASTER_ID;
    }

    public void setDUTY_MASTER_ID(String DUTY_MASTER_ID) {
        this.DUTY_MASTER_ID = DUTY_MASTER_ID;
    }

    public String getDUTY_MASTER_NAME() {
        return DUTY_MASTER_NAME;
    }

    public void setDUTY_MASTER_NAME(String DUTY_MASTER_NAME) {
        this.DUTY_MASTER_NAME = DUTY_MASTER_NAME;
    }

    public String getDUTY_POLICE_ID() {
        return DUTY_POLICE_ID;
    }

    public void setDUTY_POLICE_ID(String DUTY_POLICE_ID) {
        this.DUTY_POLICE_ID = DUTY_POLICE_ID;
    }

    public String getDUTY_POLICE_NAME() {
        return DUTY_POLICE_NAME;
    }

    public void setDUTY_POLICE_NAME(String DUTY_POLICE_NAME) {
        this.DUTY_POLICE_NAME = DUTY_POLICE_NAME;
    }

    public String getDUTY_DEPT_CODE() {
        return DUTY_DEPT_CODE;
    }

    public void setDUTY_DEPT_CODE(String DUTY_DEPT_CODE) {
        this.DUTY_DEPT_CODE = DUTY_DEPT_CODE;
    }

    public String getDUTY_DEPT_NAME() {
        return DUTY_DEPT_NAME;
    }

    public void setDUTY_DEPT_NAME(String DUTY_DEPT_NAME) {
        this.DUTY_DEPT_NAME = DUTY_DEPT_NAME;
    }

    public String getPD_REMARK() {
        return PD_REMARK;
    }

    public void setPD_REMARK(String PD_REMARK) {
        this.PD_REMARK = PD_REMARK;
    }

    public String getSP_PERSON_ID() {
        return SP_PERSON_ID;
    }

    public void setSP_PERSON_ID(String SP_PERSON_ID) {
        this.SP_PERSON_ID = SP_PERSON_ID;
    }

    public String getSP_PERSON_NAME() {
        return SP_PERSON_NAME;
    }

    public void setSP_PERSON_NAME(String SP_PERSON_NAME) {
        this.SP_PERSON_NAME = SP_PERSON_NAME;
    }

    public String getXZQH_ID() {
        return XZQH_ID;
    }

    public void setXZQH_ID(String XZQH_ID) {
        this.XZQH_ID = XZQH_ID;
    }

    public String getXZQH_NAME() {
        return XZQH_NAME;
    }

    public void setXZQH_NAME(String XZQH_NAME) {
        this.XZQH_NAME = XZQH_NAME;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public String getFK_TYPE() {
        return FK_TYPE;
    }

    public void setFK_TYPE(String FK_TYPE) {
        this.FK_TYPE = FK_TYPE;
    }

    public String getTEL_DS() {
        return TEL_DS;
    }

    public void setTEL_DS(String TEL_DS) {
        this.TEL_DS = TEL_DS;
    }

    public String getRETURN_OPNION() {
        return RETURN_OPNION;
    }

    public void setRETURN_OPNION(String RETURN_OPNION) {
        this.RETURN_OPNION = RETURN_OPNION;
    }

    public String getJQ_ADRESS() {
        return JQ_ADRESS;
    }

    public void setJQ_ADRESS(String JQ_ADRESS) {
        this.JQ_ADRESS = JQ_ADRESS;
    }

    public String getISMARK() {
        return ISMARK;
    }

    public void setISMARK(String ISMARK) {
        this.ISMARK = ISMARK;
    }

    public String getMONTHDATE() {
        return MONTHDATE;
    }

    public void setMONTHDATE(String MONTHDATE) {
        this.MONTHDATE = MONTHDATE;
    }

    public String getAFTIMEDATE() {
        return AFTIMEDATE;
    }

    public void setAFTIMEDATE(String AFTIMEDATE) {
        this.AFTIMEDATE = AFTIMEDATE;
    }

    public String getAFTIMEYEAR() {
        return AFTIMEYEAR;
    }

    public void setAFTIMEYEAR(String AFTIMEYEAR) {
        this.AFTIMEYEAR = AFTIMEYEAR;
    }

    public String getGJXLSSGS() {
        return GJXLSSGS;
    }

    public void setGJXLSSGS(String GJXLSSGS) {
        this.GJXLSSGS = GJXLSSGS;
    }

    public String getGROUP_PERSON_ID() {
        return GROUP_PERSON_ID;
    }

    public void setGROUP_PERSON_ID(String GROUP_PERSON_ID) {
        this.GROUP_PERSON_ID = GROUP_PERSON_ID;
    }

    public String getGROUP_PERSON_NAME() {
        return GROUP_PERSON_NAME;
    }

    public void setGROUP_PERSON_NAME(String GROUP_PERSON_NAME) {
        this.GROUP_PERSON_NAME = GROUP_PERSON_NAME;
    }

    public String getGROUP_TIME() {
        return GROUP_TIME;
    }

    public void setGROUP_TIME(String GROUP_TIME) {
        this.GROUP_TIME = GROUP_TIME;
    }

    public String getBJ_TIME() {
        return BJ_TIME;
    }

    public void setBJ_TIME(String BJ_TIME) {
        this.BJ_TIME = BJ_TIME;
    }
}

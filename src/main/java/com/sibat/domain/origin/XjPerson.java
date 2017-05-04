package com.sibat.domain.origin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/3.
 * 嫌疑人处置情况 刑拘人员
 */
@Entity
@Table(name = "xj_person")
public class XjPerson {
    private String ID_NUMBER;
    private String AJXZ;
    private String  BCF_NAME;
    private String  SEX;
    private String  AGE;
    private String HJ;
    private String  XZZ;
    private String  PHONE;
    private String  ZADD;
    private String  XJ_TIME;
    private String  YSA_TIME;
    private String BADW;
    private String JBR;
    private String JBR_CODE;
    private String DEPTCODE;
    private String CREATE_TIME;
    @Id
    private String  ID;
    private String  CLCS;
    private String  CLCSDATE;
    private String CZCLCSDATE;
    private String JBR_SEC;
    private String  JBR_CODE_SEC;
    private String JBR2_SEC;
    private String JBR2_CODE_SEC;
    private String  AJLX;

}

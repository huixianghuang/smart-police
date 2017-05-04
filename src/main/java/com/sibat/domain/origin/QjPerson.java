package com.sibat.domain.origin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/3.
 * 嫌疑人处置情况 戒毒人员
 */
@Entity
@Table(name = "qj_person")
public class QjPerson {
    private String NAME;
    private String SEX;
    private String AGE;
    private String ID_NUMBER;
    private String HJ;
    private String XZZ;
    private String PHONE;
    private String CFQK;
    private String BADW;
    private String JBR;
    private String XDLX;
    private String DEPT_CODE;
    private String CF_TIME;
    @Id
    private String ID;
    private String CREATE_DATE;
    private String JBR_CODE;
    private String XZ;
    private String JBR_SEC;
    private String JBR_CODE_SEC;

}

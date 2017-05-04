package com.sibat.domain.origin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/3.
 */
@Entity
@Table(name = "xz_person")
public class XzPerson {
    private String AJXZ;
    private String NAME;
    private String SEX;
    private String AGE;
    private String ID_NUMBER;
    private String HJ;
    private String XZZ;
    private String PHONE;
    private String BAXX;
    private String ZADD;
    private String CFJG;
    private String BADW;
    private String JBR;
    private String CREATE_TIME;
    private String ZASJ;
    private String CF_TIME;
    private String JBR_CODE;
    private String DEPT_CODE;
    @Id
    private String ID;
    private String JBR_SEC;
    private String JBR_CODE_SEC;
    private String CFLX;

}

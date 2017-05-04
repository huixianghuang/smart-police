package com.sibat.domain.origin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/3.
 */
@Entity
@Table(name = "cb_person")
public class CbPerson {
    private String CBBH;
    private String AJS;
    private String RS;
    private String NAME;
    private String AY;
    private String YSY;
    private String SADW;
    private String IS_SB;
    private String IS_PB;
    private String AFDD;
    private String SB_TIME;
    private String PB_TIME;
    private String CREATE_TIME;
    private String DEPT_CODE;
    @Id
    private String ID;
    private String IS_YJ;
    private String YSY_CODE;
}

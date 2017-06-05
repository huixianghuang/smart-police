package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by tgw61 on 2017/5/3.
 * 嫌疑人处置情况 刑拘人员
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_xj_person")
public class XjPerson {
    private String idNumber;
    private String ajxz;
    private String bcfName;
    private String sex;
    private String age;
    private String hj;
    private String xzz;
    private String phone;
    private String zadd;
    private Timestamp xjTime;
    private Timestamp ysaTime;
    private String badw;
    private String jbr;
    private String jbrCode;
    private String deptcode;
    private Timestamp createTime;
    @Id
    private int id;
    private String clcs;
    private String clcsdate;
    private String czclcsdate;
    private String jbrSec;
    private String jbr_code_sec;
    private String jbr2_sec;
    private String jbr2_code_sec;
    private Integer ajlx;

}

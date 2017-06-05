package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tgw61 on 2017/5/3.
 * 嫌疑人处置情况 戒毒人员
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_qj_person")
public class QjPerson {
    private String name;
    private String sex;
    private String age;
    private String idNumber;
    private String hj;
    private String xzz;
    private String phone;
    private String cfqk;
    private String badw;
    private String jbr;
    private String xdlx;
    private String deptCode;
    private Timestamp cfTime;
    @Id
    private int id;
    private Timestamp createDate;
    private String jbrCode;
    @Column(name = "xz")
    private String ajxz;
    private String jbr_sec;
    private String jbr_code_sec;
}

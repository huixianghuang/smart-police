package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by tgw61 on 2017/5/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_xz_person")
public class XzPerson {
    private String ajxz;
    private String name;
    private String sex;
    private String age;
    private String idNumber;
    private String hj;
    private String xzz;
    private String phone;
    private String baxx;
    private String zadd;
    private String cfjg;
    private String badw;
    private String jbr;
    private Timestamp createTime;
    private String zasj;
    private Timestamp cfTime;
    private String jbrCode;
    private String deptCode;
    @Id
    private int id;
    private String jbr_sec;
    private String jbr_code_sec;
    private Integer cflx;
}

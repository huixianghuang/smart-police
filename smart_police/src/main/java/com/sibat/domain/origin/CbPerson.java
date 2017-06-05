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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "t_cb_person")
public class CbPerson {
    private String cbbh;
    private String ajs;
    private String rs;
    private String name;
    private String ay;
    private String ysy;
    private String sadw;
    private String isSb;
    private String isPb;
    private String afdd;
    private Timestamp sbTime;
    private Timestamp pbTime;
    private Timestamp createTime;
    private String deptCode;
    @Id
    private int id;
    private int isYj;
    private String ysyCode;
}

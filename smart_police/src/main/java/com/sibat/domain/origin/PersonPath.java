package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by tgw61 on 2017/5/16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_gjzd_person_gj_new")
public class PersonPath {
    @Id
    private int ID;
    @Column(name = "\"NAME\"")
    private String NAME;
    private String BZTYPE;
    private String DWMC;
    private String NR;
    private Timestamp CREATE_DATE;
    private String S_ID_NUMBER;
    private String GXPCS;
}

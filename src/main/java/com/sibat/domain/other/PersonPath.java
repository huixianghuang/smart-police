package com.sibat.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/16.
 */
@Entity
@Table(name = "person_path")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonPath {
    @Id
    private int ID;
    private String NAME;
    private String BZTYPE;
    private String DWMC;
    private String NR;
    private String CREATE_DATE;
    private String S_ID_NUMBER;
    private String GXPCS;
}

package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tgw61 on 2017/5/23.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dt_jlb_pcs")
public class TDtJlbPcs {
    @Id
    private Integer SECID;//流水序号，取seq_bs_id
    private Timestamp FBRQ;//值班日期
    private String PCSID;//派出所ID
    private String PCSNAME;//派出所名称
    private String LD_ZB;//值班领导_主班
    private String LD_FB;// 值班领导_副班
    private String ZBMJ;//值班民警
}

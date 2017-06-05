package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/23.
 */
public interface TDtJlbPcsDao extends JpaRepository<TDtJlbPcs, Integer> {
    @Query(value = "select * from t_dt_jlb_pcs where PCSNAME =?1 and FBRQ = ?2",nativeQuery = true)
    List<TDtJlbPcs> findByPoliceAndTime(String police, Timestamp date);
}

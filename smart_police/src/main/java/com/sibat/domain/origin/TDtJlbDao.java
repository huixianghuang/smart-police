package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/23.
 */
public interface TDtJlbDao extends JpaRepository<TDtJlb, Integer> {
    @Query("select obj from TDtJlb obj where obj.PCSNAME =?1 and obj.FBRQ = ?2")
    List<TDtJlb> findByPoliceAndTime(String police, Timestamp date);
}

package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/24.
 */
public interface CbPersonDao extends JpaRepository<CbPerson,Integer> {
    @Query(value = "select * from t_cb_preson where xj_time=?1 and badw like ?2", nativeQuery = true)
    List findByDateAndPolice(String date, String police);

    @Query(value = "select * from t_cb_preson where pb_time between ?1 and ?2 and badw = ?3",nativeQuery = true)
    List<CbPerson> findByDateAndPolice(Timestamp time_start, Timestamp time_end, String police);
}

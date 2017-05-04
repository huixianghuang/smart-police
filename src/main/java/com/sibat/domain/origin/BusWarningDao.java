package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/2.
 */
public interface BusWarningDao extends JpaRepository<BusWarning, String> {
    @Query("select obj from BusWarning obj where obj.AF_TIME between ?1 and ?2")
    List<BusWarning> findByAF_TIME(String s, String s1);

    @Query(value = "select xl_id from bus_warning where af_time between ?1 and ?2 group by xl_id;", nativeQuery = true)
    List<String> findByAF_TIMEGroupByLine(String s, String s1);

    @Query(value = "select obj from BusWarning obj where obj.XL_ID=?3 and obj.AF_TIME between ?1 and ?2")
    List<BusWarning> findByAF_TIMEAndLineId(String start,String end ,String lineId);
}

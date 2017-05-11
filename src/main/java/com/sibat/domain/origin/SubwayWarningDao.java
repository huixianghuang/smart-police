package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/2.
 */
public interface SubwayWarningDao extends JpaRepository<SubwayWarning, String> {
    @Query("select obj from SubwayWarning obj where obj.AF_TIME between ?1 and ?2")
    List<SubwayWarning> findByAF_TIME(String start, String end);

    @Query("select obj from SubwayWarning obj where obj.AF_TIME like ?1")
    List<SubwayWarning> findByTime(String lastDay);

    @Query("select count(obj) from SubwayWarning obj where obj.AF_TIME between ?1 and ?2")
    Integer count(String start, String end);

    @Query("select count(obj) from SubwayWarning obj where obj.AF_TIME like ?1")
    Integer count(String time);

    @Query(value = "select wfjgxs_id,count(*) from subway_warning  where af_time between ?1 and ?2 group by wfjgxs_id;",nativeQuery = true)
    List<Object[]> countByPolice(String start, String end);
}

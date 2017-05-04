package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/2.
 */
public interface SubwayWarningDao extends JpaRepository<SubwayWarning, String> {
    @Query("select obj from SubwayWarning obj where obj.AF_TIME between ?1 and ?2")
    List<SubwayWarning> findByAF_TIME(String start,String end);

    @Query("select obj from SubwayWarning obj where obj.AF_TIME like ?1")
    List<SubwayWarning> findByTime(String lastDay);
}

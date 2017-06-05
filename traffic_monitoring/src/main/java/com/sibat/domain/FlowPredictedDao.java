package com.sibat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/26.
 */
public interface FlowPredictedDao extends JpaRepository<FlowPredicted, Long> {
    @Query("select obj from FlowPredicted obj where obj.startId=?1 and obj.endId=?2 and obj.time between ?3 and ?4 order by obj.time desc")
    List<FlowPredicted> findByStatus(String start, String end, String timeStart, String timeEnd);
}

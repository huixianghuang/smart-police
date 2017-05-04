package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tgw61 on 2017/5/4.
 */
public interface EventCountDao extends JpaRepository<EventCount, Integer> {
    @Query("select obj.eventSum from EventCount obj where obj.time=?1")
    Integer findByTime(String date);
}

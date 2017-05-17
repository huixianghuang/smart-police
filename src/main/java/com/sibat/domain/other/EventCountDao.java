package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/4.
 */
public interface EventCountDao extends JpaRepository<EventCount, Integer> {
    @Query("select obj.eventSum from EventCount obj where obj.time=?1")
    Integer findByTime(String date);

    @Query("select obj from EventCount obj where obj.time =?1")
    List<EventCount> findObjByTime(String date);
}

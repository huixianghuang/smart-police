package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/4.
 */
public interface LocalPoliceEventDao extends JpaRepository<LocalPoliceEvent, Integer> {
    @Query("select obj from LocalPoliceEvent obj where obj.time=?1")
    List<LocalPoliceEvent> findByTime(String date);
}

package com.sibat.domain.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/15.
 */
public interface TrafficCongestionDao extends JpaRepository<TrafficCongestion,Integer> {
    @Query(value = "select * from traffic_congestion  where time like ?1 order by time desc",nativeQuery = true)
    List<TrafficCongestion> select(String date);
}

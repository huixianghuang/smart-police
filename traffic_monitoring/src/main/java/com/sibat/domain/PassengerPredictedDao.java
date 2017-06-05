package com.sibat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/26.
 */
public interface PassengerPredictedDao extends JpaRepository<PassengerPredicted, Long> {
    @Query("select obj from PassengerPredicted obj where obj.stationId=?1 and obj.time between ?2 and ?3 order by obj.time desc")
    List<PassengerPredicted> findByStatus(String stationId ,String timeStart, String timeEnd);
}

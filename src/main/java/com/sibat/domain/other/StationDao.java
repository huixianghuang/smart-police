package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tgw61 on 2017/5/4.
 */
public interface StationDao extends JpaRepository<Station, Integer> {
    String findStationIdByStationName(String stationName);

    @Query("select obj from Station obj where obj.stationName like ?1")
    Station findByStationName(String stationName);

    Station findByStationId(String station_id);
}

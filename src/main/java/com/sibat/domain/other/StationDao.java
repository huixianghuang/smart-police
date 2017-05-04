package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tgw61 on 2017/5/4.
 */
public interface StationDao extends JpaRepository<Station, Integer> {
    String findStationIdByStationName(String stationName);

    Station findByStationName(String stationName);

    Station findByStationId(String station_id);
}

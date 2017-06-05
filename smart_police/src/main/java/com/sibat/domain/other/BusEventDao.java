package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/10.
 */
public interface BusEventDao extends JpaRepository<BusEvent, String> {
    @Query(value = "select police_id,count(*) from bus_event where event_time between ?1 and ?2 group by police_id;", nativeQuery = true)
    List<Object[]> countByPolice(String start, String end);

    @Query(value = "select line_id,count(*) from bus_event where event_time between ?1 and ?2 group by line_id;", nativeQuery = true)
    List<Object[]> countByLineId(String start, String end);

    @Query(value = "select station_name,count(*) from bus_event where event_time between ?1 and ?2 group by station_name;", nativeQuery = true)
    List<Object[]> countByStationName(String start, String end);

    @Query(value = "select type,count(*) from bus_event where event_time between ?1 and ?2 group by type;", nativeQuery = true)
    List<Object[]> countByType(String start, String end);

    @Query(value = "select category,count(*) from bus_event where event_time between ?1 and ?2 group by category;", nativeQuery = true)
    List<Object[]> countByCategory(String start, String end);
}

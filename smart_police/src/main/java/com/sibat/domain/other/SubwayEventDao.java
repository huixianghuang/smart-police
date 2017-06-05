package com.sibat.domain.other;

import com.sibat.domain.pojo.TimeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/4.
 */
public interface SubwayEventDao extends JpaRepository<SubwayEvent, Integer> {

    @Query(value = "select time,count(*) as value from subway_event group by time order by time;", nativeQuery = true)
    List<TimeCount> countEvent();

    @Query("select obj.stationName,count(obj) from SubwayEvent obj where obj.eventTime like ?1 group by obj.stationName")
    List<Object[]> selectByEventTime(String date);

    @Query("select obj.eventId,obj.category,obj.type,obj.content,obj.eventTime from SubwayEvent obj where obj.eventTime like ?2 and obj.stationId =?1")
    List<Object[]> findByStationIdAndTime(String station_id, String date);

    @Query(value = "select category,count(*) from subway_event where time =?1 group by category;", nativeQuery = true)
    List<Object[]> countEventCategory(@Param("date") String date);

    @Query(value = "select count(*) from subway_event where time =?1", nativeQuery = true)
    Integer countEvent(@Param("date") String date);

    @Query(value = "select police_id,count(*) from subway_event where time =?1 group by police_id;", nativeQuery = true)
    List<Object[]> countLocalPoliceEvent(@Param("date") String date);

    @Query(value = "select police from subway_event where police_id=?1 limit 1", nativeQuery = true)
    String findPoliceByPoliceId(@Param("policeId") String piliceId);

    @Query(value = "select police_id,count(*) from subway_event where time between ?1 and ?2 group by police_id;", nativeQuery = true)
    List<Object[]> countByPolice(String start, String end);

    @Query(value = "select line_id,count(*) from subway_event where time between ?1 and ?2 group by line_id;", nativeQuery = true)
    List<Object[]> countByLineId(String start, String end);

    @Query(value = "select station_name,count(*) from subway_event where time between ?1 and ?2 group by station_name;", nativeQuery = true)
    List<Object[]> countByStationName(String start, String end);

    @Query(value = "select type,count(*) from subway_event where time between ?1 and ?2 group by type;", nativeQuery = true)
    List<Object[]> countByType(String start, String end);

    @Query(value = "select category,count(*) from subway_event where time between ?1 and ?2 group by category;", nativeQuery = true)
    List<Object[]> countByCategory(String start, String end);
}

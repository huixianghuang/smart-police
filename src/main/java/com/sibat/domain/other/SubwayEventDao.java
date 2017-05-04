package com.sibat.domain.other;

import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.pojo.StationCount;
import com.sibat.domain.pojo.TimeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

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
}

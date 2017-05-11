package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/4.
 */
public interface EventCategoryCountDao extends JpaRepository<EventCategoryCount, Integer> {
    @Query("select obj.name,obj.count from EventCategoryCount obj where obj.time=?1")
    List<Object[]> findByTime(String date);
}

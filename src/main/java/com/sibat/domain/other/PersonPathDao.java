package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/16.
 */
public interface PersonPathDao extends JpaRepository<PersonPath, Integer> {
    @Query(value = "select * from person_path where CREATE_DATE like :date order by CREATE_DATE desc;", nativeQuery = true)
    List<PersonPath> findByTime(@Param("date") String date);
}

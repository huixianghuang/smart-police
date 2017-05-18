package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/16.
 */
public interface PersonPathDao extends JpaRepository<PersonPath, Integer> {
    @Query(value = " select * from t_gjzd_person_gj_new where Date(create_date) = ?1 order by create_date desc;", nativeQuery = true)
    List<PersonPath> findByTime(@Param("date") Date date);
}

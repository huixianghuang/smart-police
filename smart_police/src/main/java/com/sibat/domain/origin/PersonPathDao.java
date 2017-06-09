package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/16.
 */
public interface PersonPathDao extends JpaRepository<PersonPath, Integer> {
    @Query(value = " select * from t_gjzd_person_gj_new where Date(create_date) = ?1 order by create_date desc;", nativeQuery = true)
    List<PersonPath> findByTime(@Param("date") Date date);

    @Query("select obj from PersonPath obj where obj.S_ID_NUMBER=?1 order by obj.CREATE_DATE desc")
    List<PersonPath> findById(String id);

    @Query(value = "select * from t_gjzd_person_gj_new where s_id_number=?1 order by create_date desc",nativeQuery = true)
    List<PersonPath> findByIdtype2(String id);

    @Query("select obj from PersonPath obj where obj.NAME =?1")
    List<PersonPath> findByName(String NAME);

    @Query("select obj from PersonPath obj where obj.CREATE_DATE between ?1 and ?2 order by obj.CREATE_DATE desc")
    List<PersonPath> selectByTime(Timestamp start,Timestamp end);

    @Query(value = "select * from t_gjzd_person_gj_new where s_id_number in (:strings); ", nativeQuery = true)
    List<PersonPath> findByS_ID_NUMBER(@Param(value = "strings") List<String> strings);

}

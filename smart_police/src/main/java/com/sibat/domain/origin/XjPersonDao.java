package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/24.
 */
public interface XjPersonDao extends JpaRepository<XjPerson, Integer> {
    @Query(value = "select * from t_xj_preson where  badw = ?3 and xj_time between ?1 and ?2 ", nativeQuery = true)
    List<XjPerson> findByDateAndPolice(Timestamp datestart, Timestamp dateend, String police);
}

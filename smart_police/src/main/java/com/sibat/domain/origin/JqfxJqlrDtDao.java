package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/2.
 */
public interface JqfxJqlrDtDao extends JpaRepository<JqfxJqlrDt, String>,JpaSpecificationExecutor<JqfxJqlrDt>{
    @Query("select obj from JqfxJqlrDt obj where obj.AF_TIME between ?1 and ?2")
    List<JqfxJqlrDt> findByAF_TIME(String start, String end);

    @Query("select obj from JqfxJqlrDt obj where obj.AF_TIME like ?1")
    List<JqfxJqlrDt> findByTime(String lastDay);

    @Query("select count(obj) from JqfxJqlrDt obj where obj.AF_TIME between ?1 and ?2")
    Integer count(Timestamp start, Timestamp end);

    @Query("select count(obj) from JqfxJqlrDt obj where obj.AF_TIME = ?1")
    Integer count(Timestamp time);

    @Query(value = "select wfjgxs_id,count(*) from jqfx_jqlr_dt  where af_time between ?1 and ?2 group by wfjgxs_id;", nativeQuery = true)
    List<Object[]> countByPolice(Timestamp start, Timestamp end);

    @Query(value = "select * from jqfx_jqlr_dt", nativeQuery = true)
    List<JqfxJqlrDt> findAllObj();

    @Query(value = "select jqxz_id,count(*) from jqfx_jqlr_dt  where af_time between ?1 and ?2 group by jqxz_id;", nativeQuery = true)
    List<Object[]> countByType(Timestamp startTimestamp, Timestamp endTimestamp);

    @Query(value = "select obj from JqfxJqlrDt obj where obj.AF_TIME between ?1 and ?2")
    List<JqfxJqlrDt> select(Timestamp startTimestamp, Timestamp endTimestamp);

    @Query(value = "select xl_id,count(*) from jqfx_jqlr_dt  where af_time between ?1 and ?2 group by xl_id;", nativeQuery = true)
    List<Object[]> countByLineId(Timestamp startTimestamp, Timestamp endTimestamp);
}

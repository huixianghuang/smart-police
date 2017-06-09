package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/15.
 */
public interface SuspectDao extends JpaRepository<Suspect, Integer>, PagingAndSortingRepository<Suspect, Integer> {
    @Query(value = "select deptname,count(*) from tb_gjzd_person group by deptname;", nativeQuery = true)
    List<Object[]> countSumGroupByPolice();

    @Query(value = "select zdrystate,count(*) from tb_gjzd_person group by zdrystate;", nativeQuery = true)
    List<Object[]> countSumGroupByType();

    @Query(value = "select * from tb_gjzd_person obj where id_number_18=?1 limit 1", nativeQuery = true)
    Suspect findByID(String s_id_number);

    @Query(value = "select * from tb_gjzd_person tb where tb.id_number_18 in " +
            "(select obj.s_id_number from t_gjzd_person_gj_new obj where obj.CREATE_DATE between ?1 and ?2 order by obj.CREATE_DATE desc) " +
            "and tb.deptname=?3", nativeQuery = true)
    List<Suspect> findByTimeAndPolice(Timestamp start, Timestamp end, String police);

    @Query(value = "select * from tb_gjzd_person tb where tb.id_number_18 in " +
            "(select obj.s_id_number from t_gjzd_person_gj_new obj where obj.CREATE_DATE between ?1 and ?2 order by obj.CREATE_DATE desc) ", nativeQuery = true)
    List<Suspect> findAllByTimeAndPolice(Timestamp start, Timestamp end);

    @Query(value = "select * from tb_gjzd_person obj where id_number_18=?1 and deptname=?2 limit 1", nativeQuery = true)
    Suspect findByIDAndDEPTNAME(String s_id_number, String deptName);
}

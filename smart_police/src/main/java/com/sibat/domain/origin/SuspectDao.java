package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

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
}

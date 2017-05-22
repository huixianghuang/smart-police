package com.sibat.domain.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/15.
 */
public interface KeyPersonDao extends JpaRepository<KeyPerson, Integer>, PagingAndSortingRepository<KeyPerson, Integer> {
    @Query(value = "select DEPTNAME,count(*) from tb_gjzd_person group by DEPTNAME;", nativeQuery = true)
    List<Object[]> countSumGroupByPolice();

    @Query(value = "select ZDRYSTATE,count(*) from tb_gjzd_person group by ZDRYSTATE;", nativeQuery = true)
    List<Object[]> countSumGroupByType();

    @Query(value = "select * from tb_gjzd_person obj where ID_NUMBER=?1 limit 1", nativeQuery = true)
    KeyPerson findByID(String s_id_number);
}

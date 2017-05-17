package com.sibat.domain.other;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/15.
 */
public interface KeyPersonDao extends JpaRepository<KeyPerson, Integer>, PagingAndSortingRepository<KeyPerson, Integer> {
//    @Query("select obj from KeyPerson obj")
//    Page<KeyPerson> findList(Pageable pageable);

    @Query(value = "select DEPTNAME,count(*) from key_person group by DEPTNAME;", nativeQuery = true)
    List<Object[]> countSumGroupByPolice();

    @Query(value = "select ZDRYSTATE,count(*) from key_person group by ZDRYSTATE;", nativeQuery = true)
    List<Object[]> countSumGroupByType();

    @Query("select obj from KeyPerson obj where obj.ID_NUMBER=?1")
    List<KeyPerson> findByID(String s_id_number);
}

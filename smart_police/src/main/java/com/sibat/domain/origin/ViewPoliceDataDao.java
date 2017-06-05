package com.sibat.domain.origin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/26.
 */
public interface ViewPoliceDataDao extends JpaRepository<ViewPoliceData, String>,
        PagingAndSortingRepository<ViewPoliceData, String>, JpaSpecificationExecutor<ViewPoliceData> {



    @Query("select obj from ViewPoliceData obj where obj.deptName=?1")
    List<ViewPoliceData> findByDeptName(String deptName);
}

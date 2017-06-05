package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tgw61 on 2017/5/24.
 */
public interface PoliceDao extends JpaRepository<Police, Integer> {
    @Query("select obj.policeName from Police obj where obj.policeId=?1")
    String fingPoliceNameByPoliceId(String policeId);

    Police findByPoliceName(String policeName);
}

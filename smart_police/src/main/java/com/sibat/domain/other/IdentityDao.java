package com.sibat.domain.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tgw61 on 2017/5/31.
 */
public interface IdentityDao extends JpaRepository<Identity, String> {
    @Query("select obj.place from Identity obj where obj.id=?1")
    String findPlaceById(String id);
}

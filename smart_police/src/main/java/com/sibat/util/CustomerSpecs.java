package com.sibat.util;

import com.sibat.domain.origin.JqfxJqlrDt;
import com.sibat.domain.pojo.WarningAnalysis;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by tgw61 on 2017/5/23.
 */
public class CustomerSpecs {
    public static Specification<JqfxJqlrDt> analysis(WarningAnalysis warningAnalysis) {
        return new Specification<JqfxJqlrDt>() {
            @Override
            public Predicate toPredicate(Root<JqfxJqlrDt> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                cb.like(root.get("AF_TIME"), "2013-05-25%");
                return cb.equal(root.get("DDMC"), "侨香站");
            }
        };
    }
}

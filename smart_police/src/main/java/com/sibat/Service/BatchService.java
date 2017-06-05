package com.sibat.Service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/3.
 */
@Service
public class BatchService {
    Logger logger = LoggerFactory.getLogger(BatchService.class);
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void batchInsert(List list) {
        for (int i = 0, size = list.size(); i < size; i++) {
            entityManager.persist(list.get(i));
            if (size > 5000 && i % 5000 == 0) {
                entityManager.flush();
                entityManager.clear();
                logger.info("完成一部分");
            } else {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    @Transactional
    public void batchUpdate(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            entityManager.merge(list.get(i));
            if (i % 10 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}

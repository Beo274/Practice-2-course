package ru.is.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.is.models.Appeal;

import java.util.List;

@Service
public class WatchAppealService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Appeal> getAppealsWithEmptyResolution() {
        return entityManager.createQuery("SELECT a FROM Appeal a WHERE a.resolution IS NULL OR a.resolution = ''", Appeal.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Appeal getAppealById(Long id) {
        return entityManager.find(Appeal.class, id);
    }

}

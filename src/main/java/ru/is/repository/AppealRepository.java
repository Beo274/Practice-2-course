package ru.is.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.is.models.Appeal;

@Repository
public class AppealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Appeal findById(Long id) {
        try {
            return entityManager.createQuery("SELECT a FROM Appeal a WHERE a.id = :id", Appeal.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Appeal appeal) {
        entityManager.persist(appeal);
    }

    public void update(Appeal appeal) {
        entityManager.merge(appeal);
    }
}

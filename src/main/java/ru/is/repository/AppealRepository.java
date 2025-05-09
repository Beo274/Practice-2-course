package ru.is.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.is.models.Appeal;

import java.util.Optional;

public class AppealRepository {
    private final SessionFactory sessionFactory;

    public AppealRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Appeal> findAppealById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Appeal appeal = session.createQuery("FROM Appeal WHERE id = :id", Appeal.class)
                .setParameter("id", id).uniqueResult();
        return Optional.ofNullable(appeal);
    }
}

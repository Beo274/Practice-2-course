package ru.is.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.is.models.Appeal;

@Service
public class SendAppealService {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Long createAppeal(Appeal appeal) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(appeal);
        return appeal.getId();
    }

    // Добавьте этот метод
    @Transactional
    public void saveAppeal(Appeal appeal) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(appeal);
    }

    @Transactional(readOnly = true)
    public Appeal getAppealById(Long id) {
        return sessionFactory.getCurrentSession().get(Appeal.class, id);
    }
}
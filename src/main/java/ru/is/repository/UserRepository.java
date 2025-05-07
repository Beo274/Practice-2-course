package ru.is.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.is.models.User;
import java.util.Optional;

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.createQuery("FROM User WHERE username = :username", User.class)
                .setParameter("username", username)
                .uniqueResult();
        return Optional.ofNullable(user);
    }
}
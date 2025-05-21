package ru.is.service;

import ru.is.models.User;
import java.util.Optional;

public interface UserService {
    /**
     * Находит пользователя по имени пользователя
     * @param username имя пользователя для поиска
     * @return Optional с пользователем, если найден
     */
    Optional<User> findByUsername(String username);
}
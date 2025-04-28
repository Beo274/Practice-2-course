package ru.is.repositories;

import ru.is.models.User;
import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    boolean isExists(String name, String password);
}

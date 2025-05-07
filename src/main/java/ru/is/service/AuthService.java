package ru.is.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.is.models.User;
import ru.is.repository.UserRepository;
import ru.is.utils.PasswordUtil;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public boolean authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        return PasswordUtil.checkPassword(password, user.getPassword());
    }
}
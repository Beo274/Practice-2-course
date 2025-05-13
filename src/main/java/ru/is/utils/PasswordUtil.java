package ru.is.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PasswordUtil {

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

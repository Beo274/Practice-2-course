package ru.is.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PasswordUtil {
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

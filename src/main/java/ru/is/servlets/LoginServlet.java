package ru.is.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import ru.is.DataBaseConnection;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {

            stmt.setInt(1,1);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    System.out.println("hashed " + hashedPassword);

                    boolean passwordMatches = BCrypt.checkpw(pass, hashedPassword);
                    System.out.println("Password verification result: " + passwordMatches);

                    if (passwordMatches) {
                        req.getRequestDispatcher("jsp/main.jsp").forward(req, resp);
                    }
                } else {
                    System.out.println("User not found");
                }
            }
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }
}
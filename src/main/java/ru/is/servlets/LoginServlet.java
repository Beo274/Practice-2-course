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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String pass = req.getParameter("password");

        System.out.println(name);
        System.out.println(pass);

        try (Connection conn = DataBaseConnection.getConnection()) {
            if (conn == null) {
                throw new ServletException("Не удалось установить соединение с БД");
            }

            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?")) {
                stmt.setString(1, name);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");

                    if (BCrypt.checkpw(pass, hashedPassword)) {
                        HttpSession session = req.getSession();
                        session.setAttribute("username", name);
                        resp.sendRedirect("home.jsp");
                        return;
                    }
                }

                req.setAttribute("error", "Неверное имя пользователя или пароль");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException("Ошибка базы данных: " + e.getMessage(), e);
        }
    }
}
package ru.is.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.is.models.User;
import ru.is.service.UserService;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter implements Filter {
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {
        this.userService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean(UserService.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Разрешаем доступ к публичным URL без авторизации
        if (isPublicPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Проверка аутентификации для защищённых URL
        User loggedUser = getAuthenticatedUser(httpRequest);

        if (loggedUser == null) {
            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return path.equals("/") ||
                path.equals("/home") ||
                path.equals("/login") ||
                path.startsWith("/static/") ||
                path.startsWith("/resources/");
    }

    private User getAuthenticatedUser(HttpServletRequest request) {
        // 1. Проверка существующей сессии
        HttpSession session = request.getSession(false);
        if (session != null) {
            User sessionUser = (User) session.getAttribute("loggedUser");
            if (sessionUser != null) {
                return sessionUser;
            }
        }

        // 2. Проверка rememberMe куки
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMe".equals(cookie.getName())) {
                    Optional<User> userOptional = userService.findByUsername(cookie.getValue());
                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        // Создаём новую сессию для восстановленного пользователя
                        request.getSession(true).setAttribute("loggedUser", user);
                        return user;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public void destroy() {
        // Очистка ресурсов (не требуется)
    }
}
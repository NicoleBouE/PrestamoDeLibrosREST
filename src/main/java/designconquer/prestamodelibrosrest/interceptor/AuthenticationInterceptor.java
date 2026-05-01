package designconquer.prestamodelibrosrest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        // Direcciones públicas
        if (isPublicPath(path)) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("User") != null) {
            // Permitir solicitud si hay sesión de usuario
            return true;
        }

        log.info("No se pudo autenticar {}", path);
        // Redirigir a home
        response.sendRedirect(request.getContextPath() + "/?error=user_not_authenticated");
        return false;
    }

    private boolean isPublicPath(String path) {
        if (path == null) return true;
        return path.equals("/")
                || path.startsWith("/login")
                || path.startsWith("/error")
                || path.startsWith("/api/");

    }
}

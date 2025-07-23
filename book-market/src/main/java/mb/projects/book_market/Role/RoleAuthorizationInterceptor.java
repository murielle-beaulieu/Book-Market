package mb.projects.book_market.Role;

import java.util.Set;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mb.projects.book_market.Enums.Role;

@Component
public class RoleAuthorizationInterceptor implements HandlerInterceptor {
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        AllowedRoles allowedRoles = handlerMethod.getMethodAnnotation(AllowedRoles.class);
        if (allowedRoles == null) {
            return true;
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new AuthenticationException();
        }

        Set<String> authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        Role[] roles = allowedRoles.value();
        for (Role allowedRole : roles) {
            if (authorities.contains("ROLE_" + allowedRole)) {
                return true;
            }
        }
        
        throw new AccessDeniedException("Not Authorised due to Role");
    }

}

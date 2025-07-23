package mb.projects.book_market.Greeting;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String helloAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authRole = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst()
                .orElse("UNKNOWN");
        String readable = authRole.substring(5).toLowerCase();
        return "Welcome " + readable;
    }

}
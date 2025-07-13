package mb.projects.book_market.Config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    // OncePerRequestFilter ensures this is done only once per HttpRequest, better for perfomance and avoid doubling actions
    // provide HttpServletRequest and HttpServletResponse 

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader + " is AUTH HEADER ");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authHeader.substring(7);

        // get the user by its email (it's unique)
        String userEmail = jwtService.extractUsername(jwtToken);

        // if email is not null and no user is already set in securityContextHolder
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // find user in db
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println(this.jwtService.isTokenValid(jwtToken, userDetails) + " IS TOKEN VALID?");

            // double check that the token is valid/not expired
            if (this.jwtService.isTokenValid(jwtToken, userDetails)) {

               //  extract username/ password from HTTP request & prepare Authentication type object
                UsernamePasswordAuthenticationToken userPassToken = new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
                
                // set the user in SecurityContextHolder
                userPassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userPassToken);
            }

        }

        // after all the checks and extract necessary information, we're ready to go to the next step
        filterChain.doFilter(request, response);

    }

}
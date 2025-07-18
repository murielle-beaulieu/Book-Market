package mb.projects.book_market.Config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import mb.projects.book_market.User.User;

@Component
@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String secretKey;
    // private static final String SECRET_KEY = System.getenv("SECRET_KEY");

    public boolean isTokenValid(String token, UserDetails userDetails) {
        if (userDetails == null) {
            return false;
        }
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        String username = extractClaims(token, Claims::getSubject);
        System.out.println("Extracted username: " + username);
        return username;
    }

    public boolean isTokenExpired(String token) {
        boolean expired = extractExpiration(token).before(new Date());
        System.out.println("Token expired: " + expired);
        return expired;
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    @SuppressWarnings("unused")
    private String generateToken(Object object, UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(User user) {
        return Jwts
                .builder()
                .setClaims(null)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24)) // valid for one day
                .signWith(this.getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        // decode secret key to a byte array
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        // HMAC key for SHA (Secure Hash Algorithm), is a cryptographic key used in HMAC
        // (Hash-based Message Authentication Code) operations.
        // HMAC is a construction for creating a secure way to verify both
        // the integrity and authenticity of a message or data.
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long extractUserId(String token) {
        Claims allClaims = this.extractAllClaims(token);
        return Long.parseLong(allClaims.getSubject());

    }

}

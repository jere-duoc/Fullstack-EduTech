package EduTech.Servicio.Autorizacion.security; 
import EduTech.Servicio.Autorizacion.dto.UsuarioValidationResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;    

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;            
import java.util.HashMap;
import java.util.List;            
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretString;

    @Value("${jwt.expiration.ms}")
    private long jwtExpirationMs;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretString.getBytes());
    }

    public String generateToken(UsuarioValidationResponseDTO userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails.getRoles() != null && !userDetails.getRoles().isEmpty()) {
            claims.put("roles", userDetails.getRoles());
        }
        claims.put("userId", userDetails.getId());
        claims.put("nombre", userDetails.getNombre());

        return createToken(claims, userDetails.getRun());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSigningKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String usernameFromPrincipal) { 
        final String username = extractUsername(token);
        return (username.equals(usernameFromPrincipal) && !isTokenExpired(token));
    }
}
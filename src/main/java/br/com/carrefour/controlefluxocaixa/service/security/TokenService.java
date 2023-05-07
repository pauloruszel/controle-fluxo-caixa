package br.com.carrefour.controlefluxocaixa.service.security;

import br.com.carrefour.controlefluxocaixa.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TokenService {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String gerarToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("Lancamentos")
                .setSubject(usuario.getUsername())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"))))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String getSubject(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .requireIssuer("Lancamentos")
                    .build()
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            Date expiration = claims.getExpiration();
            Date now = new Date();

            if (expiration != null && now.before(expiration)) {
                return claims.getSubject();
            }
        } catch (JwtException e) {
            log.error("Erro ao verificar token JWT: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido");
        }
        return null;
    }
}

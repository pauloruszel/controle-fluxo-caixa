package br.com.carrefour.controlefluxocaixa.service.security;

import br.com.carrefour.controlefluxocaixa.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Test
    @DisplayName("Deve obter o subject a partir do token JWT")
    public void testGetSubject() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("usuario_teste");

        String token = tokenService.gerarToken(usuario);

        String subject = tokenService.getSubject(token);

        assertNotNull(subject);
        assertEquals(usuario.getUsername(), subject);
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar obter o subject de um token expirado")
    public void testGetSubjectExpirado() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("usuario_teste");

        String token = Jwts.builder()
                .setSubject(usuario.getUsername())
                .setIssuer("Lancamentos")
                .setExpiration(Date.from(LocalDateTime.now().minusSeconds(2).toInstant(ZoneOffset.of("-03:00"))))
                .signWith(SECRET_KEY)
                .compact();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tokenService.getSubject(token);
        });

        assertEquals(HttpStatus.UNAUTHORIZED.value(), exception.getBody().getStatus());
    }


    @Test
    @DisplayName("Deve lançar uma exceção ao tentar obter o subject de um token inválido")
    public void testGetSubjectInvalido() {
        String token = "token_invalido";

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tokenService.getSubject(token);
        });

        assertAll("exception",
                () -> {
                    assertNotNull(exception.getBody());
                    assertEquals(HttpStatus.UNAUTHORIZED.value(), exception.getBody().getStatus());
                },
                () -> assertFalse(hasMalformedJwtExceptionCause(exception))
        );
    }

    private boolean hasMalformedJwtExceptionCause(Throwable exception) {
        Throwable cause = exception.getCause();

        if (cause == null) {
            return false;
        }

        if (cause instanceof MalformedJwtException) {
            return true;
        }

        return hasMalformedJwtExceptionCause(cause);
    }


}


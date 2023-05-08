package br.com.carrefour.controlefluxocaixa.controller;

import br.com.carrefour.controlefluxocaixa.dto.Login;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void testLoginFailure() {
        Login login = new Login("username", "password");
        Authentication authentication = new UsernamePasswordAuthenticationToken(login.login(), login.password());
        Mockito.when(authenticationManager.authenticate(authentication)).thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> authController.login(login));
    }

}
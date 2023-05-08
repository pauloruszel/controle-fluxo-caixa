package br.com.carrefour.controlefluxocaixa.service.security;

import br.com.carrefour.controlefluxocaixa.model.Usuario;
import br.com.carrefour.controlefluxocaixa.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testLoadUserByUsername() {
        Usuario usuario = new Usuario();
        usuario.setLogin("testUser");
        usuario.setPassword("testPassword");

        when(usuarioRepository.findByLogin("testUser")).thenReturn(usuario);

        UserDetails userDetails = authenticationService.loadUserByUsername("testUser");

        assertEquals("testUser", userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsernameWithInvalidUsername() {
        String invalidUsername = "nonExistentUser";
        when(usuarioRepository.findByLogin(invalidUsername)).thenReturn(null);

        UserDetails userDetails = authenticationService.loadUserByUsername(invalidUsername);

        assertNull(userDetails);
    }


}
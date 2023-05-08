package br.com.carrefour.controlefluxocaixa.repository;

import br.com.carrefour.controlefluxocaixa.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testSaveUsuario() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuario");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode("senha"));
        usuarioRepository.save(usuario);

        assertNotNull(usuario.getId());
    }

    @Test
    void testFindByLogin() {
        String login = "teste-" + UUID.randomUUID();
        String password = "1234";

        // Limpa o banco de dados antes de cada execução do teste
        usuarioRepository.deleteAll();

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(password));

        usuarioRepository.save(usuario);

        Usuario foundUsuario = usuarioRepository.findByLogin(login);

        assertNotNull(foundUsuario);
        assertEquals(login, foundUsuario.getLogin());
    }



}


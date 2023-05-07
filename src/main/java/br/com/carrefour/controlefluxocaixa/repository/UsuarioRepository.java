package br.com.carrefour.controlefluxocaixa.repository;

import br.com.carrefour.controlefluxocaixa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}


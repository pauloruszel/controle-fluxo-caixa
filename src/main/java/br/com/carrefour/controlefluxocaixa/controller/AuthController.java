package br.com.carrefour.controlefluxocaixa.controller;

import br.com.carrefour.controlefluxocaixa.dto.Login;
import br.com.carrefour.controlefluxocaixa.model.Usuario;
import br.com.carrefour.controlefluxocaixa.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.login(), login.password());

        Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);
        var usuario = (Usuario) authenticate.getPrincipal();
        return tokenService.gerarToken(usuario);

    }
}

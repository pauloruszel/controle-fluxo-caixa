package br.com.carrefour.controlefluxocaixa.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTests {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ApplicationContext context;

    @Test
    void testSecurityConfig() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.createObjectNode()
                .put("login", "Paulo")
                .put("password", "1234");

        String jsonStr = objectMapper.writeValueAsString(json);

        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        assertNotNull(token);

        mockMvc.perform(get("/swagger-ui.html"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/lancamentos"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void testAuthenticationManager() {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);
        assertNotNull(authenticationManager);
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        assertNotNull(passwordEncoder);

        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);

        assertTrue(passwordEncoder.matches(password, encodedPassword));
    }


}









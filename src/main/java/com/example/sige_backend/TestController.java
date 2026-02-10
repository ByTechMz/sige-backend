package com.example.sige_backend;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/firebase-test")
    public String testFirebase() {
        try {
            // Listar apenas 1 usuário como teste
            UserRecord user = FirebaseAuth.getInstance().getUserByEmail("teste@sige.com");
            return "Usuário encontrado: " + user.getEmail();
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }
}

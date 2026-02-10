package com.example.sige_backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            if (!FirebaseApp.getApps().isEmpty()) {
                return;
            }

            String firebaseConfig = System.getenv("FIREBASE_CONFIG");

            if (firebaseConfig == null || firebaseConfig.isBlank()) {
                throw new RuntimeException("FIREBASE_CONFIG não definida");
            }

            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8))
            );

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println("🔥 Firebase inicializado com sucesso");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar Firebase", e);
        }
    }
}

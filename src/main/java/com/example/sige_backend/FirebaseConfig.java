package com.example.sige_backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            // Lê o JSON do Firebase a partir da variável de ambiente FIREBASE_CONFIG
            String firebaseConfigJson = System.getenv("FIREBASE_CONFIG");
            if (firebaseConfigJson == null || firebaseConfigJson.isEmpty()) {
                throw new IllegalStateException("Variável de ambiente FIREBASE_CONFIG não encontrada!");
            }

            InputStream serviceAccount = new ByteArrayInputStream(firebaseConfigJson.getBytes(StandardCharsets.UTF_8));

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("Firebase inicializado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o Firebase", e);
        }
    }
}

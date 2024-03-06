package com.epam.secrets.service;

import com.epam.secrets.entity.Secret;
import com.epam.secrets.repository.SecretRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SecretService {

    private final SecretRepository secretRepository;

    public SecretService(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    public String saveSecret(String message) {
        Secret secret = Secret.builder().text(message).build();
        secretRepository.save(secret);
        return secret.getId().toString();
    }

    public String getSecretMessageAndDeleteSecret(String id) {
        String text = "There is not any secret!";
        Optional<Secret> optionalSecret = secretRepository.findById(UUID.fromString(id));
        if (optionalSecret.isPresent()) {
            Secret secret = optionalSecret.get();
            text = secret.getText();
            secretRepository.delete(secret);
        }
        return text;
    }
}

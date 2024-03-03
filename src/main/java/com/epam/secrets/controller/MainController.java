package com.epam.secrets.controller;


import com.epam.secrets.dto.Secret;
import com.epam.secrets.service.SecretService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final SecretService service;

    public MainController(SecretService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        Secret secret = new Secret();
        model.addAttribute("secret", secret);
        return "index";
    }

    @PostMapping("/create")
    public String createSecret(Model model, @ModelAttribute("secret") Secret secret) {
        String createdSecretId = service.saveSecret(secret.getText());
        model.addAttribute("secretId", createdSecretId);
        return "success";
    }

    @GetMapping("/show/{id}")
    public String seeSecret(Model model, @PathVariable("id") String secretId) {
        String secretMessage = service.getSecretMessageAndDeleteSecret(secretId);
        model.addAttribute("secretMessage", secretMessage);
        return "see_secret";
    }
}

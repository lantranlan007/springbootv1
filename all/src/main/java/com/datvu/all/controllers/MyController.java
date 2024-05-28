package com.datvu.all.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/protected-resource")
    public ResponseEntity<String> accessProtectedResource(Authentication authentication) throws Exception {
        if (authentication == null || !authentication.isAuthenticated()) {
            // Handle unauthenticated access
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        // Get the currently authenticated user
        String username = authentication.getName();

        // Load the authorized client for the configured provider
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                "<provider-registration-id>", username);

        // Extract access token from the authorized client
        String accessToken = client.getAccessToken().getTokenValue();

        // Use the access token to access a protected resource
        String resourceUrl = "https://api.example.com/protected-data";
        RestTemplate restTemplate = new RestTemplate();


        return ResponseEntity.ok(accessToken);
    }
}

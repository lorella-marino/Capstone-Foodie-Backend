package it.epicode.Foodie.auth.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String nome;
    private String email;
    private Set<String> roles;
}


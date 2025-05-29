package it.epicode.Foodie.auth.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private Set<String> roles;
}


package it.epicode.Foodie.auth.authorization;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String telefono;
    private String indirizzo;
    private String email;
}

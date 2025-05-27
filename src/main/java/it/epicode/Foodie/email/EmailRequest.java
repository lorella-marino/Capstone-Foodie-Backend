package it.epicode.Foodie.email;

import lombok.Data;

@Data
public class EmailRequest {
    private String nome;
    private String email;
    private String messaggio;
}


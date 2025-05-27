package it.epicode.Foodie.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoRequest {
    private String nome;
    private double prezzo;
    private int calorie;
    private String descrizione;
    private String immagine;
}

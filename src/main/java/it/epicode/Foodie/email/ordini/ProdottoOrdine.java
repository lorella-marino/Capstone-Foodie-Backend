package it.epicode.Foodie.email.ordini;

import lombok.Data;

import java.util.List;

@Data
public class ProdottoOrdine {
    private String nome;
    private int quantita;
    private List<String> toppings;
    private String note;
}

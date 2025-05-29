package it.epicode.Foodie.email.ordini;

import lombok.Data;

import java.util.List;

@Data

public class EmailOrdineRequest {
    private String nomeCliente;
    private String cognomeCliente;
    private String emailCliente;
    private List<ProdottoOrdine> prodotti;
    private String tipoConsegna;
    private String indirizzo;
    private double totale;
}
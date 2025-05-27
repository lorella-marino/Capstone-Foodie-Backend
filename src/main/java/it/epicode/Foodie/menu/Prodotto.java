package it.epicode.Foodie.menu;

import it.epicode.Foodie.menu.toppings.Topping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menus")

public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private double prezzo;
    private int calorie;
    private String descrizione;
    private SezioneMenu sezioneMenu;
    private String immagine;
    @ManyToMany
    private List<Topping> topping = new ArrayList<>();


}
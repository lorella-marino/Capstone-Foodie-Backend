package it.epicode.Foodie.menu.toppings;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
    List<Topping> findByNome(String nome);
}
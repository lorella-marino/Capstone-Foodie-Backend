package it.epicode.Foodie.menu.toppings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToppingRunner implements CommandLineRunner {
    @Autowired
    private ToppingRepository toppingRepository;

    public void run(String... args) throws Exception {
        addToppingIfNotExists("Pomodoro", 0.50);
        addToppingIfNotExists("Mozzarella", 1.0);
        addToppingIfNotExists("Avocado", 1.0);
        addToppingIfNotExists("Cipolla", 0.50);
        addToppingIfNotExists("Funghi", 0.50);
    }

    private void addToppingIfNotExists(String nome, double prezzo) {
        List<Topping> existing = toppingRepository.findByNome(nome);
        if (existing.isEmpty()) {
            Topping topping = new Topping();
            topping.setNome(nome);
            topping.setPrezzo(prezzo);
            toppingRepository.save(topping);
        }
    }

}

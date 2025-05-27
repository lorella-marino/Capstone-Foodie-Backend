package it.epicode.Foodie.menu.toppings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ToppingRunner implements CommandLineRunner {
    @Autowired
    private ToppingRepository toppingRepository;

    public void run(String... args) throws Exception {
        Topping pomodoro = new Topping();
        pomodoro.setNome("Pomodoro");
        pomodoro.setPrezzo(0.50);

        Topping mozzarella = new Topping();
        mozzarella.setNome("Mozzarella");
        mozzarella.setPrezzo(1.0);

        Topping avocado = new Topping();
        avocado.setNome("Avocado");
        avocado.setPrezzo(1.0);

        Topping cipolla = new Topping();
        cipolla.setNome("Cipolla");
        cipolla.setPrezzo(0.50);

        Topping funghi = new Topping();
        funghi.setNome("Funghi");
        funghi.setPrezzo(0.50);

        toppingRepository.save(pomodoro);
        toppingRepository.save(mozzarella);
        toppingRepository.save(avocado);
        toppingRepository.save(cipolla);
        toppingRepository.save(funghi);
    }
}

package it.epicode.Foodie.menu;


import it.epicode.Foodie.cloudinary.CloudinaryService;
import it.epicode.Foodie.menu.toppings.Topping;
import it.epicode.Foodie.menu.toppings.ToppingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ProdottoService {
    private final ProdottoRepository prodottoRepository;
    private final ToppingRepository toppingRepository;
    private final CloudinaryService cloudinaryService;

    public void uploadImmagine(Long id, MultipartFile fotoProfilo) {
        Prodotto dipendente = prodottoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Piatto non trovato"));
        String url = cloudinaryService.uploadImage(fotoProfilo);
        dipendente.setImmagine(url);
        prodottoRepository.save(dipendente);
    }

    public List<Prodotto> getMenu() {
          return prodottoRepository.findAll();
       }

    public Prodotto create(ProdottoRequest prodottoRequest, SezioneMenu sezioneMenu ) {
        Prodotto prodotto = new Prodotto();
        BeanUtils.copyProperties(prodottoRequest, prodotto);
        prodotto.setSezioneMenu(sezioneMenu);
        List<Topping> toppings = toppingRepository.findAll();
        prodotto.setTopping(toppings);
        return prodottoRepository.save(prodotto);
    }

    public Prodotto update(Long id, ProdottoRequest prodottoRequest) {
        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));
        BeanUtils.copyProperties(prodottoRequest, prodotto);
        return prodottoRepository.save(prodotto);
    }

    public void delete(Long id) {
        prodottoRepository.deleteById(id);
    }
}

package it.epicode.Foodie.menu;

import it.epicode.Foodie.menu.toppings.Topping;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping ("/api/menu" )
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @PatchMapping(path = "/{id}/immagine", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImmagine(@PathVariable Long id, @RequestParam MultipartFile file) {
        prodottoService.uploadImmagine(id, file);
    }

    @GetMapping
    public List<Prodotto> getMenu() {
        return prodottoService.getMenu();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prodotto create(@RequestBody ProdottoRequest prodottoRequest , @RequestParam SezioneMenu sezioneMenu) {
        return prodottoService.create(prodottoRequest, sezioneMenu);
    }

    @PutMapping("/{id}")
    public Prodotto update(@PathVariable Long id, @RequestBody ProdottoRequest prodottoRequest) {
        return prodottoService.update(id, prodottoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prodottoService.delete(id);
    }

}

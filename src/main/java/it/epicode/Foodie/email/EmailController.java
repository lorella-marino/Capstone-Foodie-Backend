package it.epicode.Foodie.email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/richieste" )
@CrossOrigin(origins = "*") // abilita il frontend a inviare richieste
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inviaRichiesta(@RequestBody EmailRequest richiesta) throws MessagingException {
        String subject = "Nuova richiesta da " + richiesta.getNome();
        String body = "<strong>Nome:</strong> " + richiesta.getNome() +
                "<br><strong>Email:</strong> " + richiesta.getEmail() +
                "<br><br><strong>Messaggio:</strong><br>" + richiesta.getMessaggio();


        emailService.sendEmail("lorellamarino.code@gmail.com", richiesta.getEmail(), subject, body);
    }
}



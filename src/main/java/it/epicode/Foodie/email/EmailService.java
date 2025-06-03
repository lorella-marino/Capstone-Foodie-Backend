package it.epicode.Foodie.email;

import it.epicode.Foodie.email.ordini.EmailOrdineRequest;
import it.epicode.Foodie.email.ordini.ProdottoOrdine;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String replyTo, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String[] recipients = to.split("\\s*[,;]\\s*");
        helper.setTo(recipients);

        helper.setReplyTo(replyTo); // imposta l’email dell’utente come mittente di risposta
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);

        System.out.println("Email inviata con successo a " + to);
    }

    public void sendOrder(EmailOrdineRequest ordine) throws MessagingException {
        String subjectAdmin = "Nuovo ordine da " + ordine.getNomeCliente() + " " + ordine.getCognomeCliente();
        String subjectUser = "Il tuo ordine Foodie";

        StringBuilder body = new StringBuilder();
        body.append("<strong>Cliente:</strong> ").append(ordine.getNomeCliente()).append(" ").append(ordine.getCognomeCliente())
                .append("<br><strong>Email:</strong> ").append(ordine.getEmailCliente())
                .append("<br><strong>Tipo Consegna:</strong> ").append(ordine.getTipoConsegna());

        if ("domicilio".equalsIgnoreCase(ordine.getTipoConsegna()) && ordine.getIndirizzo() != null) {
            body.append("<br><strong>Indirizzo:</strong> ").append(ordine.getIndirizzo());
        }

        if ("ritiro".equalsIgnoreCase(ordine.getTipoConsegna()) && ordine.getIndirizzo() != null) {
            body.append("<br><strong>Location ritiro:</strong> ").append(ordine.getIndirizzo());
        }

        if (ordine.getOrario() != null && !ordine.getOrario().isEmpty()) {
            body.append("<br><strong>Orario:</strong> ").append(ordine.getOrario());
        }

        body.append("<br><br><strong>Prodotti Ordinati:</strong><ul>");
        for (ProdottoOrdine p : ordine.getProdotti()) {
            body.append("<li>")
                    .append(p.getNome())
                    .append(" x").append(p.getQuantita());

            if (p.getToppings() != null && !p.getToppings().isEmpty()) {
                body.append("<br><i>Toppings:</i> ").append(String.join(", ", p.getToppings()));
            }

            if (p.getNote() != null && !p.getNote().isEmpty()) {
                body.append("<br><i>Note:</i> ").append(p.getNote());
            }

            body.append("</li>");
        }
        body.append("</ul><br><strong>Totale:</strong> €").append(ordine.getTotale());

        // Invia email a admin
        sendEmail("lorellamarinocode@gmail.com", ordine.getEmailCliente(), subjectAdmin, body.toString());

        // Invia email al cliente
        sendEmail(ordine.getEmailCliente(), "lorellamarinocode@gmail.com", subjectUser, body.toString());
    }


}


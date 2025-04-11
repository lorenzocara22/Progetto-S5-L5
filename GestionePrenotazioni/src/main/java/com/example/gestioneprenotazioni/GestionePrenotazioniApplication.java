package com.example.gestioneprenotazioni;

import com.example.gestioneprenotazioni.model.Edificio;
import com.example.gestioneprenotazioni.model.Postazione;
import com.example.gestioneprenotazioni.model.Postazione.TipoPostazione;
import com.example.gestioneprenotazioni.model.Utente;
import com.example.gestioneprenotazioni.service.PrenotazioneService;
import com.example.gestioneprenotazioni.repository.EdificioRepository;
import com.example.gestioneprenotazioni.repository.PostazioneRepository;
import com.example.gestioneprenotazioni.repository.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class GestionePrenotazioniApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionePrenotazioniApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            EdificioRepository edificioRepo,
            PostazioneRepository postazioneRepo,
            UtenteRepository utenteRepo,
            PrenotazioneService prenotazioneService
    ) {
        return args -> {
            // 1) Creazione di un edificio
            Edificio edificio = new Edificio("Sede Centrale", "Via Roma 1", "Milano");
            edificioRepo.save(edificio);

            // 2) Creazione di una postazione
            Postazione postazione = new Postazione(
                    "PST-001",
                    "Scrivania vicino alla finestra",
                    TipoPostazione.PRIVATO,
                    1,
                    edificio
            );
            postazioneRepo.save(postazione);

            // 3) Creazione di un utente
            Utente utente = new Utente("user1", "Mario", "Rossi", "mario.rossi@example.com");
            utenteRepo.save(utente);

            // 4) Tentativo di prenotazione (la prima dovrebbe andare a buon fine)
            try {
                prenotazioneService.creaPrenotazione(LocalDate.now(), utente, postazione);
                System.out.println("Prenotazione creata con successo per utente " + utente.getUsername());
            } catch (RuntimeException ex) {
                System.err.println("Errore nella prenotazione: " + ex.getMessage());
            }

            // 5) Tentativo di una seconda prenotazione sulla stessa data, che dovrebbe lanciare un'eccezione
            try {
                prenotazioneService.creaPrenotazione(LocalDate.now(), utente, postazione);
            } catch (RuntimeException ex) {
                System.err.println("Errore nella prenotazione (seconda volta): " + ex.getMessage());
            }

            // 6) Esempio di ricerca: postazioni PRIVATE a Milano
            var postazioniMilano = postazioneRepo.findByTipoAndEdificio_Citta(TipoPostazione.PRIVATO, "Milano");
            System.out.println("Postazioni PRIVATE a Milano: " + postazioniMilano);
        };
    }
}

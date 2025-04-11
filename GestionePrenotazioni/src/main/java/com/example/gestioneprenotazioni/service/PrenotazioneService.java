package com.example.gestioneprenotazioni.service;

import com.example.gestioneprenotazioni.model.Prenotazione;
import com.example.gestioneprenotazioni.model.Postazione;
import com.example.gestioneprenotazioni.model.Utente;
import com.example.gestioneprenotazioni.repository.PrenotazioneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepo;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepo) {
        this.prenotazioneRepo = prenotazioneRepo;
    }


    public Prenotazione creaPrenotazione(LocalDate data, Utente utente, Postazione postazione) {
        boolean esistePrenotazione = prenotazioneRepo.existsByPostazioneAndData(postazione, data);

        if (esistePrenotazione) {

            throw new RuntimeException("La postazione è già prenotata per la data: " + data);
        }


        Prenotazione nuova = new Prenotazione(data, utente, postazione);
        return prenotazioneRepo.save(nuova);
    }
}

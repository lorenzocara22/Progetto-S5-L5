package com.example.gestioneprenotazioni.repository;

import com.example.gestioneprenotazioni.model.Prenotazione;
import com.example.gestioneprenotazioni.model.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {


    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);
}

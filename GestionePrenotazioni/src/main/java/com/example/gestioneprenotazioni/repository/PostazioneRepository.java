package com.example.gestioneprenotazioni.repository;

import com.example.gestioneprenotazioni.model.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {


    List<Postazione> findByTipoAndEdificio_Citta(Postazione.TipoPostazione tipo, String citta);
}

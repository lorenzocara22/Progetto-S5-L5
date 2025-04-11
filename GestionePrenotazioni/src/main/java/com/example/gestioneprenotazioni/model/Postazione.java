package com.example.gestioneprenotazioni.model;

import jakarta.persistence.*;

@Entity
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codiceUnivoco;
    private String descrizione;
    private int maxOccupanti;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo; // Es. PRIVATO, OPENSPACE, SALA_RIUNIONI

    @ManyToOne
    private Edificio edificio;


    public enum TipoPostazione {
        PRIVATO,
        OPENSPACE,
        SALA_RIUNIONI
    }


    public Postazione() {}


    public Postazione(String codiceUnivoco, String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio) {
        this.codiceUnivoco = codiceUnivoco;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getMaxOccupanti() {
        return maxOccupanti;
    }

    public void setMaxOccupanti(int maxOccupanti) {
        this.maxOccupanti = maxOccupanti;
    }

    public TipoPostazione getTipo() {
        return tipo;
    }

    public void setTipo(TipoPostazione tipo) {
        this.tipo = tipo;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", codiceUnivoco='" + codiceUnivoco + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", maxOccupanti=" + maxOccupanti +
                ", tipo=" + tipo +
                ", edificio=" + edificio +
                '}';
    }
}

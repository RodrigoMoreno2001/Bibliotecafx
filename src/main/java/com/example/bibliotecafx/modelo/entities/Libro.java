package com.example.bibliotecafx.modelo.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Libro {

    @Id
    private String ISBN;

    private String titulo;
    private Date fechaPublicacion;
    private boolean disponible;

    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "ISBN"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")
    )

    private List<Autor> autores;
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}

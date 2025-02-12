package com.example.bibliotecafx.modelo.entities;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;
    private String ISBN;
    private String titulo;
    private String editorial;
    private Integer anyoPublicacion;
    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;
    
    public Libro() {}
    
    public Libro(Long idLibro, String ISBN, String titulo,String editorial, Integer anyoPublicacion, Autor autor) {
        this.idLibro = idLibro;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.anyoPublicacion = anyoPublicacion;
        this.autor = autor;
        this.editorial=editorial;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

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

    public Integer getAnyoPublicacion() {
        return anyoPublicacion;
    }

    public void setAnyoPublicacion(Integer anyoPublicacion) {
        this.anyoPublicacion = anyoPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public String toString(){
        return titulo;
    }
}

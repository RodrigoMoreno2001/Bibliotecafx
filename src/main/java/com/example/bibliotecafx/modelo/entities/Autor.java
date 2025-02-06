package com.example.bibliotecafx.modelo.entities;

import jakarta.persistence.*;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;
    private String nombre;
    private String nacionalidad;

    public Autor() {}
    public Autor(Long idAutor, String nombre, String nacionalidad) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor=" + idAutor +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}

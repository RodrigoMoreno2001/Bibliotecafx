package com.example.bibliotecafx.modelo.entities;

import jakarta.persistence.*;

@Entity
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private Long idSocio;
    private String nombre;
    private String direccion;
    private Integer telefono;

    public Socio(){}

    public Socio(String nombre, String direccion, Integer telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString(){
        return nombre;
    }

}

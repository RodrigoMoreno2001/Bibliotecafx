package com.example.bibliotecafx.modelo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSocio;

    private String nombre;
    private String direccion;
    private int tlfn;

    public Socio(){}

    public Socio(String nombre, String direccion, int tlfn) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tlfn = tlfn;
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

    public int getTlfn() {
        return tlfn;
    }

    public void setTlfn(int tlfn) {
        this.tlfn = tlfn;
    }
}

package com.example.bibliotecafx.modelo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_prestamo")
    private Long idPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo() {}

    public Prestamo(Socio socio, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + idPrestamo +
                ", socio=" + socio.getNombre() +
                ", libro=" + libro.getTitulo() +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}


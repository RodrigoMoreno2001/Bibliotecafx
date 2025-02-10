package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Prestamo;

import java.util.List;

public interface IPrestamoDAO {

    void guardarPrestamo(Prestamo prestamo);

    void eliminarPrestamo(Prestamo prestamo);

    List<Prestamo> obtenerPrestamos(String nombre);

    List<Prestamo> obtenerPrestamosActuales();

}

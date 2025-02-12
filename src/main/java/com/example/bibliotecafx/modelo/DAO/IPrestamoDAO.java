package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Libro;
import com.example.bibliotecafx.modelo.entities.Prestamo;

import java.util.List;

public interface IPrestamoDAO {

    void guardarPrestamo(Prestamo prestamo);

    void eliminarPrestamo(Prestamo prestamo);

    void actualizarPrestamo(Prestamo prestamo);

    List<Prestamo> obtenerPrestamos();

    List<Prestamo> obtenerPrestamosActuales();

}

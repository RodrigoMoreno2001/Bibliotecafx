package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Libro;
import java.util.List;

public interface ILibroDAO {

    void guardarLibro(Libro libro);

    void actualizarLibro(Libro libro);

    void eliminarLibro(String ISBN);

    Libro obtenerLibroPorISBN(String ISBN);

    List<Libro> obtenerTodosLibros();
}


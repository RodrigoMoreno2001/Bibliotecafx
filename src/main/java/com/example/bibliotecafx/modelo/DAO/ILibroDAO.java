package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Libro;
import java.util.List;

public interface ILibroDAO {

    void guardarLibro(Libro libro);

    void actualizarLibro(Libro libro);

    void eliminarLibro(Libro libro);

    Libro obtenerLibroPorISBN(String ISBN);

    List<Libro> obtenerTodosLibros();

    List<Libro> obtenerLibrosPorTitulo(String titulo);

    List<Libro> obtenerLibrosPorAutor(Autor autor);

    List<Libro> obtenerLibrosDisponibles();
}


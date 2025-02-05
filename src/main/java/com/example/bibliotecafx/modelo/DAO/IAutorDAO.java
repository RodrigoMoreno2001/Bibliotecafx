package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import java.util.List;

public interface IAutorDAO {

    void guardarAutor(Autor autor);

    void actualizarAutor(Autor autor);

    void eliminarAutor(Long idAutor);

    Autor obtenerAutorPorId(Long idAutor);

    List<Autor> obtenerTodosAutores();

}

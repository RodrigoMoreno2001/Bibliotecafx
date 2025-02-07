package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Socio;

import java.util.List;

public interface ISocioDAO {

    void guardarSocio(Socio socio);

    void modificarSocio(Socio socio);

    void eliminarSocio(Socio socio);

    List<Socio> buscarSociosPorNombre(String nombre);

    List<Socio> buscarSociosPorTelefono(String telefono);

    List<Socio> listarSocios();
}


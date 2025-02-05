package com.example.bibliotecafx.controlador;

import com.example.bibliotecafx.modelo.DAO.AutorDAO;
import com.example.bibliotecafx.modelo.entities.Autor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        new AutorDAO().guardarAutor(new Autor(null,"rodrigo","españa"));

        welcomeText.setText(new AutorDAO().obtenerAutorPorId(1L).toString());

    }
}
package com.example.bibliotecafx.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SocioController {
    @FXML
    public ComboBox comboBox;
    @FXML
    public TextField textField;

    @FXML
    public void initialize() {
        comboBox.getItems().add("Nombre");
        comboBox.getItems().add("Telefono");

        comboBox.setOnAction(event -> {
            textField.setPromptText(comboBox.getValue().toString());
        });

    }

    public void onVolverClick(ActionEvent actionEvent) throws IOException {
        HelloController.cambiarScene(actionEvent,"/com/example/fxml/hello-view.fxml");
    }
}

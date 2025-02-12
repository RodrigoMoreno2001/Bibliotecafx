package com.example.bibliotecafx.controlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    protected void onLibrosClick(ActionEvent actionEvent) throws IOException {
        cambiarScene(actionEvent,"/com/example/fxml/libro-view.fxml");
    }

    @FXML
    protected void onAutoresClick(ActionEvent actionEvent) throws IOException {
        cambiarScene(actionEvent,"/com/example/fxml/autores-view.fxml");
    }

    @FXML
    protected void onPrestamosClick(ActionEvent actionEvent) throws IOException {
        cambiarScene(actionEvent,"/com/example/fxml/prestamo-view.fxml");
    }

    @FXML
    protected void onSociosClick(ActionEvent actionEvent) throws IOException {
        cambiarScene(actionEvent,"/com/example/fxml/socio-view.fxml");
    }


    public static void cambiarScene(ActionEvent actionEvent,String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource(resource));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
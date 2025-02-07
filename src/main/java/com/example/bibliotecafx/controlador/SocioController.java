package com.example.bibliotecafx.controlador;

import com.example.bibliotecafx.modelo.DAO.SocioDAO;
import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.List;

public class SocioController {
    @FXML
    public ComboBox comboBox;
    @FXML
    public TableView<Socio> tablaSocios;
    @FXML
    public TableColumn<Socio, Integer> columnaTelefono;
    @FXML
    public TableColumn<Socio,String> columnaDireccion;
    @FXML
    public TableColumn<Socio,String> columnaNombre;
    @FXML
    public TextField direccionText;
    @FXML
    public TextField telefonoText;
    @FXML
    public TextField nombreText;
    @FXML
    public TextField buscarText;
    @FXML
    public CheckBox modoEdicionCheckBox;

    private ObservableList<Socio> sociosObservableList;

    private SocioDAO socioDAO;

    public SocioController(){
        socioDAO=new SocioDAO();
        sociosObservableList=null;
    }

    @FXML
    public void initialize() {

        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        List<Socio> socios = socioDAO.listarSocios();
        sociosObservableList = FXCollections.observableArrayList(socios);
        tablaSocios.setItems(sociosObservableList);

        comboBox.getItems().add("Nombre");
        comboBox.getItems().add("Telefono");

        comboBox.setOnAction(event -> {
            buscarText.setPromptText(comboBox.getValue().toString());
        });
    }

    public void onVolverClick(ActionEvent actionEvent) throws IOException {
        HelloController.cambiarScene(actionEvent,"/com/example/fxml/hello-view.fxml");
    }

    public void onAnyadirClick(ActionEvent actionEvent) {
        Socio nuevoSocio=new Socio(nombreText.getText(),direccionText.getText(),Integer.parseInt(telefonoText.getText()));
        socioDAO.guardarSocio(nuevoSocio);
        sociosObservableList.add(nuevoSocio);
    }

    public void onBuscarClick(ActionEvent actionEvent) {

        System.out.println(comboBox.getValue().toString());

        switch(comboBox.getValue().toString()){
            case "Nombre":
                sociosObservableList.setAll(socioDAO.buscarSociosPorNombre(buscarText.getText()));
                break;
            case "Telefono":
                sociosObservableList.setAll(socioDAO.buscarSociosPorTelefono(buscarText.getText()));
                break;
        }
    }

    public void onEliminarClick(ActionEvent actionEvent) {

        Socio eliminarSocio= tablaSocios.getSelectionModel().getSelectedItem();

        if(eliminarSocio!=null){
            socioDAO.eliminarSocio(eliminarSocio);
            sociosObservableList.remove(eliminarSocio);
        }

    }
}

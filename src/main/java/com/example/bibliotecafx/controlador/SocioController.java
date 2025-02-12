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
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.List;

public class SocioController {

    public ComboBox comboBox;
    public TableView<Socio> tablaSocios;
    public TableColumn<Socio, Integer> columnaTelefono;
    public TableColumn<Socio,String> columnaDireccion;
    public TableColumn<Socio,String> columnaNombre;
    public TextField direccionText;
    public TextField telefonoText;
    public TextField nombreText;
    public TextField buscarText;
    public CheckBox modoEdicionCheckBox;

    private ObservableList<Socio> sociosObservableList;

    private SocioDAO socioDAO=new SocioDAO();

    @FXML
    public void initialize() {

        configurarTabla();

        configurarBusqueda();

        columnasEditables();

        modoEdicionCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            tablaSocios.setEditable(newVal);
        });
    }

    private void configurarBusqueda() {

        comboBox.getItems().add("Nombre");
        comboBox.getItems().add("Telefono");
        comboBox.getSelectionModel().select(0);
        buscarText.setPromptText("Nombre");

        comboBox.setOnAction(event -> {
            buscarText.setPromptText(comboBox.getValue().toString());
        });

    }

    private void configurarTabla() {

        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        List<Socio> socios = socioDAO.listarSocios();
        sociosObservableList = FXCollections.observableArrayList(socios);
        tablaSocios.setItems(sociosObservableList);

    }

    private void columnasEditables() {

        columnaNombre.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        columnaDireccion.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        columnaTelefono.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        columnaNombre.setOnEditCommit(event -> {
            Socio socio = event.getRowValue();
            socio.setNombre(event.getNewValue());
            socioDAO.modificarSocio(socio);
        });

        columnaDireccion.setOnEditCommit(event -> {
            Socio socio = event.getRowValue();
            socio.setDireccion(event.getNewValue());
            socioDAO.modificarSocio(socio);
        });

        columnaTelefono.setOnEditCommit(event -> {
            Socio socio = event.getRowValue();
            socio.setTelefono(event.getNewValue());
            socioDAO.modificarSocio(socio);
        });
    }

    public void onVolverClick(ActionEvent actionEvent) throws IOException {
        HelloController.cambiarScene(actionEvent,"/com/example/fxml/hello-view.fxml");
    }

    public void onAnyadirClick(ActionEvent actionEvent) {
        Socio nuevoSocio=new Socio(nombreText.getText(),direccionText.getText(),Integer.parseInt(telefonoText.getText()));
        socioDAO.guardarSocio(nuevoSocio);
        sociosObservableList.add(nuevoSocio);
        nombreText.setText("");
        direccionText.setText("");
        telefonoText.setText("");
    }

    public void onBuscarClick(ActionEvent actionEvent) {

        String aBuscar = buscarText.getText();

        if(aBuscar.isEmpty()){
            sociosObservableList.setAll(socioDAO.listarSocios());
            return;
        }

        switch(comboBox.getValue().toString()){
            case "Nombre":
                sociosObservableList.setAll(socioDAO.buscarSociosPorNombre(aBuscar));
                break;
            case "Telefono":
                sociosObservableList.setAll(socioDAO.buscarSociosPorTelefono(aBuscar));
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

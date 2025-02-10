package com.example.bibliotecafx.controlador;

import com.example.bibliotecafx.modelo.DAO.AutorDAO;
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

public class AutoresController {
    @FXML
    public TextField nombreText;
    @FXML
    public TextField nacionalidadText;
    @FXML
    private TableView<Autor> tablaAutores;
    @FXML
    private TableColumn<Autor, Long> columnaId;
    @FXML
    private TableColumn<Autor, String> columnaNombre;
    @FXML
    private TableColumn<Autor, String> columnaNacionalidad;
    @FXML
    public TextField buscarText;
    @FXML
    public CheckBox modoEdicionCheckBox;
    @FXML
    public ComboBox comboBox;

    private ObservableList<Autor> autoresObservableList;

    private AutorDAO autorDAO;

    // Constructor
    public AutoresController() {
        this.autorDAO = new AutorDAO();
        autoresObservableList=null;
    }

    public void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idAutor"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));

        List<Autor> autores = autorDAO.obtenerTodosAutores();
        autoresObservableList = FXCollections.observableArrayList(autores);
        tablaAutores.setItems(autoresObservableList);

        comboBox.getItems().add("Nombre");
        comboBox.getSelectionModel().select(0);
        buscarText.setPromptText("Nombre");

        comboBox.setOnAction(event -> {
            buscarText.setPromptText(comboBox.getValue().toString());
        });


        columnasEditables();

        modoEdicionCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            tablaAutores.setEditable(newVal);
        });

    }

    private void columnasEditables() {

        columnaNombre.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        columnaNacionalidad.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        columnaNombre.setOnEditCommit(event -> {
            Autor autor = event.getRowValue();
            autor.setNombre(event.getNewValue());
            autorDAO.actualizarAutor(autor);
        });

        columnaNacionalidad.setOnEditCommit(event -> {
            Autor autor = event.getRowValue();
            autor.setNacionalidad(event.getNewValue());
            autorDAO.actualizarAutor(autor);
        });
    }


    public void onAnyadirClick(ActionEvent actionEvent) {
        Autor nuevoAutor= new Autor(null,nombreText.getText(),nacionalidadText.getText());
        autorDAO.guardarAutor(nuevoAutor);
        autoresObservableList.add(nuevoAutor);

        nombreText.setText("");
        nacionalidadText.setText("");
    }

    public void onVolverClick(ActionEvent actionEvent) throws IOException {
        HelloController.cambiarScene(actionEvent,"/com/example/fxml/hello-view.fxml");
    }

    public void onBuscarClick(ActionEvent actionEvent) {

        String aBuscar = buscarText.getText();

        if(aBuscar.isEmpty()){
            autoresObservableList.setAll(autorDAO.obtenerTodosAutores());
            return;
        }

        switch(comboBox.getValue().toString()){
            case "Nombre":
                autoresObservableList.setAll(autorDAO.obtenerAutorPorNombre(aBuscar));
                break;
        }
    }

    public void onEliminarClick(ActionEvent actionEvent) {

        Autor eliminarAutor= tablaAutores.getSelectionModel().getSelectedItem();

        if(eliminarAutor!=null){
            autorDAO.eliminarAutor(eliminarAutor);
            autoresObservableList.remove(eliminarAutor);
        }

    }
}

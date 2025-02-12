package com.example.bibliotecafx.controlador;

import com.example.bibliotecafx.modelo.DAO.AutorDAO;
import com.example.bibliotecafx.modelo.DAO.LibroDAO;
import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Libro;
import com.example.bibliotecafx.modelo.entities.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.List;

public class LibroController {
    @FXML
    public ComboBox<Autor> autorComboBox;
    public TextField tituloText;
    public TextField ISBNText;
    public TextField editorialText;
    public TextField anyoText;
    public TableView<Libro> tablaLibros;
    public TableColumn<Libro,Long> columnaId;
    public TableColumn<Libro,String> columnaTitulo;
    public TableColumn<Libro,String> columnaISBN;
    public TableColumn<Libro,Autor> columnaAutor;
    public TableColumn<Libro,String> columnaEditorial;
    public TableColumn<Libro,Integer> columnaAnyo;
    public CheckBox modoEdicionCheckBox;
    public ComboBox buscarComboBox;
    public TextField buscarText;

    private ObservableList<Libro> librosObservableList;
    private AutorDAO autorDAO=new AutorDAO();
    private LibroDAO libroDAO=new LibroDAO();

    public void initialize() {

        autorComboBox.setItems(FXCollections.observableArrayList(autorDAO.obtenerTodosAutores()));

        columnaId.setCellValueFactory(new PropertyValueFactory<>("idLibro"));
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
        columnaISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnaAutor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
        columnaEditorial.setCellValueFactory(new PropertyValueFactory<>("Editorial"));
        columnaAnyo.setCellValueFactory(new PropertyValueFactory<>("anyoPublicacion"));

        List<Libro> libros = libroDAO.obtenerTodosLibros();
        librosObservableList = FXCollections.observableArrayList(libros);
        tablaLibros.setItems(librosObservableList);

        columnasEditables();
        modoEdicionCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            tablaLibros.setEditable(newVal);
        });

        buscarComboBox.getItems().add("Título");
        buscarComboBox.getItems().add("Autor");
        buscarComboBox.getItems().add("ISBN");
        buscarComboBox.getSelectionModel().select(0);
        buscarText.setPromptText("Título");

        buscarComboBox.setOnAction(event -> {
            buscarText.setPromptText(buscarComboBox.getValue().toString());
        });
    }

    private void columnasEditables() {

        columnaTitulo.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        columnaISBN.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        columnaAnyo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnaEditorial.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        columnaAutor.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(autorDAO.obtenerTodosAutores())));

        columnaTitulo.setOnEditCommit(event -> {
            Libro libro = event.getRowValue();
            libro.setTitulo(event.getNewValue());
            libroDAO.actualizarLibro(libro);
        });

        columnaISBN.setOnEditCommit(event -> {
            Libro libro = event.getRowValue();
            libro.setISBN(event.getNewValue());
            libroDAO.actualizarLibro(libro);
        });

        columnaAnyo.setOnEditCommit(event -> {
            Libro libro = event.getRowValue();
            libro.setAnyoPublicacion(event.getNewValue());
            libroDAO.actualizarLibro(libro);
        });

        columnaEditorial.setOnEditCommit(event -> {
            Libro libro = event.getRowValue();
            libro.setEditorial(event.getNewValue());
            libroDAO.actualizarLibro(libro);
        });

        columnaAutor.setOnEditCommit(event -> {
            Libro libro = event.getRowValue();
            libro.setAutor(event.getNewValue());
            libroDAO.actualizarLibro(libro);
        });
    }

    public void onVolverClick(ActionEvent actionEvent) throws IOException {
        HelloController.cambiarScene(actionEvent,"/com/example/fxml/hello-view.fxml");
    }

    public void onBuscarClick(ActionEvent actionEvent) {

        String aBuscar = buscarText.getText();

        if(aBuscar.isEmpty()){
            librosObservableList.setAll(libroDAO.obtenerTodosLibros());
            return;
        }

        switch(buscarComboBox.getValue().toString()){
            case "Título":
                librosObservableList.setAll(libroDAO.obtenerLibrosPorTitulo(aBuscar));
                break;
            case "ISBN":
                librosObservableList.setAll(libroDAO.obtenerLibroPorISBN(aBuscar));
                break;
            case "Autor":
                librosObservableList.setAll(libroDAO.obtenerLibrosPorAutor(autorDAO.obtenerAutorPorNombre(aBuscar).get(0)));
                break;
        }

    }

    public void onAnyadirClick(ActionEvent actionEvent) {

        Libro nuevoLibro = new Libro(null,ISBNText.getText(),tituloText.getText(),editorialText.getText(),Integer.parseInt(anyoText.getText()),autorComboBox.getValue());
        libroDAO.guardarLibro(nuevoLibro);
        librosObservableList.add(nuevoLibro);

    }

    public void onEliminarClick(ActionEvent actionEvent) {
        Libro eliminarLibro= tablaLibros.getSelectionModel().getSelectedItem();

        if(eliminarLibro!=null){
            libroDAO.eliminarLibro(eliminarLibro);
            librosObservableList.remove(eliminarLibro);
        }
    }
}

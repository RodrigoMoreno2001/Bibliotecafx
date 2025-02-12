package com.example.bibliotecafx.controlador;

import com.example.bibliotecafx.modelo.DAO.AutorDAO;
import com.example.bibliotecafx.modelo.DAO.LibroDAO;
import com.example.bibliotecafx.modelo.DAO.PrestamoDAO;
import com.example.bibliotecafx.modelo.DAO.SocioDAO;
import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Libro;
import com.example.bibliotecafx.modelo.entities.Prestamo;
import com.example.bibliotecafx.modelo.entities.Socio;
import com.example.bibliotecafx.util.DatePickerTableCell;
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
import java.time.LocalDate;
import java.util.List;

public class PrestamoController {

    public ComboBox buscarComboBox;
    public TextField buscarText;
    public CheckBox modoEdicionCheckBox;
    public TableView<Prestamo> tablaPrestamos;
    public TableColumn<Prestamo,Long> columnaId;
    public TableColumn<Prestamo,Libro> columnaLibro;
    public TableColumn<Prestamo,Socio> columnaSocio;
    public TableColumn<Prestamo, LocalDate> columnaPrestamo;
    public TableColumn<Prestamo, LocalDate> columnaDevolucion;
    public ComboBox<Libro> libroComboBox;
    public ComboBox<Socio> socioComboBox;
    public DatePicker prestamoDatePicker;
    public DatePicker devolucionDatePicker;

    private PrestamoDAO prestamoDAO = new PrestamoDAO();
    private LibroDAO libroDAO=new LibroDAO();
    private SocioDAO socioDAO=new SocioDAO();

    private ObservableList<Prestamo> prestamosObservableList;

    public void initialize() {

        libroComboBox.setItems(FXCollections.observableArrayList(libroDAO.obtenerTodosLibros()));
        socioComboBox.setItems(FXCollections.observableArrayList(socioDAO.listarSocios()));

        columnaId.setCellValueFactory(new PropertyValueFactory<>("idPrestamo"));
        columnaLibro.setCellValueFactory(new PropertyValueFactory<>("libro"));
        columnaSocio.setCellValueFactory(new PropertyValueFactory<>("socio"));
        columnaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        columnaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));

        List<Prestamo> prestamos = prestamoDAO.obtenerPrestamos();
        prestamosObservableList = FXCollections.observableArrayList(prestamos);
        tablaPrestamos.setItems(prestamosObservableList);

        buscarComboBox.getItems().add("Socio");
        buscarComboBox.getItems().add("Prestados");
        buscarComboBox.getSelectionModel().select(0);
        buscarText.setPromptText("Socio");

        buscarComboBox.setOnAction(event -> {
            buscarText.setPromptText(buscarComboBox.getValue().toString());
        });

        columnasEditables();

        modoEdicionCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            tablaPrestamos.setEditable(newVal);
            columnaPrestamo.setEditable(newVal);
            columnaDevolucion.setEditable(newVal);
            tablaPrestamos.refresh();
        });

    }

    private void columnasEditables() {

        columnaLibro.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(libroDAO.obtenerTodosLibros())));
        columnaSocio.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(socioDAO.listarSocios())));
        columnaPrestamo.setCellFactory(columna -> new DatePickerTableCell<>());
        columnaDevolucion.setCellFactory(columna -> new DatePickerTableCell<>());

        columnaLibro.setOnEditCommit(event -> {
            Prestamo prestamo = event.getRowValue();
            prestamo.setLibro(event.getNewValue());
            prestamoDAO.actualizarPrestamo(prestamo);
        });

        columnaSocio.setOnEditCommit(event -> {
            Prestamo prestamo = event.getRowValue();
            prestamo.setSocio(event.getNewValue());
            prestamoDAO.actualizarPrestamo(prestamo);
        });
        columnaPrestamo.setOnEditCommit(event -> {
            Prestamo prestamo = event.getRowValue();
            prestamo.setFechaPrestamo(event.getNewValue());
            prestamoDAO.actualizarPrestamo(prestamo);
        });
        columnaDevolucion.setOnEditCommit(event -> {
            Prestamo prestamo = event.getRowValue();
            prestamo.setFechaDevolucion(event.getNewValue());
            prestamoDAO.actualizarPrestamo(prestamo);
        });



    }

    public void onVolverClick(ActionEvent actionEvent) throws IOException {
        HelloController.cambiarScene(actionEvent,"/com/example/fxml/hello-view.fxml");
    }

    public void onBuscarClick(ActionEvent actionEvent) {
        
    }

    public void onAnyadirClick(ActionEvent actionEvent) {
        Prestamo nuevoPrestamo=new Prestamo(socioComboBox.getValue(),libroComboBox.getValue(),prestamoDatePicker.getValue(),devolucionDatePicker.getValue());
        prestamoDAO.guardarPrestamo(nuevoPrestamo);
        prestamosObservableList.add(nuevoPrestamo);
    }

    public void onEliminarClick(ActionEvent actionEvent) {
        Prestamo prestamoEliminar= tablaPrestamos.getSelectionModel().getSelectedItem();

        if(prestamoEliminar!=null){
            prestamoDAO.eliminarPrestamo(prestamoEliminar);
            prestamosObservableList.remove(prestamoEliminar);
        }
    }
}


package com.example.bibliotecafx.controlador;

import com.example.bibliotecafx.modelo.DAO.AutorDAO;
import com.example.bibliotecafx.modelo.entities.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AutoresController {
    @FXML
    public TextField idAutorText;
    @FXML
    public TextField NombreText;
    @FXML
    public TextField NacionalidadText;
    @FXML
    private TableView<Autor> tablaAutores;
    @FXML
    private TableColumn<Autor, Long> columnaId;
    @FXML
    private TableColumn<Autor, String> columnaNombre;
    @FXML
    private TableColumn<Autor, String> columnaNacionalidad;

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
    }

    public void onAnyadirClick(ActionEvent actionEvent) {

        Long idAutor = null;
        if (!idAutorText.getText().trim().isEmpty()) {
            try {
                idAutor = Long.parseLong(idAutorText.getText().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: El ID del autor no es válido.");
                // Maneja el error, tal vez mostrando un mensaje al usuario
                return; // O manejar el flujo de alguna manera para evitar continuar
            }
        }

        Autor nuevoAutor= new Autor(null,NombreText.getText(),NacionalidadText.getText());
        autorDAO.guardarAutor(nuevoAutor);
        autoresObservableList.add(nuevoAutor);
    }
}

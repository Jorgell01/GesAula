package dad.gesaula.controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DatosAlumnosController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ComboBox<Sexo> sexComboBox;

    @FXML
    private CheckBox repeatCheckBox;

    private GridPane root;

    private ObjectProperty<Alumno> alumnoSeleccionado = new SimpleObjectProperty<>();

    public DatosAlumnosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DatosAlumnosController.fxml"));
            loader.setController(this);
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar el ComboBox de sexo
        sexComboBox.getItems().setAll(Sexo.values());

        // Listener para actualizar los bindeos cuando cambia el alumno seleccionado
        alumnoSeleccionado.addListener((obs, oldAlumno, newAlumno) -> {
            // Desvincular las propiedades del alumno anterior
            if (oldAlumno != null) {
                nameTextField.textProperty().unbindBidirectional(oldAlumno.nombreProperty());
                surnameTextField.textProperty().unbindBidirectional(oldAlumno.apellidosProperty());
                birthDate.valueProperty().unbindBidirectional(oldAlumno.fechaNacimientoProperty());
                sexComboBox.valueProperty().unbindBidirectional(oldAlumno.sexoProperty());
                repeatCheckBox.selectedProperty().unbindBidirectional(oldAlumno.repiteProperty());
            }

            // Vincular las propiedades del nuevo alumno seleccionado
            if (newAlumno != null) {
                nameTextField.textProperty().bindBidirectional(newAlumno.nombreProperty());
                surnameTextField.textProperty().bindBidirectional(newAlumno.apellidosProperty());
                birthDate.valueProperty().bindBidirectional(newAlumno.fechaNacimientoProperty());
                sexComboBox.valueProperty().bindBidirectional(newAlumno.sexoProperty());
                repeatCheckBox.selectedProperty().bindBidirectional(newAlumno.repiteProperty());
            } else {
                // Limpiar los campos si no hay un alumno seleccionado
                nameTextField.clear();
                surnameTextField.clear();
                birthDate.setValue(null);
                sexComboBox.setValue(null);
                repeatCheckBox.setSelected(false);
            }
        });
    }

    public void setAlumno(Alumno alumno) {
        alumnoSeleccionado.set(alumno);
    }

    public GridPane getRoot() {
        return root;
    }
}

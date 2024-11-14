package dad.gesaula.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GrupoController implements Initializable {

    @FXML
    private Slider actitudSlider;

    @FXML
    private TextField denominacionTextField;

    @FXML
    private Slider examenesSlider;

    @FXML
    private DatePicker fincursoDate;

    @FXML
    private DatePicker iniciocursoDate;

    @FXML
    private Slider practicasSlider;

    @FXML
    private Label actitudLabel;

    @FXML
    private Label examenesLabel;

    @FXML
    private Label practicasLabel;

    @FXML
    private GridPane root;

    private DoubleProperty actitudProperty = new SimpleDoubleProperty();
    private DoubleProperty examenesProperty = new SimpleDoubleProperty();
    private DoubleProperty practicasProperty = new SimpleDoubleProperty();

    public GrupoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrupoController.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Bindings para actualizar los Labels de porcentaje automáticamente
        actitudLabel.textProperty().bind(Bindings.format("%.0f%%", actitudSlider.valueProperty()));
        examenesLabel.textProperty().bind(Bindings.format("%.0f%%", examenesSlider.valueProperty()));
        practicasLabel.textProperty().bind(Bindings.format("%.0f%%", practicasSlider.valueProperty()));

    }

    public GridPane getRoot() {
        return root;
    }
}

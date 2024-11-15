package dad.gesaula.controllers;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.binding.Bindings;
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

    private Grupo grupo;

    public GrupoController(Grupo grupo) {
        this.grupo = grupo;
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
        // Vincular sliders con etiquetas de porcentaje
        actitudLabel.textProperty().bind(Bindings.format("%.0f%%", actitudSlider.valueProperty()));
        examenesLabel.textProperty().bind(Bindings.format("%.0f%%", examenesSlider.valueProperty()));
        practicasLabel.textProperty().bind(Bindings.format("%.0f%%", practicasSlider.valueProperty()));

        // Sincronizar sliders con el modelo
        actitudSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                grupo.getPesos().setActitud(newVal.doubleValue()));
        examenesSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                grupo.getPesos().setExamenes(newVal.doubleValue()));
        practicasSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                grupo.getPesos().setPracticas(newVal.doubleValue()));

        // Manejar fechas
        iniciocursoDate.valueProperty().addListener((obs, oldDate, newDate) ->
                grupo.setIniCurso(newDate));
        fincursoDate.valueProperty().addListener((obs, oldDate, newDate) ->
                grupo.setFinCurso(newDate));

        // Vincular el campo de texto de denominación con la propiedad denominacion de grupo
        denominacionTextField.textProperty().bindBidirectional(grupo.denominacionProperty());
    }

    public GridPane getRoot() {
        return root;
    }
}

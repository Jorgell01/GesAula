package dad.gesaula.controllers;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    private static Grupo grupo = new Grupo();

    // Propiedad para vincular dinámicamente el nombre de archivo
    private StringProperty nombreArchivo = new SimpleStringProperty("grupo");

    @FXML
    private TabPane containerTabPane;

    @FXML
    private BorderPane root;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootController.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTabs();

        // Vincular el nombre de archivo con la denominación del grupo
        nombreArchivo.bind(grupo.denominacionProperty());
    }

    private void initializeTabs() {
        containerTabPane.getTabs().clear();

        // Crear la pestaña de Alumnos
        Tab alumnosTab = new Tab("Alumnos");
        AlumnosController alumnosController = new AlumnosController();
        alumnosTab.setContent(alumnosController.getRoot());

        // Crear la pestaña de Grupos y pasar el grupo a GrupoController
        GrupoController grupoController = new GrupoController(grupo);
        Tab grupoTab = new Tab("Grupos", grupoController.getRoot());

        // Agregar pestañas al TabPane
        containerTabPane.getTabs().addAll(alumnosTab, grupoTab);
    }

    @FXML
    void onActionNew(ActionEvent event) {
        grupo = new Grupo();
        initializeTabs();
    }

    @FXML
    void onActionSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Grupo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos XML", "*.xml"));

        // Usa el nombre del archivo de la propiedad vinculada
        String nombrePredeterminado = nombreArchivo.get();
        if (nombrePredeterminado != null && !nombrePredeterminado.trim().isEmpty()) {
            fileChooser.setInitialFileName(nombrePredeterminado + ".xml");
        } else {
            fileChooser.setInitialFileName("grupo.xml");
        }

        File file = fileChooser.showSaveDialog(root.getScene().getWindow());
        if (file != null) {
            try {
                grupo.save(file);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al guardar");
                alert.setContentText("No se pudo guardar el archivo.");
                alert.showAndWait();
            }
        }
    }

    public BorderPane getRoot() {
        return root;
    }

    public static Grupo getGrupo() {
        return grupo;
    }

    public static void setGrupo(Grupo nuevoGrupo) {
        grupo = nuevoGrupo;
    }

    public StringProperty nombreArchivoProperty() {
        return nombreArchivo;
    }
}

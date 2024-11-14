package dad.gesaula.controllers;

import dad.gesaula.ui.model.Grupo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private Tab alumnosTab;

    @FXML
    private TabPane containerTabPane;

    @FXML
    private Tab grupoTab;

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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        containerTabPane.getTabs().clear();

        alumnosTab = new Tab("Alumnos");
        AlumnosController alumnosController = new AlumnosController();
        alumnosTab.setContent(alumnosController.getRoot());

        grupoTab = new Tab("Grupos");
        GrupoController grupoController = new GrupoController();
        grupoTab.setContent(grupoController.getRoot());

        containerTabPane.getTabs().addAll(alumnosTab, grupoTab);
    }

    @FXML
    void onActionNew(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

    public BorderPane getRoot() {
        return root;
    }
}

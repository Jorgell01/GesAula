package dad.gesaula.controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlumnosController implements Initializable {

    @FXML
    private Button deleteButton;

    @FXML
    private Button newButton;

    @FXML
    private TableView<Alumno> alumnosTableView;

    @FXML
    private TableColumn<Alumno, String> nameColumn;

    @FXML
    private TableColumn<Alumno, String> surnameColumn;

    @FXML
    private TableColumn<Alumno, LocalDate> datebirthColumn;

    @FXML
    private SplitPane root;

    @FXML
    private VBox detailsBox; // Cambiado a VBox

    private DatosAlumnosController datosAlumnosController;

    public AlumnosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnosController.fxml"));
            loader.setController(this);
            loader.load();

            // Inicializar el controlador de detalles del alumno
            datosAlumnosController = new DatosAlumnosController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Enlazar las columnas de la tabla con las propiedades del modelo Alumno
        nameColumn.setCellValueFactory(data -> data.getValue().nombreProperty());
        surnameColumn.setCellValueFactory(data -> data.getValue().apellidosProperty());
        datebirthColumn.setCellValueFactory(data -> data.getValue().fechaNacimientoProperty());

        // Cargar la lista de alumnos en la tabla
        alumnosTableView.setItems(RootController.getGrupo().getAlumnos());

        // Listener para actualizar el VBox con los detalles del alumno seleccionado
        alumnosTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Establecer el alumno seleccionado en DatosAlumnosController
                datosAlumnosController.setAlumno(newSelection);

                // Reemplazar el contenido de detailsBox con el GridPane de DatosAlumnosController
                detailsBox.getChildren().setAll(datosAlumnosController.getRoot());
            } else {
                // Mostrar un Label cuando no hay selección
                Label emptyLabel = new Label("Seleccione un alumno de la tabla de la izquierda");
                detailsBox.getChildren().setAll(emptyLabel);
            }
        });
    }

    @FXML
    void onActionNew(ActionEvent event) {
        // Crear una nueva instancia de Alumno para asegurarse de que no se comparten referencias
        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre("Sin nombre");
        nuevoAlumno.setApellidos("Sin apellidos");
        nuevoAlumno.setFechaNacimiento(LocalDate.now());
        nuevoAlumno.setSexo(Sexo.HOMBRE);
        nuevoAlumno.setRepite(false);

        // Agregar el nuevo alumno al grupo
        RootController.getGrupo().getAlumnos().add(nuevoAlumno);
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        Alumno seleccionado = alumnosTableView.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Eliminar alumno");
            confirmacion.setHeaderText("Se va a eliminar al alumno '" + seleccionado.getNombre() + " " + seleccionado.getApellidos() + "'.");
            confirmacion.setContentText("¿Está seguro?");

            ButtonType resultado = confirmacion.showAndWait().orElse(ButtonType.CANCEL);
            if (resultado == ButtonType.OK) {
                RootController.getGrupo().getAlumnos().remove(seleccionado);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eliminar alumno");
            alert.setHeaderText("No hay selección");
            alert.setContentText("Por favor, seleccione un alumno para eliminar.");
            alert.showAndWait();
        }
    }

    public SplitPane getRoot() {
        return root;
    }
}

package dad.gesaula.app;

import dad.gesaula.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesAaulaApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Cargar la imagen desde resources
        Image icon = new Image(getClass().getResourceAsStream("/images/app-icon-64x64.png"));

        primaryStage.getIcons().add(icon);


        RootController rootController = new RootController();

        // Crear y configurar la escena
        Scene scene = new Scene(rootController.getRoot(), 800, 600); // Tamaño inicial preferido
        primaryStage.setScene(scene);
        primaryStage.setTitle("GesAula");

        // Mostrar la ventana
        primaryStage.show();
    }

}

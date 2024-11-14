package dad.gesaula.app;

import dad.gesaula.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GesAaulaApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        RootController rootController = new RootController();

        // Crear y configurar la escena
        Scene scene = new Scene(rootController.getRoot(), 800, 600); // Tamaño inicial preferido
        primaryStage.setScene(scene);
        primaryStage.setTitle("GesAula");

        // Permitir que la ventana sea redimensionable
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);

        // Mostrar la ventana
        primaryStage.show();
    }

}

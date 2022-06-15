package com.alcanzaelpollo;

import com.alcanzaelpollo.controlador.ControladorPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistas/principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ControladorPrincipal controladorPrincipal = fxmlLoader.getController();
        controladorPrincipal.setStage(stage);
        stage.setTitle("Alcanza al pollo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream( "iconos/icono-principal.png")));
        stage.fullScreenExitKeyProperty();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
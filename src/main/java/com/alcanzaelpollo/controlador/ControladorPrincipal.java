package com.alcanzaelpollo.controlador;

import com.alcanzaelpollo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ControladorPrincipal extends Thread{

    @FXML
    private Button botonJugar;

    @FXML
    private ImageView polloVolando;

    private Stage stage;

    private Boolean centinela;

    @FXML
    private void jugar(ActionEvent event) throws IOException {
        centinela = false;
        stop();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistas/reto.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alcanza al pollo");
        stage.setScene(scene);
        ControladorReto controladorReto = fxmlLoader.getController();
        controladorReto.setStage(stage);
        stage.setResizable(false);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream( "iconos/icono-principal.png")));
        stage.fullScreenExitKeyProperty();
        stage.show();
        this.stage.close();
    }

    @FXML
    void reportes(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistas/reportes.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alcanza al pollo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream( "iconos/icono-principal.png")));
        stage.fullScreenExitKeyProperty();
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(){
        centinela = true;
        try {
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File("src/main/resources/com/alcanzaelpollo/sonidos/canciones/granja-instrumental-inicial.wav")));

            // Comienza la reproducción
            //sonido.start();

        } catch (Exception e) {
            System.out.println("" + e);
        }
        this.start();
    }

    @Override
    public void run() {
        Image pollo1 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-5.png"));
        Image pollo2 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-6.png"));
        Image pollo3 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-7.png"));
        Image pollo4 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-8.png"));
        while (centinela)
            try {
                polloVolando.setImage(pollo1);
                Thread.sleep(50);
                polloVolando.setImage(pollo2);
                Thread.sleep(50);
                polloVolando.setImage(pollo3);
                Thread.sleep(50);
                polloVolando.setImage(pollo4);
                Thread.sleep(50);
                polloVolando.setImage(pollo3);
                Thread.sleep(50);
                polloVolando.setImage(pollo2);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}

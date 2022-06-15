package com.alcanzaelpollo.controlador;

import com.alcanzaelpollo.HelloApplication;
import com.alcanzaelpollo.modelo.Objetivo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorEncuesta {

    private Stage stage;
    private int cantidadEstrellaBonito = 0;
    private int cantidadEstrellaFacil = 0;
    private int cantidadEstrellaDivertido = 0;
    private int[] puntajeRetos;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView estrellaBonito1;

    @FXML
    private ImageView estrellaBonito2;

    @FXML
    private ImageView estrellaBonito3;

    @FXML
    private ImageView estrellaBonito4;

    @FXML
    private ImageView estrellaBonito5;

    @FXML
    private ImageView estrellaDivertido1;

    @FXML
    private ImageView estrellaDivertido2;

    @FXML
    private ImageView estrellaDivertido3;

    @FXML
    private ImageView estrellaDivertido4;

    @FXML
    private ImageView estrellaDivertido5;

    @FXML
    private ImageView estrellaFacil1;

    @FXML
    private ImageView estrellaFacil2;

    @FXML
    private ImageView estrellaFacil3;

    @FXML
    private ImageView estrellaFacil4;

    @FXML
    private ImageView estrellaFacil5;

    @FXML
    private TextField txtNombre;

    @FXML
    private ImageView pollo1;

    @FXML
    private ImageView pollo2;

    @FXML
    private ImageView pollo3;

    @FXML
    private ImageView pollo4;

    private Image estrellaActiva = new Image(HelloApplication.class.getResourceAsStream("iconos/estrellas/estrella-activa.png"));
    private Image estrellaInactiva = new Image(HelloApplication.class.getResourceAsStream("iconos/estrellas/estrella-inactiva.png"));

    public void initialize(){
        Platform.runLater(
                () -> {
                    new Objetivo(pollo1, 2).start();
                }
        );
        Platform.runLater(
                () -> {
                    new Objetivo(pollo2, 2).start();
                }
        );
        Platform.runLater(
                () -> {
                    new Objetivo(pollo3, 2).start();
                }
        );
        Platform.runLater(
                () -> {
                    new Objetivo(pollo4, 2).start();
                }
        );
    }

    private void asignarEstrellasBonito (int x){
        cantidadEstrellaBonito = x;

        estrellaBonito5.setImage(estrellaInactiva);
        estrellaBonito4.setImage(estrellaInactiva);
        estrellaBonito3.setImage(estrellaInactiva);
        estrellaBonito2.setImage(estrellaInactiva);
        estrellaBonito1.setImage(estrellaInactiva);

        switch (x){
            case 5: estrellaBonito5.setImage(estrellaActiva);
            case 4: estrellaBonito4.setImage(estrellaActiva);
            case 3: estrellaBonito3.setImage(estrellaActiva);
            case 2: estrellaBonito2.setImage(estrellaActiva);
            case 1: estrellaBonito1.setImage(estrellaActiva);
            break;
        }
    }

    private void asignarEstrellasFacil (int x){
        cantidadEstrellaFacil = x;

        estrellaFacil5.setImage(estrellaInactiva);
        estrellaFacil4.setImage(estrellaInactiva);
        estrellaFacil3.setImage(estrellaInactiva);
        estrellaFacil2.setImage(estrellaInactiva);
        estrellaFacil1.setImage(estrellaInactiva);

        switch (x){
            case 5: estrellaFacil5.setImage(estrellaActiva);
            case 4: estrellaFacil4.setImage(estrellaActiva);
            case 3: estrellaFacil3.setImage(estrellaActiva);
            case 2: estrellaFacil2.setImage(estrellaActiva);
            case 1: estrellaFacil1.setImage(estrellaActiva);
                break;
        }
    }

    private void asignarEstrellasDivertido (int x){
        cantidadEstrellaDivertido = x;

        estrellaDivertido5.setImage(estrellaInactiva);
        estrellaDivertido4.setImage(estrellaInactiva);
        estrellaDivertido3.setImage(estrellaInactiva);
        estrellaDivertido2.setImage(estrellaInactiva);
        estrellaDivertido1.setImage(estrellaInactiva);

        switch (x){
            case 5: estrellaDivertido5.setImage(estrellaActiva);
            case 4: estrellaDivertido4.setImage(estrellaActiva);
            case 3: estrellaDivertido3.setImage(estrellaActiva);
            case 2: estrellaDivertido2.setImage(estrellaActiva);
            case 1: estrellaDivertido1.setImage(estrellaActiva);
                break;
        }
    }

    @FXML
    void asignarEstrellaBonito1(MouseEvent event) {
        asignarEstrellasBonito(1);
    }

    @FXML
    void asignarEstrellaBonito2(MouseEvent event) {
        asignarEstrellasBonito(2);
    }

    @FXML
    void asignarEstrellaBonito3(MouseEvent event) {
        asignarEstrellasBonito(3);
    }

    @FXML
    void asignarEstrellaBonito4(MouseEvent event) {
        asignarEstrellasBonito(4);
    }

    @FXML
    void asignarEstrellaBonito5(MouseEvent event) {
        asignarEstrellasBonito(5);
    }

    @FXML
    void asignarEstrellaDivertido1(MouseEvent event) {
        asignarEstrellasDivertido(1);
    }

    @FXML
    void asignarEstrellaDivertido2(MouseEvent event) {
        asignarEstrellasDivertido(2);
    }

    @FXML
    void asignarEstrellaDivertido3(MouseEvent event) {
        asignarEstrellasDivertido(3);
    }

    @FXML
    void asignarEstrellaDivertido4(MouseEvent event) {
        asignarEstrellasDivertido(4);
    }

    @FXML
    void asignarEstrellaDivertido5(MouseEvent event) {
        asignarEstrellasDivertido(5);
    }

    @FXML
    void asignarEstrellaFacil1(MouseEvent event) {
        asignarEstrellasFacil(1);
    }

    @FXML
    void asignarEstrellaFacil2(MouseEvent event) {
        asignarEstrellasFacil(2);
    }

    @FXML
    void asignarEstrellaFacil3(MouseEvent event) {
        asignarEstrellasFacil(3);
    }

    @FXML
    void asignarEstrellaFacil4(MouseEvent event) {
        asignarEstrellasFacil(4);
    }

    @FXML
    void asignarEstrellaFacil5(MouseEvent event) {
        asignarEstrellasFacil(5);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPuntajeRetos(int[] puntajeRetos) {
        this.puntajeRetos = puntajeRetos;
    }

    @FXML
    void finalizar(ActionEvent event) {
        if (!txtNombre.getText().isEmpty() && cantidadEstrellaFacil > 0 && cantidadEstrellaBonito > 0 && cantidadEstrellaDivertido > 0){
            try {
                Conexion c= new Conexion();
                c.guardarUsuario(txtNombre.getText(),cantidadEstrellaFacil,cantidadEstrellaDivertido,cantidadEstrellaBonito,puntajeRetos);
                ventanaPrincipal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            mostrarAlertError();
        }
    }

    private void ventanaPrincipal() throws IOException {
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

    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Verifique los datos");
        alert.showAndWait();
    }

}

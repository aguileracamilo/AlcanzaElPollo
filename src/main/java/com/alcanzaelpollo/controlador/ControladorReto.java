package com.alcanzaelpollo.controlador;

import com.alcanzaelpollo.HelloApplication;
import com.alcanzaelpollo.modelo.Movimiento;
import com.alcanzaelpollo.modelo.Objetivo;
import com.alcanzaelpollo.modelo.Personaje;
import com.alcanzaelpollo.modelo.Reto;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ControladorReto {

    private ArrayList<Movimiento> movimientos;

    private ArrayList<int[][]> retos;

    private int[] puntajeRetos;

    private int contadorReto;

    private Stage stage;

    @FXML
    private Button botonMover;


    @FXML
    private HBox camino;

    @FXML
    private HBox hboxEstado;

    @FXML
    private ImageView estado;

    @FXML
    private ImageView estadoTexto;

    @FXML
    private ImageView flechaAbajo;

    @FXML
    private ImageView flechaArriba;

    @FXML
    private ImageView flechaDerecha;

    @FXML
    private ImageView flechaIzquierda;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView siguiente;

    @FXML
    private ImageView obstaculo;

    @FXML
    private ImageView personaje;

    @FXML
    private ImageView pollo;

    @FXML
    private ImageView eliminar;

    @FXML
    private HBox eliminarHbox;

    @FXML
    private Label lblReto;

    private ImageView textura;

    private Image estadoTextoCorrecto;

    private Image estadoTextoIncorrecto;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(){
        contadorReto = 0;
        movimientos = new ArrayList<>();
        textura = new ImageView(new Image(HelloApplication.class.getResourceAsStream("backgorund/cesped.jpg")));
        retos = new Reto().getRetos();
        puntajeRetos = new int[retos.size()];
        siguiente.setDisable(true);
        animarPollo();
        estadoTextoCorrecto = new Image(HelloApplication.class.getResourceAsStream("fuentes/genial.png"));
        estadoTextoIncorrecto = new Image(HelloApplication.class.getResourceAsStream("fuentes/error.png"));
        cargarMundo(retos.get(contadorReto));
    }

    public void animarPollo(){
        Objetivo polloPersonaje = new Objetivo(pollo, 1);
        Platform.runLater(
                () -> {
                    polloPersonaje.start();
                }
        );
    }

    @FXML
    void siguienteReto(MouseEvent event) {
        if (contadorReto + 1 < retos.size()) {
            contadorReto++;
            cargarMundo(retos.get(contadorReto));
        }
        else {
            try {
                ventanaEncuesta();
            }
            catch (Exception e){

            }
        }
    }

    @FXML
    private void mostrarAlertCabecera() {
        String texto = new String();
        for (int i = 0; i < retos.size(); i++) {
            if(puntajeRetos[i] == 1)
                texto += "Reto #" + (i + 1) + ": Correcto\n";
            else
                texto += "Reto #" + (i + 1) + ": Incorrecto\n";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Puntaje");
        alert.setTitle("Alcanza al pollo");
        alert.setContentText(texto);
        alert.showAndWait();
    }

    private void ventanaEncuesta() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistas/encuesta.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Alcanza al pollo");
        stage.setScene(scene);
        stage.setResizable(false);
        ControladorEncuesta controladorEncuesta = fxmlLoader.getController();
        controladorEncuesta.setPuntajeRetos(puntajeRetos);
        controladorEncuesta.setStage(stage);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream( "iconos/icono-principal.png")));
        stage.fullScreenExitKeyProperty();
        this.stage.close();
        mostrarAlertCabecera();
        stage.show();
    }

    @FXML
    void colorearFlechaAbajo(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba-naranja.png"));
        flechaAbajo.setImage(image);
    }

    @FXML
    void colorearFlechaArriba(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba-naranja.png"));
        flechaArriba.setImage(image);
    }

    @FXML
    void colorearFlechaDerecha(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba-naranja.png"));
        flechaDerecha.setImage(image);
    }

    @FXML
    void colorearFlechaIzquierda(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba-naranja.png"));
        flechaIzquierda.setImage(image);
    }

    @FXML
    void colorearEliminar(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("artefactos/delete-selected.png"));
        eliminar.setImage(image);
    }

    @FXML
    void colorearBotonMover(MouseEvent event) {
        botonMover.setStyle("-fx-background-color:green; -fx-background-radius: 10");
    }

    @FXML
    void correrMatriz(ActionEvent event) throws InterruptedException {
        Personaje personaje = new Personaje(this, this.personaje, movimientos, gridPane, camino, retos.get(contadorReto));
        desactivarAcciones(true);
        personaje.start();
    }

    @FXML
    void descolorearBotonMover(MouseEvent event) {
        botonMover.setStyle("-fx-background-color:orange; -fx-background-radius: 10");
    }

    @FXML
    void descolorearEliminar(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("artefactos/delete.png"));
        eliminar.setImage(image);
    }

    @FXML
    void descolorearFlechaAbajo(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        flechaAbajo.setImage(image);
    }

    @FXML
    void descolorearFlechaArriba(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        flechaArriba.setImage(image);
    }

    @FXML
    void descolorearFlechaDerecha(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        flechaDerecha.setImage(image);
    }

    @FXML
    void descolorearFlechaIzquierda(MouseEvent event) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        flechaIzquierda.setImage(image);
    }

    @FXML
    void eliminarFlecha(MouseEvent event) {
        if (movimientos.size() != 0) {
            camino.getChildren().remove(camino.getChildren().size() - 1);
            movimientos.remove(movimientos.size() - 1);
        }
    }

    @FXML
    void guardarAbajo(MouseEvent event) {
        ImageView imageView = new ImageView();
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        imageView.setImage(image);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setRotate(180);
        camino.getChildren().add(imageView);
        movimientos.add(Movimiento.Abajo);
    }

    @FXML
    void guardarArriba(MouseEvent event) {
        ImageView imageView = new ImageView();
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        imageView.setImage(image);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        camino.getChildren().add(imageView);
        movimientos.add(Movimiento.Arriba);
    }

    @FXML
    void guardarDerecha(MouseEvent event) {
        ImageView imageView = new ImageView();
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        imageView.setImage(image);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setRotate(90);
        camino.getChildren().add(imageView);
        movimientos.add(Movimiento.Derecha);
    }

    @FXML
    void guardarIzquierda(MouseEvent event) {
        ImageView imageView = new ImageView();
        Image image = new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba.png"));
        imageView.setImage(image);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setRotate(270);
        camino.getChildren().add(imageView);
        movimientos.add(Movimiento.Izquierda);
    }

    @FXML
    private Optional<ButtonType> mostrarAlertConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("Volveras al inicio y perderás el progreso\n¿Estás seguro?");
        return alert.showAndWait();
    }

    @FXML
    void inicio(MouseEvent event) throws IOException {
        Optional<ButtonType> action = mostrarAlertConfirmation();
        if (action.get() == ButtonType.OK) {
            mostrarInicio();
        }
    }

    private void mostrarInicio(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistas/principal.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControladorPrincipal controladorPrincipal = fxmlLoader.getController();
        controladorPrincipal.setStage(stage);
        stage.setScene(scene);
        stage.fullScreenExitKeyProperty();
        stage.show();
    }

    public void setEstado (boolean centinela) {
        if (centinela) {
            estado.setImage(new Image(HelloApplication.class.getResourceAsStream("iconos/estado/correcto.png")));
            puntajeRetos[contadorReto] = 1;
            estadoTexto.setImage(estadoTextoCorrecto);
            hboxEstado.setStyle("-fx-border-radius:10;" +
                    "-fx-border-color:green;" +
                    "-fx-border-width:2;" +
                    "-fx-background-color: #1f00204f;" +
                    "-fx-background-radius:10");
        } else {
            puntajeRetos[contadorReto] = 0;
            personaje.setImage(new Image(HelloApplication.class.getResourceAsStream("sprites/mono/Animations/monkey_faceforward.png")));
            estado.setImage(new Image(HelloApplication.class.getResourceAsStream("iconos/estado/incorrecto.png")));
            estadoTexto.setImage(estadoTextoIncorrecto);
            hboxEstado.setStyle("-fx-border-radius:10;" +
                    "-fx-border-color:red;" +
                    "-fx-border-width:2;" +
                    "-fx-background-color: #1f00204f;" +
                    "-fx-background-radius:10");
        }
        siguiente.setImage(new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-siguiente-activa.png")));
        siguiente.setDisable(false);
    }

    private void desactivarAcciones(boolean estado){
        flechaIzquierda.setDisable(estado);
        flechaDerecha.setDisable(estado);
        flechaAbajo.setDisable(estado);
        flechaArriba.setDisable(estado);
        eliminarHbox.setDisable(estado);
        botonMover.setDisable(estado);
    }

    public void cargarMundo(int[][] reto){
        double xGrid = gridPane.getPrefWidth();
        double yGrid = gridPane.getPrefHeight();
        gridPane.getChildren().removeAll(gridPane.getChildren());
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {

                ImageView viewTextura = new ImageView(textura.getImage());
                viewTextura.setFitWidth(56);
                viewTextura.setFitHeight(56);
                gridPane.add(viewTextura, j, i);

                switch (reto[i][j]){
                    case 1:
                        double x = (gridPane.getLayoutX() + 8) + (j) * (xGrid / gridPane.getColumnCount());
                        double y = (gridPane.getLayoutY() + 3) + (i) * (yGrid / gridPane.getRowCount());

                        personaje.setX(0);
                        personaje.setY(0);

                        personaje.setLayoutX(x);
                        personaje.setLayoutY(y);
                        break;
                    case 5:
                        ImageView viewObstaculo = new ImageView(obstaculo.getImage());
                        viewObstaculo.setFitWidth(44);
                        viewObstaculo.setFitHeight(42);
                        gridPane.add(viewObstaculo, j, i);
                        gridPane.getColumnConstraints().get(j).setHalignment(HPos.CENTER);
                        break;
                    case 9:
                        pollo.setFitWidth(53);
                        pollo.setFitHeight(60);
                        gridPane.add(pollo, j, i);break;
                }
            }
        }
        gridPane.setGridLinesVisible(false);
        gridPane.setGridLinesVisible(true);
        personaje.setImage(new Image(HelloApplication.class.getResourceAsStream("sprites/mono/Animations/monkey_walk_away_1.png")));
        siguiente.setImage(new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-siguiente-inactiva.png")));
        siguiente.setDisable(true);
        estado.setImage(new Image(HelloApplication.class.getResourceAsStream("iconos/estado/neutro.png")));
        hboxEstado.setStyle("-fx-border-radius:10;" +
                "-fx-border-color:orange;" +
                "-fx-border-width:2;" +
                "-fx-background-color: #1f00204f;" +
                "-fx-background-radius:10");
        camino.getChildren().clear();
        movimientos.clear();
        lblReto.setText(String.valueOf(contadorReto + 1));
        estadoTexto.setImage(null);
        desactivarAcciones(false);
    }
}

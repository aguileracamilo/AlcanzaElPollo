package com.alcanzaelpollo.modelo;

import com.alcanzaelpollo.HelloApplication;
import com.alcanzaelpollo.controlador.ControladorReto;
import javafx.application.Platform;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Personaje extends  Thread{
    private ImageView personaje;
    private ArrayList<Movimiento> movimientos;
    private GridPane gridPane;
    private HBox camino;
    private int[][] reto;
    private int iInicial, jInicial;
    ControladorReto controladorReto;

    public Personaje(ControladorReto controladorReto, ImageView personaje, ArrayList<Movimiento> movimientos, GridPane gridPane, HBox camino, int[][] reto) {
        this.controladorReto = controladorReto;
        this.personaje = personaje;
        this.movimientos = movimientos;
        this.gridPane = gridPane;
        this.camino = camino;
        this.reto = reto;
        asignarInicial();
    }

    private void asignarInicial(){
        for (int i = 0; i < reto.length; i++) {
            for (int j = 0; j < reto[i].length; j++) {
                if (reto[i][j] == 1){
                    iInicial = i;
                    jInicial = j;
                }
            }
        }
    }

    @Override
    public void run() {
        int i = 0, j = 0;

        for (int k = 0; k < movimientos.size(); k++) {
            Movimiento movimiento = movimientos.get(k);
            int contador = 0;
            switch (movimiento){
                case Arriba:
                    if (iInicial - 1 >= 0) {
                        iInicial--;
                        while (contador != gridPane.getHeight() / gridPane.getRowCount()) {
                            personaje.setY(personaje.getY() - 1);
                            contador++;
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //Verifica si hay o no un obstaculo
                        verificarCasilla(iInicial, jInicial, k);
                    }
                    else
                        impedirPaso(k);
                break;
                case Abajo:
                    contador = 0;
                    if (iInicial  + 1 < 5) {
                        iInicial++;
                        while (contador != gridPane.getHeight() / gridPane.getRowCount()) {
                            personaje.setY(personaje.getY() + 1);
                            contador++;
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //Verifica si hay o no un obstaculo
                        verificarCasilla(iInicial, jInicial, k);
                    }
                    else
                        impedirPaso(k);
                break;
                case Izquierda:
                    contador = 0;
                    if (jInicial - 1 >= 0) {
                        jInicial--;
                        while (contador != gridPane.getWidth() / gridPane.getColumnCount()) {
                            personaje.setX(personaje.getX() - 1);
                            contador++;
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //Verifica si hay o no un obstaculo
                        verificarCasilla(iInicial, jInicial, k);
                    }
                    else
                        impedirPaso(k);
                    break;
                case Derecha:
                    contador = 0;
                    if (jInicial  + 1 < 5) {
                        jInicial++;
                        while (contador != gridPane.getWidth() / gridPane.getColumnCount()) {
                            personaje.setX(personaje.getX() + 1);
                            contador++;
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //Verifica si hay o no un obstaculo
                        verificarCasilla(iInicial, jInicial, k);
                    }
                    else
                        impedirPaso(k);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (reto[iInicial][jInicial] == 9)
            controladorReto.setEstado(true);//Asigna la imagen de incorrecto al estado
        else
            controladorReto.setEstado(false);
    }

    private void verificarCasilla(int iMatriz, int jMatriz, int kCamino){
        if (reto[iMatriz][jMatriz] == 5){
            impedirPaso(kCamino);
        }
        marcarHuella(iMatriz, jMatriz);
        permitirPaso(kCamino);
    }

    private void marcarHuella(int iInicial, int jInicial) {
        Image image = new Image(HelloApplication.class.getResourceAsStream("artefactos/huella-mono.png"));
        ImageView pataIzquierda = new ImageView(image);
        pataIzquierda.setFitWidth(12);
        pataIzquierda.setFitHeight(13);
        ImageView pataDerecha = new ImageView(image);
        pataDerecha.setFitWidth(12);
        pataDerecha.setFitHeight(13);
        pataDerecha.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        HBox hbox = new HBox(pataIzquierda, pataDerecha);
        hbox.setAlignment(Pos.CENTER);
        Platform.runLater(
                () -> {
                    gridPane.add(hbox, jInicial, iInicial);
                }
        );
    }

    private void impedirPaso(int posicion){
        ImageView imageView = (ImageView) camino.getChildren().get(posicion);
        imageView.setImage(new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba-rojo.png")));
        controladorReto.setEstado(false);
        stop();//Detiene la ejecución del ciclo
    }

    private void permitirPaso(int posicion){
        ImageView imageView = (ImageView) camino.getChildren().get(posicion);
        imageView.setImage(new Image(HelloApplication.class.getResourceAsStream("iconos/flecha/flecha-arriba-verde.png")));
    }
}

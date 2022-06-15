package com.alcanzaelpollo.modelo;

import com.alcanzaelpollo.HelloApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Objetivo extends Thread{
    private final ImageView pollo;
    private int i;

    public Objetivo(ImageView pollo, int i) {
        this.i = i;
        this.pollo = pollo;
    }

    @Override
    public void run() {
        switch (i){
            case 1:
                animarPollo1();
            break;
            case 2:
                animarPollo2();
            break;
        }

    }

    private void animarPollo2(){
        Image pollo1 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-5.png"));
        Image pollo2 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-6.png"));
        Image pollo3 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-7.png"));
        Image pollo4 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-1-columna-8.png"));
        while (true)
            try {
                pollo.setImage(pollo1);
                Thread.sleep(50);
                pollo.setImage(pollo2);
                Thread.sleep(50);
                pollo.setImage(pollo3);
                Thread.sleep(50);
                pollo.setImage(pollo4);
                Thread.sleep(50);
                pollo.setImage(pollo3);
                Thread.sleep(50);
                pollo.setImage(pollo2);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void animarPollo1(){
        Image pollo1 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-9.png"));
        Image pollo2 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-10.png"));
        Image pollo3 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-11.png"));
        Image pollo4 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-12.png"));
        Image pollo5 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-13.png"));
        Image pollo6 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-14.png"));
        Image pollo7 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-15.png"));
        Image pollo8 = new Image(HelloApplication.class.getResourceAsStream( "sprites/pollo/fila-2-columna-16.png"));
        while (true)
            try {
                pollo.setImage(pollo1);
                Thread.sleep(50);
                pollo.setImage(pollo2);
                Thread.sleep(50);
                pollo.setImage(pollo3);
                Thread.sleep(50);
                pollo.setImage(pollo4);
                Thread.sleep(50);
                pollo.setImage(pollo5);
                Thread.sleep(50);
                pollo.setImage(pollo6);
                Thread.sleep(50);
                pollo.setImage(pollo7);
                Thread.sleep(50);
                pollo.setImage(pollo8);
                Thread.sleep(50);
                pollo.setImage(pollo7);
                Thread.sleep(50);
                pollo.setImage(pollo6);
                Thread.sleep(50);
                pollo.setImage(pollo5);
                Thread.sleep(50);
                pollo.setImage(pollo4);
                Thread.sleep(50);
                pollo.setImage(pollo5);
                Thread.sleep(50);
                pollo.setImage(pollo6);
                Thread.sleep(50);
                pollo.setImage(pollo7);
                Thread.sleep(50);
                pollo.setImage(pollo8);
                Thread.sleep(50);
                pollo.setImage(pollo7);
                Thread.sleep(50);
                pollo.setImage(pollo6);
                Thread.sleep(50);
                pollo.setImage(pollo5);
                Thread.sleep(50);
                pollo.setImage(pollo4);
                Thread.sleep(50);
                pollo.setImage(pollo3);
                Thread.sleep(50);
                pollo.setImage(pollo2);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}

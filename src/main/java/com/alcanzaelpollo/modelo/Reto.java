package com.alcanzaelpollo.modelo;

import java.util.ArrayList;

public class Reto {
    //Punto inicial "1"
    //Punto final "9"
    //Obstaculo "5"
    //Camino limpio "0"

    private ArrayList<int[][]> retos;

    private int[][] reto1 = {
            {0, 0, 0, 0, 9},
            {0, 0, 0, 0, 5},
            {0, 0, 0, 5, 5},
            {5, 5, 0, 0, 0},
            {0, 1, 0, 0, 0}};

    private int[][] reto2 = {
            {9, 0, 0, 0, 0},
            {5, 5, 5, 0, 5},
            {0, 0, 5, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private int[][] reto3 = {
            {0, 0, 9, 5, 1},
            {0, 5, 5, 5, 0},
            {0, 0, 5, 0, 0},
            {0, 5, 0, 0, 0},
            {0, 0, 0, 0, 0}};

    private int[][] reto4 = {
            {5, 5, 1, 0, 5},
            {9, 5, 0, 0, 5},
            {0, 0, 5, 0, 0},
            {0, 0, 5, 0, 0},
            {5, 0, 0, 0, 0}};

    private int[][] reto5 = {
            {5, 0, 0, 0, 0},
            {5, 0, 0, 5, 9},
            {5, 5, 0, 0, 5},
            {1, 0, 5, 0, 0},
            {5, 0, 0, 0, 0}};

    public Reto() {
        retos = new ArrayList<>();
        retos.add(reto1);
        retos.add(reto2);
        retos.add(reto3);
        retos.add(reto4);
        retos.add(reto5);
    }

    public ArrayList<int[][]> getRetos() {
        return retos;
    }
}

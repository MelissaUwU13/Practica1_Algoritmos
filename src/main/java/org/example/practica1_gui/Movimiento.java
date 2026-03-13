package org.example.practica1_gui;

import DeckOfCards.CartaInglesa;

public class Movimiento {

    private CartaInglesa carta;
    private String origen;
    private String destino;

    public Movimiento(CartaInglesa carta, String origen, String destino) {
        this.carta = carta;
        this.origen = origen;
        this.destino = destino;
    }

    public CartaInglesa getCarta() {
        return carta;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
}
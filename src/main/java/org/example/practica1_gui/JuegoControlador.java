package org.example.practica1_gui;

import Solitaire.SolitaireGame;

public class JuegoControlador {

    private SolitaireGame juego;
    private JuegoGUI vista;
    private boolean wasteSeleccionado = false;

    private int columnaSeleccionada = -1;

    public JuegoControlador(SolitaireGame juego, JuegoGUI vista) {
        this.juego = juego;
        this.vista = vista;

        vista.setControlador(this);
        vista.mostrar();
        vista.actualizar(juego);
    }


    public void clickColumna(int columna) {

        // Si el waste está seleccionado → mover waste a tableau
        if (wasteSeleccionado) {
            juego.moveWasteToTableau(columna);
            wasteSeleccionado = false;
            vista.actualizar(juego);
            return;
        }

        // Movimiento normal entre columnas
        if (columnaSeleccionada == -1) {
            columnaSeleccionada = columna;
        } else {
            if (columnaSeleccionada != columna) {
                juego.moveTableauToTableau(columnaSeleccionada, columna);
            }
            columnaSeleccionada = -1;
            vista.actualizar(juego);
        }
    }

    public void moverWasteATableau(int columnaDestino) {
        juego.moveWasteToTableau(columnaDestino);
        vista.actualizar(juego);
    }

    public void moverWasteAFoundation() {
        juego.moveWasteToFoundation();
        vista.actualizar(juego);
    }

    public void moverTableauAFoundation(int columna) {
        juego.moveTableauToFoundation(columna);
        vista.actualizar(juego);
    }

    public void clickMazo() {
        if (juego.getDrawPile().hayCartas()) {
            juego.drawCards();
        } else {
            juego.reloadDrawPile();
        }
        vista.actualizar(juego);
    }

    // CLICK EN WASTE
    public void clickWaste() {
        wasteSeleccionado = true;
    }

    public void clickFoundation(int numeroFoundation) {

        // Waste → Foundation
        if (wasteSeleccionado) {
            juego.moveWasteToFoundation();
            wasteSeleccionado = false;
            vista.actualizar(juego);
            return;
        }

        // Tableau → Foundation
        if (columnaSeleccionada != -1) {
            juego.moveTableauToFoundation(columnaSeleccionada);
            columnaSeleccionada = -1;
            vista.actualizar(juego);
        }
    }
}
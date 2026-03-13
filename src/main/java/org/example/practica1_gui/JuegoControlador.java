package org.example.practica1_gui;

import Solitaire.SolitaireGame;

public class JuegoControlador {

    private SolitaireGame juego;
    private JuegoGUI vista;
    private boolean wasteSeleccionado = false;
    private int columnaSeleccionada = -1;
    Pila<SolitaireGame> undoStack = new Pila<>();

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
            guardarEstado();
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
                guardarEstado();
                juego.moveTableauToTableau(columnaSeleccionada, columna);
            }
            columnaSeleccionada = -1;
            vista.actualizar(juego);
        }
    }

    public void moverWasteATableau(int columnaDestino) {
        guardarEstado();

        juego.moveWasteToTableau(columnaDestino);
        vista.actualizar(juego);
    }

    public void moverWasteAFoundation() {
        guardarEstado();

        juego.moveWasteToFoundation();
        vista.actualizar(juego);
    }

    public void moverTableauAFoundation(int columna) {
        guardarEstado();
        juego.moveTableauToFoundation(columna);
        vista.actualizar(juego);
    }

    public void clickMazo() {
        guardarEstado();
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
            guardarEstado();
            juego.moveWasteToFoundation();
            wasteSeleccionado = false;
            vista.actualizar(juego);
            return;
        }

        // Tableau → Foundation
        if (columnaSeleccionada != -1) {
            guardarEstado();
            juego.moveTableauToFoundation(columnaSeleccionada);
            columnaSeleccionada = -1;
            vista.actualizar(juego);
        }
    }

    public void undo() {

        if (!undoStack.pilaVacia()) {

            System.out.println("La pila tiene cosas");

            juego = undoStack.pop();

            vista.actualizar(juego);
        }
        else{
            System.out.println("La pila esta vacia");
        }
    }

    private void guardarEstado() {
        undoStack.push(juego.clonarJuego());
        System.out.println("Guardado! ");
    }
}
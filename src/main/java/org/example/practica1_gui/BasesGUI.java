package org.example.practica1_gui;

import DeckOfCards.CartaInglesa;
import Solitaire.FoundationDeck;
import Solitaire.SolitaireGame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class BasesGUI {

    private HBox contenedor;
    private ArrayList<ImageView> bases = new ArrayList<>();
    private JuegoControlador controlador;   // ← ESTO FALTABA

    public BasesGUI(JuegoControlador controlador) {
        this.controlador = controlador;

        contenedor = new HBox(20);

        Image vacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));

        for (int i = 0; i < 4; i++) {
            ImageView base = new ImageView(vacio);
            base.setFitWidth(50);
            base.setPreserveRatio(true);
            bases.add(base);
            contenedor.getChildren().add(base);

            int numeroBase = i + 1;

            base.setOnMouseClicked(e -> {
                controlador.clickFoundation(numeroBase);
            });
        }
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void actualizar(SolitaireGame juego) {

        Image vacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));

        for (int i = 0; i < 4; i++) {

            FoundationDeck foundation = juego.getFoundations().get(i);

            if (!foundation.estaVacio()) {
                CartaInglesa ultima = foundation.getUltimaCarta();
                CartaGUI cartaGUI = new CartaGUI(ultima);
                bases.get(i).setImage(cartaGUI.getImageView().getImage());
            } else {
                bases.get(i).setImage(vacio);
            }
        }
    }
}
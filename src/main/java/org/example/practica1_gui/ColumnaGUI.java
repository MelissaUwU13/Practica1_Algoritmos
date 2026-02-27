package org.example.practica1_gui;

import DeckOfCards.CartaInglesa;
import Solitaire.TableauDeck;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ColumnaGUI {

    private VBox contenedor;
    private int indice;

    public ColumnaGUI(int indice, JuegoControlador controlador) {
        this.indice = indice;
        contenedor = new VBox(-45);

        contenedor.setOnMouseClicked(e -> {
            controlador.clickColumna(indice);
        });
    }

    public VBox getContenedor() {
        return contenedor;
    }

    public void actualizar(TableauDeck colLogica) {

        contenedor.getChildren().clear();

        if (colLogica.isEmpty()) {
            Image vacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));
            ImageView espacio = new ImageView(vacio);
            espacio.setFitWidth(50);
            espacio.setPreserveRatio(true);
            contenedor.getChildren().add(espacio);
        } else {
            for (CartaInglesa carta : colLogica.getCards()) {
                CartaGUI cartaGUI = new CartaGUI(carta);
                contenedor.getChildren().add(cartaGUI.getImageView());
            }
        }
    }
}
package org.example.practica1_gui;

import DeckOfCards.CartaInglesa;
import Solitaire.SolitaireGame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MazoGUI {

    private ImageView mazoVisual;
    private ImageView descarteVisual;
    private HBox contenedor;

    public MazoGUI(JuegoControlador controlador) {

        Image reverso = new Image(getClass().getResourceAsStream("/Cartas/reverso.png"));
        Image vacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));

        mazoVisual = new ImageView(reverso);
        mazoVisual.setFitWidth(50);
        mazoVisual.setPreserveRatio(true);

        descarteVisual = new ImageView(vacio);
        descarteVisual.setFitWidth(50);
        descarteVisual.setPreserveRatio(true);

        mazoVisual.setOnMouseClicked(e -> {
            controlador.clickMazo();
        });

        descarteVisual.setOnMouseClicked(e -> {
            controlador.clickWaste();
        });

        contenedor = new HBox(20, mazoVisual, descarteVisual);
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void actualizar(SolitaireGame juego) {

        Image vacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));
        Image reverso = new Image(getClass().getResourceAsStream("/Cartas/reverso.png"));

        if (juego.getDrawPile().hayCartas()) {
            mazoVisual.setImage(reverso);
        } else {
            mazoVisual.setImage(vacio);
        }

        CartaInglesa cartaWaste = juego.getWastePile().verCarta();

        if (cartaWaste != null) {
            CartaGUI cartaGUI = new CartaGUI(cartaWaste);
            descarteVisual.setImage(cartaGUI.getImageView().getImage());
        } else {
            descarteVisual.setImage(vacio);
        }
    }
}
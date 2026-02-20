package org.example.practica1_gui;

import DeckOfCards.Carta;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaGUI {

    private ImageView imageView;
    private Carta carta;

    public CartaGUI(Carta carta) {
        this.carta = carta;
        this.imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        actualizarImagen();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void actualizarImagen() {
        String nombreArchivo;

        if (!carta.isFaceup()) {
            nombreArchivo = "reverso.png";
        } else {
            nombreArchivo = carta.getValor() + "_" + carta.getPalo().name() + ".png";
        }

        Image imagen = new Image(getClass()
                .getResourceAsStream("/Cartas/" + nombreArchivo));

        imageView.setImage(imagen);
    }

    public Carta getCarta() {
        return carta;
    }
}
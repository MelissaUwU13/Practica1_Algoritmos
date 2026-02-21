package org.example.practica1_gui;

import DeckOfCards.Carta;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaGUI {

    private ImageView imageView;
    private Carta carta;

    //definimos los componentes de la carta
    public CartaGUI(Carta carta) {
        this.carta = carta;
        this.imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        actualizarImagen();
    }

    //nos regresa la imagen de la carta
    public ImageView getImageView() {
        return imageView;
    }

    //este metodo nos permite emparejar la carta logica con la gui, sacando las imagenes
    //de una carpeta llamada cartas, a partir de su valor y palo, tambien esta la imagen de reverso
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

    //nos regresa la carta
    public Carta getCarta() {
        return carta;
    }
}
package org.example.practica1_gui;

import DeckOfCards.Carta;
import DeckOfCards.CartaInglesa;
import Solitaire.FoundationDeck;
import Solitaire.TableauDeck;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Solitaire.SolitaireGame;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.util.ArrayList;

public class Juego {
    private SolitaireGame juego;
    private HBox columnas;
    private VBox columnas1;
    private int columnaSeleccionada = -1;
    private ArrayList<ImageView> basesVisuales = new ArrayList<>();
    private ImageView descarteVisual;
    private ImageView mazoVisual;
    private boolean modoWasteATableau = false;

    public void mostrar(Stage stage) {

        juego = new SolitaireGame();
        columnas = new HBox(40);
        columnas.setTranslateY(100);

        columnas1 = new VBox(20);
        columnas1.setTranslateY(100);


        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpeg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1262);
        fondo.setFitHeight(799);

        //Botones
        Button btnMoverPilaBase = new Button("MOVER PILA A BASE");
        Button btnDibujar = new Button("DIBUJAR");
        Button btnRecargar = new Button("RECARGAR");
        Button btnMoverCartaBase = new Button("MOVER CARTA A BASE");
        Button btnMoverCartaCarta = new Button("MOVER CARTA A CARTA");
        Button btnMoverPilaCarta = new Button("MOVER PILA A CARTA");
        Button btnSalir = new Button("SALIR");

        btnMoverPilaBase.getStyleClass().add("boton-moverpilabase");
        btnDibujar.getStyleClass().add("boton-dibujar");
        btnRecargar.getStyleClass().add("boton-recargar");
        btnMoverCartaBase.getStyleClass().add("boton-movercartabase");
        btnMoverCartaCarta.getStyleClass().add("boton-movercartacarta");
        btnMoverPilaCarta.getStyleClass().add("boton-moverpilacarta");
        btnSalir.getStyleClass().add("boton-salir");

        btnMoverPilaBase.setOnAction(e -> {
            juego.moveWasteToFoundation();
            actualizarVista();
        });

        btnDibujar.setOnAction(e -> {
            juego.drawCards();
            actualizarVista();
        });

        btnRecargar.setOnAction(e -> {
            juego.reloadDrawPile();
            actualizarVista();
        });

        btnMoverCartaBase.setOnAction(e -> {
            if (columnaSeleccionada != -1) {
                juego.moveTableauToFoundation(columnaSeleccionada);
                columnaSeleccionada = -1;
                actualizarVista();
            }
        });

        btnMoverCartaCarta.setOnAction(e -> {
            if (columnaSeleccionada != -1) {
                juego.moveWasteToTableau(columnaSeleccionada);
                columnaSeleccionada = -1;
                actualizarVista();
            }
        });

        btnMoverPilaCarta.setOnAction(e -> {
            modoWasteATableau = true;
            columnaSeleccionada = -1;
        });

        btnSalir.setOnAction(e -> stage.close());


        HBox opciones = new HBox(20, btnMoverPilaBase, btnDibujar, btnRecargar, btnMoverCartaBase, btnMoverCartaCarta, btnMoverPilaCarta, btnSalir);
        opciones.setAlignment(Pos.BOTTOM_CENTER);


        Image imgVacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));
        Image Reverso = new Image(getClass().getResourceAsStream("/Cartas/reverso.png"));

        mazoVisual = new ImageView(Reverso);
        mazoVisual.setFitWidth(50);
        mazoVisual.setPreserveRatio(true);

        descarteVisual = new ImageView(imgVacio);
        descarteVisual.setFitWidth(50);
        descarteVisual.setPreserveRatio(true);

        HBox bases = new HBox(20);
        bases.setAlignment(Pos.CENTER);

        for(int i = 0; i < 4; i++){
            ImageView base = new ImageView(imgVacio);
            base.setFitWidth(50);
            base.setPreserveRatio(true);

            basesVisuales.add(base); // ← GUARDAMOS REFERENCIA
            bases.getChildren().add(base);
        }

        HBox zonaSuperior = new HBox(40);
        zonaSuperior.setAlignment(Pos.CENTER);

        zonaSuperior.getChildren().addAll(mazoVisual, descarteVisual, bases);

        BorderPane tablero = new BorderPane();

        tablero.setTop(zonaSuperior);
        tablero.setCenter(columnas);
        tablero.setBottom(opciones);

        BorderPane.setAlignment(opciones, Pos.CENTER);
        BorderPane.setMargin(opciones, new Insets(20));

        StackPane root = new StackPane();
        root.getChildren().addAll(fondo, tablero);

        actualizarVista();

        Scene escenaJuego = new Scene(root, 1200, 599);
        escenaJuego.getStylesheets().add("/estilo.css");

        stage.setTitle("Solitario - Juego");
        stage.setScene(escenaJuego);
    }

    private void actualizarVista() {

        columnas.getChildren().clear();

        for (int i = 0; i < 7; i++) {

            final int indiceColumna = i + 1;
            VBox col = new VBox(-45);

            col.setOnMouseClicked(e -> {
                manejarClickColumna(indiceColumna);
            });

            TableauDeck colLogica = juego.getTableau().get(i);

            if (colLogica.isEmpty()) {

                Image vacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));
                ImageView espacio = new ImageView(vacio);
                espacio.setFitWidth(50);
                espacio.setPreserveRatio(true);

                col.getChildren().add(espacio);

            } else {

                for (CartaInglesa carta : colLogica.getCards()) {
                    CartaGUI cartaGUI = new CartaGUI(carta);
                    col.getChildren().add(cartaGUI.getImageView());
                }
            }

            columnas.getChildren().add(col);
            columnas.setAlignment(Pos.CENTER);
        }

        for (int i = 0; i < basesVisuales.size(); i++) {

            FoundationDeck foundation = juego.getLastFoundationUpdated();

            if (foundation != null && !foundation.estaVacio()) {
                CartaInglesa ultima = foundation.getUltimaCarta();
                CartaGUI cartaGUI = new CartaGUI(ultima);
                basesVisuales.get(ultima.getPalo().ordinal()).setImage(cartaGUI.getImageView().getImage());
            }
        }

        CartaInglesa cartaWaste = juego.getWastePile().verCarta();

        if (cartaWaste != null) {
            CartaGUI cartaGUI = new CartaGUI(cartaWaste);
            descarteVisual.setImage(cartaGUI.getImageView().getImage());
        } else {
            descarteVisual.setImage(new Image(getClass().getResourceAsStream("/Cartas/vacio.png")));
        }

        if (juego.getDrawPile().hayCartas()) {
            mazoVisual.setImage(new Image(getClass().getResourceAsStream("/Cartas/reverso.png")));
        } else {
            mazoVisual.setImage(new Image(getClass().getResourceAsStream("/Cartas/vacio.png")));
        }
    }

    private void manejarClickColumna(int columna) {

        if (modoWasteATableau) {

            boolean movido = juego.moveWasteToTableau(columna);

            modoWasteATableau = false;
            actualizarVista();
            return;
        }

        if (columnaSeleccionada == -1) {
            columnaSeleccionada = columna;
        }
        else {
            boolean movido = juego.moveTableauToTableau(columnaSeleccionada, columna);

            columnaSeleccionada = -1;
            actualizarVista();
        }
    }
}

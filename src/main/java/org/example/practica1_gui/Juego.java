package org.example.practica1_gui;

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

        //creamos la instancia del juego logico
        juego = new SolitaireGame();

        //creamos las columnas que nos serviran para guardar las cartas
        columnas = new HBox(40);
        columnas.setTranslateY(100);

        columnas1 = new VBox(20);
        columnas1.setTranslateY(100);

        //creamos la imagen de fondo
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

        //Le damos eventos a los botones, los cuales los tomamos de la clase SolitarieGame
        //y despues de realizar la accion utilizamos nuestra funcion actualizarVista
        //Para que se muestre graficamente el cambio que haya sucedido entre cartas
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

        //en estos casos que son acciones que requieren ubicaciones de columnas
        //primero confirmamos que si hubo una seleccion (de 0 a 6), y en caso de que si
        //se realizara la opcion y volveremos a la normalidad nuestra bandera, y por ultimo
        //actualizamos nuevamente nuestra vista
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

        //En este caso tenemos otra bandera ya que esta opcion solo nos pide una columa a seleccionar
        //Si se da click al boton, se activa la bandera que nos servira mas adelante en nuestro
        //metodo de actualizar vista
        btnMoverPilaCarta.setOnAction(e -> {
            modoWasteATableau = true;
            columnaSeleccionada = -1;
        });

        //boton de salida del juego
        btnSalir.setOnAction(e -> stage.close());


        //Agregamos los botones en un Hbox horizontal y lo acomodamos abajo de forma centrada
        HBox opciones = new HBox(20, btnMoverPilaBase, btnDibujar, btnRecargar, btnMoverCartaBase, btnMoverCartaCarta, btnMoverPilaCarta, btnSalir);
        opciones.setAlignment(Pos.BOTTOM_CENTER);

        //Aqui creamos las imagenes de vacio y reverso que nos serviran para el mazo y descarte
        Image imgVacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));
        Image Reverso = new Image(getClass().getResourceAsStream("/Cartas/reverso.png"));

        mazoVisual = new ImageView(Reverso);
        mazoVisual.setFitWidth(50);
        mazoVisual.setPreserveRatio(true);

        descarteVisual = new ImageView(imgVacio);
        descarteVisual.setFitWidth(50);
        descarteVisual.setPreserveRatio(true);

        //Creamos un hbox de las bases, donde primeramente estaremos mostrandolas con una imagen vacia
        HBox bases = new HBox(20);
        bases.setAlignment(Pos.CENTER);

        for(int i = 0; i < 4; i++){
            ImageView base = new ImageView(imgVacio);
            base.setFitWidth(50);
            base.setPreserveRatio(true);

            basesVisuales.add(base); //guardamos la referencia
            bases.getChildren().add(base);
        }

        //Creamos un hbox especial para la zona de arriba que tendra el mazo, el descarte y las bases
        HBox zonaSuperior = new HBox(40);
        zonaSuperior.setAlignment(Pos.CENTER);

        zonaSuperior.getChildren().addAll(mazoVisual, descarteVisual, bases);

        //Utilizamos el BorderPane que nos ayudara con la estructura de nuestra aplicacion
        //Ademas de acomodar de mejor manera nuestra zona superior, columnas y botones
        BorderPane tablero = new BorderPane();

        tablero.setTop(zonaSuperior);
        tablero.setCenter(columnas);
        tablero.setBottom(opciones);

        BorderPane.setAlignment(opciones, Pos.CENTER);
        BorderPane.setMargin(opciones, new Insets(20));

        //Creamos un stackpane con el tablero y el fondo
        StackPane root = new StackPane();
        root.getChildren().addAll(fondo, tablero);

        //Antes de crear o mostrar el escenario, es necesario utilizar nuestro metodo actualizarVista
        //Quien es quien vincula nuestra clase CartaGUI con Juego y nos permite mostrar las imagenes
        //Y actualizar cualquier cambio referente a ellas
        actualizarVista();

        //Creamos nuestra escena, añadimos css y mostramos el juego
        Scene escenaJuego = new Scene(root, 1200, 599);
        escenaJuego.getStylesheets().add("/estilo.css");

        stage.setTitle("Solitario - Juego");
        stage.setScene(escenaJuego);
    }

    //En este metodo ingresamos las cartas en las columnas, en las bases, en el mazo y el descarte
    //Ademas que a lo largo del juego lo estaremos utilizando ya que nos sirve para actualizar cualquier
    //cambio que se haya producido con las cartas o bases
    private void actualizarVista() {

        //limpiamos siempre antes de utilizar
        columnas.getChildren().clear();

        for (int i = 0; i < 7; i++) {

            final int indiceColumna = i + 1;
            VBox col = new VBox(-45);

            //Si nuestra columna fue seleccionada llamamos al metodo manejarclick que analizara
            //el siguiente movimiento
            col.setOnMouseClicked(e -> {
                manejarClickColumna(indiceColumna);
            });

            TableauDeck colLogica = juego.getTableau().get(i);

            //si esta la columna vacia, ingresamos una imagen de vacio, mas que nada para que el usuario
            //sepa que la columna esta libre y se puede seguir utilizando
            if (colLogica.isEmpty()) {

                Image vacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));
                ImageView espacio = new ImageView(vacio);
                espacio.setFitWidth(50);
                espacio.setPreserveRatio(true);

                col.getChildren().add(espacio);

            } else {
                //utilizamos la CartaGUI para ingresar las cartas
                for (CartaInglesa carta : colLogica.getCards()) {
                    CartaGUI cartaGUI = new CartaGUI(carta);
                    col.getChildren().add(cartaGUI.getImageView());
                }
            }

            columnas.getChildren().add(col);
            columnas.setAlignment(Pos.CENTER);
        }

        //creamos e ingresamos las cartas en las bases
        for (int i = 0; i < basesVisuales.size(); i++) {

            FoundationDeck foundation = juego.getLastFoundationUpdated();

            if (foundation != null && !foundation.estaVacio()) {
                CartaInglesa ultima = foundation.getUltimaCarta();
                CartaGUI cartaGUI = new CartaGUI(ultima);
                basesVisuales.get(ultima.getPalo().ordinal()).setImage(cartaGUI.getImageView().getImage());
            }
        }

        CartaInglesa cartaWaste = juego.getWastePile().verCarta();

        //Si el espacio de descarte es diferente de null, entonces mostramos la carta que esta mostrando
        //sino ponemos una imagen de carta vacia
        //El mismo proceso se aplica en el mazo, solo que en este caso no mostramos la carta, sino el reverso
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

    //En este metodo manejamos lo que es la seleccion de la columna a la hora de ciertos botone
    //Tenemos 3 casos, el primer es el que antes mencionamos, de si tocar hacer una sola seleccion
    //En ese caso pasamos directo al metodo y actualizamos la vista
    //Otro caso es cuando utilizamos un boton que requiera dos selecciones, primero guardamos la primera
    //seleccion en la variable de columnaSeleccionada, despues pasamos a un else donde una vez
    //seleccionamos la segunda posicion, es entonces que realizamos el metodo y actualizamos vista
    //tambien regresamos a la normalidad nuestra columna seleccionada
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
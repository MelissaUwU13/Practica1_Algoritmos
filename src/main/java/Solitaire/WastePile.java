package Solitaire;

import DeckOfCards.CartaInglesa;
import org.example.practica1_gui.Pila;
import java.util.ArrayList;

/**
 * Modela el montículo donde se colocan las cartas
 * que se extraen de Draw pile.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (2025-2)
 */
public class WastePile {
    private Pila<CartaInglesa> cartas;

    public WastePile() {
        cartas = new Pila<>();
    }

    //un for para revisar todas las cartas y agregarlas una por una
    public void addCartas(ArrayList<CartaInglesa> nuevas) {
        for(CartaInglesa c : nuevas){
            cartas.push(c);
        }
    }

    public ArrayList<CartaInglesa> emptyPile() {

        ArrayList<CartaInglesa> pile = new ArrayList<>();

        //mientras la pila de cartas no este vacia iremos sacando cartas y agregandola a pile
        //agregamos un index, para que inserte al inicio del arrayList para conservar el orden original
        while(!cartas.pilaVacia()){
            pile.add(0, cartas.pop());
        }

        return pile;
    }

    /**
     * Obtener la última carta sin removerla.
     * @return Carta que está encima. Si está vacía, es null.
     */
    public CartaInglesa verCarta() {

        CartaInglesa regresar = null;

        //si la pila de cartas no esta vacia, entonces nos permite ver la ultima del tope
        if (!cartas.pilaVacia()) {
            regresar = (CartaInglesa) cartas.getPila()[cartas.getTope()];
        }

        return regresar;
    }

    public CartaInglesa getCarta() {

        //si la pila no esta vacia,nos regresa la ultima carta
        if (!cartas.pilaVacia()) {
            return cartas.pop();
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        if (cartas.pilaVacia()) {
            stb.append("---");
        } else {
            //obtenemos la ultima carta de la pila
            CartaInglesa regresar = (CartaInglesa) cartas.getPila()[cartas.getTope()];
            regresar.makeFaceUp();
            stb.append(regresar.toString());
        }
        return stb.toString();
    }

    public boolean hayCartas() {
        //si la pila no esta vacia entonces si hay cartas
        return !cartas.pilaVacia();
    }
}

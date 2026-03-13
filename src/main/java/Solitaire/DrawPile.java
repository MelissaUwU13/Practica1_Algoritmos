package Solitaire;

import DeckOfCards.CartaInglesa;
import org.example.practica1_gui.Pila;
import java.util.ArrayList;

/**
 * Modela un mazo de cartas de solitario.
 * @author Cecilia Curlango
 * @version 2025
 */
public class DrawPile {
    private Pila<CartaInglesa> cartas;
    private int cuantasCartasSeEntregan = 3;

    public DrawPile() {
        DeckOfCards.Mazo mazo = new DeckOfCards.Mazo();
        ArrayList<CartaInglesa> lista = mazo.getCartas();

        cartas = new Pila<>();

        //vamos guardando las cartas hasta que la lista este vacia
        for(CartaInglesa c : lista){
            cartas.push(c);
        }

        setCuantasCartasSeEntregan(3);
    }

    /**
     * Establece cuantas cartas se sacan cada vez.
     * Puede ser 1 o 3 normalmente.
     * @param cuantasCartasSeEntregan
     */
    public void setCuantasCartasSeEntregan(int cuantasCartasSeEntregan) {
        this.cuantasCartasSeEntregan = cuantasCartasSeEntregan;
    }

    /**
     * Regresa la cantidad de cartas que se sacan cada vez.
     * @return cantidad de cartas que se entregan
     */
    public int getCuantasCartasSeEntregan() {
        return cuantasCartasSeEntregan;
    }

    /**
     * Retirar una cantidad de cartas. Este método se utiliza al inicio
     * de una partida para cargar las cartas de los tableaus.
     * Si se tratan de remover más cartas de las que hay,
     * se provocará un error.
     * @param cantidad de cartas que se quieren a retirar
     * @return cartas retiradas
     */
    public ArrayList<CartaInglesa> getCartas(int cantidad) {
        ArrayList<CartaInglesa> retiradas = new ArrayList<>();
        //en un for con la cantidad de cartas, usamos pop para sacarlas de la pila cartas
        for (int i = 0; i < cantidad; i++) {
            retiradas.add(cartas.pop());
        }
        return retiradas;
    }

    /**
     * Retira y entrega las cartas del monton. La cantidad que retira
     * depende de cuántas cartas quedan en el montón y serán hasta el máximo
     * que se configuró inicialmente.
     * @return Cartas retiradas.
     */
    public ArrayList<CartaInglesa> retirarCartas() {
        ArrayList<CartaInglesa> retiradas = new ArrayList<>();

        int maximoARetirar;
        //verificamos nuestro limite a entregar para poder ir sacando cartas

        if(cartas.getTope()+1 < cuantasCartasSeEntregan){
            maximoARetirar = cartas.getTope()+1;
        }
        else{
            maximoARetirar = cuantasCartasSeEntregan;
        }

        //con pop las vamos sacando
        for (int i = 0; i < maximoARetirar; i++) {
            CartaInglesa retirada = cartas.pop();
            retirada.makeFaceUp();
            retiradas.add(retirada);
        }

        return retiradas;
    }

    /**
     * Indica si aún quedan cartas para entregar.
     * @return true si hay cartas, false si no.
     */
    public boolean hayCartas() {
        //si carta vacia no es igual a 0 entonces si hay cartas
        return !cartas.pilaVacia();
    }

    public CartaInglesa verCarta() {
        CartaInglesa regresar = null;

        //si la pila no esta vacia entonces nos regresa la carta del tope
        if (!cartas.pilaVacia()) {
            regresar = (CartaInglesa) cartas.getPila()[cartas.getTope()];
        }

        return regresar;
    }

    /**
     * Agrega las cartas recibidas al monton y las voltea
     * para que no se vean las caras.
     * @param cartasAgregar cartas que se agregan
     */

    public void recargar(ArrayList<CartaInglesa> cartasAgregar) {
        //reiniciamos/vaciamos la pila
        cartas = new Pila<>();

        //agregamos las cartas que quedaron en cartas con push, de forma volteadas
        //pero necesitamos voltearlas de nuevo para que se ingresen en su orden original
        for (int i = cartasAgregar.size()-1; i >= 0; i--) {
            CartaInglesa c = cartasAgregar.get(i);
            c.makeFaceDown();
            cartas.push(c);
        }
    }

    @Override
    public String toString() {
        //si la pila esta vacia entonces no mostrar nada
        if (cartas.pilaVacia()) {
            return "-E-";
        }
        return "@";
    }
}

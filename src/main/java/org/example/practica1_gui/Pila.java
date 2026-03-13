package org.example.practica1_gui;

public class Pila<T> {
    private int tope;
    private T[] pila;

    public Pila() {
        pila = (T[]) new Object[1000];
        tope = -1;
    }

    //SETTERS
    public void setPila(T [] pila) {
        this.pila = pila;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }


    //GETTERS
    public int getTope() {
        return tope;
    }

    public Object[] getPila() {
        return pila;
    }


    public boolean pilaVacia(){
        return tope == -1; //si el tope es igual a -1, significa que no hay nada dentro
    }

    public boolean pilaLlena(){
        return tope == pila.length -1; //si tanto el tamaño del arreglo y tope son iguales, entonces esta llena
    }


    //POP Y PUSH

    public T pop() {
        T dato = null;
        if (pilaVacia()){
            System.out.println("La pila esta vacia");
        }
        else{
            dato = pila[tope];
            tope--;
        }
        return dato;
    }

    public void push(T dato){
        if(pilaLlena()){
            System.out.println("La pila esta llena, no se puede agregar nada mas");
        }
        else{
            tope++;
            pila[tope]=dato; //agregamos el dato T a nuestro arreglo
        }
    }

    //nos regresa el ultimo valor sin borrarlo
    public T peek() {
        T dato = null;
        if (pilaVacia()){
            System.out.println("La pila esta vacia");
        }
        else{
            dato = pila[tope];
        }
        return dato;
    }


}

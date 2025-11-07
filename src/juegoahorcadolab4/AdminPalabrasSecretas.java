/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

/**
 *
 * @author jerem
 */
import java.util.ArrayList;
import java.util.Random;

public class AdminPalabrasSecretas {

    private ArrayList<String> listaPalabras;

    public AdminPalabrasSecretas() {
        listaPalabras = new ArrayList<>();

        listaPalabras.add("BATMAN");
        listaPalabras.add("VOLEIBOL");
        listaPalabras.add("DANZA");
        listaPalabras.add("GIMNASIO");
        listaPalabras.add("MUSICA");
    }

    public void agregarPalabra(String palabra) throws Error.PalabraDuplicada {
        palabra = palabra.toUpperCase();
        if (listaPalabras.contains(palabra)) {
            throw new Error.PalabraDuplicada("La palabra (" + palabra + ") ya existe en la lista.");
        }
        listaPalabras.add(palabra);
        System.out.println("Palabra agregada: " + palabra);
    }

    public String obtenerPalabraAzar() throws Error.ListaVacia {
        if (listaPalabras.isEmpty()) {
            throw new Error.ListaVacia("No hay palabras disponibles para jugar.");
        }
        Random rand = new Random();
        return listaPalabras.get(rand.nextInt(listaPalabras.size()));
    }

    public void mostrarPalabras() {
        System.out.println("Palabras registradas: " + listaPalabras);
    }
    
    public ArrayList<String> getLista() {
    return new ArrayList<>(listaPalabras);
}
}





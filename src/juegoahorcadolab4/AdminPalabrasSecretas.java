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
    }

    // 🟩 Agregar palabra (verifica duplicados)
    public void agregarPalabra(String palabra) {
        palabra = palabra.toUpperCase();
        if (!listaPalabras.contains(palabra)) {
            listaPalabras.add(palabra);
            System.out.println("Palabra agregada: " + palabra);
        } else {
            System.out.println("La palabra [" + palabra + "] ya existe en la lista.");
        }
    }


    public String obtenerPalabraAzar() {
        if (listaPalabras.isEmpty()) {
            System.out.println("No hay palabras disponibles.");
            return null;
        }
        Random rand = new Random();
        return listaPalabras.get(rand.nextInt(listaPalabras.size()));
    }

    public void mostrarPalabras() {
        System.out.println("📋 Palabras registradas: " + listaPalabras);
    }
}



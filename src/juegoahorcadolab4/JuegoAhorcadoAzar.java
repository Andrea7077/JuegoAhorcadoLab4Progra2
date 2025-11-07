/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author najma
 */
public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    
    // atributos
    private final ArrayList<String> palabras;
    
    private final Random random = new Random;
    
    public JuegoAhorcadoAzar(ArrayList<String> palabrasDisponibles) {
        super();
        if (palabrasDisponibles == null || palabrasDisponibles.isEmpty()) {
            this.palabras = new ArrayList<>();
            this.palabras.add("Sombrero");
            this.palabras.add("Iugana");
            this.palabras.add("Botella");
            this.palabras.add("Computadora");
            this.palabras.add("Naturaleza");
            this.palabras.add("Erizo");
            this.palabras.add("Lago");
            this.palabras.add("Casa");
            this.palabras.add("Silla");
            this.palabras.add("Criminal");
        } else {
            this.palabras = new ArrayList<>(palabrasDisponibles);
        }
    }
    
    public JuegoAhorcadoAzar() {
        this(null);
    }
    
    public void agregarPalabra(String palabra) {
        if (palabra != null && !palabra.isBlank()) {
            this.palabras.add(palabra.trim());
        }
    }
    
    public ArrayList<String> getPalaras() {
        return new ArrayList<>(palabras);
    }
    
    public void inicializarPalabraSecreta() {
        if (palabras.isEmpty()) {
            throw new IllegalStateException("No hay palabras disponibles!");
        }
        
        int idx = random.nextInt
    }
}

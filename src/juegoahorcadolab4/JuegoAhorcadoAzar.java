/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author najma
 */
public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    
    // atributos
    private final ArrayList<String> palabras;
    private final Random random = new Random();
    
    // constructores
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
    
    // método clave 
    public void agregarPalabra(String palabra) {
        if (palabra != null && !palabra.isBlank()) {
            this.palabras.add(palabra.trim());
        }
    }
    
    // getter
    public ArrayList<String> getPalaras() {
        return new ArrayList<>(palabras);
    }
    
    // sobreescritura de los métodos abstractos
    @Override
    public void inicializarPalabraSecreta() {
        if (palabras.isEmpty()) {
            throw new IllegalStateException("No hay palabras disponibles!");
        }
        
        int idx = random.nextInt(palabras.size());
        this.palabraSecreta = palabras.get(idx).toLowerCase(Locale.ROOT);
        reiniciarJuego();
    }
    
    @Override
    public void actualizarPalabraActual(char letra) {
        char[] actual = palabraActual.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                actual[i] = letra;
            }
        }
        palabraActual = new String(actual);
    }
    
    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }
    
    @Override
    public boolean hasGanado() {
        return !palabraActual.contains("_");
    }
    
    @Override
    public void jugar() {
        inicializarPalabraSecreta();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

import java.util.ArrayList;

/**
 *
 * @author najma
 */
public abstract class JuegoAhorcadoBase implements JuegoAhorcado {
    
    // atributos 
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;
    
    // constructor
    public JuegoAhorcadoBase() {
        this.letrasUsadas = new ArrayList<>();
        this.intentos = limiteIntentos;
    }
    
    // Métodos claves
    public void reiniciarJuego() {
        this.intentos = limiteIntentos;
        this.letrasUsadas.clear();
        this.palabraActual = "";
        for (int i = 0; i < palabraSecreta.length(); i++) {
            palabraActual += "_";
        }
    }
    
    public boolean registrarLetra(char letra) {
        if (!letrasUsadas.contains(letra)) {
            letrasUsadas.add(letra);
            return true;
        }
        return false;
    }
    
    // Getters
    public String getPalabraActual() {
        return palabraActual;
    }

    public int getIntentos() {
        return intentos;
    }

    public ArrayList<Character> getLetrasUsadas() {
        return letrasUsadas;
    }
    
    public void perderIntento() {
        if (intentos > 0)
            intentos--;
    }
    
    // Métodos abstractos
    public abstract void actualizarPalabraActual(char letra);
    
    public abstract boolean verificarLetra(char letra);
    
    public abstract boolean hasGanado();
    
}

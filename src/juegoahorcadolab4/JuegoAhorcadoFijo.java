/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    public JuegoAhorcadoFijo(String palabraFija) {
        if (palabraFija == null || palabraFija.isBlank()) {
             new IllegalArgumentException("La palabra fija no puede estar vacía");
        }
        this.palabraSecreta = palabraFija.toUpperCase();
        this.limiteIntentos = 6;
        this.intentos = limiteIntentos;
        this.letrasUsadas = new ArrayList<>();
        this.figuraAhorcado = new ArrayList<>();
        inicializarPalabraActual();
    }

    private void inicializarPalabraActual() {
        palabraActual = "_".repeat(palabraSecreta.length());
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        letra = Character.toUpperCase(letra);
        StringBuilder sb = new StringBuilder(palabraActual);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                sb.setCharAt(i, letra);
            }
        }
        palabraActual = sb.toString();
    }

    @Override
    public boolean verificarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        if (letrasUsadas.contains(letra)) {
            JOptionPane.showMessageDialog(null, "Letra repetida: " + letra);
            return false;
        }

        letrasUsadas.add(letra);

        if (palabraSecreta.indexOf(letra) >= 0) {
            actualizarPalabraActual(letra);
            return true;
        } else {
            intentos--;
            return false;
        }
    }

    @Override
    public boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void inicializarPalabraSecreta() {
        inicializarPalabraActual();
        letrasUsadas.clear();
        intentos = limiteIntentos;
    }

    @Override
    public void jugar() {
        inicializarPalabraSecreta();

    }
    
        public enum ResultadoIntento { ACIERTO, FALLO, REPETIDA, INVALIDA, VICTORIA, DERROTA }

    public ResultadoIntento intentar(char entrada) {
        char letra = Character.toUpperCase(entrada);

        // Validación de entrada
        if (!Character.isLetter(letra)) return ResultadoIntento.INVALIDA;

        // Letra repetida
        if (letrasUsadas.contains(letra)) return ResultadoIntento.REPETIDA;

        // Registrar letra nueva
        letrasUsadas.add(letra);

        // Verificar si acierta
        if (palabraSecreta.indexOf(letra) >= 0) {
            actualizarPalabraActual(letra);
            if (hasGanado()) return ResultadoIntento.VICTORIA;
            return ResultadoIntento.ACIERTO;
        } else {
            intentos--;
            if (intentos == 0) return ResultadoIntento.DERROTA;
            return ResultadoIntento.FALLO;
        }
    }
    
    public String mostrarFigura() {
        String[] figura = {
            " ______",
            " |    |",
            " |    " + (limiteIntentos - intentos >= 1 ? "O" : " "),
            " |    " + (limiteIntentos - intentos >= 2 ? "|" : " "),
            " |   " + (limiteIntentos - intentos >= 3 ? "/" : " ") + (limiteIntentos - intentos >= 4 ? "\\" : " "),
            " |   " + (limiteIntentos - intentos >= 5 ? "/" : " ") + " " + (limiteIntentos - intentos >= 6 ? "\\" : " "),
            "_|_"
        };
        return String.join("\n", figura);
    }

}

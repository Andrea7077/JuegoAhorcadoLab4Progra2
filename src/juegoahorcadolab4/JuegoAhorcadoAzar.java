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
    this.palabras = new ArrayList<>();

    // Primero agregamos las palabras predeterminadas
    this.palabras.add("sombrero");
    this.palabras.add("botella");
    this.palabras.add("naturaleza");
    this.palabras.add("erizo");
    this.palabras.add("silla");
    this.palabras.add("criminal");

    if (palabrasDisponibles != null && !palabrasDisponibles.isEmpty()) {
        for (String p : palabrasDisponibles) {
            String lower = p.toLowerCase().trim();
            if (!this.palabras.contains(lower)) { // evitar duplicados
                this.palabras.add(lower);
            }
        }
    }
    }

    public JuegoAhorcadoAzar() {
        this(null);
    }

    // métodos claves
    public void agregarPalabra(String palabra) {
        if (palabra != null && !palabra.isBlank()) {
            this.palabras.add(palabra.trim());
        }

    }

    public enum ResultadoIntento {
        ACIERTO, FALLO, REPETIDA, INVALIDA, VICTORIA, DERROTA
    }

    public ResultadoIntento intentar(char entrada) {
        char letra = Character.toLowerCase(entrada);

        if (!Character.isLetter(letra)) {
            return ResultadoIntento.INVALIDA;
        }

        if (!registrarLetra(letra)) {
            return ResultadoIntento.REPETIDA;
        }

        if (verificarLetra(letra)) {
            actualizarPalabraActual(letra);
            if (hasGanado()) {
                return ResultadoIntento.VICTORIA;
            }
            return ResultadoIntento.ACIERTO;
        } else {
            perderIntento();
            if (getIntentos() == 0) {
                return ResultadoIntento.DERROTA;
            }
            return ResultadoIntento.FALLO;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

/**
 *
 * @author jerem
 */
public class Error {

    static class PalabraDuplicada extends Exception {
        public PalabraDuplicada(String mensaje) {
            super(mensaje);
        }
    }

    static class ListaVacia extends Exception {
        public ListaVacia(String mensaje) {
            super(mensaje);
        }
    }
    
}

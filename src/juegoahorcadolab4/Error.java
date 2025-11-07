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

    static class ErrorPalabraDuplicada extends Exception {
        public ErrorPalabraDuplicada(String mensaje) {
            super(mensaje);
        }
    }

    static class ErrorListaVacia extends Exception {
        public ErrorListaVacia(String mensaje) {
            super(mensaje);
        }
    }
    
}

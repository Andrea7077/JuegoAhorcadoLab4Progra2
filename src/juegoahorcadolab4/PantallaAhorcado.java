/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author andre
 */
public class PantallaAhorcado extends JFrame {

    
    protected JLabel PalabraActual;
    protected JLabel Intentos;
    protected JTextArea Ahorcado;
    protected JTextField Letra;
    protected JButton btnProbar;
    protected JButton btnJuegoFijo;
    protected JButton btnJuegoAzar;
    protected JLabel lblMensaje;
    protected JTextArea LetrasUsadas;
    
    /**
     * Constructor
     */
    public PantallaAhorcado() {
        configurarVentana();
        crearComponentes();
    }
    
    /**
     * Configura la ventana
     */
    private void configurarVentana() {
        setTitle("Juego del Ahorcado");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Crea los componentes
     */
    private void crearComponentes() {
        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titulo = new JLabel("AHORCADO");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        
        // Botones de modo
        JPanel panelBotones = new JPanel();
        btnJuegoFijo = new JButton("Palabra Fija");
        btnJuegoAzar = new JButton("Palabra Aleatoria");
        panelBotones.add(btnJuegoFijo);
        panelBotones.add(btnJuegoAzar);
        panel.add(panelBotones);
        panel.add(Box.createVerticalStrut(20));
        
        // Figura del ahorcado
        Ahorcado = new JTextArea(8, 20);
        Ahorcado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        Ahorcado.setEditable(false);
        Ahorcado.setText(getFiguraInicial());
        JScrollPane scrollFigura = new JScrollPane(Ahorcado);
        panel.add(scrollFigura);
        panel.add(Box.createVerticalStrut(10));
        
        // Palabra actual
        PalabraActual = new JLabel("_ _ _ _ _");
        PalabraActual.setFont(new Font("Arial", Font.BOLD, 28));
        PalabraActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(PalabraActual);
        panel.add(Box.createVerticalStrut(10));
        
        // Intentos
        Intentos = new JLabel("Intentos: 6");
        Intentos.setFont(new Font("Arial", Font.PLAIN, 16));
        Intentos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Intentos);
        panel.add(Box.createVerticalStrut(10));
        
        // Letras usadas
        JLabel lblLetras = new JLabel("Letras usadas:");
        lblLetras.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblLetras);
        
        LetrasUsadas = new JTextArea(2, 20);
        LetrasUsadas.setEditable(false);
        LetrasUsadas.setLineWrap(true);
        JScrollPane scrollLetras = new JScrollPane(LetrasUsadas);
        panel.add(scrollLetras);
        panel.add(Box.createVerticalStrut(15));
        
        // Panel de entrada
        JPanel panelEntrada = new JPanel();
        panelEntrada.add(new JLabel("Letra:"));
        Letra = new JTextField(3);
        Letra.setFont(new Font("Arial", Font.BOLD, 20));
        panelEntrada.add(Letra);
        btnProbar = new JButton("Probar");
        panelEntrada.add(btnProbar);
        panel.add(panelEntrada);
        panel.add(Box.createVerticalStrut(10));
        
        // Mensaje
        lblMensaje = new JLabel("Seleccione un modo de juego");
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblMensaje);
        
        add(panel);
    }
    
    /**
     * Figura inicial vacía
     */
    private String getFiguraInicial() {
        return """
               
                  ______
                  |    |
                  |     
                  |     
                  |     
                 _|_
                """;
    }
    
}

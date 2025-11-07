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
//Diseno
    protected JLabel PalabraActual;
    protected JLabel Intentos;
    protected JTextArea Ahorcado;
    protected JTextField Letra;
    protected JButton btnProbar;
    protected JButton btnJuegoFijo;
    protected JButton btnJuegoAzar;
    protected JLabel lblMensaje;
    protected JTextArea LetrasUsadas;

    public PantallaAhorcado() {
        setTitle("Juego del Ahorcado");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#f4f4f4"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título
        JLabel titulo = new JLabel("AHORCADO");
        titulo.setFont(new Font("Verdana", Font.BOLD, 28));
        titulo.setForeground(Color.decode("#333"));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));

        // Botones de modo
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.decode("#f4f4f4"));
        btnJuegoFijo = crearBoton("Palabra Fija", "#ff9800");
        btnJuegoAzar = crearBoton("Palabra Aleatoria", "#4caf50");
        panelBotones.add(btnJuegoFijo);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnJuegoAzar);
        panel.add(panelBotones);
        panel.add(Box.createVerticalStrut(15));

        // Figura del ahorcado
        Ahorcado = new JTextArea(7, 20);
        Ahorcado.setFont(new Font("Monospaced", Font.PLAIN, 16));
        Ahorcado.setEditable(false);
        Ahorcado.setText(getFiguraInicial());
        Ahorcado.setBackground(Color.decode("#e0e0e0"));
        Ahorcado.setBorder(BorderFactory.createLineBorder(Color.decode("#333")));
        panel.add(Ahorcado);
        panel.add(Box.createVerticalStrut(10));

        // Palabra actual
        PalabraActual = new JLabel("_ _ _ _ _");
        PalabraActual.setFont(new Font("Verdana", Font.BOLD, 26));
        PalabraActual.setForeground(Color.decode("#333"));
        PalabraActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(PalabraActual);
        panel.add(Box.createVerticalStrut(10));

        // Intentos
        Intentos = new JLabel("Intentos: 6");
        Intentos.setFont(new Font("Verdana", Font.PLAIN, 16));
        Intentos.setForeground(Color.decode("#333"));
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
        LetrasUsadas.setWrapStyleWord(true);
        LetrasUsadas.setBackground(Color.decode("#e0e0e0"));
        LetrasUsadas.setBorder(BorderFactory.createLineBorder(Color.decode("#333")));
        panel.add(LetrasUsadas);
        panel.add(Box.createVerticalStrut(15));

        // Panel de entrada
        JPanel panelEntrada = new JPanel();
        panelEntrada.setBackground(Color.decode("#f4f4f4"));
        panelEntrada.add(new JLabel("Letra:"));
        Letra = new JTextField(3);
        Letra.setFont(new Font("Verdana", Font.BOLD, 20));
        panelEntrada.add(Letra);
        btnProbar = crearBoton("Probar", "#2196f3");
        panelEntrada.add(btnProbar);
        panel.add(panelEntrada);
        panel.add(Box.createVerticalStrut(10));

        // Mensaje
        lblMensaje = new JLabel("Seleccione un modo de juego");
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMensaje.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblMensaje.setForeground(Color.decode("#555"));
        panel.add(lblMensaje);

        add(panel);
    }

    private JButton crearBoton(String texto, String colorHex) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.decode(colorHex));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Verdana", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(150, 35));
        return btn;
    }

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

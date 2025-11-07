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

    protected JLabel lblPalabraActual;
    protected JLabel lblIntentos;
    protected JTextArea txtAhorcado;
    protected JTextField txtLetra;
    protected JButton btnProbar;
    protected JButton btnFijo;
    protected JButton btnAzar;
    protected JLabel lblMensaje;
    protected JTextArea txtLetrasUsadas;

    public PantallaAhorcado() {
        setTitle("Juego del Ahorcado");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal compacto y centrado
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#f0fff0")); // fondo verde muy suave
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título
        JLabel titulo = new JLabel("AHORCADO");
        titulo.setFont(new Font("Verdana", Font.BOLD, 28));
        titulo.setForeground(Color.decode("#2e7d32")); // verde más fuerte
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));

        // Botones de modo de juego
        JPanel panelModos = new JPanel();
        panelModos.setBackground(Color.decode("#f0fff0"));
        btnFijo = crearBoton("Palabra Fija", "#2e7d32");
        btnAzar = crearBoton("Palabra Aleatoria", "#2e7d32");
        panelModos.add(btnFijo);
        panelModos.add(Box.createHorizontalStrut(15));
        panelModos.add(btnAzar);
        panel.add(panelModos);
        panel.add(Box.createVerticalStrut(15));

        // Figura del ahorcado
        txtAhorcado = new JTextArea(7, 20);
        txtAhorcado.setFont(new Font("Monospaced", Font.PLAIN, 16));
        txtAhorcado.setEditable(false);
        txtAhorcado.setText(getFiguraInicial());
        txtAhorcado.setBackground(Color.decode("#e8f5e9"));
        txtAhorcado.setBorder(BorderFactory.createLineBorder(Color.decode("#2e7d32")));
        txtAhorcado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtAhorcado);
        panel.add(Box.createVerticalStrut(10));

        // Palabra actual
        lblPalabraActual = new JLabel("_ _ _ _ _");
        lblPalabraActual.setFont(new Font("Verdana", Font.BOLD, 26));
        lblPalabraActual.setForeground(Color.decode("#2e7d32"));
        lblPalabraActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPalabraActual);
        panel.add(Box.createVerticalStrut(10));

        // Intentos restantes
        lblIntentos = new JLabel("Intentos: 6");
        lblIntentos.setFont(new Font("Verdana", Font.PLAIN, 16));
        lblIntentos.setForeground(Color.decode("#2e7d32"));
        lblIntentos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblIntentos);
        panel.add(Box.createVerticalStrut(10));

        // Letras usadas
        JLabel lblLetras = new JLabel("Letras usadas:");
        lblLetras.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblLetras);

        txtLetrasUsadas = new JTextArea(2, 20);
        txtLetrasUsadas.setEditable(false);
        txtLetrasUsadas.setLineWrap(true);
        txtLetrasUsadas.setWrapStyleWord(true);
        txtLetrasUsadas.setBackground(Color.decode("#e8f5e9"));
        txtLetrasUsadas.setBorder(BorderFactory.createLineBorder(Color.decode("#2e7d32")));
        txtLetrasUsadas.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtLetrasUsadas);
        panel.add(Box.createVerticalStrut(15));

        // Panel de entrada para letra
        JPanel panelEntrada = new JPanel();
        panelEntrada.setBackground(Color.decode("#f0fff0"));
        panelEntrada.add(new JLabel("Letra:"));
        txtLetra = new JTextField(3);
        txtLetra.setFont(new Font("Verdana", Font.BOLD, 20));
        panelEntrada.add(txtLetra);
        btnProbar = crearBoton("Probar", "#2e7d32");
        panelEntrada.add(btnProbar);
        panelEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(panelEntrada);
        panel.add(Box.createVerticalStrut(10));

        // Mensaje de guía
        lblMensaje = new JLabel("Seleccione un modo de juego");
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMensaje.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblMensaje.setForeground(Color.decode("#388e3c"));
        panel.add(lblMensaje);

        add(panel);
    }

    private JButton crearBoton(String texto, String colorHex) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.decode(colorHex));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Verdana", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(160, 40));
        btn.setBorder(BorderFactory.createLineBorder(Color.decode("#1b5e20"), 2));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorderPainted(true);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
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

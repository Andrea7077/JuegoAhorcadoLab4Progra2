/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoahorcadolab4;

/**
 *
 * @author najma
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Juego extends JFrame {

    private AdminPalabrasSecretas adminPalabras = new AdminPalabrasSecretas();
    // ----- Lógica -----
    private JuegoAhorcadoBase juego; // Azar o Fijo (polimórfico)
    private final int LIMITE_DEFECTO = 6;       // si tu base expone getLimiteIntentos(), usaremos ese valor

    // ----- Diseño (tus componentes) -----
    protected JLabel lblPalabraActual;
    protected JLabel lblIntentos;
    protected JTextArea txtAhorcado;
    protected JTextField txtLetra;
    protected JButton btnProbar;
    protected JButton btnFijo;
    protected JButton btnAzar;
    protected JLabel lblMensaje;
    protected JTextArea txtLetrasUsadas;
    protected JButton btnAgregarPalabra;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Juego ui = new Juego();
            ui.setVisible(true);
        });
    }

    public Juego() {
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
        JLabel lblAgregar = new JLabel("Agregar palabras aleatorias:");
       
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
        btnAgregarPalabra = crearBoton("Agregar Palabra", "#388e3c"); // color diferente
        panelModos.add(Box.createHorizontalStrut(15));
        panelModos.add(btnAgregarPalabra);

        // Figura del ahorcado
        txtAhorcado = new JTextArea(7, 20);
        txtAhorcado.setFont(new Font("Monospaced", Font.PLAIN, 16));
        txtAhorcado.setEditable(false);
        txtAhorcado.setText(getFigura(0));
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

        // === Eventos ===
        btnAzar.addActionListener(this::iniciarModoAzar);
        btnFijo.addActionListener(this::iniciarModoFijo);
        btnProbar.addActionListener(this::probarLetra);
        txtLetra.addActionListener(this::probarLetra); // Enter
        btnAgregarPalabra.addActionListener(e -> {
            String nueva = JOptionPane.showInputDialog(this, "Ingrese nueva palabra:");
            if (nueva == null || nueva.isBlank()) {
                return;
            }

            try {
                adminPalabras.agregarPalabra(nueva);
                lblMensaje.setText("Palabra agregada: " + nueva.toUpperCase());
            } catch (Error.PalabraDuplicada ex) {
                lblMensaje.setText(ex.getMessage());
            }
        });
        // Estado inicial
        habilitarEntrada(false);
    }

    // ===================== Eventos =====================
    private void iniciarModoAzar(ActionEvent e) {
        try {
        ArrayList<String> lista = adminPalabras.getLista(); 
        if (lista.isEmpty()) {
            juego = new JuegoAhorcadoAzar();
        } else {
            juego = new JuegoAhorcadoAzar(lista);
        }
        
        juego.inicializarPalabraSecreta();
        lblMensaje.setText("Modo: Aleatorio. ¡A jugar!");
        resetYRefrescar();
        habilitarEntrada(true);
    } catch (Exception ex) {
        lblMensaje.setText("Error: " + ex.getMessage());
    }
    }

    private void iniciarModoFijo(ActionEvent e) {
        try {
            String palabra = JOptionPane.showInputDialog(this, "Ingrese la palabra fija:", "Palabra Fija", JOptionPane.QUESTION_MESSAGE);
            if (palabra == null || palabra.isBlank()) {
                lblMensaje.setText("Debe ingresar una palabra válida.");
                return;
            }
            juego = new JuegoAhorcadoFijo(palabra);
            juego.inicializarPalabraSecreta();

            lblMensaje.setText("Modo: Fijo. ¡A jugar!");
            resetYRefrescar();
            habilitarEntrada(true);
        } catch (Exception ex) {
            lblMensaje.setText("Error: " + ex.getMessage());
        }
    }

    private void probarLetra(ActionEvent e) {
        if (juego == null) {
            lblMensaje.setText("Primero elija un modo (Fijo o Aleatorio).");
            return;
        }

        String in = txtLetra.getText().trim().toLowerCase();
        txtLetra.setText("");
        if (in.isEmpty()) {
            return;
        }

        char c = in.charAt(0);
        if (!Character.isLetter(c)) {
            lblMensaje.setText("Solo letras A-Z.");
            return;
        }

        // Llamamos a intentar(...) y lo convertimos a String (funciona con ambos enums)
        String res = intentarComoString(c);

        switch (res) {
            case "REPETIDA" ->
                lblMensaje.setText("Letra repetida.");
            case "INVALIDA" ->
                lblMensaje.setText("Entrada inválida.");
            case "ACIERTO" ->
                lblMensaje.setText("¡Correcto!");
            case "FALLO" ->
                lblMensaje.setText("Incorrecto.");
            case "VICTORIA" -> {
                // Mensaje solo si ganó por acierto (palabra sin guiones)
                if (!juego.getPalabraActual().contains("_")) {
                    lblMensaje.setText("🎉 ¡FELICITACIONES! Has adivinado la palabra: "
                            + juego.getPalabraSecreta().toUpperCase());
                }
                habilitarEntrada(false);
            }
            case "DERROTA" -> {
                // Solo si se acabaron los intentos
                if (juego.getIntentos() == 0) {
                    // Nota: se utiliza exactamente el texto solicitado por ti
                    lblMensaje.setText("Lo sentimos. A perdido");
                }
                habilitarEntrada(false);
            }
            default -> {
            }
        }

        refrescarVista();
    }

    // ===================== Lógica de apoyo =====================
    private void resetYRefrescar() {
        // Palabra, intentos, letras usadas, figura
        refrescarVista();
        txtAhorcado.setText(getFigura(0));
    }

    private void refrescarVista() {
        // Palabra con espacios
        String p = (juego != null) ? juego.getPalabraActual() : "";
        lblPalabraActual.setText(formatoEspaciado(p));

        // Intentos
        int intentos = (juego != null) ? juego.getIntentos() : LIMITE_DEFECTO;
        lblIntentos.setText("Intentos: " + intentos);

        // Letras usadas
        txtLetrasUsadas.setText((juego != null) ? juego.getLetrasUsadas().toString() : "");

        // Figura según fallos
        int fallos = calcularFallos();
        txtAhorcado.setText(getFigura(fallos));
    }

    private void habilitarEntrada(boolean on) {
        txtLetra.setEnabled(on);
        btnProbar.setEnabled(on);
        if (on) {
            txtLetra.requestFocusInWindow();
        }
    }

    private String intentarComoString(char letra) {
        try {
            if (juego instanceof JuegoAhorcadoAzar azar) {
                Object r = azar.intentar(letra);
                return ((Enum<?>) r).name();
            } else if (juego instanceof JuegoAhorcadoFijo fijo) {
                Object r = fijo.intentar(letra);
                return ((Enum<?>) r).name();
            } else {
                throw new IllegalStateException("Tipo de juego no soportado.");
            }
        } catch (Exception ex) {
            lblMensaje.setText("Error al intentar: " + ex.getMessage());
            return "INVALIDA";
        }
    }

    /**
     * Calcula fallos como (límite - intentos). Si tu base expone
     * getLimiteIntentos(), úsalo.
     */
    private int calcularFallos() {
        int limite = LIMITE_DEFECTO;
        try {
            // Si tu JuegoAhorcadoBase tiene getLimiteIntentos(), úsalo:
            limite = (int) JuegoAhorcadoBase.class
                    .getMethod("getLimiteIntentos")
                    .invoke(juego);
        } catch (Throwable ignored) {
        }
        int intentosRestantes = (juego != null) ? juego.getIntentos() : limite;
        return Math.max(0, limite - intentosRestantes);
    }

    private String formatoEspaciado(String palabra) {
        if (palabra == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : palabra.toCharArray()) {
            sb.append(c).append(' ');
        }
        return sb.toString().trim();
    }

    // ===================== Estilo y utilitarios visuales =====================
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

    /**
     * Figura ASCII por número de fallos (0 a 6).
     */
    private String getFigura(int fallos) {
        return switch (Math.max(0, Math.min(6, fallos))) {
            case 0 ->
                """
                       
                       ______
                       |    |
                       |     
                       |     
                       |     
                      _|_
                    """;
            case 1 ->
                """
                       
                       ______
                       |    |
                       |    O
                       |     
                       |     
                      _|_
                    """;
            case 2 ->
                """
                       
                       ______
                       |    |
                       |    O
                       |    |
                       |     
                      _|_
                    """;
            case 3 ->
                """
                       
                       ______
                       |    |
                       |    O
                       |   /|
                       |     
                      _|_
                    """;
            case 4 ->
                """
                       
                       ______
                       |    |
                       |    O
                       |   /|\\
                       |     
                      _|_
                    """;
            case 5 ->
                """
                       
                       ______
                       |    |
                       |    O
                       |   /|\\
                       |   / 
                      _|_
                    """;
            default ->
                """
                       
                       ______
                       |    |
                       |    O
                       |   /|\\
                       |   / \\
                      _|_
                    """;
        };
    }
}

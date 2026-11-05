// Archivo: src/presentacion/PresentacionGUI.java
package presentacion;

import logica.LogicaEnrutamiento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentacionGUI extends JFrame {
    
    private LogicaEnrutamiento logica; // Referencia a la capa inferior

    // Componentes gráficos
    private JCheckBox chkAlarma;
    private JCheckBox chkS2, chkS1, chkS0;
    private JButton btnProcesar;
    private JTextArea txtResultado;

    public PresentacionGUI(LogicaEnrutamiento logica) {
        this.logica = logica;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Enrutamiento de Evacuación (74HC138)");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        // --- Panel Superior: Alarma General ---
        JPanel pnlAlarma = new JPanel();
        chkAlarma = new JCheckBox("Alarma General Activada (Enable G1)");
        chkAlarma.setFont(new Font("Arial", Font.BOLD, 14));
        chkAlarma.setForeground(Color.RED);
        pnlAlarma.add(chkAlarma);

        // --- Panel Central: Red de Sensores (Simula DIP Switch) ---
        JPanel pnlSensores = new JPanel(new GridLayout(3, 1));
        pnlSensores.setBorder(BorderFactory.createTitledBorder("Red de Sensores (DIP Switch S2, S1, S0)"));
        chkS2 = new JCheckBox("Sensor C (S2) - Valor 4");
        chkS1 = new JCheckBox("Sensor B (S1) - Valor 2");
        chkS0 = new JCheckBox("Sensor A (S0) - Valor 1");
        
        pnlSensores.add(chkS2);
        pnlSensores.add(chkS1);
        pnlSensores.add(chkS0);

        // --- Panel Inferior: Botón y Consola de Salida ---
        JPanel pnlInferior = new JPanel(new BorderLayout());
        btnProcesar = new JButton("Calcular Enrutamiento (Simular Demux)");
        btnProcesar.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Simulación de un panel LED para el resultado
        txtResultado = new JTextArea(4, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.BOLD, 14));
        txtResultado.setBackground(Color.BLACK);
        txtResultado.setForeground(Color.GREEN);
        
        pnlInferior.add(btnProcesar, BorderLayout.NORTH);
        pnlInferior.add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        // Acción al hacer clic en el botón
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarDatos();
            }
        });

        // Ensamblar todo en la ventana principal
        add(pnlAlarma, BorderLayout.NORTH);
        add(pnlSensores, BorderLayout.CENTER);
        add(pnlInferior, BorderLayout.SOUTH);
    }

    // Método que traduce los clics a la capa de Lógica
    private void procesarDatos() {
        boolean estadoAlarma = chkAlarma.isSelected();
        // Operador ternario: si está seleccionado es 1, si no es 0
        int s2 = chkS2.isSelected() ? 1 : 0;
        int s1 = chkS1.isSelected() ? 1 : 0;
        int s0 = chkS0.isSelected() ? 1 : 0;

        try {
            // Le pedimos a la capa LÓGICA que haga el trabajo
            String resultado = logica.procesarEvacuacion(estadoAlarma, s2, s1, s0);
            txtResultado.setText("\n  >>> " + resultado + "\n");
        } catch (Exception ex) {
            txtResultado.setText("\n  >>> ERROR: " + ex.getMessage() + "\n");
        }
    }

    public void iniciar() {
        setVisible(true); // Hace visible la ventana
    }
}
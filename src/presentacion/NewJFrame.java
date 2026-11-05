/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

/**
 *
 * @author sergio
 */

import java.awt.Color;
import java.time.LocalDateTime;
import logica.LogicaEnrutamiento;

public class NewJFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame.class.getName());

    /**
     * Creates new form NewJFrame
     */
    
    private LogicaEnrutamiento logica;
    
    public NewJFrame() {
        initComponents();
        logica = new LogicaEnrutamiento();
    }

    private void procesarCambios() {
        boolean estadoAlarma = enable.isSelected();
        
        // Convertimos los booleanos de los checkboxes a enteros (0 o 1)
        int s0 = alarmaPiso1.isSelected() ? 1 : 0;
        int s1 = alarmaPiso2.isSelected() ? 1 : 0; // Ojo: en tu GUI alarmaPiso3 dice "Piso 2"
        int s2 = alarmaPiso3.isSelected() ? 1 : 0; // Ojo: en tu GUI alarmaPiso2 dice "Piso 3"

        try {
            // 1. Enviamos los datos a la capa lógica y obtenemos la decisión
            String resultado = logica.procesarEvacuacion(estadoAlarma, s2, s1, s0);
            
            // 2. Actualizamos el JLabel en color ROJO
            salidaEmergencia.setText(resultado);
            salidaEmergencia.setForeground(Color.RED);
            
            // 3. Creamos un registro con la hora exacta para la persistencia visual
            LocalDateTime ahora = LocalDateTime.now();
            String hora = String.format("%02d:%02d:%02d", ahora.getHour(), ahora.getMinute(), ahora.getSecond());
            
            String registroLog = String.format("[%s] Alarma: %b | Sensores: %d%d%d -> %s\n", 
                                                hora, estadoAlarma, s2, s1, s0, resultado);
            
            // 4. Usamos .append() en lugar de .setText() para conservar el historial
            jTextArea1.append(registroLog);

        } catch (IllegalArgumentException ex) {
            // Manejo visual si hay un error en los sensores
            salidaEmergencia.setText("ERROR CRÍTICO EN SENSORES");
            salidaEmergencia.setForeground(Color.RED);
            
            jTextArea1.append("[ALERTA] " + ex.getMessage() + "\n");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        enable = new javax.swing.JCheckBox();
        alarmaPiso1 = new javax.swing.JCheckBox();
        alarmaPiso3 = new javax.swing.JCheckBox();
        alarmaPiso2 = new javax.swing.JCheckBox();
        persistencia = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        salidaEmergencia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Titulo.setText("Evaluacion de mejor Ruta de Evacuacion");

        enable.setText("Activar Alarma");
        enable.addActionListener(this::enableActionPerformed);

        alarmaPiso1.setText("Piso 1");
        alarmaPiso1.addActionListener(this::alarmaPiso1ActionPerformed);

        alarmaPiso3.setText("Piso 3");
        alarmaPiso3.addActionListener(this::alarmaPiso3ActionPerformed);

        alarmaPiso2.setText("Piso 2");
        alarmaPiso2.addActionListener(this::alarmaPiso2ActionPerformed);

        persistencia.setEnabled(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        persistencia.setViewportView(jTextArea1);

        salidaEmergencia.setText("hola");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enable)
                        .addGap(131, 131, 131)
                        .addComponent(salidaEmergencia))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(alarmaPiso1)
                        .addGap(55, 55, 55)
                        .addComponent(alarmaPiso2)
                        .addGap(48, 48, 48)
                        .addComponent(alarmaPiso3)))
                .addGap(424, 424, 424))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(Titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(persistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enable)
                    .addComponent(salidaEmergencia))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alarmaPiso1)
                    .addComponent(alarmaPiso3)
                    .addComponent(alarmaPiso2))
                .addGap(35, 35, 35)
                .addComponent(persistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableActionPerformed
        // TODO add your handling code here:
        procesarCambios();
    }//GEN-LAST:event_enableActionPerformed

    private void alarmaPiso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alarmaPiso1ActionPerformed
        // TODO add your handling code here:
        procesarCambios();
    }//GEN-LAST:event_alarmaPiso1ActionPerformed

    private void alarmaPiso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alarmaPiso3ActionPerformed
        // TODO add your handling code here:
        procesarCambios();
    }//GEN-LAST:event_alarmaPiso3ActionPerformed

    private void alarmaPiso2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alarmaPiso2ActionPerformed
        // TODO add your handling code here:
        procesarCambios();
    }//GEN-LAST:event_alarmaPiso2ActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titulo;
    private javax.swing.JCheckBox alarmaPiso1;
    private javax.swing.JCheckBox alarmaPiso2;
    private javax.swing.JCheckBox alarmaPiso3;
    private javax.swing.JCheckBox enable;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane persistencia;
    private javax.swing.JLabel salidaEmergencia;
    // End of variables declaration//GEN-END:variables
}

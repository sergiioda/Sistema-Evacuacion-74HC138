// Archivo: src/logica/LogicaEnrutamiento.java
package logica;

import persistencia.PersistenciaEvacuacion;
import presentacion.PresentacionConsola;
import presentacion.PresentacionGUI;
import presentacion.NewJFrame;

public class LogicaEnrutamiento {

    private PersistenciaEvacuacion persistencia;

    public LogicaEnrutamiento() {
        this.persistencia = new PersistenciaEvacuacion();
    }

    // --- LÓGICA MATEMÁTICA Y DE NEGOCIO ---
    public String procesarEvacuacion(boolean estadoAlarma, int s2, int s1, int s0) throws IllegalArgumentException {
        if (!estadoAlarma) {
            return "Sistema en reposo. No se requiere evacuación.";
        }

        if ((s2 != 0 && s2 != 1) || (s1 != 0 && s1 != 1) || (s0 != 0 && s0 != 1)) {
            throw new IllegalArgumentException("ERROR CRÍTICO DE HARDWARE: Lectura inválida en sensores.");
        }

        int rutaSegura = (s2 * 4) + (s1 * 2) + (s0 * 1);
        String rutaSeleccionada = determinarRuta(rutaSegura);

        persistencia.guardarRegistro(estadoAlarma, s2, s1, s0, rutaSeleccionada);
        return "Activando " + rutaSeleccionada;
    }

    private String determinarRuta(int indiceRuta) {
        switch (indiceRuta) {
            case 0:
                return "LED Y0: Ruta Segura Norte";
            case 1:
                return "LED Y1: Ruta Segura Sur";
            case 2:
                return "LED Y2: Ruta Segura Este";
            case 3:
                return "LED Y3: Ruta Segura Oeste";
            case 4:
                return "LED Y4: Escaleras de Emergencia A";
            case 5:
                return "LED Y5: Escaleras de Emergencia B";
            case 6:
                return "LED Y6: Salida Principal Frontal";
            case 7:
                return "LED Y7: Salida Alterna Trasera";
            default:
                return "Ruta Desconocida";
        }
    }

    // --- PUNTO DE ARRANQUE DEL SISTEMA (MAIN) ---
    public static void main(String[] args) {
       /* 
         * Usamos EventQueue.invokeLater porque es la forma correcta,
         * estable y segura de arrancar ventanas gráficas en Java Swing.
         * NetBeans recomienda arrancar sus JFrames siempre de esta manera.
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Instanciamos el JFrame generado por NetBeans
                NewJFrame ventana = new NewJFrame();
                
                // Lo hacemos visible en pantalla
                ventana.setVisible(true);
            }
        });
    }
}

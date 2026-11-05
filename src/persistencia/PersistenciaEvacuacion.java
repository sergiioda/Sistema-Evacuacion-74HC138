// Archivo: src/persistencia/PersistenciaEvacuacion.java
package persistencia;

import java.time.LocalDateTime;

public class PersistenciaEvacuacion {
    
    public void guardarRegistro(boolean estadoAlarma, int s2, int s1, int s0, String rutaAsignada) {
        String fechaHora = LocalDateTime.now().toString();
        String log = String.format("[%s] EVENTO GUARDADO - Alarma: %b | Sensores: %d%d%d | Decisión: %s", 
                                   fechaHora, estadoAlarma, s2, s1, s0, rutaAsignada);
        
        System.out.println("\n--- [CAPA PERSISTENCIA] ---");
        System.out.println(log);
        System.out.println("---------------------------\n");
    }
}
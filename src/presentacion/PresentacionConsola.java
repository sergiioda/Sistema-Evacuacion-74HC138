// Archivo: src/presentacion/PresentacionConsola.java
package presentacion;

import logica.LogicaEnrutamiento;
import java.util.Scanner;

public class PresentacionConsola {
    
    private LogicaEnrutamiento logica;

    // Constructor: recibe la lógica ya lista para usarse
    public PresentacionConsola(LogicaEnrutamiento logica) {
        this.logica = logica;
    }

    // Método que arranca la interfaz
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA DE ENRUTAMIENTO DINÁMICO DE EVACUACIÓN ===");
        
        try {
            System.out.print("¿Alarma general activada? (true/false): ");
            boolean estadoAlarma = scanner.nextBoolean();

            int s2 = 0, s1 = 0, s0 = 0;

            if (estadoAlarma) {
                System.out.print("Ingrese estado binario del sensor S2 (0 o 1): ");
                s2 = scanner.nextInt();
                System.out.print("Ingrese estado binario del sensor S1 (0 o 1): ");
                s1 = scanner.nextInt();
                System.out.print("Ingrese estado binario del sensor S0 (0 o 1): ");
                s0 = scanner.nextInt();
            }

            System.out.println("\nCalculando enrutamiento de emergencia...");
            
            // Llama a la lógica que le inyectaron
            String resultado = logica.procesarEvacuacion(estadoAlarma, s2, s1, s0);
            System.out.println(">>> " + resultado);

        } catch (IllegalArgumentException e) {
            System.out.println("\n>>> " + e.getMessage());
            System.out.println(">>> Activando protocolo de evacuación general por falla.");
        } catch (Exception e) {
            System.out.println("\n>>> Error de entrada. Por favor ingrese datos válidos.");
        } finally {
            scanner.close();
        }
    }
}
# Sistema de Enrutamiento Dinámico de Evacuación

## 1. Descripción del Proyecto
Este proyecto integrador simula un sistema inteligente para dirigir la evacuación de un edificio en caso de emergencia. Utiliza un modelo híbrido que abarca el diseño electrónico de hardware (basado en el circuito integrado **74HC138**) y una simulación de software desarrollada en **Java** bajo una **arquitectura de 3 capas**.

El sistema lee el estado de una alarma general y de 3 sensores de zona (Pisos 1, 2 y 3) para decidir cuál es la ruta de evacuación más segura, bloqueando las salidas que representan un peligro.

---

## 2. Componente de Hardware (Lógica Digital)
El cerebro del circuito físico es el Demultiplexor/Decodificador **74HC138** (o 74LS138).

* **Entradas de Control (Sensores):** Pines 1, 2 y 3 ($A, B, C$ o $S0, S1, S2$). Se controlan mediante un DIP Switch con resistencias *pull-down* para asegurar estados lógicos de $0\text{V}$ (GND) y $5\text{V}$ (VCC).
* **Habilitación (Enable):** 
  * $G1$ (Pin 6): Activo en ALTO ($+5\text{V}$). Funciona como la **Alarma General**.
  * $G2A'$ y $G2B'$ (Pines 4 y 5): Activos en BAJO ($0\text{V}$). Van directos a tierra (GND).
* **Salidas (Rutas):** Pines de $Y0$ a $Y7$. Son **activas en bajo**, por lo que los LEDs de las rutas de evacuación se conectan con su ánodo a $+5\text{V}$ y su cátodo al integrado (mediante una resistencia limitadora de $220\,\Omega$).

---

## 3. Componente de Software (Java)
El código fuente está estructurado siguiendo las mejores prácticas de la **Arquitectura de 3 Capas (3-Tier)** para garantizar bajo acoplamiento y alta cohesión.

### Estructura de Paquetes
* `presentacion`: Contiene la interfaz gráfica de usuario (GUI) desarrollada con **Java Swing** (`JFrame`). Se encarga de capturar la interacción del usuario mediante *Checkboxes* (que simulan los interruptores del DIP Switch) y mostrar los resultados visualmente.
* `logica`: Contiene la clase `LogicaEnrutamiento.java`. Es el cerebro del software. Replica el comportamiento del demultiplexor convirtiendo la lectura binaria de los sensores a un número decimal para determinar la ruta. También contiene el método `main` (Raíz de composición).
* `persistencia`: Simula el almacenamiento de datos. En la interfaz gráfica, se evidencia mediante un área de texto que acumula un *log* o historial inmutable de cada evento del sistema (con hora exacta, estado de sensores y decisión tomada), vital para auditorías posteriores a una emergencia.

---

## 4. Tabla de Enrutamiento (Lógica de Decisión)
Cuando la **Alarma General está Activada** (Enable $G1 = 1$), el sistema lee los sensores S2 (Piso 3), S1 (Piso 2) y S0 (Piso 1) como un número binario de 3 bits:

| S2 (Piso 3) | S1 (Piso 2) | S0 (Piso 1) | Valor Decimal | Ruta de Evacuación Asignada (LED Activado) |
| :---: | :---: | :---: | :---: | :--- |
| 0 | 0 | 0 | 0 | LED Y0: Ruta Segura Norte |
| 0 | 0 | 1 | 1 | LED Y1: Ruta Segura Sur |
| 0 | 1 | 0 | 2 | LED Y2: Ruta Segura Este |
| 0 | 1 | 1 | 3 | LED Y3: Ruta Segura Oeste |
| 1 | 0 | 0 | 4 | LED Y4: Escaleras de Emergencia A |
| 1 | 0 | 1 | 5 | LED Y5: Escaleras de Emergencia B |
| 1 | 1 | 0 | 6 | LED Y6: Salida Principal Frontal |
| 1 | 1 | 1 | 7 | LED Y7: Salida Alterna Trasera |

*(Si la Alarma General está apagada, ninguna salida se activa, independientemente de los sensores).*

---

![Mapa de Rutas y Salidas de Evacuación](img/mapa_evacuacion.jpg)

## 5. Instrucciones de Ejecución (Software)
1. Abrir el proyecto en el IDE (ej. Apache NetBeans).
2. Asegurarse de que la clase principal (Main Class) esté configurada como `logica.LogicaEnrutamiento`.
3. Ejecutar el proyecto (Run).
4. En la ventana emergente, marcar la casilla **"Activar Alarma"**.
5. Alternar las casillas de los **Pisos 1, 2 y 3** para observar cómo cambia dinámicamente la ruta recomendada (marcada en rojo) y cómo se genera el historial en la caja de texto inferior.

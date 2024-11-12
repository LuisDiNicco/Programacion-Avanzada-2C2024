package algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import colaDePrioridad.*;


public class SolucionAlternativa {
    private int[][] grafo;

    public SolucionAlternativa(int[][] grafo) {
        this.grafo=grafo;
    }

    public List<Camino> encontrarCaminoNMinimo(int nodoInicio, int nodoFin, int N) {
        List<Camino> caminosMinimos = new ArrayList<>();
        ColaDePrioridad colaDePrioridad = new ColaDePrioridad();
        
        // Iniciar el primer camino con el nodo de inicio
        colaDePrioridad.insertar(new Camino(Arrays.asList(nodoInicio), 0));
        
        while (!colaDePrioridad.estaVacio() && caminosMinimos.size() < N) {
        	Camino caminoActual = colaDePrioridad.extraerMin();
            int posicionUltimoNodo=caminoActual.getNodos().size() - 1;
            int nodoActual = caminoActual.getNodos().get(posicionUltimoNodo);
            
            // Si llegamos al nodo final, guardamos el camino
            if (nodoActual == nodoFin) {
                caminosMinimos.add(caminoActual);
                continue;
            }
            
            // Explorar vecinos del nodo actual
            for (int w = 0; w < grafo.length; w++) {
                int costo = grafo[nodoActual][w];
                
                // Verificar si existe una arista válida
                if (costo > 0 && costo < Integer.MAX_VALUE) {
                    // Evitar ciclos comprobando si el nodo ya está en el camino actual
                    if (caminoActual.getNodos().contains(w)) {
                        continue;
                    }
                    
                    // Crear un nuevo camino extendido
                    List<Integer> nuevoCamino = new ArrayList<>(caminoActual.getNodos());
                    nuevoCamino.add(w);
                    //int costoNuevoCamino = caminoActual.costo + costo;
                    int costoNuevoCamino = caminoActual.getCosto() + costo;
                    
                    // Añadir el camino extendido a la cola de prioridad
                    colaDePrioridad.insertar(new Camino(nuevoCamino, costoNuevoCamino));
                }
            }
        }
        
        return caminosMinimos;
    }
}
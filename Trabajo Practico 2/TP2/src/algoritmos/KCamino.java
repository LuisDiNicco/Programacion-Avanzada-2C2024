package algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import juego.Mapa;

public class KCamino {

    private Mapa mapa;
    private int[][] grafo;
    private int cantNodos;

    public KCamino(Mapa mapa) {
        this.mapa = mapa;
        this.grafo = mapa.getGrafo();
        this.cantNodos = grafo.length;
    }

    public List<int[]> kEsimoMejorCamino(int nodoOrigen, int nodoDestino, int k) {
        // Cola para almacenar los caminos en orden de costo
        PriorityQueue<Camino> colaDeCaminos = new PriorityQueue<>(new Comparator<Camino>() {
            @Override
            public int compare(Camino camino1, Camino camino2) {
                return Integer.compare(camino1.costo, camino2.costo);
            }
        });
        
        // Set para guardar los caminos encontrados y evitar duplicados
        Set<String> caminosEncontradosSet = new HashSet<>();
        // Lista para devolver los caminos encontrados en formato de arreglo
        List<int[]> caminosEncontrados = new ArrayList<>();
        
        // Mapa para almacenar caminos desde un nodo
        Map<Integer, List<Camino>> caminosCache = new HashMap<>();

        // Agrega el camino inicial a la cola
        int[] caminoInicial = new int[1];
        caminoInicial[0] = nodoOrigen;
        colaDeCaminos.add(new Camino(0, caminoInicial));

        // Mientras haya caminos en la cola
        while (!colaDeCaminos.isEmpty()) {
            Camino caminoActual = colaDeCaminos.poll();
            int nodoActual = caminoActual.camino[caminoActual.camino.length - 1];

            // Si llegamos al nodo destino, guardamos el camino
            if (nodoActual == nodoDestino) {
                // Convertir el camino actual a un String para usar en el Set
                String caminoString = Arrays.toString(caminoActual.camino);
                if (!caminosEncontradosSet.contains(caminoString)) {
                    caminosEncontradosSet.add(caminoString); // Agregar al Set
                    caminosEncontrados.add(caminoActual.camino.clone()); // Guarda una copia del camino
                    if (caminosEncontrados.size() == k) {
                        return caminosEncontrados; // Retorna los k caminos encontrados
                    }
                }
            }

            // Manejo de caminos en el cache
            if (caminosCache.containsKey(nodoActual)) {
                List<Camino> caminosDesdeActual = caminosCache.get(nodoActual);
                for (int i = 0; i < caminosDesdeActual.size(); i++) {
                    Camino caminoCacheado = caminosDesdeActual.get(i);
                    int nuevoCosto = caminoActual.costo + caminoCacheado.costo;

                    // Crear un nuevo camino combinando el camino actual y el camino cacheado
                    int[] nuevoCamino = new int[caminoActual.camino.length + caminoCacheado.camino.length - 1];
                    for (int j = 0; j < caminoActual.camino.length; j++) {
                        nuevoCamino[j] = caminoActual.camino[j];
                    }
                    for (int j = 1; j < caminoCacheado.camino.length; j++) {
                        nuevoCamino[caminoActual.camino.length + j - 1] = caminoCacheado.camino[j];
                    }
                    
                    // Verificar si hay nodos repetidos en el nuevo camino
                    if (!hayNodosRepetidos(nuevoCamino)) {
                        // Agregar el nuevo camino a la cola
                        colaDeCaminos.add(new Camino(nuevoCosto, nuevoCamino));
                    }
                }
            }

            // Explorar los nodos adyacentes
            for (int i = 0; i < cantNodos; i++) {
                if (grafo[nodoActual][i] != Integer.MAX_VALUE && !yaVisitado(caminoActual.camino, i)) {
                    int nuevoCosto = caminoActual.costo + grafo[nodoActual][i];

                    // Crear un nuevo camino con el nodo adyacente
                    int[] nuevoCamino = new int[caminoActual.camino.length + 1];
                    for (int j = 0; j < caminoActual.camino.length; j++) {
                        nuevoCamino[j] = caminoActual.camino[j];
                    }
                    nuevoCamino[nuevoCamino.length - 1] = i;

                    // Verificar si hay nodos repetidos en el nuevo camino
                    if (!hayNodosRepetidos(nuevoCamino)) {
                        // Agregar el nuevo camino a la cola
                        colaDeCaminos.add(new Camino(nuevoCosto, nuevoCamino));
                    }
                }
            }

            // Almacenar en cache los caminos explorados desde este nodo al destino
            if (nodoActual == nodoDestino) {
                if (!caminosCache.containsKey(nodoActual)) {
                    caminosCache.put(nodoActual, new ArrayList<>());
                }
                caminosCache.get(nodoActual).add(caminoActual);
            }
        }

        return new ArrayList<>(); // Retorna una lista vacía si no se encuentra el K-ésimo camino
    }

    // Verifica si un nodo ya fue visitado en el camino
    private boolean yaVisitado(int[] camino, int nodo) {
        for (int n : camino) {
            if (n == nodo) {
                return true;
            }
        }
        return false;
    }

    // Clase interna para representar un camino y su costo
    private static class Camino {
        int costo;
        int[] camino;

        Camino(int costo, int[] camino) {
            this.costo = costo;
            this.camino = camino;
        }
    }
    
    private boolean hayNodosRepetidos(int[] camino) {
        Set<Integer> nodosVisitados = new HashSet<>();
        for (int nodo : camino) {
            if (nodosVisitados.contains(nodo)) {
                return true; // Hay un nodo repetido
            }
            nodosVisitados.add(nodo);
        }
        return false; // No hay nodos repetidos
    }
}
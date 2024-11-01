package algoritmos;

import java.util.HashSet;
import java.util.Set;

import juego.Mapa;

public class Dijkstra {

	private Mapa mapa;
	private int[][] grafo;
	private int[] predecesores;
	private int[] distancia;
	private Set<Integer> adyacentes;
	private int nodoOrigen;
	private int cantNodos;

	public Dijkstra(Mapa mapa, int nodoOrigen) {
		this.mapa = mapa;
		this.grafo = mapa.getGrafo();
		this.cantNodos = grafo.length;
		this.distancia = new int[cantNodos];
		this.predecesores = new int[cantNodos];
		this.adyacentes = new HashSet<>();
		this.nodoOrigen = nodoOrigen-1;
	}

	// ---------------Metodos-------------------//
	
	private int minimo(boolean[] visitado) {
	    int min = Integer.MAX_VALUE;
	    int pos = -1;
	    for (int i = 0; i < cantNodos; i++) {
	        if (distancia[i] < min && !visitado[i] && adyacentes.contains(i)) {
	            min = distancia[i];
	            pos = i;
	        }
	    }
	    return pos;
	}
	
	public void calcularDistanciaDijkstra() {
	    // Inicializas vector de visitados
	    boolean[] visitado = new boolean[cantNodos];

	    // Marcas nodo origen como visitado
	    visitado[nodoOrigen] = true;
	    //System.out.println(nodoOrigen);

	    // Cargas las distancias desde el nodo de origen
	    for (int i = 0; i < cantNodos; i++) {
	        distancia[i] = grafo[nodoOrigen][i]; 
	        // Si hay una conexión directa desde el origen, establece el predecesor inicial
	        if (grafo[nodoOrigen][i] != Integer.MAX_VALUE && i != nodoOrigen) {
	            predecesores[i] = nodoOrigen;
	        }
	    }
	    
	    adyacentes.addAll(mapa.getPueblosAdyacentes(nodoOrigen));
	    //System.out.println(adyacentes);
	    int w = minimo(visitado);
	    //System.out.println(w);
	    //int NodoActual = nodoOrigen;

	    while (w != -1) {
	        visitado[w] = true;

	        for (int i = 0; i < cantNodos; i++) {
	            if (!visitado[i] && grafo[w][i] != Integer.MAX_VALUE) {
	                int nuevaDistancia = grafo[w][i] + distancia[w];
	                if (nuevaDistancia < distancia[i]) {
	                    // Actualiza la distancia y el predecesor
	                    distancia[i] = nuevaDistancia;
	                    predecesores[i] = w;
	                    //System.out.println("Predecesor de : " + i + " es " + w);
	                }
	            }
	        }

	        //NodoActual = w;
	        //System.out.println("W: " + w);
	        adyacentes.addAll(mapa.getPueblosAdyacentes(w));
	        w = minimo(visitado);
	    }
	}

	// ---------------Getters-------------------//

	public int[] getVectorDistancia() {
		return distancia;
	}

	public int[] getVectorPredecesores() {
		return predecesores;
	}

	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public int getDistancia(int otroNodo) {
		return distancia[otroNodo - 1];
	}
}
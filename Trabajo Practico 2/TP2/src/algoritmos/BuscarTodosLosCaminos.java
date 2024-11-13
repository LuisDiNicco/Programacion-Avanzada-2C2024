package algoritmos;

import java.util.ArrayList;

import colaDePrioridad.Camino;
import colaDePrioridad.ColaDePrioridad;

public class BuscarTodosLosCaminos {
    private int[][] grafo;
    private ColaDePrioridad colaDePrioridad;

    public BuscarTodosLosCaminos(int[][] grafo) {
    	this.grafo=grafo;
    	this.colaDePrioridad=new ColaDePrioridad();
    }


    public void encontrarTodosLosCaminos(int inicio, int fin) {
        ArrayList<Integer> camino = new ArrayList<>();
        boolean[] visitados = new boolean[grafo.length];
        encontrarTodosLosCaminos(inicio, fin, visitados, camino, 0);
    }

    private void encontrarTodosLosCaminos(int inicio, int fin, boolean[] visited, ArrayList<Integer> camino, int costoCamino) {
        visited[inicio] = true;
        camino.add(inicio);

        if (inicio == fin) {
            Camino caminoAux= new Camino(camino, costoCamino);
            this.colaDePrioridad.insertar(caminoAux);
            
        } else {
            for (int w = 0; w < grafo.length; w++) {
                if (grafo[inicio][w] >= 0 && !visited[w] && grafo[inicio][w]<Integer.MAX_VALUE) {
                	encontrarTodosLosCaminos(w, fin, visited, camino, costoCamino + grafo[inicio][w]);
                }
            }
        }

        visited[inicio] = false;
        camino.remove(camino.size() - 1);
    }


	public ColaDePrioridad getColaDePrioridad() {
		return colaDePrioridad;
	}
  
}

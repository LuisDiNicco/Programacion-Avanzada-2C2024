package algoritmos;

import static org.junit.Assert.*;

import org.junit.Test;

public class TiemposBuscarTodosLosCaminosTests {

    private int[][] crearGrafoCompleto(int numVertices) {
        int[][] grafo = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    grafo[i][j] = 1;
                } else {
                    grafo[i][j] = 0;
                }
            }
        }
        return grafo;
    }

    @Test
    public void testGrafo5Vertices() {
        int[][] grafo = crearGrafoCompleto(5);
        BuscarTodosLosCaminos buscador = new BuscarTodosLosCaminos(grafo);

        long tiempoInicio = System.nanoTime();
        buscador.encontrarTodosLosCaminos(0, 5);
        long tiempoFin = System.nanoTime();

        long duracion = tiempoFin - tiempoInicio;
        assertTrue(duracion < 30_000_000_000L);

        assertNotNull(buscador.getColaDePrioridad());
    }

    @Test
    public void testGrafo10Vertices() {
        int[][] grafo = crearGrafoCompleto(10);
        BuscarTodosLosCaminos buscador = new BuscarTodosLosCaminos(grafo);

        long tiempoInicio = System.nanoTime();
        buscador.encontrarTodosLosCaminos(0, 9);
        long tiempoFin = System.nanoTime();

        long duracion = tiempoFin - tiempoInicio;
        assertTrue(duracion < 30_000_000_000L);

        assertNotNull(buscador.getColaDePrioridad());
    }
    
    @Test
    public void testGrafo12Vertices() {
        int[][] grafo = crearGrafoCompleto(12);
        BuscarTodosLosCaminos buscador = new BuscarTodosLosCaminos(grafo);

        long tiempoInicio = System.nanoTime();
        buscador.encontrarTodosLosCaminos(0, 11);
        long tiempoFin = System.nanoTime();

        long duracion = tiempoFin - tiempoInicio;
        assertTrue(duracion < 30_000_000_000L);

        assertNotNull(buscador.getColaDePrioridad());
    }
}
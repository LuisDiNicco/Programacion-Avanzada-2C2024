package algoritmos;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {

	private Dijkstra dijkstra;
	private int[][] grafo;
	private int nodoOrigen;

	@Before
	public void setUp() {
		grafo = new int[][] { { 0, 10, Integer.MAX_VALUE, 30, Integer.MAX_VALUE },
				{ 10, 0, 20, Integer.MAX_VALUE, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, 20, 0, 10, Integer.MAX_VALUE },
				{ 30, Integer.MAX_VALUE, 10, 0, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 } };
		nodoOrigen = 0;
		dijkstra = new Dijkstra(grafo, nodoOrigen);
		dijkstra.calcularDistanciaDijkstra();
	}

	@Test
	public void testDistanciaDesdeNodoOrigen() {
		int[] distanciasEsperadas = { 0, 10, 30, 30, Integer.MAX_VALUE};
		assertArrayEquals(distanciasEsperadas, dijkstra.getVectorDistancia());
	}

	@Test
	public void testPredecesoresDesdeNodoOrigen() {
		int[] predecesoresEsperados = { 0, 0, 1, 0, 0};
		assertArrayEquals(predecesoresEsperados, dijkstra.getVectorPredecesores());
	}

	@Test
	public void testDistanciaIndividual() {
		assertEquals(0, dijkstra.getDistancia(1));
		assertEquals(10, dijkstra.getDistancia(2));
		assertEquals(30, dijkstra.getDistancia(3));
		assertEquals(30, dijkstra.getDistancia(4));
		assertEquals(Integer.MAX_VALUE, dijkstra.getDistancia(5));
	}

	@Test
	public void testDistanciaEntreNodosInalcanzables() {
		int[][] grafoInalcanzable = { { 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 10 }, { Integer.MAX_VALUE, Integer.MAX_VALUE, 10, 0 } };
		Dijkstra dijkstraInalcanzable = new Dijkstra(grafoInalcanzable, 0);
		dijkstraInalcanzable.calcularDistanciaDijkstra();

		assertEquals(0, dijkstraInalcanzable.getDistancia(1));
		assertEquals(Integer.MAX_VALUE, dijkstraInalcanzable.getDistancia(2));
		assertEquals(Integer.MAX_VALUE, dijkstraInalcanzable.getDistancia(3));
		assertEquals(Integer.MAX_VALUE, dijkstraInalcanzable.getDistancia(4));
	}

	@Test
	public void testGetNodoOrigen() {
		assertEquals(nodoOrigen, dijkstra.getNodoOrigen());
	}
	
	@Test
	public void testRutaHastaNodoAlcanzable() {
	    List<Integer> rutaEsperada = Arrays.asList(0, 1, 2);
	    assertEquals(rutaEsperada, dijkstra.getRuta(2));
	}

	@Test
	public void testRutaDirectaHastaNodoAlcanzable() {
	    List<Integer> rutaEsperada = Arrays.asList(0, 3);
	    assertEquals(rutaEsperada, dijkstra.getRuta(3));
	}

	@Test
	public void testRutaDesdeOrigenAHastaSiMismo() {
	    List<Integer> rutaEsperada = Arrays.asList(0);
	    assertEquals(rutaEsperada, dijkstra.getRuta(0));
	}

	@Test
	public void testRutaHastaNodoInalcanzable() {   
	    int nodoInalcanzable = 5;
	    assertEquals(dijkstra.getDistancia(nodoInalcanzable), Integer.MAX_VALUE);
	}
}

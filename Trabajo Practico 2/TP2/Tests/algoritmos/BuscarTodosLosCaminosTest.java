package algoritmos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import colaDePrioridad.Camino;
import colaDePrioridad.ColaDePrioridad;

public class BuscarTodosLosCaminosTest {

	private int[][] grafo;
	private BuscarTodosLosCaminos buscador;

	@Before
	public void setUp() {
		grafo = new int[][] { { 0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE }, { 1, 0, 2, Integer.MAX_VALUE }, { Integer.MAX_VALUE, 2, 0, 1 }, { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 0 } };
		buscador = new BuscarTodosLosCaminos(grafo);
	}

	@Test
	public void testEncontrarTodosLosCaminosConCaminoExistente() {
		buscador.encontrarTodosLosCaminos(0, 3);
		ColaDePrioridad cola = buscador.getColaDePrioridad();

		assertEquals(1, cola.tamaño());
		Camino camino = cola.extraerMin();
		assertNotNull(camino);
		assertEquals(4, camino.getCosto());
	}

	@Test
	public void testEncontrarTodosLosCaminosSinCamino() {
		grafo = new int[][] { { 0, -1, -1 }, { -1, 0, -1 }, { -1, -1, 0 } };
		buscador = new BuscarTodosLosCaminos(grafo);

		buscador.encontrarTodosLosCaminos(0, 2);
		ColaDePrioridad cola = buscador.getColaDePrioridad();

		assertTrue(cola.estaVacio());
	}

	@Test
	public void testGrafoConUnSoloNodo() {
		grafo = new int[][] { { 0 } };
		buscador = new BuscarTodosLosCaminos(grafo);

		buscador.encontrarTodosLosCaminos(0, 0);
		ColaDePrioridad cola = buscador.getColaDePrioridad();

		assertEquals(1, cola.tamaño());
		Camino camino = cola.extraerMin();
		assertNotNull(camino);
		assertEquals(0, camino.getCosto());
	}

	@Test
	public void testColaDePrioridadVacia() {
		ColaDePrioridad cola = buscador.getColaDePrioridad();
		assertEquals(0, cola.tamaño());
	}
}


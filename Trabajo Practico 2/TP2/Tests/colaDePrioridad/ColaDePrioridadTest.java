package colaDePrioridad;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ColaDePrioridadTest {

	private ColaDePrioridad cola;
	private Camino camino1;
	private Camino camino2;
	private Camino camino3;

	@Before
	public void setUp() {
		cola = new ColaDePrioridad();

		camino1 = new Camino(Arrays.asList(0, 1, 3), 10);
		camino2 = new Camino(Arrays.asList(0, 1), 4);
		camino3 = new Camino(Arrays.asList(1, 3), 6);
	}

	@Test
	public void testInsertarYVerPrimeroDeCola() {
		cola.insertar(camino1);
		cola.insertar(camino2);
		cola.insertar(camino3);

		assertEquals(camino2, cola.verPrimeroDeCola());
	}

	@Test
	public void testExtraerMin() {
		cola.insertar(camino1);
		cola.insertar(camino2);
		cola.insertar(camino3);

		assertEquals(camino2, cola.extraerMin());
		assertEquals(camino3, cola.extraerMin());
		assertEquals(camino1, cola.extraerMin());
		assertNull(cola.extraerMin());
	}

	@Test
	public void testEstaVacio() {
		assertTrue(cola.estaVacio());

		cola.insertar(camino1);
		assertFalse(cola.estaVacio());

		cola.extraerMin();
		assertTrue(cola.estaVacio());
	}

	@Test
	public void testTamaño() {
		assertEquals(0, cola.tamaño());

		cola.insertar(camino1);
		assertEquals(1, cola.tamaño());

		cola.insertar(camino2);
		cola.insertar(camino3);
		assertEquals(3, cola.tamaño());

		cola.extraerMin();
		assertEquals(2, cola.tamaño());
	}
}

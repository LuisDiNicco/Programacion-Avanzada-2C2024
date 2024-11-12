package raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;

public class ReralopesTest {

	private Reralopes reralopes;
	private String rutaArchivo = "./ArchivoTest.txt";

	@Before
	public void setUp() {
		LogWriter.iniciar(rutaArchivo);
		reralopes = new Reralopes();
	}

	@Test
	public void testConstructor() {
		assertEquals(53, reralopes.getSalud());
		assertFalse(reralopes.isConcentrado());
		assertEquals(0, reralopes.getCantidadAtaquesConcentrado());
	}

	@Test
	public void testAtacarSinConcentracion() {
		int daño = reralopes.atacar();
		assertTrue(daño == 0 || daño == 27);
	}

	@Test
	public void testAtacarConConcentracion() {
		reralopes.descansar();
		assertTrue(reralopes.isConcentrado());

		int daño = reralopes.atacar();
		assertTrue(daño == 0 || daño == 54);

		if (daño == 54) {
			assertEquals(1, reralopes.getCantidadAtaquesConcentrado());
		}
	}

	@Test
	public void testNoPasaLimiteAtaquesConcentrado() {
		reralopes.descansar();
		int ataquesConcentrados = 0;

		for (int i = 0; i < 2; i++) {
			reralopes.atacar();
			ataquesConcentrados++;
		}

		assertEquals(ataquesConcentrados, reralopes.getCantidadAtaquesConcentrado());
		// assertEquals(3, ataquesConcentrados);
		assertTrue(reralopes.isConcentrado());
	}

	@Test
	public void testPasaLimiteAtaquesConcentrado() {
		reralopes.descansar();

		for (int i = 0; i < 3; i++) {
			reralopes.atacar();
		}

		assertEquals(0, reralopes.getCantidadAtaquesConcentrado());
		assertTrue(!reralopes.isConcentrado());
	}

	@Test
	public void testRecibirAtaque() {
		int daño = 20;
		int saludEsperada = reralopes.getSalud() - daño;
		reralopes.recibirAtaque(daño);
		assertEquals(saludEsperada, reralopes.getSalud());
		assertFalse(reralopes.isConcentrado());
	}

	@Test
	public void testRecibirAtaqueYMuere() {
		int daño = 60;
		reralopes.recibirAtaque(daño);
		assertTrue(reralopes.getSalud() <= 0);
		assertFalse(reralopes.isConcentrado());
	}

	@Test
	public void testDescansar() {
		reralopes.descansar();
		assertTrue(reralopes.isConcentrado());
	}
	
	@After
	public void borrarArchivo() {
		LogWriter.cerrar();
		File archivo = new File(rutaArchivo);
		if (archivo.exists()) {
			archivo.delete();
		}
	}
}
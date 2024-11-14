package raza;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;
import estadoNortaichian.*;

public class NortaichianTest {

	private Nortaichian nortaichian;
	private String rutaArchivo = "./ArchivoTest.txt";

	@Before
	public void setUp() {
		LogWriter.iniciar(rutaArchivo);
		nortaichian = new Nortaichian();
	}

	@Test
	public void atacarEnEstadoNormal() {
		nortaichian.cambiarAEstadoNormal();
		int daño = nortaichian.atacar();

		assertEquals(nortaichian.getDañoBase(), daño);
		assertTrue(nortaichian.getSalud() <= nortaichian.getSaludMaxima());
	}

	@Test
	public void recibirAtaqueEnEstadoNormal() {
		int saludAnterior = nortaichian.getSalud();
		nortaichian.recibirAtaque(10);

		assertTrue(nortaichian.getSalud() < saludAnterior);
		assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);
	}

	@Test
	public void descansarEnEstadoNormal() {
		nortaichian.recibirAtaque(10);
		int saludAnterior = nortaichian.getSalud();
		nortaichian.descansar();

		assertTrue(nortaichian.getSalud() > saludAnterior);
		assertTrue(nortaichian.getEstado() instanceof EstadoDePiedraNortaichian);
	}

	@Test
	public void atacarConPocaVida() {
		nortaichian.recibirAtaque(10);
		int daño = nortaichian.atacar();

		assertEquals(nortaichian.getDañoBase() * 2, daño);
		assertTrue(nortaichian.getSalud() <= nortaichian.getSaludMaxima());
	}

	@Test
	public void atacarEnEstadoEnfurecido() {
		nortaichian.cambiarAEstadoEnfurecido();
		int daño = nortaichian.atacar();

		assertEquals(nortaichian.getDañoBase() * 2, daño);
		assertTrue(nortaichian.getSalud() <= nortaichian.getSaludMaxima());
	}

	@Test
	public void recibirAtaqueEnEstadoEnfurecido() {
		nortaichian.cambiarAEstadoEnfurecido();
		int saludAnterior = nortaichian.getSalud();

		nortaichian.recibirAtaque(10);

		assertTrue(nortaichian.getSalud() < saludAnterior);
		assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);
	}

	@Test
	public void muereEstadoEnfurecido() {
		nortaichian.cambiarAEstadoEnfurecido();
		int saludAnterior = nortaichian.getSalud();

		nortaichian.recibirAtaque(1000);

		assertTrue(nortaichian.getSalud() < saludAnterior);
		assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);
	}

	@Test
	public void descansarEnEstadoEnfurecido() {
		nortaichian.recibirAtaque(10);
		int saludAnterior = nortaichian.getSalud();
		assertNotEquals(saludAnterior, nortaichian.getSaludMaxima());

		nortaichian.descansar();

		assertEquals(nortaichian.getSaludMaxima(), nortaichian.getSalud());
	}

	@Test
	public void atacarEnEstadoDePiedra() {
		nortaichian.cambiarAEstadoDePiedra();
		int salud = nortaichian.getSalud();
		int daño = nortaichian.atacar();

		assertEquals(0, daño);
		assertEquals(salud, nortaichian.getSalud());
	}

	@Test
	public void recibirAtaqueEnEstadoDePiedra() {
		nortaichian.cambiarAEstadoDePiedra();
		int saludAnterior = nortaichian.getSalud();
		nortaichian.recibirAtaque(10);

		assertEquals(saludAnterior - 5, nortaichian.getSalud());
	}

	@Test
	public void descansarEnEstadoDePiedra() {
		nortaichian.cambiarAEstadoDePiedra();
		int saludAnterior = nortaichian.getSalud();
		nortaichian.descansar();

		assertEquals(saludAnterior, nortaichian.getSalud());
		assertTrue(nortaichian.getEstado() instanceof EstadoDePiedraNortaichian);
	}

	@Test
	public void atacarEnEstadoDePiedra_TercerTurno() {
		nortaichian.cambiarAEstadoDePiedra();
		nortaichian.atacar();
		nortaichian.atacar();
		nortaichian.atacar();

		assertTrue(nortaichian.getEstado() instanceof EstadoNormalNortaichian);
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

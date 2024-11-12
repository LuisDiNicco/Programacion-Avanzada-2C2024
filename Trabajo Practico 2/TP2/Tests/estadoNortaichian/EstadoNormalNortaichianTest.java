package estadoNortaichian;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import archivo.LogWriter;

import org.junit.Before;
import raza.Nortaichian;

public class EstadoNormalNortaichianTest {

	private Nortaichian nortaichian;
	private EstadoNormalNortaichian estadoNormal;
	private String rutaArchivo = "./ArchivoTest.txt";

	@Before
	public void setUp() {
		LogWriter.iniciar(rutaArchivo);
		nortaichian = new Nortaichian();
		estadoNormal = new EstadoNormalNortaichian();
		nortaichian.cambiarAEstadoNormal();
	}

	@Test
	public void testAtacarEnEstadoNormalVidaLlena() {
		int saludInicial = nortaichian.getSalud();
		int daño = estadoNormal.atacar(nortaichian);

		assertEquals(nortaichian.getDañoBase(), daño);
		assertEquals(saludInicial, nortaichian.getSalud());
	}

	@Test
	public void testAtacarEnEstadoNormalVidaBaja() {
		nortaichian.recibirAtaque(10);
		nortaichian.cambiarAEstadoNormal();
		int saludInicial = nortaichian.getSalud();
		int daño = estadoNormal.atacar(nortaichian);
		int curacion = nortaichian.getSaludMaxima() * 4 / 100;

		assertEquals(nortaichian.getDañoBase(), daño);
		assertEquals(saludInicial + curacion, nortaichian.getSalud());
	}

	@Test
	public void testRecibirAtaqueCambiaAEstadoEnfurecido() {
		int saludInicial = nortaichian.getSalud();
		estadoNormal.recibirAtaque(nortaichian, 20);

		assertEquals(saludInicial - 20, nortaichian.getSalud());
		assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);
	}

	@Test
	public void testDescansarEnEstadoNormal() {
		estadoNormal.descansar(nortaichian);

		assertEquals(nortaichian.getSaludMaxima(), nortaichian.getSalud());
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


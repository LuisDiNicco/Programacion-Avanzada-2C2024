package estadoNortaichian;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;
import raza.Nortaichian;

public class EstadoDePiedraNortaichianTest {

	private Nortaichian nortaichian;
	private EstadoDePiedraNortaichian estadoDePiedra;

	@Before
	public void setUp() {
		LogWriter.iniciar("./ArchivoTest.txt");
		nortaichian = new Nortaichian();
		estadoDePiedra = new EstadoDePiedraNortaichian();
		nortaichian.cambiarAEstadoDePiedra();
	}

	@Test
	public void testAtacarPrimerTurnoEnEstadoDePiedra() {
		int daño = estadoDePiedra.atacar(nortaichian);

		assertEquals(0, daño);
		assertEquals(1, nortaichian.getCantidadTurnoEnEstadoDePiedra());
	}

	@Test
	public void testAtacarSegundoTurnoEnEstadoDePiedra() {
		nortaichian.incrementarTurnoDePiedra();
		int daño = estadoDePiedra.atacar(nortaichian);

		assertEquals(0, daño);
		assertEquals(2, nortaichian.getCantidadTurnoEnEstadoDePiedra());
	}
	
	@Test
	public void testAtacarTercerTurnoEnEstadoDePiedra() {
		nortaichian.incrementarTurnoDePiedra();
		nortaichian.incrementarTurnoDePiedra();
		int daño = estadoDePiedra.atacar(nortaichian);

		assertTrue(nortaichian.getEstado() instanceof EstadoNormalNortaichian);
		assertEquals(nortaichian.getDañoBase(), daño);
	}

	@Test
	public void testRecibirAtaqueEnEstadoDePiedra() {
		int saludInicial = nortaichian.getSalud();
		estadoDePiedra.recibirAtaque(nortaichian, 20);

		assertEquals(saludInicial - 10, nortaichian.getSalud());
		assertEquals(1, nortaichian.getCantidadTurnoEnEstadoDePiedra());
	}

	@Test
	public void testDescansarEnEstadoDePiedra() {
		estadoDePiedra.descansar(nortaichian);

		assertEquals(0, nortaichian.getCantidadTurnoEnEstadoDePiedra());
		assertTrue(nortaichian.getEstado() instanceof EstadoDePiedraNortaichian);
	}
}


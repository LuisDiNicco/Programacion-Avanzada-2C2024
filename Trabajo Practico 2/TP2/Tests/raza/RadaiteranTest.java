package raza;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import archivo.LogWriter;

public class RadaiteranTest {

	private Radaiteran radaiteran;
	private String rutaArchivo = "./ArchivoTest.txt";

	@Before
	public void setUp() {
		LogWriter.iniciar(rutaArchivo);
		radaiteran = new Radaiteran();
	}

	@Test
	public void testConstructor() {
		assertEquals(36, radaiteran.getSalud());
		assertEquals("Shurikens", radaiteran.getTipoArma());
		assertEquals(0, radaiteran.getCantidadDeAtaques());
	}

	@Test
	public void testAtacar() {
		int dañoEsperado = 56;
		assertEquals(dañoEsperado, radaiteran.atacar());

		dañoEsperado += 3;
		assertEquals(dañoEsperado, radaiteran.atacar());

		dañoEsperado += 3;
		assertEquals(dañoEsperado, radaiteran.atacar());
	}

	@Test
	public void testRecibirAtaque() {
		int daño = 10;
		int saludEsperada = radaiteran.getSalud() - daño;
		radaiteran.recibirAtaque(daño);
		assertEquals(saludEsperada, radaiteran.getSalud());

		daño = 30;
		saludEsperada = radaiteran.getSalud() - daño;
		radaiteran.recibirAtaque(daño);
		assertEquals(saludEsperada, radaiteran.getSalud());
	}

	@Test
	public void testRecibirAtaqueYMuere() {
		int daño = 50;
		radaiteran.recibirAtaque(daño);
		assertTrue(radaiteran.getSalud() <= 0);
	}

	@Test
	public void testDescansar() {
		int saludActual = radaiteran.getSalud();
		int dañoActual = radaiteran.getDañoBase();
		radaiteran.descansar();
		assertEquals(radaiteran.getSalud(), saludActual);
		assertEquals(radaiteran.getDañoBase(), dañoActual);

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

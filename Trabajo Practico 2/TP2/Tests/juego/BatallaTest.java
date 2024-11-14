package juego;

import archivo.LogWriter;
import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import raza.NombreRaza;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BatallaTest {

	private Pueblo puebloDebil;
	private Pueblo puebloFuerte;
	private String rutaArchivo = "./ArchivoTest.txt";

	@Before
	public void setUp() {
		LogWriter.iniciar(rutaArchivo);
		puebloDebil = new Pueblo(1, 5, NombreRaza.WRIVES, TipoDePueblo.ALIADO);
		puebloDebil.generarEjercito(NombreRaza.WRIVES.crearRaza());

		puebloFuerte = new Pueblo(2, 50, NombreRaza.WRIVES, TipoDePueblo.ENEMIGO);
		puebloFuerte.generarEjercito(NombreRaza.WRIVES.crearRaza());
	}

	@Test
	public void testGananAliados() {
		Batalla.batalla(puebloFuerte, puebloDebil);
		assertFalse(puebloDebil.hayEjercito());
		assertTrue(puebloFuerte.hayEjercito());
	}

	@Test
	public void testPierdenAliados() {
		Batalla.batalla(puebloDebil, puebloFuerte);
		assertFalse(puebloDebil.hayEjercito());
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

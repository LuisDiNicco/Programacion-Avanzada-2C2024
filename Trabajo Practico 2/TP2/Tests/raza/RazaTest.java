package raza;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RazaTest {

	private Radaiteran radaiteran;
	private Radaiteran radaiteran1;
	private Radaiteran radaiteran2;
	private Radaiteran radaiteran3;
	private Radaiteran radaiteranNull;

	@Before
	public void setUp() {
		radaiteran = new Radaiteran();

		radaiteran1 = new Radaiteran();
		radaiteran2 = new Radaiteran();
		radaiteran3 = radaiteran1;
		radaiteranNull = null;
	}

	@Test
	public void testGetNombreRaza() {
		assertEquals("RADAITERAN", radaiteran.getNombreRaza());
	}

	@Test
	public void testGetSaludInicial() {
		assertEquals(36, radaiteran.getSalud());
	}

	@Test
	public void testGetSaludMaxima() {
		assertEquals(36, radaiteran.getSaludMaxima());
	}

	@Test
	public void testGetDañoBase() {
		assertEquals(56, radaiteran.getDañoBase());
	}

	@Test
	public void testGetRangoMinimo() {
		assertEquals(17, radaiteran.getRangoMinimo());
	}

	@Test
	public void testGetRangoMaximo() {
		assertEquals(41, radaiteran.getRangoMaximo());
	}

	@Test
	public void testGetTipoArma() {
		assertEquals("Shurikens", radaiteran.getTipoArma());
	}

	@Test
	public void testGetIdUnico() {
		assertNotEquals(radaiteran1.getIdUnico(), radaiteran2.getIdUnico());
		assertEquals(radaiteran1.getIdUnico(), radaiteran3.getIdUnico());
	}

	@Test
	public void testEquals() {
		assertTrue(radaiteran1.equals(radaiteran3));
		assertFalse(radaiteran1.equals(radaiteran2));
	}

	@Test
	public void testHashCode() {
		assertEquals(radaiteran1.hashCode(), radaiteran3.hashCode());
		assertNotEquals(radaiteran1.hashCode(), radaiteran2.hashCode());
	}

	@Test
	public void testEqualsNull() {
		assertFalse(radaiteran1.equals(radaiteranNull));
	}

	@Test
	public void testNotEquals() {
		Raza otraRaza = new Nortaichian();
		assertFalse(radaiteran1.equals(otraRaza));
	}
}
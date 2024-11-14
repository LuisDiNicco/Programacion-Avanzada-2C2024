package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimulacionTest {

	@Test
	public void testConvertirDiasAHorasEnteros() {
		assertEquals("1 día y 0 horas ", Simulacion.convertirDiasAHoras(1.0));
		assertEquals("2 días y 0 horas ", Simulacion.convertirDiasAHoras(2.0));
		assertEquals("0 días y 0 horas ", Simulacion.convertirDiasAHoras(0.0));
	}

	@Test
	public void testConvertirDiasAHorasConHorasDecimales() {
		assertEquals("1 día y 12 horas ", Simulacion.convertirDiasAHoras(1.5));
		assertEquals("0 días y 6 horas ", Simulacion.convertirDiasAHoras(0.25));
		assertEquals("2 días y 12 horas ", Simulacion.convertirDiasAHoras(2.5));
	}

	@Test
	public void testConvertirDiasAHorasRedondeoHoras() {
		// Verificación del redondeo correcto de horas
		assertEquals("1 día y 1 hora ", Simulacion.convertirDiasAHoras(1.04));
		assertEquals("1 día y 23 horas ", Simulacion.convertirDiasAHoras(1.96));
	}

	@Test
	public void testConvertirDiasAHorasCasosLimite() {
		assertEquals("0 días y 1 hora ", Simulacion.convertirDiasAHoras(0.04));
		assertEquals("0 días y 23 horas ", Simulacion.convertirDiasAHoras(0.96));
	}

	@Test
	public void testConvertirDiasAHorasSinHoras() {
		assertEquals("3 días y 0 horas ", Simulacion.convertirDiasAHoras(3.0));
		assertEquals("1 día y 0 horas ", Simulacion.convertirDiasAHoras(1.0));
	}

}

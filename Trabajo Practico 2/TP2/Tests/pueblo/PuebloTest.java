package pueblo;
import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;
import raza.NombreRaza;

import static org.junit.Assert.*;

public class PuebloTest {
	private Pueblo pueblo;

    @Before
    public void setUp() {
    	LogWriter.iniciar("./ArchivoTest.txt");
        pueblo = new Pueblo(1, 5, NombreRaza.WRIVES, TipoDePueblo.ALIADO);
    }

    @Test
    public void testGenerarEjercito() {        
        pueblo.generarEjercito(NombreRaza.WRIVES.crearRaza());
        
        assertTrue(pueblo.hayEjercito());
    }

    @Test
    public void testNoHayEjercitoCuandoNoHaySoldados() {
        assertFalse(pueblo.hayEjercito());
    }

    @Test
    public void testHayEjercitoCuandoHaySoldados() {
        pueblo.generarEjercito(NombreRaza.WRIVES.crearRaza());

        assertTrue(pueblo.hayEjercito());
    }

    @Test
    public void testAgregarAliados() {
    	pueblo.generarEjercito(NombreRaza.WRIVES.crearRaza());
    	
        Pueblo aliado = new Pueblo(2, 6, NombreRaza.NORTAICHIAN, TipoDePueblo.ALIADO);
        aliado.generarEjercito(NombreRaza.NORTAICHIAN.crearRaza());
        
        pueblo.agregarAliados(aliado);

        int cantidadPersonajes=pueblo.getEjercito().getTamaño();
        assertEquals(8, cantidadPersonajes);
    }

    @Test
    public void testGetNumeroPueblo() {
        assertEquals(1, pueblo.getNumeroPueblo());
    }

    @Test
    public void testGetCantidadHabitantes() {
        assertEquals(5, pueblo.getCantidadHabitantes());
    }

    @Test
    public void testGetNombreRaza() {
        assertEquals(NombreRaza.WRIVES, pueblo.getNombreRaza());
    }

    @Test
    public void testGetTipoDePueblo() {
        assertEquals(TipoDePueblo.ALIADO, pueblo.getTipoDePueblo());
    }
}
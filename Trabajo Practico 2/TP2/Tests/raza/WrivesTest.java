package raza;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;

public class WrivesTest {

	    private Wrives wrives;

	    @Before
	    public void setUp() {
	        LogWriter.iniciar("./ArchivoTest.txt");
	        wrives = new Wrives();
	    }

	    @Test
	    public void testConstructor() {
	        assertEquals(108, wrives.getSalud());
	        assertEquals( 0, wrives.getCantidadAtaques());
	        assertFalse( wrives.getRehusaAtacar());
	    }

	    @Test
	    public void testAtacarSinRehusar() {
	        int daño = wrives.atacar();
	        assertEquals( 113, daño);
	        assertEquals( 1, wrives.getCantidadAtaques());
	    }

	    @Test
	    public void testAtacarMultiplicador() {
	        wrives.atacar(); 
	        wrives.atacar();  
	        int daño = wrives.atacar();  
	        assertEquals(113 * 2, daño);
	        assertEquals( 0, wrives.getCantidadAtaques());
	    }

	    @Test
	    public void testRehusarAtacar() {
	        wrives.descansar();  
	        int daño = wrives.atacar();
	        assertEquals( 0, daño);
	        assertTrue(wrives.getRehusaAtacar());
	    }

	    @Test
	    public void testRecibirAtaque() {
	        int daño = 10;
	        wrives.recibirAtaque(daño);
	        assertEquals( 108 - 2 * daño, wrives.getSalud());
	        assertFalse( wrives.getRehusaAtacar());
	    }

	    @Test
	    public void testRecibirAtaqueYMuere() {
	        int daño = 60;
	        wrives.recibirAtaque(daño);
	        assertTrue(wrives.getSalud() <= 0);
	    }

	    @Test
	    public void testDescansar() {
	        wrives.descansar();
	        assertEquals( 108 + 50, wrives.getSaludMaxima());
	        assertEquals( 108 + 50, wrives.getSalud());
	        assertTrue( wrives.getRehusaAtacar());
	    }
	}
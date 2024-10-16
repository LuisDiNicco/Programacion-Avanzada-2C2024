package raza;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WrivesTest {

  private Wrives wrives;

    @Before
    public void inicio() {
        wrives = new Wrives();
    }

    @Test
    public void testConstructor() {
        assertNotNull(wrives);
        assertEquals(1, wrives.getCantidadAtaques());
<<<<<<< HEAD
        assertFalse(wrives.getRehusaAtacar());       
=======
        assertFalse(wrives.rehusaAtacar());       
>>>>>>> 6616680ded6fb8b34d044cee7893dbe0138997a6
    }
	
	@Test
	public void testPrimerAtaque() {
	    int daño = wrives.atacar();
	    assertEquals(113, daño);
	}

	@Test
	public void testSegundoAtaque() {
	    wrives.atacar(); 
	    int daño = wrives.atacar(); 
	    assertEquals(226, daño);
	}
	
	@Test
	public void testRecibirAtaque() {
	    wrives.recibirAtaque(50);
	    assertEquals(8, wrives.getSalud()); 
	}

	@Test
	public void testRecibirUltimoAtaque() {
	    wrives.recibirAtaque(54); 
	    assertEquals(0, wrives.getSalud()); 
	}
	
	@Test
	public void testDescansar() {
	    wrives.descansar();
	    assertEquals(158, wrives.getSaludMaxima()); 
<<<<<<< HEAD
	    assertTrue(wrives.getRehusaAtacar());
	}

}

=======
	    assertTrue(wrives.rehusaAtacar());
	}

}
>>>>>>> 6616680ded6fb8b34d044cee7893dbe0138997a6

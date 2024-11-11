package raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ReralopesTest {
	
	private Reralopes reralopes;
	
    @Before
    public void inicio() {
        reralopes = new Reralopes();
    }

    @Test
    public void testConstructor() {
        assertNotNull(reralopes);
        assertEquals(0, reralopes.getCantidadAtaquesConcentrado());
        assertFalse(reralopes.isConcentrado());       
    }
	
	@Test
	public void testPrimerAtaqueNoConcentrado() {
	    int daño = reralopes.atacar();
	    assertEquals(27, daño);
	}

	@Test
	public void testSegundoAtaqueNoConcentrado() {
		reralopes.atacar(); 
	    int daño = reralopes.atacar(); 
	    assertEquals(27, daño);
	}
	
	@Test
	public void testPrimerAtaqueConcentrado() {
		reralopes.descansar();
	    int daño = reralopes.atacar();
	    assertEquals(54, daño);
	}

	@Test
	public void testTercerAtaqueConcentrado() {
		reralopes.descansar();
		reralopes.atacar();
		reralopes.atacar();
		reralopes.atacar();
	    int daño = reralopes.atacar(); 
	    assertEquals(27, daño);
	    assertFalse(reralopes.isConcentrado());
	}
	
	@Test
	public void testRecibirAtaque() {
		reralopes.recibirAtaque(50);
	    assertEquals(3, reralopes.getSalud()); 
	}

	@Test
	public void testRecibirUltimoAtaque() {
		reralopes.recibirAtaque(53); 
	    assertEquals(0, reralopes.getSalud()); 
	}
	
	@Test
	public void testDescansar() {
		reralopes.descansar();
	    assertTrue(reralopes.isConcentrado());
	}

}

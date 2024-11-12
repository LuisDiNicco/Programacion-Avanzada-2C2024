package raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;

public class ReralopesTest {
	
	private Reralopes reralopes;
	
    @Before
    public void inicio() {
    	LogWriter.iniciar("./ArchivoTest.txt");
        reralopes = new Reralopes();
    }

    @Test
    public void testConstructor() {
        assertEquals(53, reralopes.getSalud());
        assertFalse( reralopes.isConcentrado());
        assertEquals( 0, reralopes.getCantidadAtaquesConcentrado());
    }

    @Test
    public void testAtacarSinConcentracion() {
        int daño = reralopes.atacar();
        assertTrue( daño == 0 || daño == 27);
    }

    @Test
    public void testAtacarConConcentracion() {
        reralopes.descansar();  
        assertTrue(reralopes.isConcentrado());

        int daño = reralopes.atacar();
        assertTrue( daño == 0 || daño == 54);
        
        if (daño == 54) { 
            assertEquals( 1, reralopes.getCantidadAtaquesConcentrado());
        }
    }

    @Test
    public void testLimiteAtaquesConcentrado() {
        reralopes.descansar(); 
        int ataquesConcentrados = 0;

        for (int i = 0; i < 6; i++) {
            int daño = reralopes.atacar();
            if (daño == 54) ataquesConcentrados++;
        }

        assertEquals(0, reralopes.getCantidadAtaquesConcentrado());
        assertEquals(3, ataquesConcentrados);
        assertFalse( reralopes.isConcentrado());
    }

    @Test
    public void testRecibirAtaque() {
        int daño = 20;
        int saludEsperada = reralopes.getSalud() - daño;
        reralopes.recibirAtaque(daño);
        assertEquals( saludEsperada, reralopes.getSalud());
        assertFalse(reralopes.isConcentrado());
    }

    @Test
    public void testRecibirAtaqueYMuere() {
        int daño = 60;
        reralopes.recibirAtaque(daño);
        assertTrue( reralopes.getSalud() <= 0);
        assertFalse( reralopes.isConcentrado());
    }

    @Test
    public void testDescansar() {
        reralopes.descansar();
        assertTrue( reralopes.isConcentrado());
    }
}
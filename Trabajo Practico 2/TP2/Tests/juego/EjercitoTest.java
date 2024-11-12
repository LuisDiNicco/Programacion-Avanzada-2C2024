package juego;

import archivo.LogWriter;
import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import raza.NombreRaza;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EjercitoTest {
	private Pueblo pueblo;
	private Ejercito ejercito;

    @Before
    public void setUp() {
    	LogWriter.iniciar("./ArchivoTest.txt");
        pueblo = new Pueblo(1, 5, NombreRaza.WRIVES, TipoDePueblo.ALIADO);
        pueblo.generarEjercito(NombreRaza.WRIVES.crearRaza());
        this.ejercito=pueblo.getEjercito();
    }

    @Test
    public void testAtacar() {
    	int dañoEsperado = 113;
        int daño=ejercito.atacar();
        
        assertEquals(dañoEsperado, daño);
    }

    @Test
    public void testRecibirAtaque() {
    	int daño=10;
        this.ejercito.recibirAtaque(daño);
        int salud = this.ejercito.getPrimero().getSalud();
        
        assertEquals(88, salud);
    }
    
    @Test
    public void testRecibirAtaqueQueMataAlGuerrero() {
    	int daño=54;
        this.ejercito.recibirAtaque(daño);
        int cantidad = this.ejercito.getTamaño();
        
        assertEquals(4, cantidad);
    }
    
    @Test
    public void testRecordenarSoldados() {
    	int daño=10;
        this.ejercito.recibirAtaque(daño);
        
        this.ejercito.reordenarse();
        
        int salud = this.ejercito.getPrimero().getSalud();
        
        assertEquals(108, salud);
    }
    
    @Test
    public void testLosSoldadosDescansan() {
        this.ejercito.descansar();
        int salud= this.ejercito.getPrimero().getSalud();

        assertEquals(158, salud);
    }
}
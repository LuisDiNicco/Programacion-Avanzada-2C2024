package juego;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;
import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import raza.NombreRaza;

public class MapaTest {

	
    @Before
    public void setUp() {
    	LogWriter.iniciar("./ArchivoTest.txt");
    }
    
    @Test
    public void testSetPueblosMapa() {
    	
    	int cantidadCiudades=2;
    	Mapa.inicializarMapa(cantidadCiudades);
    	Mapa mapa=Mapa.getInstancia();
    	
    	Pueblo[] pueblos= new Pueblo[2];
    	
		int totalHabitantes = 10;
		NombreRaza nombreRaza = NombreRaza.RADAITERAN;
		TipoDePueblo tipo =TipoDePueblo.ALIADO;

		pueblos[0] = new Pueblo(0, totalHabitantes, nombreRaza, tipo);
		
		totalHabitantes = 20;
		nombreRaza = NombreRaza.NORTAICHIAN;
		tipo =TipoDePueblo.ENEMIGO;

		pueblos[1] = new Pueblo(0, totalHabitantes, nombreRaza, tipo);
    	
		mapa.setPueblos(pueblos);
		Pueblo pueblo1= mapa.getPueblo(0);
    	
        assertEquals(10,pueblo1.getCantidadHabitantes());
        assertEquals(2,mapa.getCantidadDePueblos());
        Mapa.reiniciarMapa();
    }
    
    @Test
    public void testGenerarMapa() {
    	
    	int cantidadCiudades=2;
    	Mapa.inicializarMapa(cantidadCiudades);
    	Mapa mapa=Mapa.getInstancia();
   	
    	int[][] grafo=mapa.getGrafo();
     
        assertEquals(2,grafo.length);
        assertEquals(2,grafo[0].length);
        
        Mapa.reiniciarMapa();
    }
    
    @Test
    public void testDistanciaMapa() {
    	
    	int cantidadCiudades=2;
    	Mapa.inicializarMapa(cantidadCiudades);
    	Mapa mapa=Mapa.getInstancia();
    	
    	mapa.setDistancia(1, 1, 10);
   	
    	int[][] grafo=mapa.getGrafo();
    	     
        assertEquals(10,grafo[0][0]);
  
        Mapa.reiniciarMapa();
    }

}

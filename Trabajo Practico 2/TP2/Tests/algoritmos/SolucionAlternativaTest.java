package algoritmos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class SolucionAlternativaTest {
    private SolucionAlternativa solucion;
    
    @Before
    public void setUp() {
        int[][] grafo = {
            {0, 10, 15, Integer.MAX_VALUE},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {Integer.MAX_VALUE, 25, 30, 0}
        };
        
        solucion = new SolucionAlternativa(grafo);
    }

    @Test
    public void testEncontrarCaminoNMinimo_UnicoCamino() {
        List<Camino> caminos = solucion.encontrarCaminoNMinimo(0, 3, 1);

        assertEquals(1, caminos.size());
        Camino camino = caminos.get(0);
        assertEquals(Arrays.asList(0, 1, 3), camino.getNodos());
        assertEquals(35, camino.getCosto());
    }

    @Test
    public void testEncontrarCaminoNMinimo_MultiplesCaminos() {
        List<Camino> caminos = solucion.encontrarCaminoNMinimo(0, 3, 2);

        assertEquals(2, caminos.size());

        Camino primerCamino = caminos.get(0);
        assertEquals(Arrays.asList(0, 1, 3), primerCamino.getNodos());
        assertEquals(35, primerCamino.getCosto());

        Camino segundoCamino = caminos.get(1);
        assertEquals(Arrays.asList(0, 2, 3), segundoCamino.getNodos());
        assertEquals(45, segundoCamino.getCosto());
    }

    @Test
    public void testEncontrarCaminoNMinimo_SinCamino() {
        int[][] grafoSinCamino = {
            {0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        solucion = new SolucionAlternativa(grafoSinCamino);

        List<Camino> caminos = solucion.encontrarCaminoNMinimo(0, 3, 1);
        assertTrue(caminos.isEmpty());
    }

    @Test
    public void testEncontrarCaminoNMinimo_CantidadCaminosMayorQuePosibles() {
        List<Camino> caminos = solucion.encontrarCaminoNMinimo(0, 3, 5);

        assertEquals(4, caminos.size());  
    
        Camino primerCamino = caminos.get(0);
        assertEquals(Arrays.asList(0, 1, 3), primerCamino.getNodos());
        assertEquals(35, primerCamino.getCosto());
        
        Camino segundoCamino = caminos.get(1);
        assertEquals(Arrays.asList(0, 2, 3), segundoCamino.getNodos());
        assertEquals(45, segundoCamino.getCosto());
        
        Camino tercerCamino = caminos.get(2);
        assertEquals(75, tercerCamino.getCosto());
        
        Camino cuartoCamino = caminos.get(3);
        assertEquals(75, cuartoCamino.getCosto());
    }
}
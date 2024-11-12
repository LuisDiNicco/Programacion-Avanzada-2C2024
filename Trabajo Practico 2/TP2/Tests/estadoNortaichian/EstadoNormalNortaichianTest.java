package estadoNortaichian;

import static org.junit.Assert.*;
import org.junit.Test;

import archivo.LogWriter;

import org.junit.Before;
import raza.Nortaichian;

public class EstadoNormalNortaichianTest {

    private Nortaichian nortaichian;
    private EstadoNormalNortaichian estadoNormal;

    @Before
    public void setUp() {
    	LogWriter.iniciar("./ArchivoTest.txt");
        nortaichian = new Nortaichian();
        estadoNormal = new EstadoNormalNortaichian();
        nortaichian.cambiarAEstadoNormal(); // Aseguramos que esté en estado normal
    }

    @Test
    public void testAtacarEnEstadoNormalVidaLlena() {
        int saludInicial = nortaichian.getSalud();
        int daño = estadoNormal.atacar(nortaichian);

        // Verificar que el daño sea igual al daño base
        assertEquals(nortaichian.getDañoBase(), daño);
        // Verificar que la salud haya aumentado en 4 puntos
        assertEquals(saludInicial, nortaichian.getSalud());
    }
    
    @Test
    public void testAtacarEnEstadoNormalVidaBaja() {
        nortaichian.recibirAtaque(10);
        nortaichian.cambiarAEstadoNormal();
        int saludInicial = nortaichian.getSalud();
        int daño = estadoNormal.atacar(nortaichian);
        int curacion = nortaichian.getSaludMaxima() * 4 / 100;

        // Verificar que el daño sea igual al daño base
        assertEquals(nortaichian.getDañoBase(), daño);
        // Verificar que la salud haya aumentado en 4 puntos
        assertEquals(saludInicial + curacion, nortaichian.getSalud());
    }

    @Test
    public void testRecibirAtaqueCambiaAEstadoEnfurecido() {
        int saludInicial = nortaichian.getSalud();
        estadoNormal.recibirAtaque(nortaichian, 20);

        // Verificar que la salud disminuya por el daño recibido
        assertEquals(saludInicial - 20, nortaichian.getSalud());
        // Verificar que el estado haya cambiado a enfurecido
        assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);
    }

    @Test
    public void testDescansarEnEstadoNormal() {
        int saludInicial = nortaichian.getSalud();
        estadoNormal.descansar(nortaichian);

        // Verificar que la salud haya aumentado hasta el máximo
        assertEquals(nortaichian.getSaludMaxima(), nortaichian.getSalud());
        // Verificar que el estado siga siendo normal después de descansar
        assertTrue(nortaichian.getEstado() instanceof EstadoNormalNortaichian);
    }
}


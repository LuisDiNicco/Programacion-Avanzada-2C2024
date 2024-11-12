package estadoNortaichian;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;
import raza.Nortaichian;

public class EstadoEnfurecidoNortaichianTest {

    private Nortaichian nortaichian;
    private EstadoEnfurecidoNortaichian estadoEnfurecido;

    @Before
    public void setUp() {
    	LogWriter.iniciar("./ArchivoTest.txt");
        nortaichian = new Nortaichian();
        estadoEnfurecido = new EstadoEnfurecidoNortaichian();
        nortaichian.cambiarAEstadoEnfurecido();
    }

    @Test
    public void testAtacarPrimerTurnoEnEstadoEnfurecido() {
    	 nortaichian.recibirAtaque(20);
    	int saludInicial = nortaichian.getSalud();
        int daño = estadoEnfurecido.atacar(nortaichian);
        int curacion = nortaichian.getSaludMaxima()*4/100;

        assertEquals(nortaichian.getDañoBase() * 2, daño);
        assertEquals(1, nortaichian.getCantidadTurnoEnEstadoEnfurecido());
        assertEquals(saludInicial + curacion, nortaichian.getSalud());
    }

    @Test
    public void testAtacarSegundoTurnoEnEstadoEnfurecido() {
        nortaichian.recibirAtaque(20);
    	nortaichian.incrementarTurnoEnfurecido();
        int saludInicial = nortaichian.getSalud();
        int daño = estadoEnfurecido.atacar(nortaichian);
        int curacion = nortaichian.getSaludMaxima()*4/100;

        assertEquals(nortaichian.getDañoBase() * 2, daño);
        assertEquals(2, nortaichian.getCantidadTurnoEnEstadoEnfurecido());
        assertEquals(saludInicial + curacion, nortaichian.getSalud());
    }

    @Test
    public void testAtacarCambiaAEstadoNormalDespuésDeDosTurnos() {
        nortaichian.incrementarTurnoEnfurecido();
        nortaichian.incrementarTurnoEnfurecido();
        int daño = estadoEnfurecido.atacar(nortaichian);

        assertTrue(nortaichian.getEstado() instanceof EstadoNormalNortaichian);
        assertEquals(nortaichian.getDañoBase(), daño);
    }

    @Test
    public void testRecibirAtaqueEnEstadoEnfurecido() {
        int saludInicial = nortaichian.getSalud();
        estadoEnfurecido.recibirAtaque(nortaichian, 20);

        assertEquals(saludInicial - 20, nortaichian.getSalud());
    }

    @Test
    public void testDescansarEnEstadoEnfurecido() {
        int saludInicial = nortaichian.getSalud();
        estadoEnfurecido.descansar(nortaichian);

        assertEquals(nortaichian.getSaludMaxima(), nortaichian.getSalud());
        assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);
    }
}
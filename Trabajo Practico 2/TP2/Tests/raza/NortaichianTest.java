package raza;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import archivo.LogWriter;
import estadoNortaichian.*;

public class NortaichianTest {

    private Nortaichian nortaichian;

    @Before
    public void setUp() {
    	LogWriter.iniciar("./ArchivoTest.txt");
        nortaichian = new Nortaichian();
    }

    // Test Estado Normal

    @Test
    public void atacarEnEstadoNormal_DeberiaHacerDañoBaseYCurarse() {
        nortaichian.cambiarAEstadoNormal();
        int daño = nortaichian.atacar();
        
        assertEquals(nortaichian.getDañoBase(), daño);
        assertTrue(nortaichian.getSalud() <= nortaichian.getSaludMaxima());  // Verifica que la salud no supera la salud máxima después de curarse
    }

    @Test
    public void recibirAtaqueEnEstadoNormal_DeberiaCambiarAEstadoEnfurecido() {
        int saludAnterior = nortaichian.getSalud();
        nortaichian.recibirAtaque(10);

        assertTrue(nortaichian.getSalud() < saludAnterior);  // Verifica que se redujo la salud
        assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);  // Verifica el cambio de estado
    }

    @Test
    public void descansarEnEstadoNormal_DeberiaCurarseYConvertirseEnPiedra() {
        nortaichian.recibirAtaque(10);
    	int saludAnterior = nortaichian.getSalud();
        nortaichian.descansar();

        assertTrue(nortaichian.getSalud() > saludAnterior);  // Verifica que la salud aumentó
        assertTrue(nortaichian.getEstado() instanceof EstadoDePiedraNortaichian);  // Verifica que se convirtió en piedra
    }

    // Test Estado Enfurecido

    @Test
    public void atacarEnEstadoEnfurecido_DeberiaHacerElDobleDeDañoBaseYCurarse() {
        nortaichian.cambiarAEstadoEnfurecido();
        int daño = nortaichian.atacar();
        
        assertEquals(nortaichian.getDañoBase() * 2, daño);
        assertTrue(nortaichian.getSalud() <= nortaichian.getSaludMaxima());  // Verifica que la salud no supera la máxima
    }

    @Test
    public void recibirAtaqueEnEstadoEnfurecido_DeberiaReducirSalud() {
        nortaichian.cambiarAEstadoEnfurecido();
        int saludAnterior = nortaichian.getSalud();
        
        nortaichian.recibirAtaque(10);

        assertTrue(nortaichian.getSalud() < saludAnterior);  // Verifica que la salud se redujo
        assertTrue(nortaichian.getEstado() instanceof EstadoEnfurecidoNortaichian);  // Sigue en estado enfurecido
    }

    @Test
    public void descansarEnEstadoEnfurecido_DeberiaCurarseAlMaximo() {
        nortaichian.recibirAtaque(10);
    	//nortaichian.cambiarAEstadoEnfurecido();
        int saludAnterior = nortaichian.getSalud();
        assertNotEquals(saludAnterior, nortaichian.getSaludMaxima());
        
        nortaichian.descansar();

        assertEquals(nortaichian.getSaludMaxima(), nortaichian.getSalud());  // Verifica curación completa
    }

    // Test Estado De Piedra

    @Test
    public void atacarEnEstadoDePiedra_DeberiaHacerCeroDaño() {
        nortaichian.cambiarAEstadoDePiedra();
        int daño = nortaichian.atacar();

        assertEquals(0, daño);  // Verifica que no causa daño
    }

    @Test
    public void recibirAtaqueEnEstadoDePiedra_DeberiaReducirSaludMitadDelDaño() {
        nortaichian.cambiarAEstadoDePiedra();
        int saludAnterior = nortaichian.getSalud();
        nortaichian.recibirAtaque(10);

        assertEquals(saludAnterior - 5, nortaichian.getSalud());  // Verifica que solo se reduce mitad del daño
    }

    @Test
    public void descansarEnEstadoDePiedra_NoDeberiaHacerNada() {
        nortaichian.cambiarAEstadoDePiedra();
        int saludAnterior = nortaichian.getSalud();
        nortaichian.descansar();

        assertEquals(saludAnterior, nortaichian.getSalud());  // La salud no cambia
        assertTrue(nortaichian.getEstado() instanceof EstadoDePiedraNortaichian);  // Estado no cambia
    }

    // Verificación del cambio de estado en Estado de Piedra después de 2 turnos
    @Test
    public void atacarEnEstadoDePiedra_TercerTurno_DeberiaCambiarAEstadoNormal() {
        nortaichian.cambiarAEstadoDePiedra();
        nortaichian.atacar(); // Primer turno en piedra
        nortaichian.atacar(); // Segundo turno en piedra
        nortaichian.atacar(); // Vuelve al normal

        // Verifica que en el tercer turno regresa a estado normal
        assertTrue(nortaichian.getEstado() instanceof EstadoNormalNortaichian);
    }
}

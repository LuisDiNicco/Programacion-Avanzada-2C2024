package raza;

import static org.junit.Assert.*;
import org.junit.Test;

public class WrivesTest {
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
/*
    private Wrives wrives;
    private Raza objetivo;

    // Clase concreta que extiende de Raza para usar como objetivo en los tests
    private class RazaDummy extends Raza {
        public RazaDummy(String nombre, int saludActual, int rangoMin, int rangoMax, int dañoBase) {
            super(nombre, saludActual, rangoMin, rangoMax, dañoBase);
        }

        @Override
        public void recibirAtaque(int daño) {
            this.salud -= daño;  // Reduce la salud cuando recibe un ataque
        }

		@Override
		public void atacar(Raza objetivo) {
			return;
		}

		@Override
		public void descansar() {
			return;
		}
    }

    @Before
    public void setUp() {
        wrives = new Wrives();
        // Inicializamos un objetivo de prueba usando la clase concreta
        objetivo = new RazaDummy("Objetivo", 100, 0, 0, 0);
    }

    @Test
    public void testAtaqueIncremental() {
        // Primer ataque
        wrives.atacar(objetivo);
        assertEquals(100 - 113, objetivo.getSaludActual(), 0.01, "El objetivo debería recibir 113 de daño");

        // Segundo ataque, debería hacer el doble de daño
        wrives.atacar(objetivo);
        assertEquals(100 - (113 + 113 * 2), objetivo.getSaludActual(), 0.01, "El objetivo debería haber recibido un total de 339 de daño");

        // Tercer ataque, resetea el ciclo de ataques
        wrives.atacar(objetivo);
        assertEquals(100 - (113 + 113 * 2 + 113), objetivo.getSaludActual(), 0.01, "El ciclo de ataques debería reiniciarse");
    }

    @Test
    public void testRecibirAtaqueYMorir() {
        // Wrives recibe un ataque grande que lo mataría
        wrives.recibirAtaque(60);  // 60 x 2 = 120 de daño
        assertTrue(wrives.getSaludActual() <= 0, "Wrives debería estar muerto");
    }

    @Test
    public void testRecibirAtaqueNoMuerte() {
        // Wrives recibe un ataque menor
        wrives.recibirAtaque(20);  // 20 x 2 = 40 de daño
        assertEquals(108 - 40, wrives.getSaludActual(), 0.01, "Wrives debería tener 68 de salud restante");
    }

    @Test
    public void testDescansar() {
        // Verificamos la salud antes del descanso
        int saludInicial = wrives.getSaludActual();
        
        wrives.descansar();
        
        assertEquals(saludInicial + 50, wrives.getSaludActual(), 0.01, "La salud actual debería aumentar en 50");
        assertEquals(158, wrives.getSaludMaxima(), 0.01, "La salud máxima debería aumentar en 50");
        assertTrue(wrives.isRehusaAtacar(), "Wrives debería estar en modo de rehusar atacar tras descansar");
    }

    @Test
    public void testNoAtacarSiRehusaAtacar() {
        wrives.descansar();  // Se rehúsa a atacar después de descansar
        wrives.atacar(objetivo);
        assertEquals(100, objetivo.getSaludActual(), 0.01, "Wrives no debería atacar cuando se rehúsa a atacar");
    }

*/
}



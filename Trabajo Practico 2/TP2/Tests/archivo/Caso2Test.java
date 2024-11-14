package archivo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algoritmos.BuscarTodosLosCaminos;
import algoritmos.Dijkstra;
import colaDePrioridad.ColaDePrioridad;
import juego.Mapa;
import juego.Simulacion;

public class Caso2Test {

	private Simulacion simulacion;
	private String rutaArchivo = "./Archivos de Prueba/Caso 2 - Pueblos en linea y mueren en batalla.txt";
	private Mapa mapa;
	
	@Before
	public void setUp() {
		simulacion = new Simulacion();
		Mapa.reiniciarMapa();
		Archivo.leerArchivo(rutaArchivo, simulacion);
		mapa=Mapa.getInstancia();
	}
	
	@Test
	public void testLeerArchivoCaso2() {

		assertEquals(0, simulacion.getPuebloInicio());
		assertEquals(6, simulacion.getPuebloFin());
	}

	@Test
	public void testCalcularDijkstra() {

		int[][] grafo= mapa.getGrafo();
		
		Dijkstra dijkstra = new Dijkstra(grafo, simulacion.getPuebloFin());
		dijkstra.calcularDistanciaDijkstra();
		
		int [] distancia=dijkstra.getVectorDistancia();

		assertEquals(60, distancia[simulacion.getPuebloFin()]);
	}
	
	@Test
	public void testCalcularOtrasDistancias() {
		int[][] grafo= mapa.getGrafo();

		BuscarTodosLosCaminos busqueda= new BuscarTodosLosCaminos(grafo);
		busqueda.encontrarTodosLosCaminos(0, 3);
		ColaDePrioridad colaDePrioridad = busqueda.getColaDePrioridad();
		
		assertEquals(1, colaDePrioridad.tamaño());
	}
}

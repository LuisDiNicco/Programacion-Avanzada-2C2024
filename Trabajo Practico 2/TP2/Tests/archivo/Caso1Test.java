package archivo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algoritmos.BuscarTodosLosCaminos;
import algoritmos.Dijkstra;
import colaDePrioridad.Camino;
import colaDePrioridad.ColaDePrioridad;
import juego.Simulacion;
import juego.Mapa;

public class Caso1Test {

	private Simulacion simulacion;
	private String rutaArchivo = "./Archivos de Prueba/Caso 1 - Consigna y llegan a destino.txt";
	private Mapa mapa;
	
	@Before
	public void setUp() {
		simulacion = new Simulacion();
		Mapa.reiniciarMapa();
		Archivo.leerArchivo(rutaArchivo, simulacion);
		mapa=Mapa.getInstancia();
	}
	
	@Test
	public void testLeerArchivoCaso1() {

		assertEquals(0, simulacion.getPuebloInicio());
		assertEquals(3, simulacion.getPuebloFin());
	}

	@Test
	public void testCalcularDijkstra() {

		int[][] grafo= mapa.getGrafo();
		
		Dijkstra dijkstra = new Dijkstra(grafo, simulacion.getPuebloFin());
		dijkstra.calcularDistanciaDijkstra();
		
		int [] distancia=dijkstra.getVectorDistancia();

		assertEquals(22, distancia[simulacion.getPuebloFin()]);
	}
	
	@Test
	public void testCalcularOtrasDistancias() {
		int[][] grafo= mapa.getGrafo();

		BuscarTodosLosCaminos busqueda= new BuscarTodosLosCaminos(grafo);
		busqueda.encontrarTodosLosCaminos(0, 3);
		ColaDePrioridad colaDePrioridad = busqueda.getColaDePrioridad();
		
		Camino camino = colaDePrioridad.extraerMin();
		camino = colaDePrioridad.extraerMin();
		
		assertEquals(27, camino.getCosto());
	}
}

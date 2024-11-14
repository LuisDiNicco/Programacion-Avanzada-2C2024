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

public class Caso7Test {

	private Simulacion simulacion;
	private String rutaArchivo = "./Archivos de Prueba/Caso 7 - 2 caminos mismo costo y llegan con otra ruta.txt";
	private Mapa mapa;
	
	@Before
	public void setUp() {
		simulacion = new Simulacion();
		Mapa.reiniciarMapa();
		Archivo.leerArchivo(rutaArchivo, simulacion);
		mapa=Mapa.getInstancia();
	}
	
	@Test
	public void testLeerArchivoCaso7() {

		assertEquals(0, simulacion.getPuebloInicio());
		assertEquals(4, simulacion.getPuebloFin());
	}

	@Test
	public void testCalcularDijkstra() {

		int[][] grafo= mapa.getGrafo();
		
		Dijkstra dijkstra = new Dijkstra(grafo, simulacion.getPuebloFin());
		dijkstra.calcularDistanciaDijkstra();
		
		int [] distancia=dijkstra.getVectorDistancia();

		assertEquals(25, distancia[simulacion.getPuebloFin()]);
	}
	
	@Test
	public void testCalcularOtrasDistancias() {
		int[][] grafo= mapa.getGrafo();

		BuscarTodosLosCaminos busqueda= new BuscarTodosLosCaminos(grafo);
		busqueda.encontrarTodosLosCaminos(0, 4);
		ColaDePrioridad colaDePrioridad = busqueda.getColaDePrioridad();
		
		Camino camino = colaDePrioridad.extraerMin();
		camino = colaDePrioridad.extraerMin();
		
		assertEquals(25, camino.getCosto());
	}
}
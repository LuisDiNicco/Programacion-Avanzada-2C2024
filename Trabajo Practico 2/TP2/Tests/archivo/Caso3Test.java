package archivo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algoritmos.BuscarTodosLosCaminos;
import algoritmos.Dijkstra;
import colaDePrioridad.Camino;
import colaDePrioridad.ColaDePrioridad;
import juego.Mapa;
import juego.Simulacion;

public class Caso3Test {

	private Simulacion simulacion;
	private String rutaArchivo = "./Archivos de Prueba/Caso 3 - Todos pueblos enemigos y llegan con otra ruta.txt";
	private Mapa mapa;
	
	@Before
	public void setUp() {
		simulacion = new Simulacion();
		Mapa.reiniciarMapa();
		Archivo.leerArchivo(rutaArchivo, simulacion);
		mapa=Mapa.getInstancia();
	}
	
	@Test
	public void testLeerArchivoCaso3() {

		assertEquals(0, simulacion.getPuebloInicio());
		assertEquals(4, simulacion.getPuebloFin());
	}

	@Test
	public void testCalcularDijkstra() {

		int[][] grafo= mapa.getGrafo();
		
		Dijkstra dijkstra = new Dijkstra(grafo, simulacion.getPuebloFin());
		dijkstra.calcularDistanciaDijkstra();
		
		int [] distancia=dijkstra.getVectorDistancia();

		assertEquals(35, distancia[simulacion.getPuebloFin()]);
	}
	
	@Test
	public void testCalcularOtrasDistancias() {
		int[][] grafo= mapa.getGrafo();

		BuscarTodosLosCaminos busqueda= new BuscarTodosLosCaminos(grafo);
		busqueda.encontrarTodosLosCaminos(0, 3);
		ColaDePrioridad colaDePrioridad = busqueda.getColaDePrioridad();
		
		Camino camino = colaDePrioridad.extraerMin();
		camino = colaDePrioridad.extraerMin();
		
		assertEquals(40, camino.getCosto());
	}
}
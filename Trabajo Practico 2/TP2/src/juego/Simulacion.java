package juego;

import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import archivo.*;

import java.util.Stack;
import algoritmos.*;

public class Simulacion {
	private Pueblo[] pueblos;
	private int[][] grafo;
	private int puebloInicio;
	private int puebloFin;

	public Simulacion() {
		super();
	}
	
	// ---------------Setters-------------------//
	
	public void setPuebloInicio(int puebloInicio) {
		this.puebloInicio = puebloInicio;
	}

	public void setPuebloFin(int puebloFin) {
		this.puebloFin = puebloFin;
	}

	public void setPueblos(Pueblo[] pueblos) {
		this.pueblos = pueblos;
	}

	public void setGrafo(int[][] grafo) {
		this.grafo = grafo;
	}
	
	// ---------------Metodos-------------------//

	/*
	 * public void iniciarSimulacion(String rutaArchivo) {
	 * Archivo.leerArchivo(rutaArchivo, this); System.out.println(
	 * "-----------------------------------------------------------------------");
	 * System.out.println("Inicia una nueva aventura para el pueblo: " +
	 * puebloInicio + "...");
	 * System.out.println("Lograrán nuestros heroes llegar al pueblo: " + puebloFin
	 * + "?"); System.out.println(
	 * "-----------------------------------------------------------------------");
	 * 
	 * Dijkstra dijkstra = new Dijkstra(grafo, puebloInicio);
	 * 
	 * dijkstra.calcularDistanciaDijkstra();
	 * 
	 * int[] distancias = dijkstra.getVectorDistancia(); if (distancias[puebloFin -
	 * 1] == Integer.MAX_VALUE) { return; }
	 * 
	 * int[] predecesores = dijkstra.getVectorPredecesores(); Stack<Integer> pila =
	 * new Stack<>();
	 * 
	 * pila.push(puebloFin);
	 * 
	 * int i = puebloFin - 1; while (i != puebloInicio - 1) { pila.push(i); i =
	 * predecesores[i]; }
	 * 
	 * Pueblo miPueblo = pueblos[puebloInicio - 1]; int distanciaRecorrida =
	 * dijkstra.getDistancia(puebloFin);
	 * 
	 * int proximo;
	 * 
	 * while (miPueblo.hayEjercito() && !pila.isEmpty()) { proximo = pila.pop();
	 * Pueblo puebloAVisitar = pueblos[proximo - 1]; //distanciaRecorrida +=
	 * puebloAVisitar. FALTA AGREGAR LA DISTANCIA
	 * System.out.println("Partiendo hacia el pueblo: " + proximo + "...");
	 * System.out.println(
	 * "-----------------------------------------------------------------------");
	 * 
	 * TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo(); if
	 * (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) { System.out.
	 * println("Oh no! Este pueblo resulto ser hostil :( Hay que pararse de manos!"
	 * ); System.out.println(
	 * "-----------------------------------------------------------------------");
	 * Batalla.batalla(miPueblo, puebloAVisitar); } else if
	 * (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) { System.out.
	 * println("Que agradables sujetos, nos dejaron descansar y se sumaran al ejercito :)"
	 * ); System.out.println(
	 * "-----------------------------------------------------------------------");
	 * miPueblo.getEjercito().descansar(); miPueblo.agregarAliados(puebloAVisitar);
	 * }
	 * 
	 * }
	 * 
	 * if (miPueblo.hayEjercito()) { System.out.println("El ejercito del pueblo " +
	 * miPueblo.getNumeroPueblo() + " llego a destino."); } else {
	 * System.out.println("El ejercito de " + miPueblo.getNumeroPueblo() +
	 * " no llego a destino."); // solucionAlternativa.ejecutar(); }
	 * 
	 * 
	 * System.out.println(
	 * "-----------------------------------------------------------------------");
	 * System.out.println("Fin de la aventura"); System.out.println(
	 * "-----------------------------------------------------------------------");
	 * 
	 * return;
	 * 
	 * Hay que revisar el vector de predecesores
	 * 
	 * }
	 */

	public void iniciarSimulacion(String rutaArchivo, String rutaLog) {
		// Crear instancia de LogWriter para log
		LogWriter.iniciar(rutaLog);
		LogWriter logWriter = LogWriter.getInstancia();

		// Leer archivo de datos
		Archivo.leerArchivo(rutaArchivo, this);

		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Inicia una nueva aventura para el pueblo: " + puebloInicio + "...");
		System.out.println("Lograrán nuestros héroes llegar al pueblo: " + puebloFin + "?");
		System.out.println("-----------------------------------------------------------------------");

		Dijkstra dijkstra = new Dijkstra(grafo, puebloInicio);
		dijkstra.calcularDistanciaDijkstra();

		int[] distancias = dijkstra.getVectorDistancia();
		if (distancias[puebloFin - 1] == Integer.MAX_VALUE) {
			return;
		}

		int[] predecesores = dijkstra.getVectorPredecesores();
		Stack<Integer> pila = new Stack<>();
		pila.push(puebloFin);

		int i = puebloFin - 1;
		while (i != puebloInicio - 1) {
			pila.push(i);
			i = predecesores[i];
		}

		Pueblo miPueblo = pueblos[puebloInicio - 1];

		int proximo = pila.pop();

		// Registrar el inicio de la aventura en el log
		logWriter.escribirLog("-----------------------------------------------------------------------");
		logWriter.escribirLog("Comienza la aventura!");
		logWriter.escribirLog("Partimos desde el pueblo: " + proximo);

		while (miPueblo.hayEjercito() && !pila.isEmpty()) {
			proximo = pila.pop();
			Pueblo puebloAVisitar = pueblos[proximo - 1];

			logWriter.escribirLog("-----------------------------------------------------------------------");
			logWriter.escribirLog("Partiendo hacia el pueblo: " + proximo + "...");

			// Imprimir en consola
			System.out.println("Partiendo hacia el pueblo: " + proximo + "...");

			TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo();
			if (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) {
				// Registrar en el log la batalla
				logWriter.escribirLog("Oh no! Este pueblo resultó ser hostil :( Hay que pararse de manos!");
				logWriter.escribirLog("Comienza la batalla en el pueblo: " + puebloAVisitar.getNumeroPueblo());

				// Imprimir en consola
				System.out.println("Oh no! Este pueblo resultó ser hostil :( Hay que pararse de manos!");
				System.out.println("-----------------------------------------------------------------------");

				// Simular batalla
				Batalla.batalla(miPueblo, puebloAVisitar);

			} else if (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) {
				// Registrar en el log el descanso
				logWriter.escribirLog("Qué agradables sujetos, nos dejaron descansar y se sumarán al ejército :)");
				logWriter.escribirLog("El ejército descansa y gana aliados.");

				// Imprimir en consola
				System.out.println("Qué agradables sujetos, nos dejaron descansar y se sumarán al ejército :)");
				System.out.println("-----------------------------------------------------------------------");

				miPueblo.getEjercito().descansar();
				miPueblo.agregarAliados(puebloAVisitar);
			}
		}

		if (miPueblo.hayEjercito()) {
			System.out.println("El ejército del pueblo " + miPueblo.getNumeroPueblo() + " llegó a destino.");
		} else {
			System.out.println("El ejército de " + miPueblo.getNumeroPueblo() + " no llegó a destino.");
		}

		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Fin de la aventura");
		System.out.println("-----------------------------------------------------------------------");

		// Cerrar el archivo de log
		logWriter.cerrar();
	}
}
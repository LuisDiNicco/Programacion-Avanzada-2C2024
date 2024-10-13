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

	public void setPuebloInicio(int puebloInicio) {
		this.puebloInicio = puebloInicio;
	}

	public void setPuebloFin(int puebloFin) {
		this.puebloFin = puebloFin;
	}

	public Simulacion() {
		super();
	}

	public void setPueblos(Pueblo[] pueblos) {
		this.pueblos = pueblos;
	}

	public void setGrafo(int[][] grafo) {
		this.grafo = grafo;
	}

	public void iniciarSimulacion(String rutaArchivo) {
		Archivo.leerArchivo(rutaArchivo, this);
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Inicia una nueva aventura para el pueblo: " + puebloInicio + "...");
		System.out.println("Lograrán nuestros heroes llegar al pueblo: " + puebloFin + "?");
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
		    System.out.println(i);
		}

		Pueblo miPueblo = pueblos[puebloInicio - 1]; 
		int distanciaRecorrida = dijkstra.getDistancia(puebloFin);
		System.out.println(distanciaRecorrida);
		
		int proximo;	
		
		while (miPueblo.hayEjercito() && !pila.isEmpty()) {
			proximo = pila.pop();
			Pueblo puebloAVisitar = pueblos[proximo - 1];
			//distanciaRecorrida += puebloAVisitar.  FALTA AGREGAR LA DISTANCIA
			System.out.println("Partiendo hacia el pueblo: " + proximo + "...");
			System.out.println("-----------------------------------------------------------------------");

			TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo();
			if (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) {
				System.out.println("Oh no! Este pueblo resulto ser hostil :( Hay que pararse de manos!");
				System.out.println("-----------------------------------------------------------------------");
				Batalla.batalla(miPueblo, puebloAVisitar);
			} else if (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) {
				System.out.println("Que agradables sujetos, nos dejaron descansar y se sumaran al ejercito :)");
				System.out.println("-----------------------------------------------------------------------");
				miPueblo.getEjercito().descansar();
				miPueblo.agregarAliados(puebloAVisitar);
			}
			
		}

		if (miPueblo.hayEjercito()) {
			System.out.println("El ejercito del pueblo " + miPueblo.getNumeroPueblo() + " llego a destino.");
		} else {
			System.out.println("El ejercito de " + miPueblo.getNumeroPueblo() + " no llego a destino.");
			// solucionAlternativa.ejecutar();
		}

		
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Fin de la aventura");
		System.out.println("-----------------------------------------------------------------------");
		
		return;
		
		/*
		Hay que revisar el vector de predecesores
		
		*/

	}
}
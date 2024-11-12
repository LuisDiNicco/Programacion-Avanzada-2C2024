package juego;

import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import archivo.*;
import colaDePrioridad.Camino;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import algoritmos.*;

public class Simulacion {
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

	// ---------------Metodos-------------------//
	public void iniciarSimulacion(String rutaArchivo, String rutaLog) {
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("\t\t\tInicio de la Aventura");
		System.out.println("-----------------------------------------------------------------------\n");
		LogWriter.iniciar(rutaLog);

		Archivo.leerArchivo(rutaArchivo, this);

		System.out.println("Inicia una nueva aventura para el pueblo: " + puebloInicio + "...");
		System.out.println("Su objetivo? ~Reconquistar la tierra de fantasia~");
		System.out.println("Lograrán nuestros héroes llegar al pueblo: " + puebloFin + "?\n");

		Mapa mapa = Mapa.getInstancia();

		Dijkstra dijkstra = new Dijkstra(mapa.getGrafo(), puebloInicio);
		dijkstra.calcularDistanciaDijkstra();

		int[] distancias = dijkstra.getVectorDistancia();
		if (distancias[puebloFin - 1] == Integer.MAX_VALUE) {
			System.out.println("Es imposible llegar desde el pueblo de inicio hasta el destino.");
			return;
		}

		ArrayList<Integer> rutaMinima = dijkstra.getRuta(puebloInicio - 1, puebloFin - 1);
		System.out.println(
				"La ruta planeada para llegar desde el pueblo " + puebloInicio + " hasta el " + puebloFin + " es:");
		System.out.println(rutaMinima + "\n");

		int[] predecesores = dijkstra.getVectorPredecesores();

		Stack<Integer> pila = new Stack<>();
		pila.push(puebloFin - 1);

		int i = predecesores[puebloFin - 1];
		while (i != puebloInicio - 1) {
			pila.push(i);
			i = predecesores[i];
		}

		Pueblo miPueblo = mapa.getPueblo(puebloInicio - 1);

		int proximo = puebloInicio;

		LogWriter.escribirSeparador();
		LogWriter.escribirTextoIncioBatalla(proximo);

		System.out.println("Comienza la aventura!");
		System.out.println("Partimos desde el pueblo: " + proximo + "\n");

		int kmRecorrido = 0;
		double tiempoRecorrido = 0;
		double tiempoPrevio = 0;
		int kmAnterior = 0;
		int dia = 0;

		while (miPueblo.hayEjercito() && !pila.isEmpty()) {
			proximo = pila.pop();

			Pueblo puebloAVisitar = mapa.getPueblo(proximo);

			System.out.println("-----------------------------------------------------------------------");
			System.out.println("\t\t\t\tDia: " + dia + "\n");
			kmRecorrido += distancias[proximo] - distancias[predecesores[proximo]];
			kmRecorrido = (kmRecorrido - kmAnterior);
			tiempoRecorrido += (double) kmRecorrido / 10 * 24;

			LogWriter.escribirSeparador();
			LogWriter.escribirSeparador();
			LogWriter.escribirTextoPartida(proximo);

			System.out.println("Partiendo hacia el pueblo: " + (proximo + 1) + "...");

			int tiempoRecorridoHoras = (int) tiempoRecorrido / 24;
			tiempoRecorridoHoras *= 24;
			while ((tiempoRecorridoHoras - tiempoPrevio) >= 24) {
				tiempoPrevio += 24;
				dia++;
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("\t\t\t\tDia: " + dia + "\n");
				System.out.println("Seguimos en camino hacia el pueblo: " + (proximo + 1));
			}

			System.out.println(
					"[Luego de " + convertirDiasAHoras(tiempoRecorrido / 24.0) + " desde el comienzo de la aventura]");
			System.out.println("LLEGAMOS AL PUEBLO " + (proximo + 1) + "!");
			TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo();
			if (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) {

				LogWriter.escribirTextoBatallaConPuebloEnemigo(puebloAVisitar.getNumeroPueblo());

				System.out.println("-Resultó ser un pueblo enemigo, debemos vencerlos para poder avanzar!\n");
				System.out.println("-Empieza la batalla!");
				// System.out.println("-----------------------------------------------------------------------");
				System.out.println("-[Batalla en progreso...]");
				Batalla.batalla(miPueblo, puebloAVisitar);
				System.out.println("-Final de la batalla!");
				System.out.println("-La batalla ha durado todo un dia");

			} else if (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) {

				System.out.println("-Resultó ser un pueblo aliado, podremos descansar!");
				System.out.println("-La mitad del ejercito del poblado se sumará a nuestro ejercito!");
				LogWriter.escribirTextoLLegadaPuebloAliado();
				miPueblo.agregarAliados(puebloAVisitar);
				miPueblo.getEjercito().descansar();
			}

			tiempoRecorrido += 24;
			tiempoPrevio = tiempoRecorrido - tiempoRecorrido % 24;
			kmAnterior = kmRecorrido;

			dia++;
		}

		System.out.println("\n-----------------------  Final del camino Orignal  -----------------------\n");

		if (miPueblo.hayEjercito()) {
			System.out.println("El ejército del pueblo " + (miPueblo.getNumeroPueblo() + 1) + " llegó a destino.");
			System.out.println("Sobrevivieron " + miPueblo.getEjercito().getTamaño() + " soldados!");
			System.out.println("Luego de " + convertirDiasAHoras(tiempoRecorrido / 24.0) + " llegamos al destino");
		} else {
			System.out.println("\n-----------------------------------------------------------------------");
			System.out.println("\t\t\tLa aventura duró " + convertirDiasAHoras(tiempoRecorrido / 24.0));
			System.out.println("-----------------------------------------------------------------------");

			System.out.println("El ejército de " + (miPueblo.getNumeroPueblo() + 1) + " no llegó a destino.");
			System.out.println("Siguiendo la ruta mas corta no fue posible llegar a destino");
			System.out.println("Buscaremos una ruta alternativa...");

			boolean caminoExitoso = false;
			int[][] grafo = mapa.getGrafo();
			int N = 2;

			SolucionAlternativa solucionaAlternativa = new SolucionAlternativa(grafo);
			List<Camino> listaCaminos = new LinkedList<Camino>();

			while (caminoExitoso == false) {

				listaCaminos = solucionaAlternativa.encontrarCaminoNMinimo(puebloInicio - 1, puebloFin - 1, N);

				if (listaCaminos.size() < N) {
					System.out.println(
							"No fue posible encontrar un camino alternativo que nos permita llegar al destino.");
					System.out.println("En cada uno de los caminos posibles nuestro ejercito muere en batalla.");
					break;
				}
				caminoExitoso = simularBatalla(listaCaminos, N, mapa);
				N++;
			}
		}
		System.out.println("\n-----------------------------------------------------------------------");
		System.out.println("\t\t\tFin de la aventura");
		System.out.println("-----------------------------------------------------------------------");

		LogWriter.cerrar();
	}

	private boolean simularBatalla(List<Camino> listaCaminos, int N, Mapa mapa) {

		Camino camino = listaCaminos.get(N - 1);
		List<Integer> listaNodos = camino.getNodos();

		Pueblo miPueblo = mapa.getPueblo(puebloInicio - 1);

		int proximo = puebloInicio;

		while (miPueblo.hayEjercito() && !listaNodos.isEmpty()) {
			proximo = listaNodos.removeFirst();

			Pueblo puebloAVisitar = mapa.getPueblo(proximo);

			TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo();
			if (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) {
				Batalla.batalla(miPueblo, puebloAVisitar);

			} else if (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) {

				miPueblo.agregarAliados(puebloAVisitar);
				miPueblo.getEjercito().descansar();
			}
		}

		if (miPueblo.hayEjercito()) {
			return true;
		} else {
			return false;
		}
	}

	public static String convertirDiasAHoras(double dias) {
		int diasEnteros = (int) dias;
		double horasDecimales = (dias - diasEnteros) * 24;
		int horasEnteras = (int) Math.round(horasDecimales);

		String resultado = "";

		if (diasEnteros >= 0) {
			resultado += diasEnteros + ((diasEnteros == 1 || diasEnteros == 0) ? " día " : " días ");
		}

		if (horasEnteras >= 0) {
			if (diasEnteros > 0) {
				resultado += "y ";
			}
			resultado += horasEnteras + (horasEnteras == 1 ? " hora " : " horas ");
		}

		return resultado;
	}

	public int getPuebloInicio() {
		return puebloInicio;
	}

	public int getPuebloFin() {
		return puebloFin;
	}
}
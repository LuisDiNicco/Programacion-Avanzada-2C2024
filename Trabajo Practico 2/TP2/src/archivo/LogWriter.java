package archivo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter {

	private static LogWriter instanciaUnica;
	static private PrintWriter pw;

	private LogWriter(String rutaArchivo) {
		try {
			java.nio.file.Path path = java.nio.file.Paths.get(rutaArchivo).getParent();
			if (path != null) {
				java.nio.file.Files.createDirectories(path);
			}

			pw = new PrintWriter(new FileWriter(rutaArchivo, false), true);
		} catch (IOException e) {
			System.err.println("Error al crear el archivo de log: " + e.getMessage());
		}
	}

	public static void iniciar(String rutaArchivo) {
		if (instanciaUnica == null) {
			instanciaUnica = new LogWriter(rutaArchivo);
		}
	}

	public static void escribirLog(String mensaje) {
		if (pw != null) {
			pw.println(mensaje);
		}
	}

	public static void cerrar() {
		if (pw != null) {
			pw.close();
		}
		instanciaUnica = null;
	}

	public static void escribirSeparador() {
		escribirLog("-----------------------------------------------------------------------");
	}

	public static void escribirTextoIncioBatalla(int proximo) {
		escribirLog("Comienza la aventura!");
		escribirLog("Partimos desde el pueblo: " + proximo);
	}

	public static void escribirTextoPartida(int proximo) {
		escribirLog("Partiendo hacia el pueblo: " + (proximo + 1) + "...");
	}

	public static void escribirTextoBatallaConPuebloEnemigo(int numeroPueblo) {
		escribirLog("Resultó ser un pueblo enemigo, debemos vencerlos para poder avanzar!");
		escribirLog("Comienza la batalla en el pueblo: " + (numeroPueblo + 1));
	}

	public static void escribirTextoLLegadaPuebloAliado() {
		escribirLog("Resultó ser un pueblo aliado, podremos descansar!");
		escribirLog("La mitad del ejercito del poblado se sumará a nuestro ejercito!");

		escribirLog("Se sumará la mitad de su ejercito");
	}

	public static void escribirLlegadaADestino() {
		escribirLog("Nuestro ejercito ha llegado a destino.");
	}

	public static void escribirMuerteEnBatalla() {
		escribirLog("Nuestro ejercito no ha podido llegar a destino.");
	}

	public static void escribirBusquedaCaminosAlternativos() {
		escribirLog("Buscaremos una ruta alternativa...");
	}

	public static void escribirNoFuePosibleEncontrarOtroCamino() {
		escribirLog("No fue posible encontrar otra ruta que resulte en victoria.");
	}

	public static void escribirNoHayCaminoPosible() {
		escribirLog("No hay un camino posible entre el pueblo de inicio y el pueblo destino");
	}
}

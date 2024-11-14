package juego;

public class Main {

	public static void main(String[] args) {
		Simulacion simualcion = new Simulacion();

		String pathArchivoSimulacion = "./Archivos de Prueba/Caso 1 - Consigna y llegan a destino.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 2 - Pueblos en linea y mueren en batalla.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 3 - Todos pueblos enemigos y llegan con otra ruta.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 4 - Imposible llegar al nodo fin.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 5 - Topologia totalmente conectado y mueren en batalla.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 6 - Todos pueblos aliados y llegan a destino.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 7 - 2 caminos mismo costo y llegan con otra ruta.txt";
		
		String pathArchivoLogSimulacion = "./Archivo Log Simulacion/ResultadoSimulacion.txt";
		
		simualcion.iniciarSimulacion(pathArchivoSimulacion, pathArchivoLogSimulacion);
	}
}

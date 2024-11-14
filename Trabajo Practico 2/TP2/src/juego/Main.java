package juego;

public class Main {

	public static void main(String[] args) {
		Simulacion simualcion = new Simulacion();

		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 1 - Consigna.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 1 - Consigna2.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 1 - Consigna3.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 2 - Pueblos en linea.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 3 - Imposible llegar al destino.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 4 - Topologia estrella desde inicio.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 5 - Topologia totalmente conectado.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 6 - Todos pueblos enemigos.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 7 - Todos pueblos aliados.txt";
		String pathArchivoSimulacion = "./Archivos de Prueba/Caso 8 - Moris en batalla.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 9 - 50 pueblos.txt";
		//String pathArchivoSimulacion = "./Archivos de Prueba/Caso 10 - 2 caminos.txt";
		
		String pathArchivoLogSimulacion = "./Archivo Log Simulacion/ResultadoSimulacion.txt";
		
		simualcion.iniciarSimulacion(pathArchivoSimulacion, pathArchivoLogSimulacion);
	}
}

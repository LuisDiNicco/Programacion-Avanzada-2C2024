package juego;

public class Main {

	public static void main(String[] args) {
		Simulacion simualcion= new Simulacion();
		
		String pathArchivoSimulacion = "./Archivos de Prueba/Caso 1 - Consigna.txt";
		String pathArchivoLogSimulacion = "./Archivo Log Simulacion/ResultadoSimulacion.txt";
		
		simualcion.iniciarSimulacion(pathArchivoSimulacion, pathArchivoLogSimulacion);
	}

}

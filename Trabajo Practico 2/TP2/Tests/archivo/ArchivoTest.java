package archivo;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import juego.Simulacion;
import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import raza.NombreRaza;
import juego.Mapa;

public class ArchivoTest {

	private Simulacion simulacion;
	private String rutaArchivo = "archivo_prueba.txt";

	@Before
	public void setUp() throws IOException {
		LogWriter.iniciar(rutaArchivo);
		simulacion = new Simulacion();

		File archivo = new File(rutaArchivo);
		FileWriter writer = new FileWriter(archivo);
		writer.write("3\n");
		writer.write("1 100 Wrives propio\n");
		writer.write("2 150 Reralopes aliado\n");
		writer.write("3 200 Nortaichian enemigo\n");
		writer.write("1 -> 3\n");
		writer.write("1 2 50\n");
		writer.write("2 3 75\n");
		writer.close();
	}

	@Test
	public void testLeerArchivo_ConfiguraSimulacionCorrectamente() {
		Mapa.reiniciarMapa();
		Archivo.leerArchivo(rutaArchivo, simulacion);

		assertEquals(1, simulacion.getPuebloInicio());
		assertEquals(3, simulacion.getPuebloFin());
	}

	@Test
	public void testLeerArchivo_CargaPueblosEnMapa() {
		Mapa.reiniciarMapa();
		Archivo.leerArchivo(rutaArchivo, simulacion);

		Mapa mapa = Mapa.getInstancia();
		Pueblo[] pueblos = mapa.getPueblos();

		assertEquals(3, pueblos.length);

		assertEquals(100, pueblos[0].getCantidadHabitantes());
		assertEquals(NombreRaza.WRIVES, pueblos[0].getNombreRaza());
		assertEquals(TipoDePueblo.PROPIO, pueblos[0].getTipoDePueblo());
	}

	@Test
	public void testLeerArchivo_CargaDistanciasEnMapa() {
		Archivo.leerArchivo(rutaArchivo, simulacion);

		Mapa mapa = Mapa.getInstancia();

		assertEquals(50, mapa.getDistancia(1 - 1, 2 - 1));
		assertEquals(75, mapa.getDistancia(2 - 1, 3 - 1));
	}

	@After
	public void borrarArchivo() {
	    LogWriter.cerrar();
	    File archivo = new File(rutaArchivo);
	    if (archivo.exists() && archivo.isFile()) {
	        // Intentar eliminación inmediata
	        boolean eliminado = archivo.delete();
	        if (!eliminado) {
	            // Si no se pudo eliminar inmediatamente, lo marca para eliminación en el cierre del programa
	            archivo.deleteOnExit();
	            System.out.println("Archivo marcado para eliminación cuando el proceso termine.");
	        } else {
	            System.out.println("Archivo eliminado correctamente.");
	        }
	    } else {
	        System.out.println("El archivo no existe o no es un archivo.");
	    }
	}
}

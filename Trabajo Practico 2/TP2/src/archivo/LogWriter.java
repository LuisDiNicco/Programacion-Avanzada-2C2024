package archivo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter {

    private static LogWriter instanciaUnica; // Variable estática que contendrá la única instancia
    private PrintWriter pw;

    private LogWriter(String rutaArchivo) {
        try {
            // Crea el directorio si no existe
            java.nio.file.Path path = java.nio.file.Paths.get(rutaArchivo).getParent();
            if (path != null) {
                java.nio.file.Files.createDirectories(path);  // Crear directorios necesarios
            }
            
            // El modo de apertura es 'false' para que sobrescriba el archivo si ya existe
            pw = new PrintWriter(new FileWriter(rutaArchivo, false), true);  // Auto-flush activado
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de log: " + e.getMessage());
        }
    }

    // Método estático para inicializar la ruta del archivo
    public static void iniciar(String rutaArchivo) {
        if (instanciaUnica == null) {
            instanciaUnica = new LogWriter(rutaArchivo);
        }
    }

    // Método estático que proporciona acceso a la única instancia
    public static LogWriter getInstancia() {
        if (instanciaUnica == null) {
            throw new IllegalStateException("LogWriter no ha sido inicializado. Llama a iniciar() primero.");
        }
        return instanciaUnica;
    }

    // Método para escribir en el log
    public void escribirLog(String mensaje) {
        if (pw != null) {
            pw.println(mensaje);
        }
    }

    // Método para cerrar el PrintWriter
    public void cerrar() {
        if (pw != null) {
            pw.close();
        }
        instanciaUnica = null; // Liberamos la instancia al cerrar
    }
}

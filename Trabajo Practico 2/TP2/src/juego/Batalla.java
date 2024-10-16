package juego;

import archivo.LogWriter;
import pueblo.Pueblo;

public class Batalla {

    // Modificamos el método batalla para aceptar un PrintWriter
    public static void batalla(Pueblo aliados, Pueblo enemigos) {
    	
    	LogWriter logWriter = LogWriter.getInstancia();

        while (aliados.hayEjercito() && enemigos.hayEjercito()) {
            Ejercito ejercitoAliado = aliados.getEjercito();
            Ejercito ejercitoEnemigo = enemigos.getEjercito();

            while (ejercitoAliado.haySoldados() && ejercitoEnemigo.haySoldados()) {
                ejercitoAliado.atacar(ejercitoEnemigo);

                if (ejercitoEnemigo.haySoldados()) {
                    ejercitoEnemigo.atacar(ejercitoAliado);
                }
            }
            if (ejercitoAliado.haySoldados()) {
                // Usamos PrintWriter para registrar los eventos de la batalla
            	System.out.println("Pasando al siguiente pueblo");
            	logWriter.escribirLog("Pasando al siguiente pueblo");
                ejercitoAliado.reordenarse();
            } else {
            	System.out.println("Moriste bro");
            	logWriter.escribirLog("Moriste bro");
                return;
            }
        }
    }
}

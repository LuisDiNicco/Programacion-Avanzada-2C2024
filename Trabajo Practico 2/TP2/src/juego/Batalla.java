package juego;

import archivo.LogWriter;
import pueblo.Pueblo;

public class Batalla {

	public static void batalla(Pueblo aliados, Pueblo enemigos) {

		if (aliados.hayEjercito() && enemigos.hayEjercito()) {
			Ejercito ejercitoAliado = aliados.getEjercito();
			Ejercito ejercitoEnemigo = enemigos.getEjercito();

			int turno = 1;
			while (ejercitoAliado.haySoldados() && ejercitoEnemigo.haySoldados()) {
				LogWriter.escribirLog("\t\t\t\t\t-----------------------------------");
				LogWriter.escribirLog("\t\t\t\t\t\t\t\tTurno: " + turno++);
				LogWriter.escribirLog("\t\t\t\t\t-----------------------------------");
				LogWriter.escribirLog("Ataque de nuestro ejercito:");
				int dañoEnemigo = ejercitoAliado.atacar();
				ejercitoEnemigo.recibirAtaque(dañoEnemigo);

				if (ejercitoEnemigo.haySoldados()) {
					LogWriter.escribirLog("Ataque del ejercito enemigo:");
					int dañoAliado = ejercitoEnemigo.atacar();
					ejercitoAliado.recibirAtaque(dañoAliado);
				}
			}

			LogWriter.escribirLog("Final de la batalla!");
			if (ejercitoAliado.haySoldados()) {
				ejercitoAliado.reordenarse();
			} else {
				LogWriter.escribirLog("Nuestro ejercito ha muerto.");
				return;
			}
		}
	}
}

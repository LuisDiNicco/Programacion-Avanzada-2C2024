package juego;

import pueblo.Pueblo;

public class Batalla {

	public static void batalla(Pueblo aliados, Pueblo enemigos) {

		while (!aliados.hayEjercito() || !enemigos.hayEjercito()) {
			Ejercito ejercitoAliado = aliados.getEjercito();
			Ejercito ejercitoEnemigo = enemigos.getEjercito();

			while (ejercitoAliado.haySoldados() && ejercitoEnemigo.haySoldados()) {
				ejercitoAliado.atacar(ejercitoEnemigo);

				if (ejercitoEnemigo.haySoldados()) {
					ejercitoEnemigo.atacar(ejercitoAliado);
				}
			}
			if (ejercitoAliado.haySoldados()) {
				System.out.println("Pasando al siguiente pueblo");
				ejercitoAliado.reordenarse();
			} else {
				System.out.println("Moriste bro");
				return;
			}

		}

	}
}

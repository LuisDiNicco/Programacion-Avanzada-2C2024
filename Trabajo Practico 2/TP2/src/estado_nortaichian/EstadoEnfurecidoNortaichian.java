package estado_nortaichian;

import raza.*;

public class EstadoEnfurecidoNortaichian implements EstadoNortaichian {

	@Override
	public int atacar(Nortaichian origen) {
		origen.curarse(4);
		int daño = origen.getDañoBase() * 2;
		System.out.println("Nortaichian ataca con " + daño + " puntos de daño!");
		return daño;
	}

	@Override
	public void recibirAtaque(Nortaichian origen, int daño) {
		int salud = origen.getSalud();
		salud -= daño;
		if (salud > 0) {
			System.out.println("Nortaichian recibe " + daño + " puntos de daño. Salud restante: " + salud);
		} else {
			System.out.println(
					"Nortaichian recibe " + daño + " puntos de daño. Su salud era de: " + salud + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar(Nortaichian origen) {
		System.out.println("Nortaichian descansa.");
		origen.curarse(100);
	}
}
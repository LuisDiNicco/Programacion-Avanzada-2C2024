package estado_nortaichian;

import raza.*;

public class EstadoDePiedraNortaichian implements EstadoNortaichian {

	@Override
	public int atacar(Nortaichian origen) {
		origen.curarse(4);
		int dañoPetrificado = 0;
		return dañoPetrificado;
	}

	@Override
	public void recibirAtaque(Nortaichian origen, int daño) {
		int dañoReducido = daño/2;
		origen.bajarSalud(dañoReducido);
	}

	@Override
	public void descansar(Nortaichian origen) {
		origen.curarse(100);
	}

}


package estado_nortaichian;

import raza.*;

public class EstadoEnfurecidoNortaichian implements EstadoNortaichian {

	@Override
	public int atacar(Nortaichian origen) {
		origen.curarse(4);
		int daño = origen.getDañoBase() * 2;
		return daño;
	}

	@Override
	public void recibirAtaque(Nortaichian origen, int daño) {
		origen.bajarSalud(daño);
	}

	@Override
	public void descansar(Nortaichian origen) {
		origen.curarse(100);
	}
}
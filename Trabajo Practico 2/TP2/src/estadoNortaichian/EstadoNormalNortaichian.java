package estadoNortaichian;

import raza.*;

public class EstadoNormalNortaichian implements EstadoNortaichian {

	@Override
	public int atacar(Nortaichian origen) {
		origen.curarse(4);
		int daño = origen.getDañoBase();
		return daño;
	}

	@Override
	public void recibirAtaque(Nortaichian origen, int daño) {
		origen.bajarSalud(daño);
		origen.cambiarAEstadoEnfurecido();
	}

	@Override
	public void descansar(Nortaichian origen) {
		origen.curarse(100);
	}
}
package estadoNortaichian;

import raza.*;

public class EstadoEnfurecidoNortaichian implements EstadoNortaichian {

	@Override
	public int atacar(Nortaichian origen) {
		// Revisar bien la cantidad de turnos
		int daño;
		
		if (origen.getCantidadTurnoEnEstadoEnfurecido() == 2) {
			origen.cambiarAEstadoNormal();
			daño = origen.getEstado().atacar(origen);
		}else{
			origen.curarse(4);
			daño = origen.getDañoBase() * 2;
			origen.incrementarTurnoEnfurecido();
		}
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
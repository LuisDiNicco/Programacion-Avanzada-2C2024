package estado_nortaichian;

import raza.*;

public class EstadoDePiedraNortaichian implements EstadoNortaichian {

	@Override
	public int atacar(Nortaichian origen) {
		int daño;
		if(origen.getCantidadTurnoEnEstadoDePiedra() == 2){
			origen.cambiarAEstadoNormal();
			daño = origen.getEstado().atacar(origen);
		}
		else {
			daño = 0;
			origen.incrementarTurnoDePiedra();
		}
		return daño;
	}

	@Override
	public void recibirAtaque(Nortaichian origen, int daño) {	
		int dañoReducido = daño/2;
		origen.bajarSalud(dañoReducido);
		origen.incrementarTurnoDePiedra();
	}

	@Override
	public void descansar(Nortaichian origen) {
		return;
	}

}


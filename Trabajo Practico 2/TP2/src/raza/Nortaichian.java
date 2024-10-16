package raza;

import estado_nortaichian.*;

public class Nortaichian extends Raza {
	private static final NombreRaza nombreRazaNortaichian = NombreRaza.NORTAICHIAN;
	private static final int saludMaximaNortaichian = 66;
	private static final int rangoMinNortaichian = 16;
	private static final int rangoMaxNortaichian = 22;
	private static final int dañoBaseNortaichian = 18;

	private EstadoNortaichian estado;
	private int cantidadTurnoEnEstado;

	public Nortaichian() {
		super(nombreRazaNortaichian, saludMaximaNortaichian, saludMaximaNortaichian, rangoMinNortaichian,
				rangoMaxNortaichian, dañoBaseNortaichian);
		this.cantidadTurnoEnEstado = 0;
		this.estado = new EstadoNormalNortaichian();
	}

	@Override
	public int atacar() {
		int daño = this.estado.atacar(this);
		this.cantidadTurnoEnEstado++;

		// Revisar bien la cantidad de turnos
		if (cantidadTurnoEnEstado == 2) {
			cambiarAEstadoNormal();
		}

		if (daño > 0) {
			logWriter.escribirLog("\t-Nortaichian atacó haciendo " + daño + " de daño.");
		} else {
			logWriter.escribirLog("\t-Nortaichian no atacó. Esta petrificado");
		}

		return daño;
	}

	@Override
	public void recibirAtaque(int daño) {
		this.estado.recibirAtaque(this, daño);
		if (salud > 0) {
			logWriter.escribirLog("\t\t--Nortaichian recibe " + daño + " puntos de daño. Salud restante: " + salud);
		} else {
			logWriter.escribirLog("\t\t--Nortaichian recibe " + daño + " puntos de daño. Su salud era de: " + salud + ". Ha muerto! ");
		}
		cambiarAEstadoEnfurecido();
	}

	@Override
	public void descansar() {
		curarse(100);
		cambiarAEstadoDePiedra();
		logWriter.escribirLog("\t-Nortaichian ha descansado y se volvio de piedra.");
	}
	
	public void bajarSalud(int daño) {
		this.salud -= daño;
	}
	
	public void curarse(int porcentajeCuracion) {
		int curacion = (int) (saludMaxima * porcentajeCuracion / 100);
		salud = (curacion + salud) > saludMaxima ? saludMaxima : curacion + salud;
		logWriter.escribirLog("\t\t--Nortaichian se cura " + curacion + " puntos. Salud actual: " + salud);
	}

	public void cambiarAEstadoNormal() {
		this.estado = new EstadoNormalNortaichian();
		this.cantidadTurnoEnEstado = 0;
	}

	public void cambiarAEstadoEnfurecido() {
		this.estado = new EstadoEnfurecidoNortaichian();
		this.cantidadTurnoEnEstado = 0;
	}

	public void cambiarAEstadoDePiedra() {
		this.estado = new EstadoDePiedraNortaichian();
		this.cantidadTurnoEnEstado = 0;
	}

	public int getDañoBase() {
		return this.dañoBase;
	}

	public int getSalud() {
		return this.salud;
	}

}
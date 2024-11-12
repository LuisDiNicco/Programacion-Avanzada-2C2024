package raza;

import archivo.LogWriter;
import estadoNortaichian.*;

public class Nortaichian extends Raza {
	private static final NombreRaza nombreRazaNortaichian = NombreRaza.NORTAICHIAN;
	private static final int saludMaximaNortaichian = 66;
	private static final int rangoMinNortaichian = 16;
	private static final int rangoMaxNortaichian = 22;
	private static final int dañoBaseNortaichian = 18;
	private static final String tipoArma = "Arco";

	private EstadoNortaichian estado;
	private int cantidadTurnoEnEstadoEnfurecido;
	private int cantidadTurnoEnEstadoDePiedra;

	public Nortaichian() {
		super(nombreRazaNortaichian, saludMaximaNortaichian, saludMaximaNortaichian, rangoMinNortaichian,
				rangoMaxNortaichian, dañoBaseNortaichian, tipoArma);
		this.cantidadTurnoEnEstadoEnfurecido = 0;
		this.cantidadTurnoEnEstadoDePiedra = 0;
		this.estado = new EstadoNormalNortaichian();
	}

	// ---------------Metodos-------------------//

	@Override
	public int atacar() {
		int saludActual = salud;
		int daño = this.estado.atacar(this);

		if (daño > 0) {
			LogWriter.escribirLog("\t-Nortaichian [" + this.idUnico + "] atacó haciendo " + daño + " de daño.");
		} else {
			LogWriter.escribirLog("\t-Nortaichian [" + this.idUnico + "] no atacó. Esta petrificado");
		}

		if (salud != saludActual) {
			int curacion = salud - saludActual;
			LogWriter.escribirLog(
					"\t\t--Nortaichian [" + this.idUnico + "] se cura " + curacion + " puntos. Salud actual: " + salud);
		}

		return daño;
	}

	@Override
	public void recibirAtaque(int daño) {
		this.estado.recibirAtaque(this, daño);
		if (salud > 0) {
			LogWriter.escribirLog("\t\t--Nortaichian [" + this.idUnico + "] recibe " + daño
					+ " puntos de daño. Salud restante: " + salud);
		} else {
			LogWriter.escribirLog("\t\t--Nortaichian [" + this.idUnico + "] recibe " + daño
					+ " puntos de daño. Su salud era de: " + (salud + daño) + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar() {
		this.estado.descansar(this);
		cambiarAEstadoDePiedra();
		LogWriter.escribirLog("\t-Nortaichian [" + this.idUnico + "] ha descansado y se volvio de piedra.");
	}

	// ---------------Metodos de Apoyo-------------------//

	public void bajarSalud(int daño) {
		this.salud -= daño;
	}

	public void curarse(int porcentajeCuracion) {
		int curacion = (int) (saludMaxima * porcentajeCuracion / 100);
		salud = (curacion + salud) > saludMaxima ? saludMaxima : curacion + salud;
	}

	// ---------------Metodos para cambiar de Estado-------------------//

	public void cambiarAEstadoNormal() {
		this.estado = new EstadoNormalNortaichian();
		this.cantidadTurnoEnEstadoEnfurecido = 0;
		this.cantidadTurnoEnEstadoDePiedra = 0;
	}

	public void cambiarAEstadoEnfurecido() {
		this.estado = new EstadoEnfurecidoNortaichian();
		this.cantidadTurnoEnEstadoEnfurecido = 0;
	}

	public void cambiarAEstadoDePiedra() {
		this.estado = new EstadoDePiedraNortaichian();
		this.cantidadTurnoEnEstadoDePiedra = 0;
	}

	// ---------------Getters-------------------//

	public EstadoNortaichian getEstado() {
		return this.estado;
	}

	public void incrementarTurnoEnfurecido() {
		this.cantidadTurnoEnEstadoEnfurecido++;
	}

	public void incrementarTurnoDePiedra() {
		this.cantidadTurnoEnEstadoDePiedra++;
	}

	public int getCantidadTurnoEnEstadoEnfurecido() {
		return this.cantidadTurnoEnEstadoEnfurecido;
	}

	public int getCantidadTurnoEnEstadoDePiedra() {
		return this.cantidadTurnoEnEstadoDePiedra;
	}
}
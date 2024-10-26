package raza;

public class Reralopes extends Raza {
	private static final NombreRaza nombreRazaReralopes = NombreRaza.RERALOPES;
	private static final int saludMaximaReralopes = 53;
	private static final int rangoMinReralopes = 5;
	private static final int rangoMaxReralopes = 46;
	private static final int dañoBaseReralopes = 27;

	private boolean concentrado;
	private int cantidadAtaquesConcentrado;

	public Reralopes() {
		super(nombreRazaReralopes, saludMaximaReralopes, saludMaximaReralopes, rangoMinReralopes, rangoMaxReralopes,
				dañoBaseReralopes);
		this.concentrado = false;
		this.cantidadAtaquesConcentrado = 0;
	}

	@Override
	public int atacar() {
		int dañoTotal = this.dañoBase;

		if (concentrado == true) {
			dañoTotal = dañoBaseReralopes * 2;
			this.cantidadAtaquesConcentrado++;
		}

		if (this.cantidadAtaquesConcentrado == 3) {
			concentrado = false;
			this.cantidadAtaquesConcentrado = 0;
		}

		logWriter.escribirLog("\t-Reralopes [" + this.idUnico + "]  ataca con " + dañoTotal + " puntos de daño!");
		// objetivo.recibirAtaque(dañoTotal);
		return dañoTotal;
	}

	@Override
	public void recibirAtaque(int daño) {
		salud -= daño;
		if (salud > 0) {
			logWriter.escribirLog("\t\t--Reralopes [" + this.idUnico + "]  recibe " + daño + " puntos de daño. Salud restante: " + salud);
		} else {
			logWriter.escribirLog(
					"\t\t--Reralopes [" + this.idUnico + "] recibe " + daño + " puntos de daño. Su salud era de: " + salud + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar() {
		this.concentrado = true;
		logWriter.escribirLog("\t-Reralopes [" + this.idUnico + "]  se ha concentrado.");
	}

	// ---------------Getters-------------------//

	public boolean isConcentrado() {
		return concentrado;
	}

	public int getCantidadAtaquesConcentrado() {
		return cantidadAtaquesConcentrado;
	}
}
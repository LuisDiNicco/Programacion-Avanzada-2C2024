package raza;

public class Radaiteran extends Raza {
	private static final NombreRaza nombreRazaRadaiteran = NombreRaza.RADAITERAN;
	private static final int saludMaximaRadaiteran = 36;
	private static final int rangoMinRadaiteran = 17;
	private static final int rangoMaxRadaiteran = 41;
	private static final int dañoBaseRadaiteran = 56;

	private int cantidadAtaques;

	public Radaiteran() {
		super(nombreRazaRadaiteran, saludMaximaRadaiteran, saludMaximaRadaiteran, rangoMinRadaiteran,
				rangoMaxRadaiteran, dañoBaseRadaiteran);
		this.cantidadAtaques = 0;
	}

	// ---------------Metodos-------------------//

	@Override
	public int atacar() {
		int dañoExtra = 3 * cantidadAtaques;
		int dañoTotal = dañoBaseRadaiteran + dañoExtra;
		logWriter.escribirLog("\t-Radaiteran [" + this.idUnico + "] ataca con " + dañoTotal + " puntos de daño!");
		// objetivo.recibirAtaque(dañoTotal);
		cantidadAtaques++;
		return dañoTotal;
	}

	@Override
	public void recibirAtaque(int daño) {
		salud -= daño;
		if (salud > 0) {
			logWriter.escribirLog("\t\t--Radaiteran [" + this.idUnico + "] recibe " + daño + " puntos de daño. Salud restante: " + salud);
		} else {
			logWriter.escribirLog(
					"\t\t--Radaiteran [" + this.idUnico + "] recibe " + daño + " puntos de daño. Su salud era de: " + salud + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar() {
		logWriter.escribirLog("\t-Radaiteran [" + this.idUnico + "]  descansa. No sucede nada.");
	}
}
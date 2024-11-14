package raza;

import archivo.LogWriter;

public class Radaiteran extends Raza {
	private static final NombreRaza nombreRazaRadaiteran = NombreRaza.RADAITERAN;
	private static final int saludMaximaRadaiteran = 36;
	private static final int rangoMinRadaiteran = 17;
	private static final int rangoMaxRadaiteran = 41;
	private static final int dañoBaseRadaiteran = 56;
	private static final String tipoArma = "Shurikens";

	private int cantidadAtaques;

	public Radaiteran() {
		super(nombreRazaRadaiteran, saludMaximaRadaiteran, saludMaximaRadaiteran, rangoMinRadaiteran,
				rangoMaxRadaiteran, dañoBaseRadaiteran, tipoArma);
		this.cantidadAtaques = 0;
	}

	@Override
	public int atacar() {
		int dañoExtra = 3 * cantidadAtaques;
		int dañoTotal = dañoBaseRadaiteran + dañoExtra;
		LogWriter.escribirLog("\t-Radaiteran [" + this.idUnico + "] ataca con " + dañoTotal + " puntos de daño!");
		cantidadAtaques++;
		return dañoTotal;
	}

	@Override
	public void recibirAtaque(int daño) {
		salud -= daño;
		if (salud > 0) {
			LogWriter.escribirLog("\t\t--Radaiteran [" + this.idUnico + "] recibe " + daño
					+ " puntos de daño. Salud restante: " + salud);
		} else {
			LogWriter.escribirLog("\t\t--Radaiteran [" + this.idUnico + "] recibe " + daño
					+ " puntos de daño. Su salud era de: " + (salud + daño) + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar() {
		LogWriter.escribirLog("\t-Radaiteran [" + this.idUnico + "]  descansa. No sucede nada.");
	}

	public int getCantidadDeAtaques() {
		return cantidadAtaques;
	}
}
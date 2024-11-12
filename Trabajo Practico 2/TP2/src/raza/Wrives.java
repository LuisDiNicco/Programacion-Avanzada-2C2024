package raza;

import archivo.LogWriter;

public class Wrives extends Raza {
	private static final NombreRaza nombreRazaWrives = NombreRaza.WRIVES;
	private static final int saludMaximaWrives = 108;
	private static final int rangoMinWrives = 14;
	private static final int rangoMaxWrives = 28;
	private static final int dañoBaseWrives = 113;
	private static final String tipoArma = "Magia";

	private int cantidadAtaques;
	private boolean rehusaAtacar;

	public Wrives() {
		super(nombreRazaWrives, saludMaximaWrives, saludMaximaWrives, rangoMinWrives, rangoMaxWrives, dañoBaseWrives,tipoArma);
		this.cantidadAtaques = 0;
		this.rehusaAtacar = false;
	}
	
	// ---------------Metodos-------------------//

	@Override
	public int atacar() {
		int daño = 0;
		if (rehusaAtacar == false) {
			daño = this.dañoBase;

			if (cantidadAtaques == 2) {
				cantidadAtaques = 0;
				daño *= 2;
			}
			else {
				cantidadAtaques++;
			}
		}

	LogWriter.escribirLog("\t-Wrives [" + this.idUnico + "] ataca haciendo: " + daño + " de daño");

		return daño;
	}

	@Override
	public void recibirAtaque(int daño) {
		int dañoTotal = 2 * daño;
		salud -= dañoTotal;
		this.rehusaAtacar = false;
		if (salud > 0) {
			LogWriter.escribirLog("\t\t--Wrives [" + this.idUnico + "] recibe " + dañoTotal + " puntos de daño. Salud restante: " + salud);
		} else {
			LogWriter.escribirLog(
					"\t\t--Wrives [" + this.idUnico + "] recibe " + dañoTotal + " puntos de daño. Su salud era de: " + (salud+daño) + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar() {
		this.saludMaxima += 50;
		this.salud += 50;
		this.rehusaAtacar = true;

		LogWriter.escribirLog("\t-Wrives [" + this.idUnico + "] ha descansado para descrubrir el significado de la paz. Se rehusa a atacar primero.");
	}
	
	// ---------------Getters-------------------//

	public boolean getRehusaAtacar() {
		return this.rehusaAtacar;
	}

	public int getCantidadAtaques() {
		return this.cantidadAtaques;
	}
}


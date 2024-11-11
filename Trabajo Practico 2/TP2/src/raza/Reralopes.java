
package raza;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Reralopes extends Raza {
	private static final NombreRaza nombreRazaReralopes = NombreRaza.RERALOPES;
	private static final int saludMaximaReralopes = 53;
	private static final int rangoMinReralopes = 5;
	private static final int rangoMaxReralopes = 46;
	private static final int dañoBaseReralopes = 27;
	private static final String tipoArma = "Catapulta";

	private boolean concentrado;
	private int cantidadAtaquesConcentrado;
	private List<Boolean> secuenciaAtaque;
	private List<Boolean> listaAtaque;

	public Reralopes() {
		super(nombreRazaReralopes, saludMaximaReralopes, saludMaximaReralopes, rangoMinReralopes, rangoMaxReralopes,
				dañoBaseReralopes, tipoArma);
		this.concentrado = false;
		this.cantidadAtaquesConcentrado = 0;
		this.secuenciaAtaque = new LinkedList<Boolean>();
		this.listaAtaque = new LinkedList<Boolean>();
		listaAtaque.add(true);
		listaAtaque.add(true);
		listaAtaque.add(false);
		listaAtaque.add(false);
	}

	@Override
	public int atacar() {
		
		if(secuenciaAtaque.size() == 0){
			calcularSecuenciaAtaque();
		}
		
		int dañoTotal=0;

		boolean resultadoAtaque = this.secuenciaAtaque.removeFirst();
		
		if(resultadoAtaque){
			dañoTotal = this.dañoBase;

			if (concentrado == true) {
				dañoTotal = dañoBaseReralopes * 2;
				this.cantidadAtaquesConcentrado++;
			}else{
				dañoTotal = dañoBaseReralopes;
			}

			if (this.cantidadAtaquesConcentrado == 3) {
				this.concentrado = false;
				this.cantidadAtaquesConcentrado = 0;
			}
		}

		logWriter.escribirLog("\t-Reralopes [" + this.idUnico + "]  ataca con " + dañoTotal + " puntos de daño!");
			
		return dañoTotal;
	}

	@Override
	public void recibirAtaque(int daño) {
		int saludVieja = salud;
		salud -= daño;
		if (salud > 0) {
			logWriter.escribirLog("\t\t--Reralopes [" + this.idUnico + "]  recibe " + daño + " puntos de daño. Salud restante: " + salud);
		} else {
			logWriter.escribirLog(
					"\t\t--Reralopes [" + this.idUnico + "] recibe " + daño + " puntos de daño. Su salud era de: " + saludVieja + ". Quedo con: " + salud + " Ha muerto! ");
		}
		this.concentrado = false;
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

	private void calcularSecuenciaAtaque(){
        Collections.shuffle(listaAtaque);

		this.secuenciaAtaque = listaAtaque;
	}
}
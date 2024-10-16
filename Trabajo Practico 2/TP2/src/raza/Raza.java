package raza;

import archivo.LogWriter;

public abstract class Raza {
	// Esto esta bien??
	protected final LogWriter logWriter = LogWriter.getInstancia();
	
	protected NombreRaza nombreRaza;
	protected int salud;
	protected int saludMaxima;
	protected int dañoBase;
	protected int rangoMinimo;
	protected int rangoMaximo;

	public Raza(NombreRaza nombre, int salud, int saludMaxima, int dañoBase, int rangoMinimo, int rangoMaximo) {
		this.nombreRaza = nombre;
		this.salud = salud;
		this.saludMaxima = saludMaxima;
		this.dañoBase = dañoBase;
		this.rangoMinimo = rangoMinimo;
		this.rangoMaximo = rangoMaximo;
	}

	public abstract int atacar();

	public abstract void recibirAtaque(int daño);

	public abstract void descansar();
	
	public String getNombreRaza() {
		return nombreRaza.name();
	}

	public int getSalud() {
		return salud;
	}
	
	public int getSaludMaxima() {
		return saludMaxima;
	}

	public int getDañoBase() {
		return dañoBase;
	}

	public int getRangoMinimo() {
		return rangoMinimo;
	}

	public int getRangoMaximo() {
		return rangoMaximo;
	}
	

}
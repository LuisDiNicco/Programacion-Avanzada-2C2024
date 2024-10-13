package raza;

public abstract class Raza {
	//protected String nombre;
	private NombreRaza nombreRaza;
	protected int salud;
	protected int saludMaxima;
	protected int dañoBasico;
	protected int rangoMinimo;
	protected int rangoMaximo;

	public Raza(NombreRaza nombre, int salud, int saludMaxima, int dañoBasico, int rangoMinimo, int rangoMaximo) {
		this.nombreRaza = nombre;
		this.salud = salud;
		this.saludMaxima = saludMaxima;
		this.dañoBasico = dañoBasico;
		this.rangoMinimo = rangoMinimo;
		this.rangoMaximo = rangoMaximo;
	}

	public abstract int atacar();

	public abstract void recibirAtaque(int daño);

	public abstract void descansar();
	
	//public abstract Raza crearRaza();

	public String getNombreRaza() {
		return nombreRaza.name();
	}

	public int getSalud() {
		return salud;
	}
	
	public int getSaludMaxima() {
		return saludMaxima;
	}

	public int getDañoBasico() {
		return dañoBasico;
	}

	public int getRangoMinimo() {
		return rangoMinimo;
	}

	public int getRangoMaximo() {
		return rangoMaximo;
	}
	

}
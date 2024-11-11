package raza;

import java.util.Objects;

import archivo.LogWriter;

public abstract class Raza implements Pelea{
	// Esto esta bien??
	protected final LogWriter logWriter = LogWriter.getInstancia();
	protected static int id = 0;
	
	protected int idUnico;
	protected NombreRaza nombreRaza;
	protected int salud;
	protected int saludMaxima;
	protected int dañoBase;
	protected int rangoMinimo;
	protected int rangoMaximo;
	protected String tipoArma;

	public Raza(NombreRaza nombre, int salud, int saludMaxima, int dañoBase, int rangoMinimo, int rangoMaximo, String tipoArma) {
		this.nombreRaza = nombre;
		this.salud = salud;
		this.saludMaxima = saludMaxima;
		this.dañoBase = dañoBase;
		this.rangoMinimo = rangoMinimo;
		this.rangoMaximo = rangoMaximo;
		this.idUnico = id++;
		this.tipoArma=tipoArma;
	}
	
	// ---------------Metodos-------------------//
	public abstract int atacar();

	public abstract void recibirAtaque(int daño);

	public abstract void descansar();
		
	// ---------------Getters-------------------//
	
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

	public int getIdUnico() {
		return idUnico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUnico);
	}
	
	// ---------------Metodos-------------------//
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Raza other = (Raza) obj;
		return idUnico == other.idUnico;
	}
}
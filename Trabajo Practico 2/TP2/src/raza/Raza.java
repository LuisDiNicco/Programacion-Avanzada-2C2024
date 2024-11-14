package raza;

import java.util.Objects;

public abstract class Raza implements Combatible {
	protected static int id = 0;

	protected int idUnico;
	protected NombreRaza nombreRaza;
	protected int salud;
	protected int saludMaxima;
	protected int dañoBase;
	protected int rangoMinimo;
	protected int rangoMaximo;
	protected String tipoArma;

	public Raza(NombreRaza nombre, int salud, int saludMaxima, int rangoMinimo, int rangoMaximo, int dañoBase,
			String tipoArma) {
		this.nombreRaza = nombre;
		this.salud = salud;
		this.saludMaxima = saludMaxima;
		this.dañoBase = dañoBase;
		this.rangoMinimo = rangoMinimo;
		this.rangoMaximo = rangoMaximo;
		this.idUnico = id++;
		this.tipoArma = tipoArma;
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

	public int getIdUnico() {
		return idUnico;
	}

	public String getTipoArma() {
		return tipoArma;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUnico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		return false;
	}
}
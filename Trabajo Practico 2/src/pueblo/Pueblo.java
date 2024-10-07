package pueblo;

import raza.*;

public class Pueblo{
	private int cantidadHabitantes;
	private Raza raza;
	private TipoDePueblo tipoDePueblo;

	public Pueblo(int cantidadHabitantes, Raza raza, TipoDePueblo tipoDePueblo) {
		this.cantidadHabitantes = cantidadHabitantes;
		this.raza = raza;
		this.tipoDePueblo = tipoDePueblo;
	}


}
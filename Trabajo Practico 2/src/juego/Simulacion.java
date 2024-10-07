package juego;

import pueblo.Pueblo;

public class Simulacion {
	private Pueblo[] pueblos;
	private int[][] grafo;
	private int puebloInicio;
	private int puebloFin;
	
	public void setPuebloInicio(int puebloInicio) {
		this.puebloInicio = puebloInicio;
	}

	public void setPuebloFin(int puebloFin) {
		this.puebloFin = puebloFin;
	}

	public Simulacion() {
		super();
	}

	public void setPueblos(Pueblo[] pueblos) {
		this.pueblos = pueblos;
	}

	public void setGrafo(int[][] grafo) {
		this.grafo = grafo;
	}
	

}

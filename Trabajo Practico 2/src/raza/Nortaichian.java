package raza;

import estado_nortaichian.*;

public class Nortaichian extends Raza {
  private EstadoNortaichian estado;
  private int cantidadTurnoEnEstado;
  private static String nombre = "Nortaichian";
  private static int saludActual = 66;
  private static int saludMaxima = 66;
  private static int rangoMin = 16;
  private static int rangoMax = 22;
  private static int dañoBase = 18;

  public Nortaichian() {
    super(nombre, saludActual, rangoMin, rangoMax, dañoBase);
    this.cantidadTurnoEnEstado = 0;
    this.estado = new EstadoNormalNortaichian();
  }

  @Override
  public void atacar(Raza objetivo) {
    this.estado.atacar(this,objetivo);
    this.cantidadTurnoEnEstado++;

    // Revisar bien la cantidad de turnos
    if (cantidadTurnoEnEstado == 2) {
      cambiarAEstadoNormal();
    }
  }

  @Override
  public void recibirAtaque(int daño) {
    this.estado.recibirAtaque(this,daño);
    cambiarAEstadoEnfurecido();
  }

  @Override
  public void descansar() {
    this.estado.descansar(this);
    cambiarAEstadoDePiedra();
  }

  public void curarse(int porcentajeCuracion) {
    int curacion = (int) (saludMaxima * porcentajeCuracion / 100);
    salud = (curacion + salud) > saludMaxima ? saludMaxima : curacion + salud;
    System.out.println("Nortaichian se cura " + curacion + " puntos. Salud actual: " + salud);
  }

  public void cambiarAEstadoNormal() {
    this.estado = new EstadoNormalNortaichian();
    this.cantidadTurnoEnEstado = 0;
  }

  public void cambiarAEstadoEnfurecido() {
    this.estado = new EstadoEnfurecidoNortaichian();
    this.cantidadTurnoEnEstado = 0;
  }

  public void cambiarAEstadoDePiedra() {
    this.estado = new EstadoDePiedraNortaichian();
    this.cantidadTurnoEnEstado = 0;
  }

public int getDañoBase() {
	return this.dañoBasico;
}

public int getSalud() {
	return Nortaichian.saludActual;
}

}
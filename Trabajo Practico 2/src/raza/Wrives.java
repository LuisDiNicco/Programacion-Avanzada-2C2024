package raza;

public class Wrives extends Raza {
  private int cantidadAtaques;
  private boolean rehusaAtacar;
  private static String nombre = "Wrives";
  private static int saludMaxima = 108;
  private static int saludActual = 108;
  private static int rangoMin = 14;
  private static int rangoMax = 28;
  private static int dañoBase = 113;

  public Wrives() {
    super(nombre, saludActual, rangoMin, rangoMax, dañoBase);
    this.cantidadAtaques = 0;
    this.rehusaAtacar = false;
  }

  @Override
  public void atacar(Raza objetivo) {
    if (rehusaAtacar == false) {
      int daño = this.dañoBasico * this.cantidadAtaques;

      if (cantidadAtaques == 2) {
        cantidadAtaques = 1;
      }

      cantidadAtaques++;
      objetivo.recibirAtaque(daño);
    }
  }

  @Override
  public void recibirAtaque(int daño) {
    int dañoTotal = 2*daño;
    saludActual -= dañoTotal;
    this.rehusaAtacar = false;
    if(salud > 0) {
      System.out.println("Wrives recibe " + dañoTotal + " puntos de daño. Salud restante: " + saludActual);
    }
    else {
      System.out.println("Wrives recibe " + dañoTotal + " puntos de daño. Su salud era de: " + saludActual + ". Ha muerto! ");
    }
  }

  @Override
  public void descansar() {
	Wrives.saludMaxima += 50;
	Wrives.saludActual += 50;
    this.rehusaAtacar = true;
    System.out.println("Wrives ha descansado para descrubrir el significado de la paz. Se rehusa a atacar primero.");
  }
}

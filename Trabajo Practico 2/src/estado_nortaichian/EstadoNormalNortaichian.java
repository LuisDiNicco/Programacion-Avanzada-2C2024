package estado_nortaichian;

import raza.*;

public class EstadoNormalNortaichian implements EstadoNortaichian {

  @Override
  public void atacar(Nortaichian origen,Raza objetivo) {
	origen.curarse(4);
    int daño = origen.getDañoBase();
    System.out.println("Nortaichian ataca con " + daño + " puntos de daño!");
    objetivo.recibirAtaque(daño);
  }

  @Override
  public void recibirAtaque(Nortaichian origen,int daño) {
    int salud = origen.getSalud();
    salud -= daño;
    if(salud > 0) {
      System.out.println("Nortaichian recibe " + daño + " puntos de daño. Salud restante: " + salud);
    }
    else {
      System.out.println("Nortaichian recibe " + daño + " puntos de daño. Su salud era de: " +salud + ". Ha muerto! ");
    }
  }

  @Override
  public void descansar(Nortaichian origen) {
    System.out.println("Nortaichian descansa.");
    origen.curarse(100);
  }
}

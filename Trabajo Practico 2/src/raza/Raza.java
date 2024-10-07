package raza;

public abstract class Raza {
    protected String nombre;
    protected int salud;
    protected int dañoBasico;
    protected int rangoMinimo;
    protected int rangoMaximo;

    public Raza(String nombre, int salud, int dañoBasico, int rangoMinimo, int rangoMaximo) {
        this.nombre = nombre;
        this.salud = salud;
        this.dañoBasico = dañoBasico;
        this.rangoMinimo = rangoMinimo;
        this.rangoMaximo = rangoMaximo;
    }

    public abstract void atacar(Raza objetivo);

    public abstract void recibirAtaque(int daño);

    public abstract void descansar();
}
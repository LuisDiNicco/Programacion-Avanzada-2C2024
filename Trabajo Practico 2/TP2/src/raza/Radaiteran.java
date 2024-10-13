package raza;

public class Radaiteran extends Raza {
	private int cantidadAtaques;
	//private static String nombre = "Radaiteran";
	private static NombreRaza nombreRaza = NombreRaza.RADAITERAN;
	private static int salud = 36;
	private static int rangoMin = 17;
	private static int rangoMax = 41;
	private static int dañoBase = 56;

	public Radaiteran() {
		super(nombreRaza, salud, salud, rangoMin, rangoMax, dañoBase);
		this.cantidadAtaques = 0;
	}

	@Override
	public int atacar() {
		int dañoExtra = 3 * cantidadAtaques;
		int dañoTotal = dañoBase + dañoExtra;
		System.out.println("Radaiteran ataca con " + dañoTotal + " puntos de daño!");
		// objetivo.recibirAtaque(dañoTotal);
		cantidadAtaques++;
		return dañoTotal;
	}

	@Override
	public void recibirAtaque(int daño) {
		salud -= daño;
		if (salud > 0) {
			System.out.println("Radaiteran recibe " + daño + " puntos de daño. Salud restante: " + salud);
		} else {
			System.out.println(
					"Radaiteran recibe " + daño + " puntos de daño. Su salud era de: " + salud + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar() {
		System.out.println("Radaiteran descansa. No sucede nada.");
	}
	
}
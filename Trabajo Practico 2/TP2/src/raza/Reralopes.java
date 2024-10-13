package raza;

public class Reralopes extends Raza {
	private boolean concentrado;
	private int cantidadAtaquesConcentrado;

	//private static String nombre = "Reralopes";
	private static NombreRaza nombreRaza = NombreRaza.RERALOPES;
	private static int salud = 53;
	private static int rangoMin = 5;
	private static int rangoMax = 46;
	private static int dañoBase = 27;

	public Reralopes() {
		super(nombreRaza, salud, salud, rangoMin, rangoMax, dañoBase);
		this.concentrado = false;
		this.cantidadAtaquesConcentrado = 0;
	}

	@Override
	public int atacar() {
		int dañoTotal = dañoBase;

		if (concentrado == true) {
			dañoTotal = dañoBase * 2;
			this.cantidadAtaquesConcentrado++;
		}

		if (this.cantidadAtaquesConcentrado == 3) {
			concentrado = false;
			this.cantidadAtaquesConcentrado = 0;
		}

		System.out.println("Reralopes ataca con " + dañoTotal + " puntos de daño!");
		// objetivo.recibirAtaque(dañoTotal);
		return dañoTotal;
	}

	@Override
	public void recibirAtaque(int daño) {
		salud -= daño;
		if (salud > 0) {
			System.out.println("Reralopes recibe " + daño + " puntos de daño. Salud restante: " + salud);
		} else {
			System.out.println(
					"Reralopes recibe " + daño + " puntos de daño. Su salud era de: " + salud + ". Ha muerto! ");
		}
	}

	@Override
	public void descansar() {
		this.concentrado = true;
		System.out.println("Reralopes ha descansado para concentrarse.");
	}

}
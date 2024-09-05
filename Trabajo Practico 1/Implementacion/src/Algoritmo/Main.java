package Algoritmo;

import MostrarImagen.MostrarImagen;

public class Main {

	public static void main(String[] args) {
		ImagenPGM imagen = LeerPGM.leerCabeceraPGM("Entrada/gato.pgm");
		//ImagenPGM imagen = LeerPGM.leerCabeceraPGM("Entrada/Foto_FEEP.pgm"); 
		//ImagenPGM imagen = LeerPGM.leerCabeceraPGM("Entrada/Montaña.pgm"); 
		//ImagenPGM imagen = LeerPGM.leerCabeceraPGM("Entrada/ola.pgm"); 

		LeerPGM.leerImagenPGM(imagen);

		imagen.invertirImagenMaximoArchivo();
		imagen.invertirImagenMaximoReal();


		EscribirPGM.escribirImagenPGM(imagen, "Salida/gato.pgm", true);
		//EscribirPGM.escribirImagenPGM(imagen, "Salida/Foto_FEEP.pgm", true);
		//EscribirPGM.escribirImagenPGM(imagen, "Salida/Montaña.pgm", true);
		//EscribirPGM.escribirImagenPGM(imagen, "Salida/ola.pgm", true);
		
		System.out.println("Termine");
		
		MostrarImagen.MostrarComparacion(imagen);

	}
}
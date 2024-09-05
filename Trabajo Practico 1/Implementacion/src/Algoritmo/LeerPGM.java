package Algoritmo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LeerPGM {

	public static ImagenPGM leerCabeceraPGM(String fileName) {
		FileInputStream archivoEntrada = null;
		Scanner sc = null;

		try {
			archivoEntrada = new FileInputStream(fileName);
			sc = new Scanner(archivoEntrada);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		String formato = sc.nextLine();
		int cantidadColumnas = sc.nextInt();
		int cantidadFilas = sc.nextInt();
		int maximo = sc.nextInt();
		sc.close();

		ImagenPGM imagen = new ImagenPGM(fileName, formato, cantidadColumnas, cantidadFilas, maximo, null);
		return imagen;
	}

	public static void leerImagenPGM(ImagenPGM imagen) {
		if (imagen.getFormato().equals("P5")) {
			leerPGM_P5(imagen);
		} else if (imagen.getFormato().equals("P2")) {
			leerPGM_P2(imagen);
		}
	}

	private static void leerPGM_P2(ImagenPGM imagen) {

		FileInputStream archivoEntrada = null;
		Scanner sc = null;

		try {
			archivoEntrada = new FileInputStream(imagen.getPath());
			sc = new Scanner(archivoEntrada);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		sc.nextLine();
		sc.nextLine();
		sc.nextLine();

		int minimo = imagen.getMaximoValorArchivo();
		int maximo = 0;

		//Estas 2 lineas son para mostrar la imagen
		BufferedImage bufferedImage = new BufferedImage(imagen.getCantidadColumnas(), imagen.getCantidadFilas(), BufferedImage.TYPE_BYTE_GRAY); 
		int valorPixel;

		for (int i = 0; i < imagen.getCantidadFilas(); i++) {
			for (int j = 0; j < imagen.getCantidadColumnas(); j++) {
				imagen.setImagenOriginal(i, j, sc.nextInt());
				if (minimo > imagen.getImagenOriginal(i, j)) {
					minimo = imagen.getImagenOriginal(i, j);
				}
				if (maximo < imagen.getImagenOriginal(i, j)) {
					maximo = imagen.getImagenOriginal(i, j);
				}

				// aca empieza el buffer para mostrar la imagen
				valorPixel = imagen.getImagenOriginal(i, j); // obtengo el valor del pixel
				bufferedImage.getRaster().setSample(j, i, 0, valorPixel); // cargo el valor en la buffered image

			}
		}
		imagen.setMinimo(minimo);
		imagen.setMaximoValorReal(maximo);
		sc.close();

		//guardo en la imagen la bufferedImage leida
		imagen.setBufferedImageOriginal(bufferedImage);
	}

	private static void leerPGM_P5(ImagenPGM imagen) {

		File archivo = null;
		FileInputStream lector = null;

		try {
			archivo = new File(imagen.getPath());
			lector = new FileInputStream(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {

			int saltos = 0;
			int c;
			while (saltos < 3 && (c = lector.read()) != -1) {
				if (c == '\n') {
					saltos++;
				}
			}

			//Estas 2 lineas son para mostrar la imagen
			BufferedImage bufferedImage = new BufferedImage(imagen.getCantidadColumnas(), imagen.getCantidadFilas(), BufferedImage.TYPE_BYTE_GRAY); 
			int valorPixel;

			int minimo = imagen.getMaximoValorArchivo();
			int maximo = 0;
			for (int i = 0; i < imagen.getCantidadFilas(); i++) {
				for (int j = 0; j < imagen.getCantidadColumnas(); j++) {
					imagen.setImagenOriginal(i, j, lector.read());
					if (minimo > imagen.getImagenOriginal(i, j)) {
						minimo = imagen.getImagenOriginal(i, j);
					}
					if (maximo < imagen.getImagenOriginal(i, j)) {
						maximo = imagen.getImagenOriginal(i, j);
					}

					// aca empieza el buffer para mostrar la imagen
					valorPixel = imagen.getImagenOriginal(i, j); // obtengo el valor del pixel
					bufferedImage.getRaster().setSample(j, i, 0, valorPixel); // cargo el valor en la buffered image
				}
			}
			imagen.setMinimo(minimo);
			imagen.setMaximoValorReal(maximo);

			lector.close();

			//guardo en la imagen la bufferedImage leida
			imagen.setBufferedImageOriginal(bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

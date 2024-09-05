package Algoritmo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class EscribirPGM {

	public static void escribirImagenPGM(ImagenPGM imagen, String pathSalida, boolean invertidaArchivo) {
		if (imagen.getFormato().equals("P5")) {
			escribirPGM_P5(imagen, pathSalida, invertidaArchivo);
		} else if (imagen.getFormato().equals("P2")) {
			escribirPGM_P2(imagen, pathSalida,invertidaArchivo);
		}
	}

	private static void escribirPGM_P2(ImagenPGM imagen, String pathSalida, boolean invertidaArchivo) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(pathSalida);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		writer.println("P2");
		writer.println(imagen.getCantidadColumnas() + " " + imagen.getCantidadFilas());
		writer.println(imagen.getMaximoValorArchivo());

		for (int i = 0; i < imagen.getCantidadFilas(); i++) {
			for (int j = 0; j < imagen.getCantidadColumnas(); j++) {
				if(invertidaArchivo){
					writer.printf("%d" + " ", imagen.getImagenInvertidaMaximoArchivo(i, j));
				}
				else{
					writer.printf("%d" + " ", imagen.getImagenInvertidaMaximoReal(i, j));
				}
			writer.println();
			}	
		}
		writer.close();
	}

	private static void escribirPGM_P5(ImagenPGM imagen, String pathSalida, boolean invertidaArchivo) {
		FileOutputStream escribir = null;

		try {
			escribir = new FileOutputStream(pathSalida);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			escribir.write('P');
			escribir.write('5');
			escribir.write('\n');
			escribir.write((imagen.getCantidadColumnas() + " " + imagen.getCantidadFilas() + "\n").getBytes());
			escribir.write((imagen.getMaximoValorArchivo() + "\n").getBytes());

			for (int i = 0; i < imagen.getCantidadFilas(); i++) {
				for (int j = 0; j < imagen.getCantidadColumnas(); j++) {
					if(invertidaArchivo){
						escribir.write(imagen.getImagenInvertidaMaximoArchivo(i, j));
					}else{
						escribir.write(imagen.getImagenInvertidaMaximoReal(i, j));
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

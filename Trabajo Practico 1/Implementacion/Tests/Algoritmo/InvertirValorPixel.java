package Algoritmo;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvertirValorPixel {

	@Test
	public void InversionMaximoArchivo() {
		int matriz[][]= {{10,100,200,220,255},{10,100,200,220,255},
						 {10,100,200,220,255},{10,100,200,220,255},
						 {10,100,200,220,255}};

		int matrizEsperada[][]=  {{245,155,55,35,0},{245,155,55,35,0},
								 {245,155,55,35,0},{245,155,55,35,0},
								 {245,155,55,35,0}};
		ImagenPGM imagen= new ImagenPGM(null, null, 5, 5, 255,null);
		
		for(int i=0; i< matriz.length;i++) {
			for(int j=0; j< matriz[i].length;j++) {
				imagen.setImagenOriginal(i, j, matriz[i][j]);
			}
		}
		
		imagen.invertirImagenMaximoArchivo();
		
		for(int i=0; i< matriz.length;i++) {
			for(int j=0; j< matriz[i].length;j++) {
				if(matrizEsperada[i][j] != imagen.getImagenOriginal(i, j))
					assertFalse(false);
			}
		}
		
		assertTrue(true);
	}
	
	@Test
	public void InversionMaximoReal() {
		int matriz[][]= {{0,0,0,1,0},{0,0,0,1,0},
						 {0,0,0,1,0},{0,0,0,1,0},
						 {0,0,0,1,0}};

		int matrizEsperada[][]=  {{255,255,255,0,255},{255,255,255,0,255},
								 {255,255,255,0,255},{255,255,255,0,255},
								 {255,255,255,0,255}};
		ImagenPGM imagen= new ImagenPGM(null, null, 5, 5, 255,null);
		
		for(int i=0; i< matriz.length;i++) {
			for(int j=0; j< matriz[i].length;j++) {
				imagen.setImagenOriginal(i, j, matriz[i][j]);
			}
		}
		
		imagen.setMaximoValorReal(1);
		imagen.invertirImagenMaximoReal();
		
		for(int i=0; i< matriz.length;i++) {
			for(int j=0; j< matriz[i].length;j++) {
				if(matrizEsperada[i][j] != imagen.getImagenOriginal(i, j))
					assertFalse(false);
			}
		}
		
		assertTrue(true);
	}

}



package Algoritmo;

import static org.junit.Assert.*;

import org.junit.Test;

public class LecturaImagen {

	@Test
	public void MaximoValorReal() {
		
		ImagenPGM imagen= LeerPGM.leerCabeceraPGM("ArchivosPrueba/maximo.pgm");
		
		LeerPGM.leerImagenPGM(imagen);
		
		int max=0;
		for (int i = 0; i < imagen.getCantidadFilas(); i++) {
			for (int j = 0; j < imagen.getCantidadColumnas(); j++) {
				int valor= imagen.getImagenOriginal(i, j);
				if(valor>max)
					max=valor;
			}
		}
		
		assertEquals(max, imagen.getMaximoValorArchivo());
	}
	
	@Test
	public void LecturaEncabezadoCorrecto() {
		
		ImagenPGM imagen= LeerPGM.leerCabeceraPGM("ArchivosPrueba/Encabezado.pgm");
		
		assertEquals(210, imagen.getMaximoValorArchivo());
	    assertEquals(5, imagen.getCantidadColumnas());
	    assertEquals(4, imagen.getCantidadFilas());
	}
	
	@Test
	public void LecturaFormatoCorrecto() {
		
		ImagenPGM imagen= LeerPGM.leerCabeceraPGM("ArchivosPrueba/Encabezado.pgm");
		
		assertEquals("P2", imagen.getFormato());
	}
	
	@Test
	public void LecturaValorPixeles() {
		
		ImagenPGM imagen= LeerPGM.leerCabeceraPGM("ArchivosPrueba/Encabezado.pgm");
		
		LeerPGM.leerImagenPGM(imagen);
		
		for (int i = 0; i < imagen.getCantidadFilas(); i++) {
			for (int j = 0; j < imagen.getCantidadColumnas(); j++) {
				int valor= imagen.getImagenOriginal(i, j);
				if(valor<0 || valor >imagen.getMaximoValorArchivo())
					assertFalse(false);
			}
		}
		
		assertTrue(true);
	}

}
